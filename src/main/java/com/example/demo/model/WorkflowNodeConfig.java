package com.example.demo.model;

import lombok.Data;

import java.util.Map;
@Data
public class WorkflowNodeConfig {
    private String type;
    private String description;
    private String subflow;
    private Map<String, Object> input;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSubflow() {
        return subflow;
    }

    public void setSubflow(String subflow) {
        this.subflow = subflow;
    }

    public Map<String, Object> getInput() {
        return input;
    }

    public void setInput(Map<String, Object> input) {
        this.input = input;
    }
}
