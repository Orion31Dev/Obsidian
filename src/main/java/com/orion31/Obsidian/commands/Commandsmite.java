package com.orion31.Obsidian.commands;

import com.orion31.Obsidian.ObsidianException;
import com.orion31.Obsidian.player.ObsidianPlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

public class Commandsmite extends ObsidianCommand {

    public Commandsmite() {
        super("smite");
    }

    @Override
    public boolean run(CommandSender sender, Command cmd, String label, String[] args) throws ObsidianException {
        if (args.length < 1) {
            throw new InsufficientArgumentsException();
        }

        ObsidianPlayer target = getPlayer(args[0]);
        Player p = target.getMirror();

        msgColor(sender, "&c" + target.getNick() + " &rshall feel the wrath of Zeus.");
        ghost(target, "You have angered the Gods.");

        p.getWorld().strikeLightning(p.getLocation());

        return true;
    }

    @Override
    public boolean run(ObsidianPlayer player, Command cmd, String label, String[] args) throws ObsidianException {
        return run(player.getMirror(), cmd, label, args);
    }

    @Override
    public List<String> tabComplete(CommandSender sender, Command command, String alias, String[] args) throws ObsidianException {
        if (args.length == 1) return getPlayers();
        return Collections.emptyList();
    }
}
