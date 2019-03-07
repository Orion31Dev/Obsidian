package com.orion31.Obsidian.commands;

import org.bukkit.command.Command;

import com.orion31.Obsidian.ObsidianException;
import com.orion31.Obsidian.player.ObsidianPlayer;
import com.orion31.Obsidian.player.Teleporter;

public class Commandsetspawn extends ObsidianCommand {
    public Commandsetspawn() {
	super("setspawn");
    }
    
    @Override
    public boolean run(ObsidianPlayer player, Command cmd, String label, String[] args) throws ObsidianException {
        Teleporter.setWaypoint("spawn", player.getLocation());
        msgColor(player, "&aServer Spawn&r created at your location.");
        return true;
    }
}
