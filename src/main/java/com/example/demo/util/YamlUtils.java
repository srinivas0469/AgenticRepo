package com.example.demo.util;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

public class YamlUtils {

    public static Map<String, Object> loadYamlFromClasspath(String path) {
        try (InputStream input = YamlUtils.class.getClassLoader().getResourceAsStream(path)) {
            if (input == null) throw new RuntimeException("YAML file not found: " + path);
            Yaml yaml = new Yaml();
            return yaml.load(input);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load YAML: " + path, e);
        }
    }
}
