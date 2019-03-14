package com.orion31.Obsidian.player.games.parkour;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.inventory.PlayerInventory;

import com.orion31.Obsidian.ItemBuilder;
import com.orion31.Obsidian.player.PlayerSettings;
import com.orion31.Obsidian.player.games.Game;
import com.orion31.Obsidian.player.games.ObsidianGame;

public class GameCreateParkourCourse extends ObsidianGame {

    public GameCreateParkourCourse() {
	super(Game.CREATEPARKOURCOURSE);
    }

    public ParkourCourse course = new ParkourCourse();
    public boolean isTypingName = false;
    
    @Override
    public PlayerSettings getSettings() {
	PlayerSettings settings = new PlayerSettings();
	settings.gamemode = GameMode.CREATIVE;
	settings.canRunCommands = true;
	return settings;
    }

    @Override
    public PlayerInventory getInventory() {
	PlayerInventory inv = player.getInventory();
	inv.clear();
	inv.addItem(new ItemBuilder(Material.COAL, 1).setName("&7&lSet Death Block").build());
	inv.addItem(new ItemBuilder(Material.EMERALD, 1).setName("&a&lAdd Checkpoint").build());
	inv.addItem(new ItemBuilder(Material.GOLD_INGOT, 1).setName("&6&lSet Start Point").build());
	inv.addItem(new ItemBuilder(Material.DIAMOND, 1).setName("&b&lSet End Point").build());
	inv.setItem(6, new ItemBuilder(Material.NAME_TAG, 1).setName("&lSet Course Name").build());
	inv.setItem(7, new ItemBuilder(Material.REDSTONE, 1).setName("&c&lDelete Last Checkpoint").build());
	inv.setItem(8, new ItemBuilder(Material.REDSTONE_BLOCK, 1).setName("&4&lCancel Creation").build());
	return inv;
    }
    
    public void addFinishCourseItem() {
	player.getInventory().setItem(5, new ItemBuilder(Material.NETHER_STAR, 1).setName("&e&lSave Course").build());
    }

}
