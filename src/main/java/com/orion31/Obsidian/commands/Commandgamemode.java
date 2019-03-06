package com.orion31.Obsidian.commands;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.orion31.Obsidian.ObsidianException;
import com.orion31.Obsidian.player.ObsidianPlayer;

public class Commandgamemode extends ObsidianCommand {

    public Commandgamemode() {
	super("gamemode");
    }

    public boolean run(CommandSender sender, Command cmd, String label, String[] args) throws ObsidianException {
	if (args.length < 2)
	    throw new InsufficientArgumentsException();

	ObsidianPlayer target = getPlayer(args[1]);
	GameMode gamemode = parseGamemode(args[0]);
	target.setGamemode(gamemode);

	msg(sender, target.getNick() + " gamemode is now " + getFriendlyName(gamemode));
	msg(target, "Your gamemode is now " + getFriendlyName(gamemode));
	return true;
    }

    public boolean run(ObsidianPlayer player, Command cmd, String label, String[] args) throws ObsidianException {
	if (args.length == 0) throw new InsufficientArgumentsException();
	if (args.length > 1) return run(player.getMirror(), cmd, label, args);
	
	GameMode gamemode = parseGamemode(args[0]);
	player.setGamemode(gamemode);

	msg(player, "Your gamemode is now " + getFriendlyName(gamemode));
	return true;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, Command command, String alias, String[] args) throws InvalidArgumentException {
        if (args.length == 1) {
            return Arrays.asList("survival", "creative", "adventure", "spectator");
        } else if (args.length == 2) {
            return getPlayers();
        }
        return Collections.emptyList();
    }
    
    public GameMode parseGamemode(String gamemode) throws InvalidArgumentException {

	switch (gamemode.toLowerCase(Locale.ENGLISH)) {
	case "0":
	case "s":
	case "sur":
	case "surv":
	case "survive":
	case "survival":
	    return GameMode.SURVIVAL;
	case "1":
	case "c":
	case "cr":
	case "cre":
	case "crea":
	case "creat":
	case "create":
	case "creative":
	    return GameMode.CREATIVE;
	case "2":
	case "a":
	case "adv":
	case "adven":
	case "adventure":
	    return GameMode.ADVENTURE;
	case "3":
	case "sp":
	case "spe":
	case "spec":
	case "spect":
	case "spectate":
	case "spectator":
	    return GameMode.SPECTATOR;
	default:
	    throw new InvalidArgumentException(gamemode);
	}
    }

    public String getFriendlyName(GameMode gamemode) {
	switch (gamemode) {
	case ADVENTURE:
	    return "adventure";
	case CREATIVE:
	    return "creative";
	case SPECTATOR:
	    return "spectator";
	case SURVIVAL:
	    return "survival";
	default:
	    return "";
	}
    }

}
