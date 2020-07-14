package com.orion31.Obsidian.player;

import java.util.HashMap;
import java.util.List;

import org.bukkit.inventory.ItemStack;

import com.orion31.Obsidian.ObsidianYaml;

public class KitManager {
    public static HashMap<String, Kit> kits = new HashMap<>();


    @SuppressWarnings("unchecked")
    public static void init() {
        ObsidianYaml yaml = new ObsidianYaml("kits.yml");
        for (String s : yaml.getKeys(false)) {
            ItemStack[] content = ((List<ItemStack>) yaml.get(s + ".inventory.content")).toArray(new ItemStack[41]);
            Kit k = new Kit();
            k.setItems(content);
            k.setName(s);
            kits.put(s, k);
        }
    }

    public static void save() {
        ObsidianYaml yaml = new ObsidianYaml("kits.yml");
        yaml.clear();
        for (Kit k : kits.values()) {
            yaml.set(k.name + ".inventory.content", k.getItems());
        }

        yaml.save();

    }

    public static String kitExists(String name) {
        for (String s : kits.keySet()) {
            if (name.equalsIgnoreCase(s)) return s;
        }
        return "";
    }

    public static Kit getKit(String name) {
        for (String s : kits.keySet()) {
            if (name.equalsIgnoreCase(s)) {
                return kits.get(s);
            }
        }
        return null;
    }
}

