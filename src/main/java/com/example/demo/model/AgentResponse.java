package com.example.demo.model;

import lombok.Data;

import java.util.Map;
@Data
public class AgentResponse {
    private String output;
    private Map<String, Object> data;

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
