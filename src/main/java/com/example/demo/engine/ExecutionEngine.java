package com.example.demo.engine;


import com.example.demo.loader.WorkflowLoader;
import com.example.demo.model.*;
import com.example.demo.service.AgentMemoryService;
import com.example.demo.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ExecutionEngine {

    @Autowired
    private AgentService agentService;

    @Autowired
    private AgentMemoryService memoryService;

    @Autowired
    private WorkflowLoader workflowLoader;

    @Autowired
    private WorkflowEngine workflowEngine;

    @Autowired
    private BranchingEngine branchingEngine;

    public void executeStep(Node node, Context context) {
        String type = node.getType();
        Map<String, Object> config = node.getConfig(); // already a Map

        if (config == null) {
            throw new RuntimeException("Missing config for node: " + node.getId());
        }

        switch (type.toLowerCase()) {

            case "agent":
                // Parse nested input config
                Map<String, Object> input = null;
                if (config.get("input") instanceof Map) {
                    input = (Map<String, Object>) config.get("input");
                }

                String prompt = input != null ? (String) input.get("prompt") : "No prompt configured.";
                String memorySaveAs = config.containsKey("memory_save_as") ? (String) config.get("memory_save_as") : null;
                String agentId = config.containsKey("agent_id") ? (String) config.get("agent_id") : "default_agent";

                // Build agent request
                AgentRequest request = new AgentRequest();
                request.setAgentId(agentId);
                request.setPrompt(prompt);
                request.setContext(context.getData());

                // Call agent
                AgentResponse response = agentService.invokeAgent(request);

                // Store response in memory/context
                if (memorySaveAs != null) {
                    context.getData().put(memorySaveAs, response.getOutput());
                }

                memoryService.appendToConversation(context.getContextId(), "agent", response.getOutput());

                break;

            case "human_approval":
                String field = (String) config.get("field");
                if (!context.getData().containsKey(field)) {
                    throw new RuntimeException("WAITING_FOR_INPUT");
                }
                break;

            case "subflow":
                String subflowPath = (String) config.get("subflow");
                WorkflowRequest subRequest = workflowLoader.loadWorkflow(subflowPath);
                workflowEngine.executeWorkflow(subRequest,context);
                break;

            case "end":
                System.out.println("Reached end of workflow.");
                break;
            case "tool":
                System.out.println("Simulate calling external tool...");
                break;

            case "condition":
                String condition = (String) config.get("expression");
                boolean result = branchingEngine.evaluate(condition, context);
                context.getData().put("condition_result", result);
                break;

            case "wait":
                String waitField = (String) config.get("field");
                if (!context.getData().containsKey(waitField)) {
                    throw new RuntimeException("WAITING_FOR_INPUT");
                }
                break;

            default:
                throw new RuntimeException("Unsupported node type: " + type);
        }
    }
}
