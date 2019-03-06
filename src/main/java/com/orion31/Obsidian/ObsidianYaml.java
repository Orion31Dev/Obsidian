package com.orion31.Obsidian;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import org.bukkit.configuration.file.YamlConfiguration;

public class ObsidianYaml {
    
    private File file;
    private YamlConfiguration yaml;
    
    public ObsidianYaml(String fileName) {
	file = new File(Obsidian.getInstance().getDataFolder(), fileName);
	init();
    }
    
    public ObsidianYaml() {
	file = new File(Obsidian.getInstance().getDataFolder(), "config.yml");
	init();
    }
    
    private void init() {
	yaml = YamlConfiguration.loadConfiguration(file);
    }
    
    public void set(String path, Object value) {
	yaml.set(path, value);
	save();
    }
    
    public Object get(String path) {
	return yaml.get(path);
    }
    
    public String getString(String path) {
	return yaml.getString(path);
    }
    
    public boolean getBool(String path) {
	return yaml.getBoolean(path);
    }   

    public int getInt(String path) {
	return yaml.getInt(path);
    }
    
    public double getDouble(String path) {
	return yaml.getDouble(path);
    }
    
    public Set<String> getKeys(boolean deep) {
	return yaml.getKeys(deep);
    }
    
    public void clear() {
	String fileName = file.getName();
	file.delete();
	file = new File(Obsidian.getInstance().getDataFolder(), fileName);
	init();
    }
    
    public void save() {
	try {
	    yaml.save(file);
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
}
