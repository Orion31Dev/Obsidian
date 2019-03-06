package com.orion31.Obsidian.player;

import java.util.HashMap;
import java.util.Map.Entry;

import org.bukkit.Location;
import org.bukkit.block.Sign;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.Plugin;

import com.orion31.Obsidian.ObsidianYaml;

public class Teleporter {
    private static HashMap<String, Location> waypoints = new HashMap<String, Location>();
    private static HashMap<Sign, String> teleportSigns = new HashMap<Sign, String>();

    public static void init(Plugin plugin) {
	ObsidianYaml yaml = new ObsidianYaml("waypoints.yml");
	for (String s : yaml.getKeys(false)) {
	    Location location = new Location(plugin.getServer().getWorld(yaml.getString(s + ".world")),
		    yaml.getDouble(s + ".x"), yaml.getDouble(s + ".y"), yaml.getDouble(s + ".z"));
	    setWaypoint(s, location);
	}
    }

    public static void save() {
	ObsidianYaml yaml = new ObsidianYaml("waypoints.yml");
	yaml.clear();
	for (Entry<String, Location> entry : waypoints.entrySet()) {
	    yaml.set(entry.getKey() + ".world", entry.getValue().getWorld().getName());
	    yaml.set(entry.getKey() + ".x", entry.getValue().getX());
	    yaml.set(entry.getKey() + ".y", entry.getValue().getY());
	    yaml.set(entry.getKey() + ".z", entry.getValue().getZ());
	}
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

    public static void addTeleportSign(Sign sign, String waypoint) {
	teleportSigns.put(sign, waypoint);
    }

      public static String getWaypointFromSign(Sign sign) {
	return teleportSigns.get(sign);
    }

    public static void deleteTeleportSign(Sign sign) {
	teleportSigns.remove(sign);
    }

    public static boolean teleportSignExists(Sign sign) {
	return teleportSigns.containsKey(sign);
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
