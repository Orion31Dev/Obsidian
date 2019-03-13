package com.orion31.Obsidian.player.games.parkour;

import org.bukkit.Material;
import org.bukkit.inventory.PlayerInventory;

import com.orion31.Obsidian.ItemBuilder;
import com.orion31.Obsidian.player.PlayerSettings;
import com.orion31.Obsidian.player.games.Game;
import com.orion31.Obsidian.player.games.ObsidianGame;

public class GameParkour extends ObsidianGame {

    public GameParkour() {
	super(Game.PARKOUR);
    }

    ParkourCourse activeCourse = ParkourManager.getCourse();
    int checkpoint; 
    
    @Override
    public PlayerInventory getInventory() {
	PlayerInventory inv = player.getInventory();
	inv.clear();
	inv.addItem(new ItemBuilder(Material.FEATHER, 1).setName("&c&lReset Time").build());
	return inv;
    }

    @Override
    public PlayerSettings getSettings() {
	return new PlayerSettings();
    }
    
    public ParkourCourse getActiveCourse() {
	return activeCourse;
    }
    
    public int getLastCheckpoint() {
	return checkpoint;
    }
    
    public void completeCheckpoint() {
	checkpoint++;
    }
}
