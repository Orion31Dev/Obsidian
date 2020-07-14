package com.orion31.Obsidian.commands;

import com.orion31.Obsidian.ObsidianException;
import com.orion31.Obsidian.player.ObsidianPlayer;
import com.orion31.Obsidian.player.PowerToolHandler;
import org.bukkit.Material;
import org.bukkit.command.Command;

public class Commandpowertool extends ObsidianCommand {
    public Commandpowertool() {
        super("powertool");
    }

    @Override
    public boolean run(ObsidianPlayer player, Command cmd, String label, String[] args) throws ObsidianException {
        if (args.length == 0) throw new InsufficientArgumentsException();

        Material type = player.getInventory().getItemInMainHand().getType();
        if (type == Material.AIR) throw new ObsidianException("You must hold an item.");

        PowerToolHandler.createPowerTool(player, type, String.join(" ", args));
        return true;
    }
}
