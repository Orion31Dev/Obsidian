package com.orion31.Obsidian.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;

import com.orion31.Obsidian.ObsidianException;
import com.orion31.Obsidian.player.ObsidianPlayer;
import com.orion31.Obsidian.player.Teleporter;

public class Commandgoto extends ObsidianCommand {

    public Commandgoto() {
	super("goto");
    }

    @Override
    public boolean run(ObsidianPlayer player, Command cmd, String label, String[] args) throws ObsidianException {
	if (args.length < 1)
	    throw new InsufficientArgumentsException();

	if (!Teleporter.waypointExists(args[0]))
	    throw new ObsidianException("Unknown waypoint.");
	
	Teleporter.teleport(player, args[0]);
	msg(player, "Teleported to " + ChatColor.GREEN + args[0]); // We do not want the waypoint to show colors. See Commandwaypoint.java
	return true;
    }
}
