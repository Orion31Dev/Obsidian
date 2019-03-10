package com.orion31.Obsidian.player.games;

import org.bukkit.inventory.PlayerInventory;

import com.orion31.Obsidian.player.Kit;
import com.orion31.Obsidian.player.ObsidianPlayer;
import com.orion31.Obsidian.player.PlayerSettings;

public class GamePvP extends ObsidianGame {

    private Kit kit;
    
    public GamePvP(Kit kit) {
	super(Game.PVP);
	this.kit = kit;
    }

    @Override
    public PlayerSettings getSettings() {
	return new PlayerSettings();
    }
    
    @Override
    public PlayerInventory getInventory(ObsidianPlayer player) {
        return kit.getInventory(player);
    }
    
}
