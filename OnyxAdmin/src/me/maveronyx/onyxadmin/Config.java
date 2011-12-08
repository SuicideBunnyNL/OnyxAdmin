package me.maveronyx.onyxadmin;

import org.bukkit.configuration.file.FileConfigurationOptions;


public class Config {

	OnyxAdmin plugin;

	FileConfigurationOptions config;
	
	public Config(OnyxAdmin instance){
		plugin = instance;
		initConfig();
	}
	
	private void initConfig(){
		
		addDefault("messages.enabled", true);
		addDefault("messages.player.warn", "Warning");
		addDefault("messages.player.kick", "Kicked player");
		addDefault("messages.player.ban", "Banned player");
		addDefault("messages.player.unban", "Unbanned player");
		addDefault("messages.player.frozen", "Froze player");
		addDefault("messages.player.unfrozen", "Unfroze player");
		addDefault("messages.player.mute", "Muted player");
		addDefault("messages.player.unmute", "Unmuted player");
		addDefault("messages.admin.player.offline", "Cannot perform that action. Player is offline.");
		
		plugin.getConfig().options().copyDefaults(true);
		plugin.saveConfig();
	}
	
	public void reloadConfig(){
		plugin.reloadConfig();
	}
	
	public void saveConfig(){
		plugin.saveConfig();
	}
	
	private void addDefault(String path, Object value){
		plugin.getConfig().addDefault(path, value);
	}
	
	
}