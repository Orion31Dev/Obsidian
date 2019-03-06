package com.orion31.Obsidian.commands;

import java.util.Collections;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.orion31.Obsidian.ObsidianException;
import com.orion31.Obsidian.player.ObsidianPlayer;

public class Commandnickname extends ObsidianCommand {

    public Commandnickname() {
	super("nickname");
    }

    @Override
    public boolean run(CommandSender sender, Command cmd, String label, String[] args) throws ObsidianException {
	if (args.length < 2) {
	    throw new InsufficientArgumentsException();
	}

	if (ChatColor.stripColor(color(args[0])).length() >= 13) {
	    throw new ObsidianException("Nickname must be less than 13 characters, excluding color codes.");
	}

	ObsidianPlayer target = getPlayer(args[1]);
	target.setNick(args[0].replaceAll("_", " "));
	msg(target, "Your nickname is now " + target.getNick());
	msg(sender, "Set " + target.getRealName() + "'s nickname to " + target.getNick());
	return true;
    }

    @Override
    public boolean run(ObsidianPlayer player, Command cmd, String label, String[] args) throws ObsidianException {
	if (args.length < 1) {
	    throw new InsufficientArgumentsException();
	}

	if (ChatColor.stripColor(color(args[0])).length() >= 13) {
	    throw new ObsidianException("Nickname must be less than 13 characters, excluding color codes.");
	}

	if (args.length == 1) {
	    player.setNick(args[0].replaceAll("_", " "));
	    msg(player, "Your nickname is now " + player.getNick());
	    return true;
	}
	
	return run(player.getMirror(), cmd, label, args);
    }

    @Override
    public List<String> tabComplete(CommandSender sender, Command command, String alias, String[] args)
	    throws ObsidianException {
	if (args.length == 2)
	    return getPlayers();
	return Collections.emptyList();
    }
}
