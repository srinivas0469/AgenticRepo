package com.example.demo.controller;


import com.example.demo.config.YamlConfigProperties;
import com.example.demo.loader.WorkflowLoader;
import com.example.demo.model.Edge;
import com.example.demo.model.Node;
import com.example.demo.model.WorkflowRequest;
import com.example.demo.service.VisualizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/visualize")
public class VisualizationController {

    @Autowired
    private WorkflowLoader workflowLoader;

    @Autowired
    private VisualizationService visualizationService;

    @Autowired
    private YamlConfigProperties yamlConfigProperties;

    @GetMapping
    public String visualizeWorkflow() {
        WorkflowRequest request = workflowLoader.loadWorkflow(yamlConfigProperties.getWorkflowPath());
        List<Node> nodes = request.getNodes();
        List<Edge> edges = request.getEdges();
        return visualizationService.generateDot(nodes, edges);
    }
}
