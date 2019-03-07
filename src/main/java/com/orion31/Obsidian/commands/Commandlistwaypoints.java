package com.orion31.Obsidian.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.orion31.Obsidian.ObsidianException;
import com.orion31.Obsidian.player.Teleporter;

public class Commandlistwaypoints extends ObsidianCommand {

    public Commandlistwaypoints() {
	super("listwaypoints");
    }

    @Override
    public boolean run(CommandSender sender, Command cmd, String label, String[] args) throws ObsidianException {
	StringBuilder builder = new StringBuilder();
	builder.append("Warps: ");
	for (String s : Teleporter.getWaypointNames()) {
	    if (Teleporter.getWaypointNames().toArray()[Teleporter.getWaypointNames().size() - 1].equals(s)) {
		builder.append(s);
	    } else {
		builder.append(s + ", ");
	    }
	}
	msg(sender, builder.toString());
	return true;
    }
}
