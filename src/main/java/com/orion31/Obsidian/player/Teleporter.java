package com.orion31.Obsidian.player;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.Plugin;

import com.orion31.Obsidian.ObsidianYaml;

public class Teleporter {
    private static HashMap<String, Location> waypoints = new HashMap<String, Location>();
    private static HashMap<Sign, String> teleportSigns = new HashMap<Sign, String>();
    private static HashMap<Sign, String> endGameSigns = new HashMap<Sign, String>();

    public static void init(Plugin plugin) {
	ObsidianYaml yaml = new ObsidianYaml("waypoints.yml");
	for (String s : yaml.getKeys(false)) {
	    Location location = new Location(plugin.getServer().getWorld(yaml.getString(s + ".world")),
		    yaml.getDouble(s + ".x"),
		    yaml.getDouble(s + ".y"),
		    yaml.getDouble(s + ".z"),
		    yaml.getFloat(s + ".yaw"),
		    yaml.getFloat(s + ".pitch"));
	    setWaypoint(s, location);
	}

	yaml = new ObsidianYaml("signs.yml");
	for (String s : yaml.getKeys(false)) {
	    Location l = new Location(plugin.getServer().getWorld(yaml.getString(s + ".world")),
		    yaml.getDouble(s + ".x"),
		    yaml.getDouble(s + ".y"),
		    yaml.getDouble(s + ".z"));
	    try {
		Block b = l.getBlock();
		Sign sign = (Sign) b.getState();
		if (s.startsWith("tp"))
		    teleportSigns.put(sign, yaml.getString(s + ".waypoint"));
		else if (s.startsWith("end"))
		    endGameSigns.put(sign, yaml.getString(s + ".waypoint"));
	    } catch (Exception e) {
		continue;
	    }
	}
    }

    public static void save() {
	ObsidianYaml yaml = new ObsidianYaml("waypoints.yml");
	yaml.clear();
	for (Entry<String, Location> entry : waypoints.entrySet()) {
	    yaml.set(entry.getKey() + ".x", entry.getValue().getX());
	    yaml.set(entry.getKey() + ".y", entry.getValue().getY());
	    yaml.set(entry.getKey() + ".z", entry.getValue().getZ());
	    yaml.set(entry.getKey() + ".pitch", entry.getValue().getPitch());
	    yaml.set(entry.getKey() + ".yaw", entry.getValue().getYaw());
	    yaml.set(entry.getKey() + ".world", entry.getValue().getWorld().getName());
	}
	yaml.save();
	
	yaml = new ObsidianYaml("signs.yml");
	yaml.clear();
	int index = 0;
	for (Entry<Sign, String> entry : teleportSigns.entrySet()) {
	    String s = "tp" + index++;
	    yaml.set(s + ".x", entry.getKey().getX());
	    yaml.set(s + ".y", entry.getKey().getY());
	    yaml.set(s + ".z", entry.getKey().getZ());
	    yaml.set(s + ".world", entry.getKey().getWorld().getName());
	    yaml.set(s + ".waypoint", entry.getValue());
	}
	
	index = 0;
	for (Entry<Sign, String> entry : endGameSigns.entrySet()) {
	    String s = "end" + index++;
	    yaml.set(s + ".x", entry.getKey().getX());
	    yaml.set(s + ".y", entry.getKey().getY());
	    yaml.set(s + ".z", entry.getKey().getZ());
	    yaml.set(s + ".world", entry.getKey().getWorld().getName());
	    yaml.set(s + ".waypoint", entry.getValue());
	    
	}
	yaml.save();
    }

    public static void setWaypoint(String name, Location location) {
	waypoints.put(name, location);
    }

    public static Location getWaypoint(String name) {
	return waypoints.get(name);
    }

    public static void deleteWaypoint(String name) {
	waypoints.remove(name);
    }

    public static boolean waypointExists(String name) {
	return waypoints.containsKey(name);
    }

    public static Set<String> getWaypointNames() {
	return waypoints.keySet();
    }

    public static void addTeleportSign(Sign sign, String waypoint) {
	teleportSigns.put(sign, waypoint);
    }

    public static String getWaypointFromSign(Sign sign) {
	if (teleportSignExists(sign))
	    return teleportSigns.get(sign);
	else if (endGameSignExists(sign))
	    return endGameSigns.get(sign);
	return "";
    }

    public static void deleteTeleportSign(Sign sign) {
	teleportSigns.remove(sign);
    }

    public static boolean teleportSignExists(Sign sign) {
	return teleportSigns.containsKey(sign);
    }
    
    public static void addEndGameSign(Sign sign, String waypoint) {
	endGameSigns.put(sign, waypoint);
    }

    public static void deleteEndGameSign(Sign sign) {
	endGameSigns.remove(sign);
    }

    public static boolean endGameSignExists(Sign sign) {
	return endGameSigns.containsKey(sign);
    }

    public static void teleport(ObsidianPlayer target, Location destination) {
	target.teleport(destination);
    }

    public static void teleport(ObsidianPlayer target, Entity destination) {
	target.teleport(destination);
    }

    public static void teleport(ObsidianPlayer target, ObsidianPlayer destination) {
	target.teleport(destination.getLocation());
    }

    public static void teleport(Entity target, Location destination) {
	target.teleport(destination);
    }

    public static void teleport(ObsidianPlayer target, String waypoint) {
	target.teleport(waypoints.get(waypoint));
    }

    public static void teleport(Entity target, String waypoint) {
	target.teleport(waypoints.get(waypoint));
    }
}
