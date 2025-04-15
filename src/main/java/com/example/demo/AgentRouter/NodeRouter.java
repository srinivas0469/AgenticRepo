package com.example.demo.AgentRouter;

import com.example.demo.model.Edge;
import com.example.demo.model.Node;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NodeRouter {

    public Node getNextNode(String currentNodeId, List<Edge> edges, List<Node> allNodes) {
        for (Edge edge : edges) {
            if (edge.getSource().equals(currentNodeId)) {
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
