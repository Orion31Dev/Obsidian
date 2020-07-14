package com.orion31.Obsidian.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import com.orion31.Obsidian.ObsidianException;
import com.orion31.Obsidian.player.Kit;
import com.orion31.Obsidian.player.KitManager;
import com.orion31.Obsidian.player.ObsidianPlayer;

public class Commandcreatekit extends ObsidianCommand {
    public Commandcreatekit() {
        super("createkit");
    }

    @Override
    public boolean run(ObsidianPlayer player, Command cmd, String label, String[] args) throws ObsidianException {
        if (args.length == 0) throw new InsufficientArgumentsException();

        String s = KitManager.kitExists(args[0]);
        if (!s.equals("")) throw new ObsidianException("The kit &c" + s + "&r already exists");

        Kit k = new Kit();
        PlayerInventory pi = player.getInventory();
        ItemStack[] ia = new ItemStack[41];

        for (int i = 0; i < pi.getContents().length; i++) {
            if (pi.getContents()[i] == null)
                ia[i] = new ItemStack(Material.AIR);
            else
                ia[i] = pi.getContents()[i];
        }

        k.setItems(ia);
        k.setName(args[0]);
        KitManager.kits.put(args[0], k);

        msgColor(player, "Kit created: &a" + args[0]);
        return true;
    }
}
