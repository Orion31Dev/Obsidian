package com.orion31.Obsidian.commands;

import java.util.Collections;
import java.util.List;

import com.orion31.Obsidian.player.Kit;
import com.orion31.Obsidian.player.KitManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.orion31.Obsidian.ObsidianException;
import com.orion31.Obsidian.player.ObsidianPlayer;

public class Commandkit extends ObsidianCommand {

    public Commandkit() {
        super("kit");
    }

    @Override
    public boolean run(CommandSender sender, Command cmd, String label, String[] args) throws ObsidianException {
        if (args.length < 2) {
            throw new InsufficientArgumentsException();
        }

        Kit k = KitManager.getKit(args[0]);
        if (k == null) throw new ObsidianException("Kit does not exist: &c" + args[0]);

        ObsidianPlayer target = getPlayer(args[1]);
        target.getInventory().setContents(k.getInventory(target).getContents());

        msgColor(target, "Someone has given you the kit &a" + k.name);
        msgColor(sender, "Kit &a" + k.name + "&r applied to &a" + target.getNick());


        return true;
    }

    @Override
    public boolean run(ObsidianPlayer player, Command cmd, String label, String[] args) throws ObsidianException {
        if (args.length < 1) {
            throw new InsufficientArgumentsException();
        }

        if (args.length == 1) {
            Kit k = KitManager.getKit(args[0]);
            if (k == null) throw new ObsidianException("Kit does not exist: &c" + args[0]);

            player.getInventory().setContents(k.getInventory(player).getContents());
            msgColor(player, "Applied kit &a" + k.name);
            return true;
        }

        return run(player.getMirror(), cmd, label, args);
    }

    @Override
    public List<String> tabComplete(CommandSender sender, Command command, String alias, String[] args)
            throws ObsidianException {
        if (args.length == 2)
            return getPlayers();
        return Collections.emptyList();
    }
}
