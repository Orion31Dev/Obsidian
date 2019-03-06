package com.orion31.Obsidian.player;

import java.util.HashMap;

import org.bukkit.block.Sign;

public class PvPHandler {
    private static HashMap<Sign, Kit> pvpSigns = new HashMap<Sign, Kit>();

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
