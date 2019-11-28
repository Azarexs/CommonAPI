package me.azarex.commonapi.commands;

import org.bukkit.command.CommandSender;

public interface CommandBase {

    void execute(CommandSender sender, String[] args);
    String getName();
    boolean isConsoleEnabled();

}
