package com.example.demo.orchestrator;


import com.example.demo.model.AgentResponse;
import org.springframework.stereotype.Component;

@Component
public class AgentCommunicator {

    public void send(AgentResponse response) {
        // Simulate logging or dispatch to a queue, bus, or HTTP endpoint
        System.out.println("[Communicator] Sending agent response: " + response.getOutput());
    }
}
