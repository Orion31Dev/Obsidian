package com.orion31.Obsidian.player.games;

import java.util.Locale;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

import com.orion31.Obsidian.Messenger;
import com.orion31.Obsidian.Obsidian;
import com.orion31.Obsidian.ObsidianException;
import com.orion31.Obsidian.player.ObsidianPlayer;
import com.orion31.Obsidian.player.games.parkour.GameCreateParkourCourse;
import com.orion31.Obsidian.player.games.parkour.ParkourManager;

public class Listenercreateparkourcourse extends Messenger implements Listener {
    
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) throws ObsidianException {
	ObsidianPlayer player = Obsidian.getPlayer(e.getPlayer().getUniqueId());
	if (e.getHand() != EquipmentSlot.HAND) return;
	if (player.getGameType() != Game.CREATEPARKOURCOURSE)
	    return;
	if (e.getAction() == Action.PHYSICAL)
	    return;
	
	if (player.getInventory().getItemInMainHand() == null) return;
	Material mat = player.getInventory().getItemInMainHand().getType();
	GameCreateParkourCourse game = (GameCreateParkourCourse) player.getGame();
	
	if (mat == Material.COAL) {
	    if (e.getAction() != Action.RIGHT_CLICK_BLOCK && e.getAction() != Action.LEFT_CLICK_BLOCK) return;
	    game.course.setDeathMaterial(e.getClickedBlock().getType());
	    msg(player, "Death Material is now: &7"
		    + e.getClickedBlock().getType().toString().toLowerCase(Locale.ENGLISH));
	} else if (mat == Material.EMERALD) {
	    if (e.getAction() != Action.RIGHT_CLICK_BLOCK && e.getAction() != Action.LEFT_CLICK_BLOCK) return;
	    game.course.addCheckpoint(e.getClickedBlock().getLocation());
	    msg(player, "Created checkpoint %d", game.course.getCheckpoints().size());
	} else if (mat == Material.GOLD_INGOT) {
	    if (e.getAction() != Action.RIGHT_CLICK_BLOCK && e.getAction() != Action.LEFT_CLICK_BLOCK) return;
	    game.course.setStartLoc(e.getClickedBlock().getLocation());
	    if (game.course.isReadyForCreation()) game.addFinishCourseItem();
	    msg(player, "Start point set.");
	} else if (mat == Material.DIAMOND) {
	    game.course.setEndLoc(e.getClickedBlock().getLocation());
	    if (game.course.isReadyForCreation()) game.addFinishCourseItem();
	    msg(player, "End point set.");
	} else if (mat == Material.REDSTONE) {
	    if (game.course.getCheckpoints().size() == 0) {
		msg(player, "Nothing to delete.");
		return;
	    }
	    game.course.deleteCheckpoint(game.course.getCheckpoints().size() - 1);
	    msg(player, "Deleted checkpoint %d", game.course.getCheckpoints().size() + 1);
	} else if (mat == Material.REDSTONE_BLOCK) {
	    player.endGame();
	    msg(player, "Exited Create Course mode");
	} else if (mat == Material.NETHER_STAR) {
	    ParkourManager.addCourse(game.course);
	    player.endGame();
	    msg(player, "Course created!");
	}
    }
}
