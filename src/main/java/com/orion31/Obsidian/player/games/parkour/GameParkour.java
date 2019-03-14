package com.orion31.Obsidian.player.games.parkour;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.PlayerInventory;

import com.orion31.Obsidian.ItemBuilder;
import com.orion31.Obsidian.ObsidianException;
import com.orion31.Obsidian.player.PlayerSettings;
import com.orion31.Obsidian.player.Teleporter;
import com.orion31.Obsidian.player.games.Game;
import com.orion31.Obsidian.player.games.ObsidianGame;

public class GameParkour extends ObsidianGame {
    
    ParkourCourse activeCourse;
    Location checkpointLoc;
    int checkpoint = -1;
    
    public GameParkour(String courseName) throws ObsidianException {
	super(Game.PARKOUR);
	activeCourse = ParkourManager.getCourse(courseName);
	checkpointLoc = activeCourse.getStartLoc();
    }

    @Override
    public void init() {
	Teleporter.teleport(player, checkpointLoc);
    }
    
    @Override
    public PlayerInventory getInventory() {
	PlayerInventory inv = player.getInventory();
	inv.clear();
	inv.addItem(new ItemBuilder(Material.FEATHER, 1).setName("&c&lReset Time").build());
	inv.addItem(new ItemBuilder(Material.REDSTONE_BLOCK, 1).setName("&4&lEnd Parkour").build());
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
	checkpointLoc = activeCourse.getCheckpoint(++checkpoint);
	checkpointLoc.setPitch(player.getLocation().getPitch()); // Save player orientation
	checkpointLoc.setYaw(player.getLocation().getYaw());
    }
    
    public Location getCheckpointLoc() {
	return checkpointLoc.clone().add(0, 1, 0);
    }
    
    public void resetCourse() {
	checkpoint = -1;
	checkpointLoc = activeCourse.getStartLoc();
	Teleporter.teleport(player, checkpointLoc);
    }
}
