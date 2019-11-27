package me.azarex.commonapi.utility;

import org.bukkit.ChatColor;

import java.util.List;

public class Colors {

    private Colors() {
        throw new UnsupportedOperationException("This is a utility class, and thus cannot be instantiated.");
    }

    public static String color(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static List<String> color(List<String> list) {
        list.replaceAll(Colors::color);
        return list;
    }

}
