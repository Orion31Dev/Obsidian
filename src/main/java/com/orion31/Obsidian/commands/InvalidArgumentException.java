package com.orion31.Obsidian.commands;

import com.orion31.Obsidian.ObsidianException;

public class InvalidArgumentException extends ObsidianException {

    private static final long serialVersionUID = 6400042262003235262L;

    public InvalidArgumentException(String arg) {
	super("Invalid Argument" + arg);
    }

}
