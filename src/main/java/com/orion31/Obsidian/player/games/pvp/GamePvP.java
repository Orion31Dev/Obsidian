package com.orion31.Obsidian.player.games.pvp;

import org.bukkit.inventory.PlayerInventory;

import com.orion31.Obsidian.player.Kit;
import com.orion31.Obsidian.player.PlayerSettings;
import com.orion31.Obsidian.player.games.Game;
import com.orion31.Obsidian.player.games.ObsidianGame;

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
    public PlayerInventory getInventory() {
        return kit.getInventory(player);
    }
}
