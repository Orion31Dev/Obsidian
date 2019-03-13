package com.orion31.Obsidian;

import static com.orion31.Obsidian.Messenger.console;
import static com.orion31.Obsidian.Messenger.msgColor;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import com.orion31.Obsidian.commands.CommandManager;
import com.orion31.Obsidian.player.ObsidianPlayer;
import com.orion31.Obsidian.player.PvPManager;
import com.orion31.Obsidian.player.PlayerListener;
import com.orion31.Obsidian.player.PlayerUpdater;
import com.orion31.Obsidian.player.Teleporter;
import com.orion31.Obsidian.player.games.Game;
import com.orion31.Obsidian.world.SignListener;

public final class Obsidian extends JavaPlugin {

    private static Obsidian _instance;

    private static ArrayList<ObsidianPlayer> onlinePlayers = new ArrayList<ObsidianPlayer>();

    public Obsidian() {
	_instance = this;
    }

    public static ConsoleCommandSender getConsoleSender() {
	return Bukkit.getConsoleSender();
    }

    public static Obsidian getInstance() {
	return _instance;
    }

    @Override
    public void onEnable() {
	registerGlow();

	CommandManager.registerCommands(_instance);

	Bukkit.getServer().getPluginManager().registerEvents(new PlayerListener(), this);
	Bukkit.getServer().getPluginManager().registerEvents(new SignListener(), this);

	// Register Games
	for (Game g : Game.values()) {
	    try {
		Bukkit.getServer().getPluginManager()
		    .registerEvents((Listener) Obsidian.class.getClassLoader()
			    .loadClass("com.orion31.Obsidian.player.games.Listener" + g.toString().toLowerCase())
			    .getDeclaredConstructor().newInstance(), this);
	    } catch (Exception e) {
		continue;
	    }
	}

	console("Plugin Active.");

	BukkitScheduler scheduler = getServer().getScheduler();
	scheduler.runTaskTimer(this, new PlayerUpdater(), 0L, 1L);

	// Register players on case of /reload
	for (Player player : Bukkit.getOnlinePlayers()) {
	    try {
		ObsidianPlayer oPlayer = Obsidian.getOfflinePlayer(player.getName());
		ObsidianYaml yml = new ObsidianYaml("players.yml");
		oPlayer.setNick(yml.getString(oPlayer.getRealName() + ".settings.nick"));
		oPlayer.setCanRunCommands(yml.getBool(oPlayer.getRealName() + ".settings.canRunCommands"));
		Obsidian.addPlayer(oPlayer);
		continue;
	    } catch (Exception ignored) {
	    }

	    Obsidian.addPlayer(new ObsidianPlayer(player));
	}

	// wait for multiverse
	Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {

	    @Override
	    public void run() {
		Teleporter.init(_instance);
		PvPManager.init(_instance);
	    }
	}, 20L);

    }

    @Override
    public void onDisable() {
	Teleporter.save();
	PvPManager.save();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
	try {
	    if (sender instanceof Player)
		return CommandManager.getCommand(label).run(getPlayer(((Player) sender).getUniqueId()), command, label,
			args);
	    else
		return CommandManager.getCommand(label).run(sender, command, label, args);
	} catch (ObsidianException e) {
	    msgColor(sender, e.getMessage());
	    return true;
	}
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
	try {
	    if (sender instanceof Player)
		return filter(CommandManager.getCommand(command.getName()).tabComplete(
			getPlayer(((Player) sender).getUniqueId()), command, command.getName(), args), args);
	    else
		return filter(CommandManager.getCommand(command.getName()).tabComplete(sender, command,
			command.getName(), args), args);
	} catch (ObsidianException e) {
	    msgColor(sender, e.getMessage());
	    return Collections.emptyList();
	}
    }

    private List<String> filter(List<String> unfilteredList, String[] args) {
	List<String> filteredList = new ArrayList<String>();
	for (String s : unfilteredList) {
	    if (s.startsWith(args[args.length - 1]))
		filteredList.add(s);
	}
	return filteredList;
    }

    public static ObsidianPlayer getPlayer(UUID uuid) throws PlayerNotFoundException {
	for (ObsidianPlayer player : onlinePlayers) {
	    if (player.getUUID() == uuid)
		return player;
	}
	throw new PlayerNotFoundException(uuid.toString());
    }

    public static ObsidianPlayer getPlayer(String name) throws PlayerNotFoundException {
	for (ObsidianPlayer player : onlinePlayers) {
	    if (player.getRealName().equalsIgnoreCase(name))
		return player;
	}
	for (ObsidianPlayer player : onlinePlayers) {
	    if (player.getRealName().toLowerCase().startsWith(name.toLowerCase()))
		return player;
	}
	throw new PlayerNotFoundException(name);
    }

    public static ObsidianPlayer getOfflinePlayer(String name) throws PlayerNotFoundException {
	ObsidianYaml playersYaml = new ObsidianYaml("players.yml");
	if (playersYaml.get(name) == null)
	    throw new PlayerNotFoundException(name);
	ObsidianPlayer player = new ObsidianPlayer(
		(Player) Bukkit.getOfflinePlayer(UUID.fromString(playersYaml.getString(name + ".uuid"))));
	player.setNick(playersYaml.getString(name + ".settings.nick"));
	player.setCanRunCommands(playersYaml.getBool(name + ".settings.canRunCommands"));
	return player;
    }

    public static boolean hasStoredData(String name) {
	try {
	    getOfflinePlayer(name);
	} catch (PlayerNotFoundException e) {
	    return false;
	}
	return true;
    }

    public static List<String> getPlayers() {
	List<String> playerNames = new ArrayList<String>();
	for (ObsidianPlayer o : onlinePlayers) {
	    playerNames.add(o.getRealName());
	}
	return playerNames;
    }

    public static List<ObsidianPlayer> getOfflinePlayers() {
	List<ObsidianPlayer> players = new ArrayList<ObsidianPlayer>();
	ObsidianYaml playersYaml = new ObsidianYaml("players.yml");
	for (String name : playersYaml.getKeys(false)) {
	    try {
		players.add(getOfflinePlayer(name));
	    } catch (PlayerNotFoundException e) {
		continue;
	    }
	}
	return players;
    }

    public static List<ObsidianPlayer> getObsidianPlayers() {
	return onlinePlayers;
    }

    public static void addPlayer(ObsidianPlayer player) {
	onlinePlayers.add(player);
    }

    public static void removePlayer(ObsidianPlayer player) {
	onlinePlayers.remove(player);
    }

    private void registerGlow() {
	try {
	    Field f = Enchantment.class.getDeclaredField("acceptingNew");
	    f.setAccessible(true);
	    f.set(null, true);
	} catch (Exception e) {
	    e.printStackTrace();
	}
	try {
	    Glow glow = new Glow();
	    Enchantment.registerEnchantment(glow);
	} catch (IllegalArgumentException e) {
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

}