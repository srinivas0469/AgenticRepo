package com.example.demo.service;


import com.example.demo.model.Edge;
import com.example.demo.model.Node;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisualizationService {

    public String generateDot(List<Node> nodes, List<Edge> edges) {
        StringBuilder dot = new StringBuilder("digraph G {\n");

        for (Node node : nodes) {
            dot.append("  ").append(node.getId()).append(" [label=\"")
               .append(node.getName()).append("\\n(").append(node.getType()).append(")")
               .append("\"];\n");
        }

        for (Edge edge : edges) {
            dot.append("  ").append(edge.getSource()).append(" -> ")
               .append(edge.getTarget());

            if (edge.getCondition() != null) {
                dot.append(" [label=\"").append(edge.getCondition()).append("\"]");
            }

            dot.append(";\n");
        }

        dot.append("}\n");
        return dot.toString();
    }
}
