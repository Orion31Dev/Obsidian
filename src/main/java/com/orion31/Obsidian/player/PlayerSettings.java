package com.orion31.Obsidian.player;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class PlayerSettings {
    public String nick = "";
    public GameMode gamemode;
    public boolean vanished;
    public boolean canRunCommands = false;
    
    private String _nick = "";
    private GameMode _gamemode;
    private boolean _vanished;
    private boolean _canRunCommands = false; 
    
    private boolean inPvp = false;

    public PlayerSettings(Player player) {
	nick = player.getName();
	gamemode = player.getGameMode();
	vanished = false;
    }
    
    public void togglePvp() {
	if (inPvp) {
	    nick = _nick;
	    gamemode = _gamemode;
	    vanished = _vanished;
	    canRunCommands = _canRunCommands;
	} else {
	    _nick = nick;
	    _gamemode = gamemode;
	    _vanished = vanished;
	    _canRunCommands = canRunCommands;
	    
	    gamemode = GameMode.ADVENTURE;
	    vanished = false;
	    canRunCommands = false;
	}
	inPvp = !inPvp;
    }
}
