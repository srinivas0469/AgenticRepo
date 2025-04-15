package com.example.demo.engine;


import com.example.demo.model.Context;
import com.example.demo.model.Node;
import com.example.demo.model.WorkflowRequest;
import com.example.demo.model.WorkflowResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkflowEngine {

    @Autowired
    private ExecutionEngine executionEngine;

    @Autowired
    private ContextEngine contextEngine;

    public WorkflowResponse executeWorkflow(WorkflowRequest request,Context context) {
        if(context == null)
                 context = contextEngine.initializeContext(request.getWorkflowId());
        for (Node node : request.getNodes()) {
            executionEngine.executeStep(node, context);
            contextEngine.updateContext(context);
        }

        return new WorkflowResponse(true, "Workflow execution complete", context.getData());
    }
}
