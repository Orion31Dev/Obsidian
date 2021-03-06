package com.orion31.Obsidian.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;

import com.orion31.Obsidian.ObsidianException;
import com.orion31.Obsidian.player.ObsidianPlayer;
import com.orion31.Obsidian.player.Teleporter;


public class Commandwaypoint extends ObsidianCommand {
    public Commandwaypoint() {
	super("waypoint");
    }
    
    /*
     * Waypoints have no color translation.
     * This is because we would have to check twice in /goto:
     * Once for player wants to teleport to WP with color codes
     * and once without the color codes.
     * Ex. /goto &aWaypoint = /goto Waypoint
     */
    @Override
    public boolean run(ObsidianPlayer player, Command cmd, String label, String[] args) throws ObsidianException {
	if (args.length < 1) throw new InsufficientArgumentsException();
	if (args[0].equalsIgnoreCase("spawn")) throw new ObsidianException("Set spawn with /setspawn.");
	if (args[0].contains("&")) msg(player, "Remember, color codes are not supported in waypoint names.");
	Teleporter.setWaypoint(args[0], player.getLocation());
	msg(player, "Waypoint " + ChatColor.GREEN + args[0] + ChatColor.RESET + " created at your location.");
        return true;
    }
    
    @Override
    public List<String> tabComplete(ObsidianPlayer player, Command command, String alias, String[] args)
            throws ObsidianException {
        return new ArrayList<>(Teleporter.getWaypointNames());
    }
}