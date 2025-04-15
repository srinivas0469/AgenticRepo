package com.example.demo.util;


import com.example.demo.model.Edge;
import com.example.demo.model.Node;

import java.util.List;

public class GraphUtils {

    public static String generateDotGraph(List<Node> nodes, List<Edge> edges) {
        StringBuilder dot = new StringBuilder("digraph G {\n");

        for (Node node : nodes) {
            dot.append("  ").append(node.getId())
               .append(" [label=\"").append(node.getName())
               .append("\\n(").append(node.getType()).append(")\"];\n");
        }

        for (Edge edge : edges) {
            dot.append("  ").append(edge.getSource())
               .append(" -> ").append(edge.getTarget());

            if (edge.getCondition() != null) {
                dot.append(" [label=\"").append(edge.getCondition()).append("\"]");
            }

            dot.append(";\n");
        }

        dot.append("}\n");
        return dot.toString();
    }
}
