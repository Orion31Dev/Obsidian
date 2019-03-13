package com.orion31.Obsidian.player.games.parkour;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.Material;

import com.orion31.Obsidian.ObsidianException;

public class ParkourCourse {
    String name;
    Material deathMaterial = Material.AIR;
    Material checkpointMaterial = Material.AIR;
    Location start = null;
    Location end = null;
    HashMap<Integer, Location> checkpoints = new HashMap<Integer, Location>();

    public ParkourCourse setStartLoc(Location loc) {
	start = loc;
	return this;
    }

    public ParkourCourse setEndLoc(Location loc) throws ObsidianException {
	if (!isMaterialCompatible(loc.getBlock().getType()))
	    throw new ObsidianException("This material is already used.");
	end = loc;   
	return this;
    }

    public ParkourCourse setDeathMaterial(Material mat) {
	deathMaterial = mat;
	return this;
    }

    public ParkourCourse setCheckpoint(int index, Location loc) {
	if (checkpointMaterial == Material.AIR)
	    checkpointMaterial = loc.getBlock().getType();

	if (isMaterialCompatible(loc.getBlock().getType()))
	    checkpoints.put(index, loc);

	return this;
    }

    public ParkourCourse addCheckpoint(Location loc) throws ObsidianException {
	if (checkpointMaterial == Material.AIR) {
	    if (isMaterialCompatible(loc.getBlock().getType()))
		checkpointMaterial = loc.getBlock().getType();
	    else
		throw new ObsidianException("This material is already used.");
	}

	checkpoints.put(checkpoints.size(), loc);

	return this;
    }
    
    public boolean isReadyForCreation() {
	return !(start == null || end == null);
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

    public Material getCheckpointMaterial() {
	return checkpointMaterial;
    }

    public HashMap<Integer, Location> getCheckpoints() {
	return checkpoints;
    }

    public Location getCheckpoint(int index) {
	return checkpoints.get(index);
    }

    public boolean isMaterialCompatible(Material mat) {
	if (mat == deathMaterial || mat == checkpointMaterial || mat == Material.AIR)
	    return false;
	return true;
    }
}
