package me.maveronyx.onyxadmin;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

/**
 * Class for all the admin commands
 * 
 * @author Jimmy
 * 
 */
public class AdminCommands {

	private String messages = null;
	private HashMap<String, Integer> warnedPlayers = new HashMap<String, Integer>();
	FileConfiguration config;

	/**
	 * Initialize the class
	 * @param config
	 */
	public AdminCommands(FileConfiguration config) {
		this.config = config;
	}

	/**
	 * Adds to the warning counter of a specific player. If limit is reached, a
	 * message is prompted to users with the proper permissions.
	 * 
	 * @param player
	 * @param sender
	 * @param message
	 */
	public void warnPlayer(Player player, CommandSender sender, String message) {
		if (player.isOnline() && player.getName() != sender.getName()) {

			String playerName = player.getName();

			if (!warnedPlayers.containsKey(playerName)) {
				warnedPlayers.put(playerName, 1);
			} else if (warnedPlayers.containsKey(playerName)
					&& (warnedPlayers.get(playerName) < 3)) {
				Integer totalWarnings = warnedPlayers.get(playerName);
				totalWarnings++;
				warnedPlayers.put(playerName, totalWarnings);
				if (config.getBoolean("messages.enabled"))
					player.sendMessage("Warning " + totalWarnings + "/3");
			} else if (warnedPlayers.get(playerName) == 3) {
				if (config.getBoolean("messages.enabled"))
					Bukkit.getServer().broadcast(messages,
							"server.messages.warning.max");
			}
		}
	}

	/**
	 * Kick a player
	 * 
	 * @param player
	 * @param sender
	 * @param message
	 */
	public void kickPlayer(Player player, CommandSender sender, String message) {
		if (player.isOnline() && player.getName() != sender.getName()) {
			player.kickPlayer(message);
			if (config.getBoolean("messages.enabled"))
				Bukkit.getServer().broadcast(messages, "server.messages.kick");
		}
	}

	/**
	 * Ban a player
	 * 
	 * @param player
	 * @param sender
	 * @param message
	 */
	public void banPlayer(Player player, CommandSender sender, String message) {

		if (player.isOnline() && player.getName() != sender.getName()) {
			player.setBanned(true);
			player.kickPlayer(message);
			if (config.getBoolean("messages.enabled"))
				Bukkit.getServer().broadcast(messages, "server.messages.ban");
		}
	}

	
	public void freezePlayer(Player player, CommandSender sender, String message){
		
	}
	
	public void loopWarn(Player[] targets, CommandSender sender, String message){
		for(Player player : targets){
			warnPlayer(player, sender, message);
		}
	}
	
	public void loopKick(Player[] targets, CommandSender sender, String message){
		for(Player player : targets){
			kickPlayer(player, sender, message);
		}
	}
	
	public void loopBan(Player[] targets, CommandSender sender, String message){
		for(Player player : targets){
			banPlayer(player, sender, message);
		}
	}
	
	public void loopFreeze(Player[] targets, CommandSender sender, String message){
		for(Player player : targets){
			freezePlayer(player, sender, message);
		}
	}
	
}
