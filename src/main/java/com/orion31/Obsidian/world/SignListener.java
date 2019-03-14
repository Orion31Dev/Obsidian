package com.orion31.Obsidian.world;

import org.bukkit.ChatColor;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import com.orion31.Obsidian.Messenger;
import com.orion31.Obsidian.Obsidian;
import com.orion31.Obsidian.PlayerNotFoundException;
import com.orion31.Obsidian.player.Kit;
import com.orion31.Obsidian.player.ObsidianPlayer;
import com.orion31.Obsidian.player.PvPManager;
import com.orion31.Obsidian.player.Teleporter;
import com.orion31.Obsidian.player.games.pvp.GamePvP;

public class SignListener extends Messenger implements Listener {

    @EventHandler
    public void onSignPlace(SignChangeEvent e) {

	// Teleporter Signs
	if (e.getLine(0).startsWith("o.tp")) {
	    String waypoint = e.getLine(1);
	    if (!Teleporter.waypointExists(waypoint)) {
		msg(e.getPlayer(), "Unknown waypoint: " + ChatColor.RED + waypoint);
		return;
	    }
	    Teleporter.addTeleportSign((Sign) e.getBlock().getState(), waypoint);
	    e.setLine(0, color("&5&lTeleport to"));
	    e.setLine(1, color("&a" + waypoint));
	    e.setLine(2, "");
	    e.setLine(3, "");
	    msg(e.getPlayer(), "This sign will now teleport to " + ChatColor.GREEN + waypoint);
	    return;
	}

	// PvP Signs
	if (e.getLine(0).startsWith("o.pvp")) {
	    String waypoint = e.getLine(1);
	    if (!Teleporter.waypointExists(waypoint)) {
		msg(e.getPlayer(), "Unknown waypoint: " + ChatColor.RED + waypoint);
		return;
	    }
	    Teleporter.addTeleportSign((Sign) e.getBlock().getState(), waypoint);
	    PvPManager.addPvPSign((Sign) e.getBlock().getState(), Kit.defaultIronKit());
	    e.setLine(0, color("&5&lEnter PvP"));
	    e.setLine(1, color("&a" + waypoint));
	    e.setLine(2, "");
	    e.setLine(3, "");
	    msg(e.getPlayer(), "This sign will now initiate PvP at " + ChatColor.GREEN + waypoint);
	    return;
	}
	
	// End Game Signs
	if (e.getLine(0).startsWith("o.endgame")) {
	    String waypoint = e.getLine(1);
	    if (!Teleporter.waypointExists(waypoint)) {
		msg(e.getPlayer(), "Unknown waypoint: " + ChatColor.RED + waypoint);
		return;
	    }
	    Teleporter.addEndGameSign((Sign) e.getBlock().getState(), waypoint);
	    e.setLine(0, color("&5&lLeave Game"));
	    e.setLine(1, "");
	    e.setLine(2, "");
	    e.setLine(3, "");
	    msg(e.getPlayer(), "This sign will now end the current game and teleport player to " + ChatColor.GREEN + waypoint);
	    return;
	}

	e.setLine(0, color(e.getLine(0)));
	e.setLine(1, color(e.getLine(1)));
	e.setLine(2, color(e.getLine(2)));
	e.setLine(3, color(e.getLine(3)));
	
	
    }

    @EventHandler
    public void onPlayerClickSign(PlayerInteractEvent e) throws PlayerNotFoundException {
	if (e.getClickedBlock() == null || e.getClickedBlock().getState() == null
		|| !(e.getClickedBlock().getState() instanceof Sign))
	    return;
	
	ObsidianPlayer player = Obsidian.getPlayer(e.getPlayer().getUniqueId());
	Sign sign = (Sign) e.getClickedBlock().getState();
	
	if (Teleporter.endGameSignExists(sign)) {
	    if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
		Teleporter.teleport(e.getPlayer(),
			Teleporter.getWaypointFromSign(sign));
		player.endGame();
		msg(e.getPlayer(), "Ended game.");
	    } else if (e.getAction() == Action.LEFT_CLICK_BLOCK) {
		Teleporter.deleteEndGameSign(sign);
		msg(e.getPlayer(), "Game End sign deleted.");
		e.getClickedBlock().breakNaturally();
	    }
	    return;
	}
	
	if (PvPManager.pvpSignExists(sign)) {
	    if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
		Teleporter.teleport(e.getPlayer(),
			Teleporter.getWaypointFromSign(sign));
		player.setGame(new GamePvP(Kit.defaultIronKit()));
		msg(e.getPlayer(), "Entered PvP zone " + ChatColor.DARK_RED
			+ Teleporter.getWaypointFromSign(sign));
	    } else if (e.getAction() == Action.LEFT_CLICK_BLOCK) {
		PvPManager.deletePvPSign(sign);
		Teleporter.deleteTeleportSign(sign);
		msg(e.getPlayer(), "PvP sign deleted.");
		e.getClickedBlock().breakNaturally();
	    }
	    return;
	}

	if (Teleporter.teleportSignExists(sign)) {
	    if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {

		Teleporter.teleport(e.getPlayer(),
			Teleporter.getWaypointFromSign(sign));
		msg(e.getPlayer(), "Teleported to " + ChatColor.GREEN
			+ Teleporter.getWaypointFromSign(sign));
	    } else if (e.getAction() == Action.LEFT_CLICK_BLOCK) {
		Teleporter.deleteTeleportSign(sign);
		msg(e.getPlayer(), "Teleport sign deleted.");
		e.getClickedBlock().breakNaturally();
	    }
	}
    }
}