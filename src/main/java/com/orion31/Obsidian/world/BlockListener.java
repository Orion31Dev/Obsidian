package com.orion31.Obsidian.world;

import static com.orion31.Obsidian.Messenger.color;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class BlockListener implements Listener {

    @EventHandler
    public void onSignPlace(SignChangeEvent e) {

	// Teleporter Signs
	
	try {
	    e.setLine(0, color(e.getLine(0)));
	    e.setLine(1, color(e.getLine(2)));
	    e.setLine(2, color(e.getLine(3)));
	    e.setLine(3, color(e.getLine(4)));
	} catch (ArrayIndexOutOfBoundsException ignored) {
	}
    }
}
