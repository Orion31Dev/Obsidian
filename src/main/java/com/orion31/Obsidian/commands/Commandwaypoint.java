package com.orion31.Obsidian.commands;

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
	Teleporter.setWaypoint(args[0], player.getLocation());
	msg(player, "Waypoint " + args[0] + " created at your location.");
        return true;
    }
}
