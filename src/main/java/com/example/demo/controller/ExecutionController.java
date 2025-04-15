package com.example.demo.controller;


import com.example.demo.model.Context;
import com.example.demo.model.Node;
import com.example.demo.service.ExecutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/execution")
public class ExecutionController {

    @Autowired
    private ExecutionService executionService;

    @PostMapping("/step")
    public String executeNode(@RequestBody Node node) {
        Context context = new Context(); // normally you'd load this from DB
        executionService.executeNode(node, context);
        return "Node executed: " + node.getId();
    }
}
