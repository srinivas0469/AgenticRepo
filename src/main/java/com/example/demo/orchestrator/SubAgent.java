package com.example.demo.orchestrator;


import com.example.demo.model.AgentRequest;
import com.example.demo.model.AgentResponse;
import com.example.demo.service.AgentMemoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SubAgent {
    @Autowired
private AgentMemoryService memoryService;

    public AgentResponse process(AgentRequest request) {
        // Simulate logic (call LLM/API)
        String contextId = request.getAgentId(); // or workflow ID/session
memoryService.saveToMemory(contextId, "last_prompt", request.getInput());
Object history = memoryService.recallFromMemory(contextId, "conversation_history");

// Use memory in your logic...
memoryService.appendToConversation(contextId, "agent", request.getPrompt());
memoryService.appendToConversation(contextId, "agent", "Here's your offer...");

        AgentResponse response = new AgentResponse();
        response.setOutput("Processed by subagent: " + request.getAgentId());
        response.setData(request.getInput());
        return response;
    }
}
