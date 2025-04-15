package com.example.demo.loader;


import com.example.demo.model.Edge;
import com.example.demo.model.Node;
import com.example.demo.model.WorkflowRequest;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class WorkflowLoader {

    public WorkflowRequest loadWorkflow(String yamlFilePath) {
        Yaml yaml = new Yaml();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(yamlFilePath)) {
            Map<String, Object> data = yaml.load(inputStream);
            List<Node> nodes = (List<Node>) data.get("nodes");
            List<Edge> edges = (List<Edge>) data.get("edges");

            WorkflowRequest workflowRequest = new WorkflowRequest();
            workflowRequest.setNodes(nodes);
            workflowRequest.setEdges(edges);
            return workflowRequest;
        } catch (Exception e) {
            throw new RuntimeException("Failed to load workflow YAML: " + yamlFilePath, e);
        }
    }
}
