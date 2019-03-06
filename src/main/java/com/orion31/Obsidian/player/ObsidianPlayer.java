package com.orion31.Obsidian.player;

import java.net.InetSocketAddress;
import java.util.UUID;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;

import com.orion31.Obsidian.Messenger;

public class ObsidianPlayer implements IObsidianPlayer {
    
    private Player player = null;
    private PlayerSettings settings;
    
    public ObsidianPlayer(Player player) {
	this.player = player;
	settings = new PlayerSettings(player);
    }
    
    @Override
    public String getNick() {
	return Messenger.color(settings.nick + "&r");
    }

    @Override
    public String getNickRaw() {
	return settings.nick;
    }
    
    @Override
    public String getRealName() {
	return player.getName();
    }
    
    @Override
    public Location getLocation() {
	return player.getLocation();
    }
    
    @Override
    public GameMode getGamemode() {
	return settings.gamemode;
    } 

    @Override
    public PlayerInventory getInventory() {
	return player.getInventory();
    }
    
    @Override
    public boolean getCanRunCommands() {
        return settings.canRunCommands;
    }
    
    @Override
    public PlayerSettings getSettings() {
	return settings;
    }
    
    @Override
    public InetSocketAddress getIP() {
	return player.getAddress();
    }
    
    @Override
    public UUID getUUID() {
	return player.getUniqueId();
    }
    
    @Override
    public Player getMirror() {
	return player;
    }
    
    @Override
    public void clearInventory() {
	player.getInventory().clear();
    }
    
    @Override
    public void sendMessage(String msg) {
	player.sendMessage(msg);
    }    
    
    @Override
    public void setNick(String name) {
	settings.nick = name;
    }
    
    @Override
    public void setGamemode(GameMode gamemode) {
	settings.gamemode = gamemode;
    }
    
    @Override
    public void setCanRunCommands(boolean canRunCommands) {
	settings.canRunCommands = canRunCommands;
    }
    
    @Override
    public void teleport(Location location) {
	player.teleport(location);
    }
    
    @Override
    public void teleport(Entity entity) {
	player.teleport(entity);
    }
}
