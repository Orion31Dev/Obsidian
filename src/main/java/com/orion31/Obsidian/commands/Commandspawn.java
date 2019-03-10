package com.orion31.Obsidian.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.orion31.Obsidian.ObsidianException;
import com.orion31.Obsidian.player.ObsidianPlayer;
import com.orion31.Obsidian.player.Teleporter;

public class Commandspawn extends ObsidianCommand {

    public Commandspawn() {
	super("spawn");
    }

    @Override
    public boolean run(CommandSender sender, Command cmd, String label, String[] args) throws ObsidianException {
	if (args.length < 1) throw new InsufficientArgumentsException();
	if (!Teleporter.waypointExists("spawn"))
	    throw new ObsidianException("Could not find spawn (/setspawn)");
	ObsidianPlayer target = getPlayer(args[0]);
	Teleporter.teleport(target, "spawn");
	return true;
    }

    @Override
    public boolean run(ObsidianPlayer player, Command cmd, String label, String[] args) throws ObsidianException {
	if (args.length == 0) {
	    if (!Teleporter.waypointExists("spawn"))
		throw new ObsidianException("Could not find spawn (/setspawn)");
	    Teleporter.teleport(player, "spawn");
	    return true;
	}
	return run(player.getMirror(), cmd, label, args);
    }

}
