package com.example.demo.loader;


import com.example.demo.model.AgentConfig;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class AgentLoader {

    public List<AgentConfig> loadAgents(String agentYamlFile) {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(agentYamlFile)) {
            Yaml yaml = new Yaml();
            Map<String, Object> data = yaml.load(inputStream);
            List<Map<String, Object>> agents = (List<Map<String, Object>>) data.get("agents");

            List<AgentConfig> configs = new ArrayList<>();
            for (Map<String, Object> agent : agents) {
                AgentConfig config = new AgentConfig();
                config.setId((String) agent.get("id"));
                config.setName((String) agent.get("name"));
                config.setType((String) agent.get("type"));
                config.setConfig((Map<String, Object>) agent.get("config"));
                configs.add(config);
            }

            return configs;
        } catch (Exception e) {
            throw new RuntimeException("Failed to load agent YAML: " + agentYamlFile, e);
        }
    }
}
