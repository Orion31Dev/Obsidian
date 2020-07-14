package com.orion31.Obsidian.player;

import com.bringholm.nametagchanger.NameTagChanger;
import com.orion31.Obsidian.Messenger;
import org.bukkit.GameMode;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

import com.orion31.Obsidian.Obsidian;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import static com.orion31.Obsidian.Messenger.color;

public class PlayerUpdater implements Runnable {

    @Override
    public void run() {
        for (ObsidianPlayer oPlayer : Obsidian.getObsidianPlayers()) {
            Player player = oPlayer.getMirror();

            player.setDisplayName(oPlayer.getNick());
            player.setPlayerListName(oPlayer.getNick());

            player.setGameMode(oPlayer.getGamemode());

            Scoreboard s = Obsidian.getInstance().getServer().getScoreboardManager().getMainScoreboard();
            Team team = s.getTeam(player.getName());
            if (team == null) team = s.registerNewTeam(player.getName());

            if (oPlayer.getGamemode() == GameMode.SURVIVAL || oPlayer.getGamemode() == GameMode.ADVENTURE) {
                String su = " &a";

                int h = (int) Math.floor(player.getHealth() / player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue() * 10);

                for (int i = 0; i < h; i++) su += "|";

                su += "&7";

                for (int i = 0; i < 10 - h; i++) su += "|";


                team.setSuffix(color(su));
            } else {
                team.setSuffix("");
            }
        }
    }
}
