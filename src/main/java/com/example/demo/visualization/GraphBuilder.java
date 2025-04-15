package com.example.demo.visualization;



import com.example.demo.model.Edge;
import com.example.demo.model.Node;
import com.example.demo.util.GraphUtils;

import java.util.List;

public class GraphBuilder {

    public static String build(List<Node> nodes, List<Edge> edges) {
        return GraphUtils.generateDotGraph(nodes, edges);
    }
}
