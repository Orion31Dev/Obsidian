package com.orion31.Obsidian.commands;

import java.util.ArrayList;
import java.util.Collections;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.orion31.Obsidian.Obsidian;
import com.orion31.Obsidian.ObsidianException;

public class Commandobsidian extends ObsidianCommand {

    public Commandobsidian() {
	super("obsidian");
    }

    @Override
    public boolean run(CommandSender sender, Command cmd, String label, String[] args) throws ObsidianException {
	if (args.length > 0 && args[0].equalsIgnoreCase("help")) {
	    if (args.length > 1) {
		ObsidianCommand oCmd = CommandManager.getCommand(args[1]); 
		ghostColor(sender, "&5&l" + oCmd.getUsage() + "&r: " + oCmd.getDesc());
		return true;
	    }
	    
	    ArrayList<String> commandNames = new ArrayList<String>();
	    for (ObsidianCommand oCmd : CommandManager.getCommands()) {
		commandNames.add(oCmd.getName());
	    }
	    
	    Collections.sort(commandNames);
	    for (String s : commandNames) {
		ghostColor(sender, "&5&l/" + s + "&r: " + CommandManager.getCommand(s).getDesc());
	    }
	    
	    return true;
	}
	
	ghostColor(sender, "&5&lObsidian v" + Obsidian.getInstance().getDescription().getVersion() + " by Orion31");
	return true;
    }
}
