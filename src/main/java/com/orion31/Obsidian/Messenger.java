package com.orion31.Obsidian;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.orion31.Obsidian.player.ObsidianPlayer;

public class Messenger {

    public static String tag = "&5&lObsidian >>&r ";
    public static String consoleTag = "[Obsidian]: ";

    public static void msgColor(CommandSender msgTo, String msg, Object... objects) {
	msgColor(msgTo, String.format(msg, objects));
    }

    public static void msgColor(CommandSender msgTo, String msg) {
	ghost(msgTo, color(tag + msg));
    }

    public static void msgColor(ObsidianPlayer msgTo, String msg, Object... objects) {
	msgColor(msgTo, String.format(msg, objects));
    }

    public static void msgColor(ObsidianPlayer msgTo, String msg) {
	ghost(msgTo, color(tag + msg));
    }

    public static void msg(CommandSender msgTo, String msg, Object... objects) {
	msg(msgTo, String.format(msg, objects));
    }

    public static void msg(CommandSender msgTo, String msg) {
	ghost(msgTo, color(tag) + msg);
    }

    public static void msg(ObsidianPlayer msgTo, String msg, Object... objects) {
	msg(msgTo, String.format(msg, objects));
    }

    public static void msg(ObsidianPlayer msgTo, String msg) {
	ghost(msgTo, color(tag) + msg);
    }

    public static void ghostColor(CommandSender msgTo, String msg, Object... objects) {
	ghostColor(msgTo, String.format(msg, objects));
    }

    public static void ghostColor(CommandSender msgTo, String msg) {
	ghost(msgTo, color(msg));
    }

    public static void ghostColor(ObsidianPlayer msgTo, String msg, Object... objects) {
	ghostColor(msgTo, String.format(msg, objects));
    }

    public static void ghostColor(ObsidianPlayer msgTo, String msg) {
	ghost(msgTo, color(msg));
    }

    public static void ghost(CommandSender msgTo, String msg, Object... objects) {
	ghost(msgTo, String.format(msg, objects));
    }

    public static void ghost(CommandSender msgTo, String msg) {
	msgTo.sendMessage(msg);
    }

    public static void ghost(ObsidianPlayer msgTo, String msg, Object... objects) {
	ghost(msgTo, String.format(msg, objects));
    }

    public static void ghost(ObsidianPlayer msgTo, String msg) {
	msgTo.sendMessage(msg);
    }

    public static void ghostAllColor(String msg) {
	ghostAll(color(msg));
    }

    public static void ghostAll(String msg) {
	Bukkit.broadcastMessage(msg);
    }

    public static void msgAllColor(String msg) {
	ghostAllColor(tag + msg);
    }

    public static void msgAll(String msg) {
	ghostAll(color(tag) + msg);
	
    }

    public static void console(String msg, Object... objects) {
	msg = String.format(msg, objects);
	console(msg);
    }

    public static void console(String msg) {
	Bukkit.getConsoleSender().sendMessage(consoleTag + msg);
    }

    public static String color(String color) {
	return ChatColor.translateAlternateColorCodes('&', color);
    }
    
    public static String stripColor(String noColor) {
	return ChatColor.stripColor(noColor);
    }
    
    public static String stripColorCodes(String noCode) {
	return stripColor(color(noCode));
    }

    public static void motd(Player player) {
	ghostColor(player, "&5&lPowered by Obsidian. &5/obsidian help");
	msgColor(player, "Welcome to CTCMS!");
    }
}
