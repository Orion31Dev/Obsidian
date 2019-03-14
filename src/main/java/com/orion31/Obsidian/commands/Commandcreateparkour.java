package com.orion31.Obsidian.commands;

import org.bukkit.command.Command;

import com.orion31.Obsidian.ObsidianException;
import com.orion31.Obsidian.player.ObsidianPlayer;
import com.orion31.Obsidian.player.games.parkour.GameCreateParkourCourse;

public class Commandcreateparkour extends ObsidianCommand {
    public Commandcreateparkour() {
	super("createparkour");
    }
    
    @Override
    public boolean run(ObsidianPlayer player, Command cmd, String label, String[] args) throws ObsidianException {
	msg(player, "Now creating parkour course.");
	player.setGame(new GameCreateParkourCourse());
        return true;
    }
}
