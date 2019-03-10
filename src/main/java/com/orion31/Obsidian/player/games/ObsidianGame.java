package com.orion31.Obsidian.player.games;

import org.bukkit.inventory.PlayerInventory;

import com.orion31.Obsidian.player.ObsidianPlayer;
import com.orion31.Obsidian.player.PlayerSettings;

public abstract class ObsidianGame {
    protected Game gameType;
    
    public ObsidianGame(Game gameType) {
	this.gameType = gameType;
    }
        
    public abstract PlayerSettings getSettings();
    public abstract PlayerInventory getInventory(ObsidianPlayer player);

    public Game getGameType() {
	return gameType;
    }
    
}

