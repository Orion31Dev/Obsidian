package com.orion31.Obsidian.commands;

import com.orion31.Obsidian.ObsidianException;

public class PlayerOnlyException extends ObsidianException {
    private static final long serialVersionUID = 5791922783125164158L;

    public PlayerOnlyException() {
	super("Only Players may execute this command.");
    }
}
