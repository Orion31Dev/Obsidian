package com.orion31.Obsidian.player;

import java.net.InetSocketAddress;
import java.util.UUID;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public interface IObsidianPlayer {
    public String getNick();
    
    public String getNickRaw();
    
    public String getRealName();
    
    public GameMode getGamemode();
    
    public Inventory getInventory();
    
    public Location getLocation();
    
    public PlayerSettings getSettings();
    
    public boolean getCanRunCommands();

    public UUID getUUID();
    
    public InetSocketAddress getIP();
    
    public Player getMirror();
    
    public void clearInventory();
    
    public void saveInventory();
    
    public void restoreInventory();

    public void sendMessage(String msg);

    public void setNick(String nick);
    
    public void setGamemode(GameMode mode);

    public void setCanRunCommands(boolean canRunCommands);

    public void teleport(Location location);
    
    public void teleport(Entity entity);
}
