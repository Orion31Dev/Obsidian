package com.orion31.Obsidian.player.games;

import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import com.orion31.Obsidian.Messenger;
import com.orion31.Obsidian.Obsidian;
import com.orion31.Obsidian.ObsidianException;
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

	if (player.getGameType() != Game.PARKOUR)
	    return;

	GameParkour game = (GameParkour) player.getGame();
	ParkourCourse course = game.getActiveCourse();
	if (blockStoodOn.getType() == course.getCheckpointMaterial()) {
	    if (blockStoodOn.getLocation() == course.getCheckpoint(game.getLastCheckpoint() + 1)) {
		game.completeCheckpoint();
		msg(player, "Completed Checkpoint &a" + game.getLastCheckpoint());
	    }
	} else if (blockStoodOn.getType() == course.getDeathMaterial()) {
	    Teleporter.teleport(player, course.getCheckpoint(game.getLastCheckpoint()).clone().add(0, 1, 0));
	} else if (blockStoodOn.getLocation() == course.getEndLoc()) {
	    msg(player, "You beat the course!");
	    player.endGame();
	}
    }
}
