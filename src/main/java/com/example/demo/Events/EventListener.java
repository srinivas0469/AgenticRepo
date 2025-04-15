package com.example.demo.Events;

import org.springframework.stereotype.Component;

@Component
public class EventListener {

    @org.springframework.context.event.EventListener
    public void handleAgentEvent(AgentEvent event) {
        System.out.println("[Event] Context: " + event.getContextId() +
                ", Node: " + event.getNodeId() +
                ", Message: " + event.getMessage());
        // You could also trigger logging, audit trails, message queues, etc.
    }
}
