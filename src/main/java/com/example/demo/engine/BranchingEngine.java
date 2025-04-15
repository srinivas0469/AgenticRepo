package com.example.demo.engine;


import com.example.demo.model.Context;
import com.example.demo.model.Edge;
import com.example.demo.model.Node;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BranchingEngine {

    public Optional<Node> determineNextNode(List<Edge> edges, String currentNodeId, Context context, List<Node> allNodes) {
        for (Edge edge : edges) {
            if (edge.getSource().equals(currentNodeId)) {
                String condition = edge.getCondition();
                if (evaluate(condition, context)) {
                    return allNodes.stream()
                        .filter(n -> n.getId().equals(edge.getTarget()))
                        .findFirst();
                }
            }
        }
        return Optional.empty();
    }

    /**
     * Evaluates simple condition expressions like: field == "value"
     */
    public boolean evaluate(String condition, Context context) {
        try {
            if (condition == null || !condition.contains("==")) return false;

            String[] parts = condition.split("==");
            if (parts.length != 2) return false;

            String field = parts[0].trim();
            String expected = parts[1].trim().replace("\"", "");

            Object actual = context.getData().get(field);
            return expected.equalsIgnoreCase(String.valueOf(actual));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
