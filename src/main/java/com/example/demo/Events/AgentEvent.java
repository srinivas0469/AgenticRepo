package com.example.demo.Events;

public class AgentEvent {
    private String contextId;
    private String nodeId;
    private String message;

    public AgentEvent(String contextId, String nodeId, String message) {
        this.contextId = contextId;
        this.nodeId = nodeId;
        this.message = message;
    }

    public String getContextId() {
        return contextId;
    }

    public void setContextId(String contextId) {
        this.contextId = contextId;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
