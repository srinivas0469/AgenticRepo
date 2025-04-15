package com.example.demo.temporal;

import io.temporal.workflow.SignalMethod;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

/**
 * Temporal Workflow Interface for the Agentic AI Flow.
 * This allows starting a workflow and resuming it via signals.
 */
@WorkflowInterface
public interface AgenticWorkflow {

    /**
     * Main entry point to start the workflow execution.
     *
     * @param contextId ID of the conversation or session
     * @param yamlPath Path to the workflow YAML definition
     */
    @WorkflowMethod
    void runWorkflow(String contextId, String yamlPath);

    /**
     * Signal method to provide human input and resume paused execution.
     *
     * @param field Field name (e.g., "device", "condition")
     * @param value User-provided value
     */
    @SignalMethod
    void submitInput(String field, String value);
}
