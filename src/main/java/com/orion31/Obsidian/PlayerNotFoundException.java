package com.orion31.Obsidian;

public class PlayerNotFoundException extends ObsidianException {
    private static final long serialVersionUID = 5791922783125164158L;

    String name;
    public PlayerNotFoundException(String name) {
	super("Player not found: " + name);
	this.name = name;
    }
    
    public String getName() {
	return name;
    }
}
