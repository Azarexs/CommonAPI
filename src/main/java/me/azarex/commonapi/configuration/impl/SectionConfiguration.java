package me.azarex.commonapi.configuration.impl;

import me.azarex.commonapi.configuration.Configuration;
import me.azarex.commonapi.utility.FileUtility;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.IOException;
import java.util.List;
import java.util.function.DoubleSupplier;
import java.util.function.IntSupplier;
import java.util.function.Supplier;

public class SectionConfiguration implements Configuration {

    private final ConfigurationSection section;

    public SectionConfiguration(ConfigurationSection section) {
        this.section = section;
    }

    @Override
    public void save() {
        throw new UnsupportedOperationException("Cannot save a section!");
    }

    @Override
    public void reload() {
        throw new UnsupportedOperationException("Cannot reload a section!");
    }

    @Override
    public Object get(String path) {
        return section.get(path);
    }

    @Override
    public void set(String path, Object value) {
        section.set(path, value);
    }

    @Override
    public String getString(String path) {
        return section.getString(path);
    }

    @Override
    public String getString(String path, Supplier<String> defaultSupplier) {
        return section.getString(path, defaultSupplier.get());
    }

    @Override
    public List<String> getStringList(String path) {
        return section.getStringList(path);
    }

    @Override
    public List<String> getStringList(String path, Supplier<List<String>> defaultSupplier) {
        return section.getStringList(path) == null ? defaultSupplier.get() : section.getStringList(path);
    }

    @Override
    public int getInt(String path) {
        return section.getInt(path);
    }

    @Override
    public int getInt(String path, IntSupplier defaultSupplier) {
        return section.getInt(path, defaultSupplier.getAsInt());
    }

    @Override
    public double getDouble(String path) {
        return section.getDouble(path);
    }

    @Override
    public double getDouble(String path, DoubleSupplier defaultSupplier) {
        return section.getDouble(path, defaultSupplier.getAsDouble());
    }

    @Override
    public Configuration getSection(String path) {
        return new SectionConfiguration(section.getConfigurationSection(path));
    }
}
