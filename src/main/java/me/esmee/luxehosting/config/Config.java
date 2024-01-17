package me.esmee.luxehosting.config;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

public class Config {

    private static Map<String, Object> config;

    // Getters
    public static String get(String path) {
        checkConfig();

        if (!config.containsKey(path)) {
            return null;
        }

        return config.get(path).toString();
    }

    // Methods
    private static Map<String, Object> readYAML() {
        Yaml yaml = new Yaml();

        System.out.println("Loading config.yaml...");
        InputStream inputStream = ClassLoader.getSystemResourceAsStream("config.yml");

        if (inputStream == null) {
            System.out.println("config.yaml not found!");
            return null;
        }

        System.out.println("Parsing config.yaml...");
        return yaml.load(inputStream);
    }

    private static void checkConfig() {
        if (config != null) return;
        config = readYAML();
    }

}
