package me.maveronyx.onyxadmin;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
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

	private HashMap<String, Integer> warnedPlayers;
	OnyxAdmin plugin;
	FileConfiguration config;

	/**
	 * Initialize the class
	 * 
	 * @param config
	 */
	public AdminCommands(OnyxAdmin instance, FileConfiguration config) {
		this.config = config;
		plugin = instance;
		warnedPlayers = instance.warnedPlayers;
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
				if (config.getBoolean("messages.enabled") && message != "")
					player.sendMessage(message + " " + "1/3 times");

			} else if (warnedPlayers.containsKey(playerName) && (warnedPlayers.get(playerName) < 3)) {
				int totalWarnings = warnedPlayers.get(playerName);
				totalWarnings++;
				warnedPlayers.put(playerName, totalWarnings);
				if (config.getBoolean("messages.enabled"))
					player.sendMessage(message + " " + totalWarnings + "/3 times");

				if (totalWarnings >= 3) {
					if (config.getBoolean("messages.enabled") && message != "")
						Bukkit.getServer().broadcast(config.getString("messages.admin.player.maxwarnings") + " " + player.getDisplayName(), "onyxadmin.warn");
				}
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
				Bukkit.getServer().broadcast(config.getString("messages.admin.player.kick") + " " + player.getDisplayName(), "onyxadmin.kick");
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
				Bukkit.getServer().broadcast(config.getString("messages.admin.player.ban") + " " + player.getDisplayName(), "onyxadmin.ban");
		}
	}
	
	/**
	 * Unban a player
	 * 
	 * @param player
	 * @param sender
	 */
	public void unbanPlayer(OfflinePlayer player, CommandSender sender) {

		if (player.getName() != sender.getName() && player.isBanned()) {
			player.setBanned(false);
			if (config.getBoolean("messages.enabled"))
				Bukkit.getServer().broadcast(config.getString("messages.admin.player.unban") + " " + player.getName(), "onyxadmin.unban");
		}
	}

	/**
	 * Freeze a player
	 * 
	 * @param player
	 * @param sender
	 * @param message
	 */
	public void freezePlayer(Player player, CommandSender sender, String message) {
		if (player.isOnline() && player.getName() != sender.getName()) {
			if (!plugin.frozenPlayers.contains(player.getDisplayName())) {
				plugin.frozenPlayers.add(player.getDisplayName());

				if (config.getBoolean("messages.enabled"))
					Bukkit.getServer().broadcast(config.getString("messages.admin.player.frozen") + " " + player.getDisplayName(), "onyxadmin.freeze");
			} else if (plugin.frozenPlayers.contains(player.getDisplayName())) {
				plugin.frozenPlayers.remove(player.getDisplayName());

				if (config.getBoolean("messages.enabled"))
					Bukkit.getServer().broadcast(config.getString("messages.admin.player.unfrozen") + " " + player.getDisplayName(), "onyxadmin.freeze");
			}
		}
	}

	/**
	 * Change the weather type
	 * 
	 * @param world
	 * @param type
	 * @param message
	 */
	public void setWeather(World world, String type, String message) {

		if (type.equalsIgnoreCase("rain")) {
			world.setStorm(true);

			if (config.getBoolean("messages.enabled"))
				Bukkit.getServer().broadcast(config.getString("messages.admin.world.weather") + " " + type.toUpperCase(), "onyxadmin.weather");
		} else if (type.equalsIgnoreCase("storm")) {
			world.setStorm(true);
			world.setThundering(true);

			if (config.getBoolean("messages.enabled"))
				Bukkit.getServer().broadcast(config.getString("messages.admin.world.weather") + " " + type.toUpperCase(), "onyxadmin.weather");
		} else if (type.equalsIgnoreCase("sunny")) {
			world.setStorm(false);

			if (config.getBoolean("messages.enabled"))
				Bukkit.getServer().broadcast(config.getString("messages.admin.world.weather") + " " + type.toUpperCase(), "onyxadmin.weather");
		}

	}
	
	
	/**
	 * Change the time
	 * 
	 * @param world
	 * @param type
	 * @param message
	 */
	public void setTime(World world, String type, String message) {

		if (type.equalsIgnoreCase("day")) {
			world.setTime(0);

			if (config.getBoolean("messages.enabled"))
				Bukkit.getServer().broadcast(config.getString("messages.admin.world.time") + " " + type.toUpperCase(), "onyxadmin.weather");
		} else if (type.equalsIgnoreCase("night")) {
			world.setTime(12000);

			if (config.getBoolean("messages.enabled"))
				Bukkit.getServer().broadcast(config.getString("messages.admin.world.time") + " " + type.toUpperCase(), "onyxadmin.weather");
		} 

	}

}
