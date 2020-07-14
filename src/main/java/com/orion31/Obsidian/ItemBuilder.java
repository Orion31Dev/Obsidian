package com.orion31.Obsidian;

import static com.orion31.Obsidian.Messenger.color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.orion31.Obsidian.Glow;

public class ItemBuilder {

    private Material type = null;
    private short durability = -1;
    private int quantity = 1;
    private String name = null;
    private List<String> lore = null;
    boolean glow = false;
    private boolean unbreakable = false;

    public ItemBuilder(Material material, int quantity) {
        setItem(material, quantity);
    }

    public ItemBuilder(ItemStack itemStack) {
        type = itemStack.getType();
        durability = itemStack.getDurability();
        quantity = itemStack.getAmount();
        name = itemStack.getItemMeta().getDisplayName();
        lore = itemStack.getItemMeta().getLore();
        unbreakable = itemStack.getItemMeta().isUnbreakable();
    }


    public ItemBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ItemBuilder setLore(String... lore) {
        return setLore(Arrays.asList(lore));
    }

    public ItemBuilder setLore(List<String> lore) {
        List<String> lore2 = new ArrayList<String>();
        for (String s : lore) lore2.add(color("&r" + s));
        this.lore = lore2;
        return this;
    }

    public ItemBuilder setUnbreakable(boolean unbreakable) {
        this.unbreakable = unbreakable;
        return this;
    }

    public ItemBuilder glow() {
        glow = true;
        return this;
    }

    public ItemBuilder setDurability(short durability) {
        this.durability = durability;
        return this;
    }


    public ItemBuilder setItem(Material type, int quantity) {
        this.type = type;
        this.quantity = quantity;
        return this;
    }

    public ItemStack build() {
        ItemStack itemStack = new ItemStack(type, quantity);
        ItemMeta itemMeta = itemStack.getItemMeta();
        if (name != null) itemMeta.setDisplayName(color(name));
        if (lore != null) itemMeta.setLore(lore);
        if (glow) itemMeta.addEnchant(new Glow(), 1, true);
        itemMeta.setUnbreakable(unbreakable);
        itemStack.setItemMeta(itemMeta);
        if (durability != -1) itemStack.setDurability(durability);
        ;
        return itemStack;
    }
}
