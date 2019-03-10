package com.orion31.Obsidian.player;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import com.orion31.Obsidian.ItemBuilder;

public class Kit {
    private ItemStack helmet;
    private ItemStack chestplate;
    private ItemStack leggings;
    private ItemStack boots;
    private ItemStack mainItem;
    private ItemStack[] otherItems;

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

    public Kit setMainItem(ItemStack mainItem) {
	this.mainItem = mainItem;
	return this;
    }
    
    public Kit setOtherItems(ItemStack... otherItems) {
	this.otherItems = otherItems;
	return this;
    }

    public PlayerInventory getInventory(ObsidianPlayer player) {
	PlayerInventory inv = player.getInventory();
	inv.clear();
	inv.setHelmet(helmet);
	inv.setChestplate(chestplate);
	inv.setLeggings(leggings);
	inv.setBoots(boots);
	inv.addItem(mainItem);
	inv.addItem(otherItems);
	inv.setHeldItemSlot(0);
	return inv;
    }

    public static Kit defaultIronKit() {
	return new Kit().setHelmet(new ItemBuilder(Material.IRON_HELMET, 1).build())
		.setChestplate(new ItemBuilder(Material.IRON_CHESTPLATE, 1).build())
		.setLeggings(new ItemBuilder(Material.IRON_HELMET, 1).build())
		.setBoots(new ItemBuilder(Material.IRON_HELMET, 1).build())
		.setMainItem(new ItemBuilder(Material.IRON_SWORD, 1).build())
		.setOtherItems(new ItemBuilder(Material.BOW, 1).build(),
			new ItemBuilder(Material.COOKED_BEEF, 64).build(),
			new ItemBuilder(Material.ARROW, 64).build(),
			new ItemBuilder(Material.ARROW, 64).build());
    }
}
