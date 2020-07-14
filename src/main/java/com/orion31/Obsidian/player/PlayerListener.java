package com.orion31.Obsidian.player;

import com.bringholm.nametagchanger.NameTagChanger;
import com.orion31.Obsidian.*;
import com.orion31.Obsidian.player.games.Game;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class PlayerListener extends Messenger implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        try {
            ObsidianPlayer player = Obsidian.getOfflinePlayer(e.getPlayer().getName());

            ObsidianYaml yml = new ObsidianYaml("players.yml");
            player.setNick(yml.getString(player.getRealName() + ".settings.nick"));
            player.setTag(yml.getString(player.getRealName() + ".settings.tag"));
            player.setCanRunCommands(yml.getBool(player.getRealName() + ".settings.canRunCommands"));

            Obsidian.addPlayer(player);
            NameTagChanger.INSTANCE.changePlayerName(e.getPlayer(), player.getTag());

            motd(e.getPlayer());
            e.setJoinMessage(color(player.getNick() + "&r&a joined."));

            return;
        } catch (Exception ignored) {
        }


        Obsidian.addPlayer(new ObsidianPlayer(e.getPlayer()));
        motd(e.getPlayer());
        e.setJoinMessage(color(e.getPlayer().getDisplayName() + "&r&a joined."));
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        // PowerTools
        try {
            ObsidianPlayer player = Obsidian.getOfflinePlayer(e.getPlayer().getName());
            ItemStack mh = player.getInventory().getItemInMainHand();

            if (!mh.hasItemMeta() || !mh.getItemMeta().hasLore()) return;
            List<String> lore = mh.getItemMeta().getLore();
            if (lore.size() > 1 && stripColor(lore.get(0)).equals("PowerTool")) {
                if (!player.getCanRunCommands()) return;
                int id = Integer.parseInt(stripColor(lore.get(1)));
                if (PowerToolHandler.powerTools.containsKey(id)) {
                    Bukkit.getServer().dispatchCommand(player.getMirror(), PowerToolHandler.powerTools.get(id).cmd);
                }
            }
        } catch (ObsidianException ignored) {
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        e.setQuitMessage(color(e.getPlayer().getDisplayName() + "&r&4 left."));

        try {
            ObsidianPlayer player = Obsidian.getPlayer(e.getPlayer().getName());
            ObsidianYaml yml = new ObsidianYaml("players.yml");
            yml.set(player.getRealName() + ".ip", player.getIP().getAddress().toString());
            yml.set(player.getRealName() + ".uuid", player.getUUID().toString());
            yml.set(player.getRealName() + ".settings.nick", player.getNickRaw());
            yml.set(player.getRealName() + ".settings.tag", player.getTagRaw());
            yml.set(player.getRealName() + ".settings.canRunCommands", player.getCanRunCommands());
        } catch (PlayerNotFoundException ignored) {
        }

        try {
            Obsidian.removePlayer(Obsidian.getPlayer(e.getPlayer().getUniqueId()));
        } catch (PlayerNotFoundException ignored) {
        }
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) throws PlayerNotFoundException {
        if (e.isCancelled())
            return;
        if (Obsidian.getPlayer(e.getPlayer().getUniqueId()).getGameType() == Game.CREATEPARKOURCOURSE)
            return; // Game handles chat itself;
        e.setCancelled(true);
        ghostAllColor(e.getPlayer().getDisplayName() + "&7 " + e.getMessage());
    }

    @EventHandler
    public void onPlayerCommand(PlayerCommandPreprocessEvent e) {
        try {
            if (Obsidian.getPlayer(e.getPlayer().getUniqueId()).getCanRunCommands())
                return;
            msg(e.getPlayer(), "You cannot run commands.");
            e.setCancelled(true);
        } catch (PlayerNotFoundException ignored) {
        }
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent e) throws PlayerNotFoundException {
        if (Teleporter.waypointExists("spawn")
                && Obsidian.getPlayer(e.getPlayer().getUniqueId()).getGameType() == Game.NONE)
            e.setRespawnLocation(Teleporter.getWaypoint("spawn"));
    }

    @EventHandler
    public void onServerPing(ServerListPingEvent e) {
        try {
            ObsidianYaml yml = new ObsidianYaml("players.yml");
            for (String name : yml.getKeys(false)) {
                if (yml.getString(name + ".ip").equals(e.getAddress().toString())) {
                    e.setMotd(color("&3&lC&a&lT&3&lC&a&lM&3&lS" + "\nCome join us, &a&l" + name));
                    return;
                }
            }
        } catch (Exception ignored) {
        }

        e.setMotd(color("&3&lC&a&lT&3&lC&a&lM&3&lS"));
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent e) throws PlayerNotFoundException {
        Entity ent = e.getEntity();
        if (!(ent instanceof Player)) return;

        EntityDamageEvent.DamageCause c = ent.getLastDamageCause().getCause();

        ObsidianPlayer p = Obsidian.getPlayer(ent.getName());

        switch (c) {
            case ENTITY_EXPLOSION:
            case BLOCK_EXPLOSION:
                ghostAllColor("&4%s blew up lol.", p.getNick());
                break;
            case FIRE:
                ghostAllColor("&4%s burned to death. Ouch!", p.getNick());
                break;
            case MAGIC:
                ghostAllColor("For &4%s's next trick, they will disappear!", p.getNick());
                break;
            case WITHER:
                ghostAllColor("&4%s withered away. ᵇʸᵉ ᵇʸᵉ", p.getNick());
                break;
            case STARVATION:
                ghostAllColor("&4%s starved. Imagine.", p.getNick());
                break;

        }

    }
}
