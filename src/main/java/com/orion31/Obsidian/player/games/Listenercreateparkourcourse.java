package com.orion31.Obsidian.player.games;

import java.util.Locale;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

import com.orion31.Obsidian.Messenger;
import com.orion31.Obsidian.Obsidian;
import com.orion31.Obsidian.ObsidianException;
import com.orion31.Obsidian.PlayerNotFoundException;
import com.orion31.Obsidian.player.ObsidianPlayer;
import com.orion31.Obsidian.player.games.parkour.GameCreateParkourCourse;
import com.orion31.Obsidian.player.games.parkour.ParkourManager;

public class Listenercreateparkourcourse extends Messenger implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) throws ObsidianException {
	ObsidianPlayer player = Obsidian.getPlayer(e.getPlayer().getUniqueId());
	if (e.getHand() != EquipmentSlot.HAND)
	    return;
	if (player.getGameType() != Game.CREATEPARKOURCOURSE)
	    return;
	if (e.getAction() == Action.PHYSICAL)
	    return;

	if (player.getInventory().getItemInMainHand() == null)
	    return;
	Material mat = player.getInventory().getItemInMainHand().getType();
	GameCreateParkourCourse game = (GameCreateParkourCourse) player.getGame();

	try {
	    if (mat == Material.COAL) {
		if (e.getAction() != Action.RIGHT_CLICK_BLOCK && e.getAction() != Action.LEFT_CLICK_BLOCK)
		    return;
		game.course.setDeathMaterial(e.getClickedBlock().getType());
		msgColor(player, "Death Material is now: &7"
			+ e.getClickedBlock().getType().toString().toLowerCase(Locale.ENGLISH));
		player.playSound(Sound.BLOCK_DISPENSER_DISPENSE);
	    } else if (mat == Material.EMERALD) {
		if (e.getAction() != Action.RIGHT_CLICK_BLOCK && e.getAction() != Action.LEFT_CLICK_BLOCK)
		    return;
		game.course.addCheckpoint(e.getClickedBlock().getLocation());
		msg(player, "Created checkpoint %d", game.course.getCheckpoints().size());
		player.playSound(Sound.BLOCK_NOTE_PLING);
	    } else if (mat == Material.GOLD_INGOT) {
		if (e.getAction() != Action.RIGHT_CLICK_BLOCK && e.getAction() != Action.LEFT_CLICK_BLOCK)
		    return;
		game.course.setStartLoc(e.getClickedBlock().getLocation());
		if (game.course.isReadyForCreation())
		    game.addFinishCourseItem();
		msg(player, "Start point set.");
		player.playSound(Sound.BLOCK_NOTE_PLING);
	    } else if (mat == Material.DIAMOND) {
		game.course.setEndLoc(e.getClickedBlock().getLocation());
		if (game.course.isReadyForCreation())
		    game.addFinishCourseItem();
		msg(player, "End point set.");
		player.playSound(Sound.BLOCK_NOTE_PLING);
	    } else if (mat == Material.NAME_TAG) {
		msg(player, "Type course name in chat.");
		game.isTypingName = true;
		player.playSound(Sound.BLOCK_NOTE_PLING);
	    } else if (mat == Material.REDSTONE) {
		if (game.course.getCheckpoints().size() == 0) {
		    msg(player, "Nothing to delete.");
		    return;
		}
		game.course.deleteCheckpoint(game.course.getCheckpoints().size() - 1);
		msg(player, "Deleted checkpoint %d", game.course.getCheckpoints().size() + 1);
		player.playSound(Sound.BLOCK_DISPENSER_FAIL);
	    } else if (mat == Material.REDSTONE_BLOCK) {
		player.endGame();
		msg(player, "Exited Create Course mode");
		player.playSound(Sound.BLOCK_ANVIL_FALL, 1f, .5f);
	    } else if (mat == Material.NETHER_STAR) {
		ParkourManager.addCourse(game.course);
		player.endGame();
		msg(player, "Course created!");
		player.playSound(Sound.ENTITY_PLAYER_LEVELUP);
	    }
	} catch (ObsidianException ex) {
	    msgColor(player, ex.getMessage());
	}
	e.setCancelled(true);
    }

    @EventHandler
    public void onPlayerBuild(BlockPlaceEvent e) throws ObsidianException {
	ObsidianPlayer player = Obsidian.getPlayer(e.getPlayer().getUniqueId());
	if (player.getGameType() != Game.CREATEPARKOURCOURSE)
	    return;
	e.setBuild(false);
    }

    @EventHandler
    public void asyncPlayerChat(AsyncPlayerChatEvent e) throws ObsidianException {
	ObsidianPlayer player = Obsidian.getPlayer(e.getPlayer().getUniqueId());
	if (player.getGameType() != Game.CREATEPARKOURCOURSE)
	    return;

	e.setCancelled(true);

	GameCreateParkourCourse game = (GameCreateParkourCourse) player.getGame();
	if (game.isTypingName) {
	    game.course.setName(e.getMessage());
	    game.isTypingName = false;
	    msgColor(player, "Set course name to &a" + e.getMessage());
	    if (game.course.isReadyForCreation())
		game.addFinishCourseItem();
	} else {
	    ghostAllColor(e.getPlayer().getDisplayName() + "&r: " + e.getMessage());
	}
    }

    @EventHandler
    public void onPlayerDrop(PlayerDropItemEvent e) throws PlayerNotFoundException {
	ObsidianPlayer player = Obsidian.getPlayer(e.getPlayer().getUniqueId());
	if (player.getGameType() != Game.CREATEPARKOURCOURSE)
	    return;

	e.setCancelled(true);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) throws PlayerNotFoundException {
	ObsidianPlayer player = Obsidian.getPlayer(e.getWhoClicked().getUniqueId());
	if (player.getGameType() != Game.CREATEPARKOURCOURSE)
	    return;
	e.setCancelled(true);
    }
}
