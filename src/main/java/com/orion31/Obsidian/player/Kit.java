package com.orion31.Obsidian.player;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class Kit {
    public String name;
    private ItemStack[] items;

    public ItemStack[] getItems() {
        return items;
    }

    public Kit setName(String name) {
        this.name = name;
        return this;
    }

    public Kit setItems(ItemStack... items) {
        this.items = items;
        return this;
    }

    public PlayerInventory getInventory(ObsidianPlayer player) {
        PlayerInventory inv = player.getInventory();
        inv.clear();
        if (items != null) inv.setContents(items);
        inv.setHeldItemSlot(0);
        return inv;
    }
}
