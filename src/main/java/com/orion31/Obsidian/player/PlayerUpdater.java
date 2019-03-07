package com.orion31.Obsidian.player;

import org.bukkit.entity.Player;

import com.orion31.Obsidian.Obsidian;

public class PlayerUpdater implements Runnable {

    @Override
    public void run() {
	for (ObsidianPlayer oPlayer : Obsidian.getObsidianPlayers()) {
	    	Player player = oPlayer.getMirror();
	    	
		player.setDisplayName(oPlayer.getNick());
		player.setPlayerListName(oPlayer.getNick());
		
		player.setGameMode(oPlayer.getGamemode());
	}
    }

}
