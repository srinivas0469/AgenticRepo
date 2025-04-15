package com.example.demo.model;

import com.example.demo.model.Node;
import lombok.Data;

import java.util.List;
@Data
public class WorkflowRequest {
    private String workflowId;
    private List<Node> nodes;
    private List<Edge> edges;

    public String getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }
}
