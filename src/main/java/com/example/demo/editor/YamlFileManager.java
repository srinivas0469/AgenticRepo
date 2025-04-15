package com.example.demo.editor;

import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.charset.StandardCharsets;

@Component
public class YamlFileManager {

    public String readYaml(String path) {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(path)) {
            if (inputStream == null) throw new FileNotFoundException("YAML file not found: " + path);
            return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read YAML file: " + path, e);
        }
    }

    public void writeYaml(String path, String content) {
        try (OutputStream outputStream = new FileOutputStream(path)) {
            outputStream.write(content.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException("Failed to write YAML file: " + path, e);
        }
    }
}
