package me.azarex.commonapi.configuration;

import me.azarex.commonapi.configuration.impl.SectionConfiguration;
import me.azarex.commonapi.configuration.impl.SimpleConfiguration;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class ConfigurationManager<T> {

    private final Map<T, Configuration> configurations = new HashMap<>();
    private final Path dataFolder;

    public ConfigurationManager(Path dataFolder) {
        this.dataFolder = dataFolder;
    }

    public void saveAll() {
        configurations.values().stream()
                .filter(configuration -> !(configuration instanceof SectionConfiguration))
                .forEach(Configuration::save);
    }

    public void reloadAll() {
        configurations.values().stream()
                .filter(configuration -> !(configuration instanceof SectionConfiguration))
                .forEach(Configuration::reload);
    }

    public Configuration register(T key, Configuration configuration) {
        if (configuration instanceof SectionConfiguration) return configuration;
        return configurations.put(key, configuration);
    }

    public Configuration registerSimpleConfiguration(T key, String fileName) {
        return configurations.put(key, new SimpleConfiguration(Paths.get(dataFolder + File.separator + fileName)));
    }

    public Configuration get(T key) {
        return configurations.get(key);
    }

}
