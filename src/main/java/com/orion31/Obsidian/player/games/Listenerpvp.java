package com.orion31.Obsidian.player.games;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import com.orion31.Obsidian.Obsidian;
import com.orion31.Obsidian.PlayerNotFoundException;
import com.orion31.Obsidian.player.ObsidianPlayer;
import com.orion31.Obsidian.player.Teleporter;

public class Listenerpvp implements Listener {
    @EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent e) throws PlayerNotFoundException {
		ObsidianPlayer player = Obsidian.getPlayer(e.getPlayer().getUniqueId());
		if (player.getGameType() == Game.PVP) {
	    player.endGame();
	    if (Teleporter.waypointExists("pvp"))
		e.setRespawnLocation(Teleporter.getWaypoint("pvp"));
	    else
		e.setRespawnLocation(Teleporter.getWaypoint("spawn"));
	}
    }
    
    @EventHandler
    public void onPlayerDrop(PlayerDropItemEvent e) throws PlayerNotFoundException {
	ObsidianPlayer player = Obsidian.getPlayer(e.getPlayer().getUniqueId());
	if (player.getGameType() != Game.PVP)
	    return;
	
	e.setCancelled(true);
    }
}
