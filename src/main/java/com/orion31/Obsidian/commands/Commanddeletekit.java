package com.orion31.Obsidian.commands;

import com.orion31.Obsidian.ObsidianException;
import com.orion31.Obsidian.player.Kit;
import com.orion31.Obsidian.player.KitManager;
import com.orion31.Obsidian.player.ObsidianPlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class Commanddeletekit extends ObsidianCommand {
    public Commanddeletekit() {
        super("deletekit");
    }

    @Override
    public boolean run(CommandSender sender, Command cmd, String label, String[] args) throws ObsidianException {
        if (args.length == 0) throw new InsufficientArgumentsException();

        Kit k = KitManager.getKit(args[0]);
        if (k == null) throw new ObsidianException("Kit &c" + args[0] + "&r does not exist.");

        KitManager.kits.remove(k.name);
        msgColor(sender, "Deleted kit &a" + k.name);
        return true;
    }
}
