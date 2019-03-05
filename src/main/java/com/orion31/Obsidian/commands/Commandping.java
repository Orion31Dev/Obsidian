package com.orion31.Obsidian.commands;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.orion31.Obsidian.Messenger;

public class Commandping extends ObsidianCommand {

    public Commandping() {
	super("ping");
    }

    @Override
    public boolean run(CommandSender sender, Command cmd, String label, String[] args) {
	Messenger.msgColor(sender, "Pong!");
	return true;
    }

    
    @Override
    public List<String> tabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) return Arrays.asList("easteregg!");
        return Collections.emptyList();
    }
}
