package com.orion31.Obsidian.commands;

import java.util.Collections;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.orion31.Obsidian.Obsidian;
import com.orion31.Obsidian.ObsidianException;
import com.orion31.Obsidian.player.ObsidianPlayer;

public class Commandblockcommands extends ObsidianCommand {

    public Commandblockcommands() {
	super("blockcommands");
    }
    
    @Override
    public boolean run(CommandSender sender, Command cmd, String label, String[] args) throws ObsidianException {
        if (args.length < 1) throw new InsufficientArgumentsException();
        ObsidianPlayer target = getPlayer(args[0]);
        if (target.getCanRunCommands()) {
            target.setCanRunCommands(false);
            msg(sender, target.getNick() + " can no longer run commands.");
            msg(target, "You can no longer run commands. Contact admins if you believe this is an error.");
            return true;
        }
        
        target.setCanRunCommands(true);
        msg(sender, target.getNick() + " can now run commands.");
        msg(target, "You may run commands again");
        return true;
        
    }
    
    @Override
    public List<String> tabComplete(CommandSender sender, Command command, String alias, String[] args)
            throws ObsidianException {
        if (args.length == 1) return Obsidian.getPlayers();
        return Collections.emptyList();
    }
}
