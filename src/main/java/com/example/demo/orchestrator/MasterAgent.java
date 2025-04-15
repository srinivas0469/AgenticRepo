package com.example.demo.orchestrator;


import com.example.demo.model.AgentRequest;
import com.example.demo.model.AgentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MasterAgent {

    @Autowired
    private AgentOrchestrator orchestrator;

    public AgentResponse handle(AgentRequest request) {
        // Delegate to orchestrator
        return orchestrator.routeAndExecute(request);
    }
}
