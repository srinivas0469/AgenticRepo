package com.example.demo.temporal;

import com.example.demo.temporal.AgenticWorkflow;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TemporalService {

    @Autowired
    private WorkflowClient workflowClient;

    public void startWorkflow(String contextId, String yamlPath) {
        AgenticWorkflow stub = workflowClient.newWorkflowStub(
                AgenticWorkflow.class,
                WorkflowOptions.newBuilder()
                        .setTaskQueue("AGENTIC_TASK_QUEUE")
                        .setWorkflowId(contextId)
                        .build()
        );
        stub.runWorkflow(contextId, yamlPath);
    }

    public void sendSignal(String contextId, String field, String value) {
        AgenticWorkflow stub = workflowClient.newWorkflowStub(AgenticWorkflow.class, contextId);
        stub.submitInput(field, value);
    }
}
