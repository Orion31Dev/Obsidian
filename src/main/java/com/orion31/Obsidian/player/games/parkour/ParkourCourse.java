package com.orion31.Obsidian.player.games.parkour;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.Material;

import com.orion31.Obsidian.ObsidianException;

public class ParkourCourse {
    String name = "";
    Material deathMaterial = Material.AIR;
    Location start = null;
    Location end = null;
    HashMap<Integer, Location> checkpoints = new HashMap<Integer, Location>();

    public ParkourCourse setStartLoc(Location loc) throws ObsidianException {
	if (loc.getBlock().getType() == deathMaterial)
	    throw new ObsidianException("Start block cannot be of the same type as the Death Block.");
	start = loc;
	return this;
    }

    public ParkourCourse setEndLoc(Location loc) throws ObsidianException {
	if (loc.getBlock().getType() == deathMaterial)
	    throw new ObsidianException("End block cannot be of the same type as the Death Block.");
	end = loc;
	return this;
    }

    public ParkourCourse setDeathMaterial(Material mat) {
	deathMaterial = mat;
	return this;
    }

    public ParkourCourse setCheckpoint(int index, Location loc) {
	checkpoints.put(index, loc);
	return this;
    }

    public void setName(String name) {
	this.name = name;
    }
    
    public String getName() {
	return name;
    }
    
    public ParkourCourse addCheckpoint(Location loc) throws ObsidianException {
	if (loc.getBlock().getType() == deathMaterial)
	    throw new ObsidianException("Checkpoint blocks cannot be of the same type as the Death Block.");

	checkpoints.put(checkpoints.size(), loc);

	return this;
    }

    public boolean isReadyForCreation() {
	return !(start == null || end == null || name.equals(""));
    }

    public ParkourCourse deleteCheckpoint(int index) {
	checkpoints.remove(index);
	return this;
    }

    public Location getStartLoc() {
	return start;
    }

    public Location getEndLoc() {
	return end;
    }

    public Material getDeathMaterial() {
	return deathMaterial;
    }

    public HashMap<Integer, Location> getCheckpoints() {
	return checkpoints;
    }

    public Location getCheckpoint(int index) {
	return checkpoints.get(index);
    }
}
