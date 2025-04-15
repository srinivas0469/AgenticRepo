package com.example.demo.model;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;
@Data
public class Context {
    private String contextId;
    private Map<String, Object> data = new HashMap<>();

    public String getContextId() {
        return contextId;
    }

    public void setContextId(String contextId) {
        this.contextId = contextId;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
