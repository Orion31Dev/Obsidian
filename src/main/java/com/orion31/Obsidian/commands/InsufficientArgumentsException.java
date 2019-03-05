package com.orion31.Obsidian.commands;

import com.orion31.Obsidian.ObsidianException;

public class InsufficientArgumentsException extends ObsidianException {
    private static final long serialVersionUID = -3264945767509036413L;
    
    public InsufficientArgumentsException() {
	super("Insufficient Arguments.");
    }

}
