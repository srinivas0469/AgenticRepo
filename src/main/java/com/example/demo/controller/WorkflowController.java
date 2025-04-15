package com.example.demo.controller;


import com.example.demo.config.YamlConfigProperties;
import com.example.demo.engine.ContextEngine;
import com.example.demo.model.Context;
import com.example.demo.model.WorkflowResponse;
import com.example.demo.service.AgentMemoryService;
import com.example.demo.service.WorkflowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/api/workflows")
public class WorkflowController {

    @Autowired
    private WorkflowService workflowService;

    @Autowired
    private YamlConfigProperties yamlConfigProperties;

    @Autowired
    private ContextEngine contextEngine;

    @Autowired
    private AgentMemoryService memoryService;

    @PostMapping("/start")
    public WorkflowResponse startWorkflow() {
        return workflowService.runWorkflow(yamlConfigProperties.getWorkflowPath());
    }

    @PostMapping("/start/{path}")
    public WorkflowResponse startWithPath(@PathVariable String path) {
        return workflowService.runWorkflow(path);
    }

    @PostMapping("/{id}/approve")
    public String humanApproval(@PathVariable String id, @RequestBody Map<String, String> input) {
        String value = input.get("value");

        Context context = contextEngine.loadContext(id);
        context.getData().put("human_approval", value);
        context.getData().put("status", "APPROVED");

        memoryService.appendToConversation(id, "human", value);

        contextEngine.updateContext(context);

        return "Approval received and context updated!";
    }

}
