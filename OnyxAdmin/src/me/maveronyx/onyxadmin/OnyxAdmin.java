package me.maveronyx.onyxadmin;

import java.util.HashSet;
import java.util.logging.Logger;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class OnyxAdmin extends JavaPlugin {

	Logger log = Logger.getLogger("Minecraft");
	Config conf;

	OnyxAdminText chat;
	boolean messagesEnabled = true;

	HashSet<String> frozen = new HashSet<String>();
	HashSet<String> burning = new HashSet<String>();

	OnyxAdminCommandExecutor commandExecutor;
	private final OnyxAdminPlayerListener playerListener = new OnyxAdminPlayerListener(
			this);
	private final OnyxAdminBlockListener blockListener = new OnyxAdminBlockListener(
			this);

	@Override
	public void onEnable() {

		// Get the config
		conf = new Config(this);

		log.info("[OnyxAdmin] Starting...");
		log.info("[OnyxAdmin] Registering events...");

		// Get the plugin manager
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvent(Event.Type.PLAYER_MOVE, playerListener,
				Event.Priority.Normal, this);

		commandExecutor = new OnyxAdminCommandExecutor(this);
		getCommand("warn").setExecutor(commandExecutor);
		getCommand("kick").setExecutor(commandExecutor);
		getCommand("ban").setExecutor(commandExecutor);

		log.info("[OnyxAdmin] Done!");
	}

	@Override
	public void onDisable() {

		// Check if messages should be broadcasted
		// if(messagesEnabled){
		// chat.broadcastServer("Plugin shutting down. Bye guys");
		// }
		conf.saveConfig();

		log.info("[OnyxAdmin] Shutting down...");
		log.info("[OnyxAdmin] Done!");
	}

}
