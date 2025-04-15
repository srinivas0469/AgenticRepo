package com.example.demo.service;


import com.example.demo.model.AgentRequest;
import com.example.demo.model.AgentResponse;
import org.springframework.stereotype.Service;

@Service
public class AgentService {

    public AgentResponse invokeAgent(AgentRequest request) {
        // Simulate agent call — replace with LLM or tool calls
        AgentResponse response = new AgentResponse();
        response.setOutput("Processed by agent: " + request.getAgentId());
        response.setData(request.getInput());
        return response;
    }
}
