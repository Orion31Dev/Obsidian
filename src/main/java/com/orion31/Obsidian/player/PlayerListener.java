package com.orion31.Obsidian.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.server.ServerListPingEvent;

import com.orion31.Obsidian.Messenger;
import com.orion31.Obsidian.Obsidian;
import com.orion31.Obsidian.ObsidianYaml;
import com.orion31.Obsidian.PlayerNotFoundException;

public class PlayerListener extends Messenger implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
	try {
	    ObsidianPlayer player = Obsidian.getOfflinePlayer(e.getPlayer().getName());
	    ObsidianYaml yml = new ObsidianYaml("players.yml");
	    player.setNick(yml.getString(player.getRealName() + ".settings.nick"));
	    player.setCanRunCommands(yml.getBool(player.getRealName() + ".settings.canRunCommands"));
	    Obsidian.addPlayer(player);
	    motd(e.getPlayer());
	    e.setJoinMessage(color(player.getNick() + "&r&a joined."));
	    return;
	} catch (Exception ignored) {
	}

	Obsidian.addPlayer(new ObsidianPlayer(e.getPlayer()));
	motd(e.getPlayer());
	e.setJoinMessage(color(e.getPlayer().getDisplayName() + "&r&a joined."));
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
	e.setQuitMessage(color(e.getPlayer().getDisplayName() + "&r&4 left."));

	try {
	    ObsidianPlayer player = Obsidian.getPlayer(e.getPlayer().getName());
	    ObsidianYaml yml = new ObsidianYaml("players.yml");
	    yml.set(player.getRealName() + ".ip", player.getIP().getAddress().toString());
	    yml.set(player.getRealName() + ".uuid", player.getUUID().toString());
	    yml.set(player.getRealName() + ".settings.nick", player.getNickRaw());
	    yml.set(player.getRealName() + ".settings.canRunCommands", player.getCanRunCommands());
	} catch (PlayerNotFoundException ignored) {
	}

	try {
	    Obsidian.removePlayer(Obsidian.getPlayer(e.getPlayer().getUniqueId()));
	} catch (PlayerNotFoundException ignored) {
	}
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
	e.setCancelled(true);
	ghostAllColor(e.getPlayer().getDisplayName() + "&r: " + e.getMessage());
    }

    @EventHandler
    public void onPlayerCommand(PlayerCommandPreprocessEvent e) {
	try {
	    if (Obsidian.getPlayer(e.getPlayer().getUniqueId()).getCanRunCommands())
		return;
	    msg(e.getPlayer(), "You cannot run commands.");
	    e.setCancelled(true);
	} catch (PlayerNotFoundException ignored) {
	}
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent e) {
	if (Teleporter.waypointExists("spawn"))
	    e.setRespawnLocation(Teleporter.getWaypoint("spawn"));
    }

    @EventHandler
    public void onServerPing(ServerListPingEvent e) {
	try {
	    ObsidianYaml yml = new ObsidianYaml("players.yml");
	    for (String name : yml.getKeys(false)) {
		if (yml.getString(name + ".ip").equals(e.getAddress().toString())) {
		    e.setMotd(color("&3&lC&a&lT&3&lC&a&lM&3&lS" + "\nLook here, &a&l" + name));
		    return;
		}
	    }
	} catch (Exception ignored) {
	}

	e.setMotd(color("&3&lC&a&lT&3&lC&a&lM&3&lS"));
    }
}
