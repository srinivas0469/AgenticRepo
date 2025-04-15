package com.example.demo.service;


import com.example.demo.engine.ContextEngine;
import com.example.demo.model.Context;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AgentMemoryService {

    @Autowired
    private ContextEngine contextEngine;

    public void saveToMemory(String contextId, String key, Object value) {
        Context context = contextEngine.loadContext(contextId);
        context.getData().put(key, value);
        contextEngine.updateContext(context);
    }

    public Object recallFromMemory(String contextId, String key) {
        Context context = contextEngine.loadContext(contextId);
        return context.getData().get(key);
    }

    public Map<String, Object> getFullMemory(String contextId) {
        return contextEngine.loadContext(contextId).getData();
    }
	public void appendToConversation(String contextId, String role, String message) {
    Context context = contextEngine.loadContext(contextId);
    List<Map<String, String>> history = (List<Map<String, String>>) context.getData()
            .getOrDefault("conversation_history", new ArrayList<>());

    Map<String, String> entry = new HashMap<>();
    entry.put("role", role);       // "human" or "agent"
    entry.put("message", message);
    history.add(entry);

    context.getData().put("conversation_history", history);
    contextEngine.updateContext(context);
}

public List<Map<String, String>> getConversationHistory(String contextId) {
    Context context = contextEngine.loadContext(contextId);
    return (List<Map<String, String>>) context.getData()
            .getOrDefault("conversation_history", new ArrayList<>());
}

}
