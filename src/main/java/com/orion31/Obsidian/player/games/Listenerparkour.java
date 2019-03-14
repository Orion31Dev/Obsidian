package com.orion31.Obsidian.player.games;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import com.orion31.Obsidian.Messenger;
import com.orion31.Obsidian.Obsidian;
import com.orion31.Obsidian.ObsidianException;
import com.orion31.Obsidian.PlayerNotFoundException;
import com.orion31.Obsidian.player.ObsidianPlayer;
import com.orion31.Obsidian.player.Teleporter;
import com.orion31.Obsidian.player.games.parkour.GameParkour;
import com.orion31.Obsidian.player.games.parkour.ParkourCourse;

public class Listenerparkour extends Messenger implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) throws ObsidianException {
	if (e.isCancelled() || e.getFrom().getBlock().getLocation() == e.getFrom().getBlock().getLocation())
	    return;

	Block blockStoodOn = e.getPlayer().getLocation().clone().subtract(0, 1, 0).getBlock();
	ObsidianPlayer player = Obsidian.getPlayer(e.getPlayer().getUniqueId());
	Location blockLoc = Obsidian.roundLocation(blockStoodOn.getLocation());

	if (player.getGameType() != Game.PARKOUR)
	    return;

	GameParkour game = (GameParkour) player.getGame();
	ParkourCourse course = game.getActiveCourse();
	if (blockStoodOn.getType() == course.getDeathMaterial()) {
	    Teleporter.teleport(player, game.getCheckpointLoc());
	} else if (blockLoc.equals(Obsidian.roundLocation(course.getEndLoc()))) {
	    msg(player, "You beat the course!");
	    player.playSound(Sound.ENTITY_PLAYER_LEVELUP);
	    player.endGame();
	} else if (course.getCheckpoint(game.getLastCheckpoint() + 1) != null
		&& blockLoc.equals(Obsidian.roundLocation(course.getCheckpoint(game.getLastCheckpoint() + 1)))) {
	    game.completeCheckpoint();
	    msgColor(player, "Completed Checkpoint &a" + game.getLastCheckpoint() + 1);
	    player.playSound(Sound.ENTITY_EXPERIENCE_ORB_PICKUP);
	}
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) throws ObsidianException {
	if (e.getAction() == Action.PHYSICAL)
	    return;

	ObsidianPlayer player = Obsidian.getPlayer(e.getPlayer().getUniqueId());
	if (player.getGameType() != Game.PARKOUR)
	    return;

	GameParkour game = (GameParkour) player.getGame();
	if (e.getPlayer().getInventory().getItemInMainHand().getType() == Material.FEATHER) {
	    game.resetCourse();
	    msgColor(player, "&cResetting time");
	} else if (e.getPlayer().getInventory().getItemInMainHand().getType() == Material.REDSTONE_BLOCK) {
	    player.teleport(game.getActiveCourse().getStartLoc());
	    player.endGame();
	    msgColor(player, "&4Ended parkour run.");
	}
    }

    @EventHandler
    public void onPlayerDrop(PlayerDropItemEvent e) throws PlayerNotFoundException {
	ObsidianPlayer player = Obsidian.getPlayer(e.getPlayer().getUniqueId());
	if (player.getGameType() != Game.PARKOUR)
	    return;

	e.setCancelled(true);
    }
}
