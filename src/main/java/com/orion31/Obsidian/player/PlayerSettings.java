package com.orion31.Obsidian.player;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class PlayerSettings {
    public String nick = "";
    public GameMode gamemode;
    public boolean vanished;
    public boolean canRunCommands = false;

    public PlayerSettings(Player player) {
	nick = player.getName();
	gamemode = player.getGameMode();
	vanished = false;
    }
}
