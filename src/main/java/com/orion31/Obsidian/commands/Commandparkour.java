package com.orion31.Obsidian.commands;

import org.bukkit.command.Command;

import com.orion31.Obsidian.ObsidianException;
import com.orion31.Obsidian.player.ObsidianPlayer;
import com.orion31.Obsidian.player.games.Game;
import com.orion31.Obsidian.player.games.parkour.GameParkour;

public class Commandparkour extends ObsidianCommand {
    public Commandparkour() {
	super("parkour");
    }
    
    @Override
    public boolean run(ObsidianPlayer player, Command cmd, String label, String[] args) throws ObsidianException {
	if (args.length < 1) throw new InsufficientArgumentsException();
        if (player.getGameType() != Game.NONE) throw new ObsidianException("You must leave your current game.");
        player.setGame(new GameParkour(args[0]));
        // msgColor(player, "Stand on a &6gold block &rto begin your time! (WIP)");
        msgColor(player, "Started parkour course &a" + args[0]);
        return true;
    }
}
