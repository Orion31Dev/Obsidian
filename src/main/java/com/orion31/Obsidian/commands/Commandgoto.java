package com.orion31.Obsidian.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.orion31.Obsidian.ObsidianException;
import com.orion31.Obsidian.player.ObsidianPlayer;
import com.orion31.Obsidian.player.Teleporter;

public class Commandgoto extends ObsidianCommand {

    public Commandgoto() {
	super("goto");
    }

    @Override
    public boolean run(CommandSender sender, Command cmd, String label, String[] args) throws ObsidianException {
        if (args.length < 2)
            throw new InsufficientArgumentsException();
        
	if (!Teleporter.waypointExists(args[0]))
	    throw new ObsidianException("Unknown waypoint: &c" + args[0]);
	
	ObsidianPlayer target = getPlayer(args[1]);
	Teleporter.teleport(target, args[0]);
	msg(sender, "Teleported %s to " + ChatColor.GREEN + args[0], target.getNick()); // We do not want the waypoint to show colors. See Commandwaypoint.java
	msg(target, "You were teleported to " + ChatColor.GREEN + args[0]); // We do not want the waypoint to show colors. See Commandwaypoint.java

	return true;
    }
    
    @Override
    public boolean run(ObsidianPlayer player, Command cmd, String label, String[] args) throws ObsidianException {
	if (args.length < 1)
	    throw new InsufficientArgumentsException();

	if (args.length > 1)
	    return run(player.getMirror(), cmd, label, args);
	
	if (!Teleporter.waypointExists(args[0]))
	    throw new ObsidianException("Unknown waypoint: &c" + args[0]);
	
	Teleporter.teleport(player, args[0]);
	msg(player, "Teleported to " + ChatColor.GREEN + args[0]); // We do not want the waypoint to show colors. See Commandwaypoint.java
	return true;
    }
}
