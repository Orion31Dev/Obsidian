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
import com.orion31.Obsidian.player.PvPHandler;
import com.orion31.Obsidian.player.Teleporter;

public class BlockListener extends Messenger implements Listener {

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
	    PvPHandler.addPvPSign((Sign) e.getBlock().getState(), Kit.defaultIronKit());
	    e.setLine(0, color("&5&lEnter PvP"));
	    e.setLine(1, color("&a" + waypoint));
	    e.setLine(2, "");
	    e.setLine(3, color("&4&lThis will clear your inventory"));
	    msg(e.getPlayer(), "This sign will now initiate pvp at " + ChatColor.GREEN + waypoint);
	    return;
	}

	e.setLine(0, color(e.getLine(0)));
	e.setLine(1, color(e.getLine(1)));
	e.setLine(2, color(e.getLine(2)));
	e.setLine(3, color(e.getLine(3)));

    }

    @EventHandler
    public void onPlayerClickSign(PlayerInteractEvent e) throws PlayerNotFoundException {
	if (e.getClickedBlock().getState() == null || !(e.getClickedBlock().getState() instanceof Sign))
	    return;
	
	if (PvPHandler.pvpSignExists((Sign) e.getClickedBlock().getState())) {
	    if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {

		Teleporter.teleport(e.getPlayer(),
			Teleporter.getWaypointFromSign((Sign) e.getClickedBlock().getState()));
		PvPHandler.getKitFromSign((Sign) e.getClickedBlock().getState())
			.applyToPlayer(Obsidian.getPlayer(e.getPlayer().getUniqueId()));
		msg(e.getPlayer(), "Entered PvP zone " + ChatColor.DARK_RED
			+ Teleporter.getWaypointFromSign((Sign) e.getClickedBlock().getState()));
	    } else if (e.getAction() == Action.LEFT_CLICK_BLOCK) {

		PvPHandler.deletePvPSign((Sign) e.getClickedBlock().getState());
		Teleporter.deleteTeleportSign((Sign) e.getClickedBlock().getState());
		msg(e.getPlayer(), "PvP sign deleted.");
	    }
	    return;
	}
	
	if (Teleporter.teleportSignExists((Sign) e.getClickedBlock().getState())) {
	    if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {

		Teleporter.teleport(e.getPlayer(),
			Teleporter.getWaypointFromSign((Sign) e.getClickedBlock().getState()));
		msg(e.getPlayer(), "Teleported to " + ChatColor.GREEN
			+ Teleporter.getWaypointFromSign((Sign) e.getClickedBlock().getState()));
	    } else if (e.getAction() == Action.LEFT_CLICK_BLOCK) {

		Teleporter.deleteTeleportSign((Sign) e.getClickedBlock().getState());
		msg(e.getPlayer(), "Teleport sign deleted.");
	    }
	}
    }
}
