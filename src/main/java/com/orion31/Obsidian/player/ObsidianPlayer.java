package com.orion31.Obsidian.player;

import java.net.InetSocketAddress;
import java.util.UUID;

import com.bringholm.nametagchanger.NameTagChanger;
import com.orion31.Obsidian.Obsidian;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import com.orion31.Obsidian.Messenger;
import com.orion31.Obsidian.player.games.Game;
import com.orion31.Obsidian.player.games.ObsidianGame;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import static com.orion31.Obsidian.Messenger.color;

public class ObsidianPlayer {

    private Player player = null;
    private PlayerSettings settings;
    private ObsidianGame game;
    private Game gameType = Game.NONE;
    private String nick;
    private String tag;

    private ItemStack[] _inventoryCache;
    private PlayerSettings _settingsCache;

    public ObsidianPlayer(Player player) {
        this.player = player;
        nick = player.getName();
        tag = nick;

        _inventoryCache = player.getInventory().getContents();
        settings = new PlayerSettings(player);

        Scoreboard s = Obsidian.getInstance().getServer().getScoreboardManager().getMainScoreboard();
        Team team = s.getTeam(player.getName());
        if (team == null) team = s.registerNewTeam(player.getName());

        team.addEntry(player.getName());
    }

    public String getNick() {
        if (nick.equals("")) return getRealName();

        return color("&r" + nick + "&r");
    }

    public String getTag() { return color(tag + "&r"); }

    public String getNickRaw() {
        return nick;
    }

    public String getTagRaw() {
        return tag;
    }

    public String getRealName() {
        return player.getName();
    }

    public Location getLocation() {
        return player.getLocation();
    }

    public GameMode getGamemode() {
        return settings.gamemode;
    }

    public PlayerInventory getInventory() {
        return player.getInventory();
    }

    public boolean getCanRunCommands() {
        return settings.canRunCommands;
    }

    public PlayerSettings getSettings() {
        return settings;
    }

    public InetSocketAddress getIP() {
        return player.getAddress();
    }

    public UUID getUUID() {
        return player.getUniqueId();
    }

    public Player getMirror() {
        return player;
    }

    public void playSound(Sound sound, float volume, float pitch) {
        player.playSound(player.getLocation(), sound, volume, pitch);
    }

    public void playSound(Sound sound, float volume) {
        player.playSound(player.getLocation(), sound, volume, 1f);
    }

    public void playSound(Sound sound) {
        player.playSound(player.getLocation(), sound, 1f, 1f);
    }

    public void clearInventory() {
        player.getInventory().clear();
    }

    public void saveInventory() {
        _inventoryCache = player.getInventory().getContents();
    }

    public void restoreInventory() {
        player.getInventory().setContents(_inventoryCache);
    }

    public void setGame(ObsidianGame game) {
        this.game = game;
        game.setPlayer(this);
        gameType = game.getGameType();
        _settingsCache = settings;
        settings = game.getSettings();
        saveInventory();
        player.getInventory().setContents(game.getInventory().getContents());
    }

    public void endGame() {
        settings = _settingsCache;
        gameType = Game.NONE;
        restoreInventory();
    }

    public ObsidianGame getGame() {
        return game;
    }

    public Game getGameType() {
        return gameType;
    }

    public void sendMessage(String msg) {
        player.sendMessage(msg);
    }

    public void setNick(String name) {
        nick = name;
    }

    public void setTag(String name) {
        tag = name;
    }

    public void setGamemode(GameMode gamemode) {
        settings.gamemode = gamemode;
    }

    public void setCanRunCommands(boolean canRunCommands) {
        settings.canRunCommands = canRunCommands;
    }

    public void teleport(Location location) {
        player.teleport(location);
    }

    public void teleport(Entity entity) {
        player.teleport(entity);
    }

    public boolean isCrouching() {
        return player.isSneaking();
    }
}
