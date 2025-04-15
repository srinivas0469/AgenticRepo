package com.example.demo.service;


import com.example.demo.engine.BranchingEngine;
import com.example.demo.model.Context;
import com.example.demo.model.Edge;
import com.example.demo.model.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DynamicRouterService {

    @Autowired
    private BranchingEngine branchingEngine;

    public Optional<Node> routeNext(List<Edge> edges, String currentNodeId, Context context, List<Node> allNodes) {
        return branchingEngine.determineNextNode(edges, currentNodeId, context, allNodes);
    }
}
