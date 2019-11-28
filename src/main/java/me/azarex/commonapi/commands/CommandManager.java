package me.azarex.commonapi.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.HashSet;
import java.util.Set;

public class CommandManager implements CommandExecutor {

    private final Set<CommandBase> commands = new HashSet<>();
    private final CommandBase helpCommand;
    private final String invalidSubCommand;
    private final String consoleCannotUseCommand;

    public CommandManager(CommandBase helpCommand, String invalidSubCommand, String consoleCannotUseCommand) {
        this.helpCommand = helpCommand;
        this.invalidSubCommand = ChatColor.translateAlternateColorCodes('&', invalidSubCommand);
        this.consoleCannotUseCommand = ChatColor.translateAlternateColorCodes('&', consoleCannotUseCommand);
    }

    public void registerSubCommand(CommandBase command) {
        commands.add(command);
    }

    private CommandBase getCommand(String commandName) {
        return commands.stream()
                .filter(command -> command.getName().equalsIgnoreCase(commandName))
                .findFirst().orElse(null);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            helpCommand.execute(sender, new String[0]);
            return true;
        }

        final CommandBase commandBase = getCommand(args[0]);

        if (commandBase == null) {
            sender.sendMessage(invalidSubCommand);
            return true;
        }

        if (!commandBase.isConsoleEnabled()) {
            sender.sendMessage(consoleCannotUseCommand);
            return true;
        }

        commandBase.execute(sender, args);
        return true;
    }
}
