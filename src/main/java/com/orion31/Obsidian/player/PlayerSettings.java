package com.orion31.Obsidian.player;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class PlayerSettings {
    public GameMode gamemode;
    public boolean vanished;
    public boolean canRunCommands = false;

    public PlayerSettings() {
	gamemode = GameMode.ADVENTURE;
	vanished = false;
	canRunCommands = false;
    }
    
    public PlayerSettings(Player player) {
	gamemode = player.getGameMode();
	vanished = false;
    }
}
