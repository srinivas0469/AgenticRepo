package com.example.demo.controller;


import com.example.demo.model.AgentRequest;
import com.example.demo.model.AgentResponse;
import com.example.demo.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/agents")
public class AgentController {

    @Autowired
    private AgentService agentService;

    @PostMapping("/invoke")
    public AgentResponse invokeAgent(@RequestBody AgentRequest request) {
        return agentService.invokeAgent(request);
    }
}
