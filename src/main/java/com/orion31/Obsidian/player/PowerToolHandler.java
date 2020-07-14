package com.orion31.Obsidian.player;

import java.util.HashMap;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.orion31.Obsidian.ItemBuilder;

public class PowerToolHandler {
    static HashMap<Integer, PowerTool> powerTools = new HashMap<>();

    public static void createPowerTool(ObsidianPlayer player, Material type, String cmd) {
        PowerTool t = new PowerTool(type, cmd);

        int id;
        do {
            id = new Random().nextInt();
        } while (powerTools.containsKey(id));

        powerTools.put(id, t);

        ItemStack i = new ItemBuilder(type, 1).setLore("&c&lPowerTool", String.valueOf(id)).glow().build();
        player.getInventory().setItemInMainHand(i);
    }
}

class PowerTool {
    Material type;
    String cmd;

    public PowerTool(Material type, String cmd) {
        this.type = type;
        this.cmd = cmd;
    }
}
