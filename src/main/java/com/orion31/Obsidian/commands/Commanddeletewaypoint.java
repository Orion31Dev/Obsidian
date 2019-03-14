package com.orion31.Obsidian.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.orion31.Obsidian.ObsidianException;
import com.orion31.Obsidian.player.Teleporter;

public class Commanddeletewaypoint extends ObsidianCommand {
    
    public Commanddeletewaypoint() {
	super("deletewaypoint");
    }
    
    @Override
    public boolean run(CommandSender sender, Command cmd, String label, String[] args) throws ObsidianException {
	if (args.length < 1) throw new InsufficientArgumentsException();
	if (args[0].equalsIgnoreCase("spawn")) throw new ObsidianException("You cannot delete the spawn.");
	if (!Teleporter.waypointExists(args[0]))
	    throw new ObsidianException("Unknown waypoint: &c" + args[0]);
	
	Teleporter.deleteWaypoint(args[0]);
	msg(sender, "Waypoint " + ChatColor.RED + args[0] + ChatColor.RESET + " deleted.");
        return true;
    }
}