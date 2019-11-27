package me.azarex.commonapi.configuration;

import java.util.List;
import java.util.function.DoubleSupplier;
import java.util.function.IntSupplier;
import java.util.function.Supplier;

public interface Configuration {

    void save();
    void reload();

    Object get(String path);
    void set(String path, Object value);

    String getString(String path);
    String getString(String path, Supplier<String> defaultSupplier);

    List<String> getStringList(String path);
    List<String> getStringList(String path, Supplier<List<String>> defaultSupplier);

    int getInt(String path);
    int getInt(String path, IntSupplier defaultSupplier);

    double getDouble(String path);
    double getDouble(String path, DoubleSupplier defaultSupplier);

    Configuration getSection(String path);

}
