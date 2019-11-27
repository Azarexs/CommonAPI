package me.azarex.commonapi.configuration.impl;

import me.azarex.commonapi.configuration.Configuration;
import me.azarex.commonapi.utility.FileUtility;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.function.DoubleSupplier;
import java.util.function.IntSupplier;
import java.util.function.Supplier;

public class SimpleConfiguration implements Configuration {

    private final Path path;
    private YamlConfiguration configuration;

    public SimpleConfiguration(Path path) {
        this.path = path;

        final String fileName = path.toFile().getName();
        final boolean isFilesCreated = FileUtility.createIfNotExists(path, getClass().getResourceAsStream("/" + fileName));

        if (!isFilesCreated) {
            Bukkit.getLogger().info(fileName + " was not created! Fatal error occurred!");
        }

        configuration = YamlConfiguration.loadConfiguration(path.toFile());
    }

    @Override
    public void save() {
        try {
            FileUtility.createIfNotExists(path, getClass().getResourceAsStream("/" + path.toFile().getName()));
            configuration.save(path.toFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void reload() {
        FileUtility.createIfNotExists(path, getClass().getResourceAsStream("/" + path.toFile().getName()));
        configuration = YamlConfiguration.loadConfiguration(path.toFile());
    }

    @Override
    public Object get(String path) {
        return configuration.get(path);
    }

    @Override
    public void set(String path, Object value) {
        configuration.set(path, value);
    }

    @Override
    public String getString(String path) {
        return configuration.getString(path);
    }

    @Override
    public String getString(String path, Supplier<String> defaultSupplier) {
        return configuration.getString(path, defaultSupplier.get());
    }

    @Override
    public List<String> getStringList(String path) {
        return configuration.getStringList(path);
    }

    @Override
    public List<String> getStringList(String path, Supplier<List<String>> defaultSupplier) {
        return configuration.getStringList(path) == null ? defaultSupplier.get() : configuration.getStringList(path);
    }

    @Override
    public int getInt(String path) {
        return configuration.getInt(path);
    }

    @Override
    public int getInt(String path, IntSupplier defaultSupplier) {
        return configuration.getInt(path, defaultSupplier.getAsInt());
    }

    @Override
    public double getDouble(String path) {
        return configuration.getDouble(path);
    }

    @Override
    public double getDouble(String path, DoubleSupplier defaultSupplier) {
        return configuration.getDouble(path, defaultSupplier.getAsDouble());
    }

    @Override
    public Configuration getSection(String path) {
        return new SectionConfiguration(configuration.getConfigurationSection(path));
    }
}
