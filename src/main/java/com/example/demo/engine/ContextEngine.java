package com.example.demo.engine;

import com.example.demo.model.Context;
import com.example.demo.model.WorkflowContext;
import com.example.demo.repository.WorkflowContextRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.Map;

@Service
public class ContextEngine {

    @Autowired
    private WorkflowContextRepository repository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public Context initializeContext(String contextId) {
        WorkflowContext ctx = new WorkflowContext();
        ctx.setContextId(contextId);
        ctx.setContextData(serialize(new HashMap<>()));
        repository.save(ctx);

        Context context = new Context();
        context.setContextId(contextId);
        context.setData(new HashMap<>());
        return context;
    }

    public void updateContext(Context context) {
        WorkflowContext ctx = new WorkflowContext();
        ctx.setContextId(context.getContextId());
        ctx.setContextData(serialize(context.getData()));
        repository.save(ctx);
    }

    public Context loadContext(String contextId) {
        WorkflowContext ctx = repository.findById(contextId)
            .orElseThrow(() -> new RuntimeException("Context not found"));

        Context context = new Context();
        context.setContextId(contextId);
        context.setData(deserialize(ctx.getContextData()));
        return context;
    }

    private String serialize(Map<String, Object> data) {
        try {
            return objectMapper.writeValueAsString(data);
        } catch (Exception e) {
            throw new RuntimeException("Serialization failed", e);
        }
    }

    private Map<String, Object> deserialize(String json) {
        try {
            return objectMapper.readValue(json, Map.class);
        } catch (Exception e) {
            throw new RuntimeException("Deserialization failed", e);
        }
    }
}
