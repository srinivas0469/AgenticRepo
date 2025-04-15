package com.example.demo.model;

import lombok.Data;

import java.util.Map;
@Data
public class Node {
    private String id;
    private String name;
    private String type;
    private Map<String, Object> config;
}
