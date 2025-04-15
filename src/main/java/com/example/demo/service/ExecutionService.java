package com.example.demo.service;


import com.example.demo.engine.ExecutionEngine;
import com.example.demo.model.Context;
import com.example.demo.model.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExecutionService {

    @Autowired
    private ExecutionEngine executionEngine;

    public void executeNode(Node node, Context context) {
        executionEngine.executeStep(node, context);
    }
}
