package com.orion31.Obsidian.player;

import java.net.InetSocketAddress;
import java.util.UUID;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import com.orion31.Obsidian.Messenger;
import com.orion31.Obsidian.player.games.Game;
import com.orion31.Obsidian.player.games.ObsidianGame;

public class ObsidianPlayer implements IObsidianPlayer {
    
    private Player player = null;
    private PlayerSettings settings;
    private Game game;
    private String nick;
    
    private ItemStack[] _inventoryCache;
    private PlayerSettings _settingsCache;
    
    public ObsidianPlayer(Player player) {
	this.player = player;
	nick = player.getName();
	_inventoryCache = player.getInventory().getContents();
	settings = new PlayerSettings(player);
    }
    
    @Override
    public String getNick() {
	return Messenger.color(nick + "&r");
    }

    @Override
    public String getNickRaw() {
	return nick;
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
    public void saveInventory() {
	_inventoryCache = player.getInventory().getContents();
    }
    
    @Override
    public void restoreInventory() {
	player.getInventory().setContents(_inventoryCache);
    }
    
    public void setGame(ObsidianGame game) {
	_settingsCache = settings;
	settings = game.getSettings();
	saveInventory();
	player.getInventory().setContents(game.getInventory(this).getContents());
    }
    
    public void endGame() {
	settings = _settingsCache;
	game = Game.NONE;
	restoreInventory();
    }
    
    public Game getGame() {
	return game;
    }
    
    @Override
    public void sendMessage(String msg) {
	player.sendMessage(msg);
    }    
    
    @Override
    public void setNick(String name) {
	nick = name;
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
