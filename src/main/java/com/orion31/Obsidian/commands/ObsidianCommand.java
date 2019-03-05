package com.orion31.Obsidian.commands;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.orion31.Obsidian.Messenger;
import com.orion31.Obsidian.Obsidian;
import com.orion31.Obsidian.ObsidianException;
import com.orion31.Obsidian.PlayerNotFoundException;
import com.orion31.Obsidian.player.ObsidianPlayer;

public class ObsidianCommand extends Messenger {
    public String getName() {
	return name;
    }

    public String getUsage() {
	return Obsidian.getInstance().getCommand(name).getUsage();
    }

    public String getDesc() {
	return Obsidian.getInstance().getCommand(name).getDescription();
    }

    protected String name;

    public ObsidianCommand(String name) {
	this.name = name;
    }

    public boolean run(CommandSender sender, Command cmd, String label, String[] args) throws ObsidianException {
	throw new PlayerOnlyException();
    }

    public boolean run(ObsidianPlayer player, Command cmd, String label, String[] args) throws ObsidianException {
	return run(player.getMirror(), cmd, label, args);
    }

    public ObsidianPlayer getPlayer(String name) throws PlayerNotFoundException {
	return Obsidian.getPlayer(name);
    }
    
    public ObsidianPlayer getPlayer(UUID uuid) throws PlayerNotFoundException {
	return Obsidian.getPlayer(uuid);
    }
    
    public List<String> getPlayers() {
	return Obsidian.getPlayers();
    }

    public List<String> tabComplete(CommandSender sender, Command command, String alias, String[] args) throws ObsidianException {
	return Collections.emptyList();
    }
    
    public List<String> tabComplete(ObsidianPlayer player, Command command, String alias, String[] args) throws ObsidianException {
	return tabComplete(player.getMirror(), command, alias, args);
    }
}
