package com.orion31.Obsidian.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.orion31.Obsidian.Obsidian;
import com.orion31.Obsidian.ObsidianException;
import com.orion31.Obsidian.player.ObsidianPlayer;

public class Commandbroadcast extends ObsidianCommand {

    public Commandbroadcast() {
        super("broadcast");
    }

    @Override
    public boolean run(CommandSender sender, Command cmd, String label, String[] args) throws ObsidianException {
        if (args.length == 0) throw new InsufficientArgumentsException();
        String msg = "&c[&6Broadcast&c]&r " + String.join(" ", args);

        for (ObsidianPlayer p : Obsidian.getObsidianPlayers()) {
            ghostColor(p, msg);
        }

        console(msg);

        return true;
    }
}
