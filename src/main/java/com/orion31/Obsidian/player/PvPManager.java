package com.orion31.Obsidian.player;

import java.util.HashMap;
import java.util.Map.Entry;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.plugin.Plugin;

import com.orion31.Obsidian.ObsidianYaml;

public class PvPManager {
    private static HashMap<Sign, Kit> pvpSigns = new HashMap<Sign, Kit>();

    public static void init(Plugin plugin) {
	ObsidianYaml yaml = new ObsidianYaml("signs.yml");
	for (String s : yaml.getKeys(false)) {
	    if (!s.startsWith("pvp"))
		continue;
	    Location l = new Location(plugin.getServer().getWorld(yaml.getString(s + ".world")),
		    yaml.getDouble(s + ".x"),
		    yaml.getDouble(s + ".y"),
		    yaml.getDouble(s + ".z"));
	    try {
		Block b = l.getBlock();
		Sign sign = (Sign) b.getState();
		pvpSigns.put(sign, Kit.defaultIronKit()); // TODO implement kits
	    } catch (Exception e) {
		continue;
	    }
	}
    }

    public static void save() {
	ObsidianYaml yaml = new ObsidianYaml("signs.yml"); // Teleporter already cleared file so we do not clear here.
	int index = 0;
	for (Entry<Sign, Kit> entry : pvpSigns.entrySet()) {
	    String s = "pvp" + index++;
	    yaml.set(s + ".x", entry.getKey().getX());
	    yaml.set(s + ".y", entry.getKey().getY());
	    yaml.set(s + ".z", entry.getKey().getZ());
	    yaml.set(s + ".world", entry.getKey().getWorld().getName());
	    // yaml.set(s + ".kit", entry.getValue().getName()); // TODO implement kits
	}
	yaml.save();
    }

    public static void addPvPSign(Sign sign, Kit kit) {
	pvpSigns.put(sign, kit);
    }

    public static Kit getKitFromSign(Sign sign) {
	return pvpSigns.get(sign);
    }

    public static void deletePvPSign(Sign sign) {
	pvpSigns.remove(sign);
    }

    public static boolean pvpSignExists(Sign sign) {
	return pvpSigns.containsKey(sign);
    }
}