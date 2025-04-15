package com.example.demo.loader;


import com.example.demo.config.YamlConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConfigLoader {

    @Autowired
    private YamlConfigProperties yamlConfigProperties;

    public String getWorkflowFile() {
        return yamlConfigProperties.getWorkflowPath();
    }

    public String getAgentFile() {
        return yamlConfigProperties.getAgentPath();
    }
}
