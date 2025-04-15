package com.example.demo.editor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class YamlEditorService {

    @Autowired
    private YamlFileManager yamlFileManager;

    public String loadYaml(String path) {
        return yamlFileManager.readYaml(path);
    }

    public void saveYaml(String path, String content) {
        yamlFileManager.writeYaml(path, content);
    }
}
