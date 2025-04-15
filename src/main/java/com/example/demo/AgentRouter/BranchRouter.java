package com.example.demo.AgentRouter;


import com.example.demo.model.Context;
import com.example.demo.model.Edge;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BranchRouter {

    public Optional<String> route(List<Edge> edges, String currentNodeId, Context context) {
        for (Edge edge : edges) {
            if (edge.getSource().equals(currentNodeId)) {
                String condition = edge.getCondition();
                if (evaluateCondition(condition, context)) {
                    return Optional.of(edge.getTarget());
                }
            }
        }
        return Optional.empty();
    }

    private boolean evaluateCondition(String condition, Context context) {
        // Simple expression evaluator - supports direct equality
        // e.g. condition: tradein_device == 'iPhone'
        if (condition == null) return true;

        try {
            String[] parts = condition.split("==");
            if (parts.length == 2) {
                String key = parts[0].trim();
                String value = parts[1].trim().replace("'", "").replace("\"", "");
                Object contextValue = context.getData().get(key);
                return value.equalsIgnoreCase(String.valueOf(contextValue));
            }
        } catch (Exception e) {
            System.err.println("Invalid condition: " + condition);
        }
        return false;
    }
}
