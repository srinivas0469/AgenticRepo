package com.example.demo.orchestrator;


import com.example.demo.model.AgentRequest;
import com.example.demo.model.AgentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AgentOrchestrator {

    @Autowired
    private SubAgent subAgent;

    @Autowired
    private AgentCommunicator communicator;

    public AgentResponse routeAndExecute(AgentRequest request) {
        // You could inspect input and route to subagents conditionally
        AgentResponse response = subAgent.process(request);

        // Communicate with external systems if needed
        communicator.send(response);

        return response;
    }
}
