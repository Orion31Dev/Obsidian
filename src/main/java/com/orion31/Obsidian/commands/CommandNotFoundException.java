package com.orion31.Obsidian.commands;

import com.orion31.Obsidian.ObsidianException;

public class CommandNotFoundException extends ObsidianException {
    
    private static final long serialVersionUID = 5791922783125164158L;
    String cmd;
    
    public CommandNotFoundException(String cmd) {
	super("Command Not Found: " + cmd);
	this.cmd = cmd;
    }
    
    public String getCmd() {
	return cmd;
    }
}

