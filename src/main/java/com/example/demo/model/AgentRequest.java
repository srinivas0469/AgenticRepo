package com.example.demo.model;

import lombok.Data;

import java.util.Map;
@Data
public class AgentRequest {
    private String agentId;
    private String prompt;
    private Map<String, Object> context;
    private Map<String, Object> input;

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public Map<String, Object> getInput() {
        return input;
    }

    public void setInput(Map<String, Object> input) {
        this.input = input;
    }
}
