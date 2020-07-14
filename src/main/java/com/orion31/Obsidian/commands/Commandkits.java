package com.orion31.Obsidian.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.ItemStack;

import com.orion31.Obsidian.ObsidianException;
import com.orion31.Obsidian.player.KitManager;

public class Commandkits extends ObsidianCommand {
    public Commandkits() {
        super("kits");
    }

    @Override
    public boolean run(CommandSender sender, Command cmd, String label, String[] args) throws ObsidianException {
        msg(sender, "Kits: " + String.join(", ", KitManager.kits.keySet()));
        return true;
    }
}
