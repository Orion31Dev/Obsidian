package com.orion31.Obsidian.commands;

import java.util.Collections;
import java.util.List;

import com.bringholm.nametagchanger.NameTagChanger;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.orion31.Obsidian.ObsidianException;
import com.orion31.Obsidian.player.ObsidianPlayer;

public class Commandnametag extends ObsidianCommand {

    public Commandnametag() {
        super("nametag");
    }

    @Override
    public boolean run(CommandSender sender, Command cmd, String label, String[] args) throws ObsidianException {
        if (args.length < 2) {
            throw new InsufficientArgumentsException();
        }

        if (args[0].length() > 16) {
            throw new ObsidianException("Nametag cannot be more than 16 characters.");
        }

        ObsidianPlayer target = getPlayer(args[1]);
        target.setTag(args[0].replaceAll("_", " "));
        msg(target, "Your nametag is now " + target.getTag());
        msg(sender, "Set %s's nametag to " + target.getTag(), target.getRealName());

        NameTagChanger.INSTANCE.changePlayerName(target.getMirror(), target.getTag());
        return true;
    }

    @Override
    public boolean run(ObsidianPlayer player, Command cmd, String label, String[] args) throws ObsidianException {
        if (args.length < 1) {
            throw new InsufficientArgumentsException();
        }

        if (args[0].length() > 16) {
            throw new ObsidianException("Nametag cannot be more than 16 characters.");
        }

        if (args.length == 1) {
            player.setTag(args[0].replaceAll("_", " "));
            msg(player, "Your nametag is now " + player.getTag());

            NameTagChanger.INSTANCE.changePlayerName(player.getMirror(), player.getTag());
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
