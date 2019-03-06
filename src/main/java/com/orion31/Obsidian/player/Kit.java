package com.orion31.Obsidian.player;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.orion31.Obsidian.ItemBuilder;

public class Kit {
    private ItemStack helmet;
    private ItemStack chestplate;
    private ItemStack leggings;
    private ItemStack boots;
    private ItemStack[] mainItems;

    public Kit setHelmet(ItemStack helmet) {
	this.helmet = helmet;
	return this;
    }

    public Kit setChestplate(ItemStack chestplate) {
	this.chestplate = chestplate;
	return this;
    }

    public Kit setLeggings(ItemStack leggings) {
	this.leggings = leggings;
	return this;
    }

    public Kit setBoots(ItemStack boots) {
	this.boots = boots;
	return this;
    }

    public Kit setMainItems(ItemStack... mainItems) {
	this.mainItems = mainItems;
	return this;
    }

    public void applyToPlayer(ObsidianPlayer player) {
	player.clearInventory();
	player.getInventory().setHelmet(helmet);
	player.getInventory().setChestplate(chestplate);
	player.getInventory().setLeggings(leggings);
	player.getInventory().setBoots(boots);
	player.getInventory().setContents(mainItems);
    }

    public static Kit defaultIronKit() {
	return new Kit().setHelmet(new ItemBuilder().setItem(Material.IRON_HELMET, 1).build())
		.setChestplate(new ItemBuilder().setItem(Material.IRON_CHESTPLATE, 1).build())
		.setLeggings(new ItemBuilder().setItem(Material.IRON_HELMET, 1).build())
		.setBoots(new ItemBuilder().setItem(Material.IRON_HELMET, 1).build())
		.setMainItems(new ItemBuilder().setItem(Material.IRON_SWORD, 1).build(),
			new ItemBuilder().setItem(Material.BOW, 1).build(),
			new ItemBuilder().setItem(Material.COOKED_BEEF, 64).build(),
			new ItemBuilder().setItem(Material.ARROW, 64 * 2).build());
    }
}
