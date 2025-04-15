package com.example.demo.engine;


import com.example.demo.model.Edge;
import com.example.demo.model.Node;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoutingEngine {

    public Node routeToNextNode(Node currentNode, List<Edge> edges, List<Node> allNodes) {
        for (Edge edge : edges) {
            if (edge.getSource().equals(currentNode.getId())) {
                String targetId = edge.getTarget();
                return allNodes.stream()
                    .filter(n -> n.getId().equals(targetId))
                    .findFirst()
                    .orElse(null);
            }
        }
        return null;
    }
}
