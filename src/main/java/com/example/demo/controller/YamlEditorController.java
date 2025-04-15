package com.example.demo.controller;


import com.example.demo.config.YamlConfigProperties;
import com.example.demo.editor.YamlEditorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/yaml")
public class YamlEditorController {

    @Autowired
    private YamlEditorService yamlEditorService;

    @Autowired
    private YamlConfigProperties yamlConfigProperties;

    @GetMapping("/load")
    public String loadYamlConfiguration() {
        return yamlEditorService.loadYaml(yamlConfigProperties.getWorkflowPath());
    }

    @PostMapping("/save")
    public String saveYaml(@RequestBody String yamlContent) {
        yamlEditorService.saveYaml(yamlConfigProperties.getWorkflowPath(), yamlContent);
        return "YAML saved.";
    }
}
