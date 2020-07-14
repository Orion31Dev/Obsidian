package com.orion31.Obsidian.commands;

import com.orion31.Obsidian.ObsidianException;
import com.orion31.Obsidian.player.ObsidianPlayer;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.inventory.meta.ItemMeta;

public class Commanditemname extends ObsidianCommand {
    public Commanditemname() {
        super("itemname");
    }

    @Override
    public boolean run(ObsidianPlayer player, Command cmd, String label, String[] args) throws ObsidianException {
        if (args.length == 0) throw new InsufficientArgumentsException();
        if (player.getInventory().getItemInMainHand().getType() == Material.AIR) throw new ObsidianException("You must hold an item.");

        String name = String.join(" ", args);
        ItemMeta m = player.getInventory().getItemInMainHand().getItemMeta();
        m.setDisplayName(color(name));

        player.getInventory().getItemInMainHand().setItemMeta(m);


        return true;
    }
}
