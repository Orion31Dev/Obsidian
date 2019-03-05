package com.orion31.Obsidian.commands;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.PluginCommandYamlParser;
import org.bukkit.plugin.Plugin;

public class CommandManager {
	
	private static HashMap<String, ObsidianCommand> registeredCommands = new HashMap<String, ObsidianCommand>(); 
	private static HashMap<String, String> commandAliases = new HashMap<String, String>();
	
	public static void registerCommands(final Plugin plugin) {
		List<Command> commands = PluginCommandYamlParser.parse(plugin);
		
		for (Command command : commands) {
		    ObsidianCommand oCommand;
		    try {
			oCommand = (ObsidianCommand) CommandManager.class.getClassLoader()
				.loadClass("com.orion31.Obsidian.commands.Command" + command.getName()).getDeclaredConstructor().newInstance();
		    } catch (Exception e) { 
			continue;
		    }
		    registeredCommands.put(command.getName(), oCommand);
		    for (String alias : command.getAliases()) {
			commandAliases.put(alias, command.getName());
		    }
		    commandAliases.put(command.getName(), command.getName());
		}
	}
	
	public static ObsidianCommand getCommand(String cmd) throws CommandNotFoundException {
	    if (!commandAliases.containsKey(cmd)) throw new CommandNotFoundException(cmd);
	    return registeredCommands.get(commandAliases.get(cmd));
	}
	
	public static Collection<ObsidianCommand> getCommands() {
	    return registeredCommands.values();
	}

}
