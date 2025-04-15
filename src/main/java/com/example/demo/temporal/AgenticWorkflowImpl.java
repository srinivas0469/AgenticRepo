package com.example.demo.temporal;


import com.example.demo.engine.WorkflowEngine;
import com.example.demo.loader.WorkflowLoader;
import com.example.demo.model.Context;
import com.example.demo.model.WorkflowRequest;
import io.temporal.workflow.Workflow;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of the AgenticWorkflow interface.
 * Executes a workflow YAML via Temporal, supports pause/resume via signals.
 */
public class AgenticWorkflowImpl implements AgenticWorkflow {

    private final Map<String, Object> contextData = new HashMap<>();
    private String contextId;

    @Autowired
    private WorkflowLoader workflowLoader;

    @Autowired
    private WorkflowEngine workflowEngine;

    private boolean resumeSignalReceived = false;

    @Override
    public void runWorkflow(String contextId, String yamlPath) {
        this.contextId = contextId;

        // Load YAML-defined workflow
        WorkflowRequest workflowRequest = workflowLoader.loadWorkflow(yamlPath);

        // Prepare context
        Context context = new Context();
        context.setContextId(contextId);
        context.setData(contextData);

        try {
            // Execute the workflow
            workflowEngine.executeWorkflow(workflowRequest, context);
        } catch (RuntimeException e) {
            if (e.getMessage().contains("WAITING_FOR_INPUT")) {
                Workflow.await(() -> resumeSignalReceived);
                resumeSignalReceived = false; // Reset for future pauses
                // After resume, call again
                runWorkflow(contextId, yamlPath);
            } else {
                throw e;
            }
        }
    }

    @Override
    public void submitInput(String field, String value) {
        contextData.put(field, value);
        resumeSignalReceived = true;
    }
}
