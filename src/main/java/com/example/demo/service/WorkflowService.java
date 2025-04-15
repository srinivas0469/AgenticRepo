package com.example.demo.service;


import com.example.demo.engine.WorkflowEngine;
import com.example.demo.loader.WorkflowLoader;
import com.example.demo.model.WorkflowRequest;
import com.example.demo.model.WorkflowResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkflowService {

    @Autowired
    private WorkflowEngine workflowEngine;

    @Autowired
    private WorkflowLoader workflowLoader;

    public WorkflowResponse runWorkflow(String yamlPath) {
        WorkflowRequest request = workflowLoader.loadWorkflow(yamlPath);
        return workflowEngine.executeWorkflow(request,null);
    }
}
