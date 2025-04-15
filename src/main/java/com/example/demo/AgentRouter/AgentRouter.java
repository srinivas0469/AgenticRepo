package com.example.demo.AgentRouter;


import com.example.demo.model.AgentRequest;
import com.example.demo.model.AgentResponse;
import com.example.demo.service.AgentService;

public class AgentRouter {

    private final AgentService agentService;

    public AgentRouter() {
        this.agentService = new AgentService();
    }

    public AgentResponse route(AgentRequest agentRequest) {
        // Route the agent request to the appropriate agent
        return agentService.invokeAgent(agentRequest);
    }
}
