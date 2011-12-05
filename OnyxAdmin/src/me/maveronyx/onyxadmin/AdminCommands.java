package me.maveronyx.onyxadmin;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
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


	public AdminCommands(){
	
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
				player.sendMessage("Warning " + totalWarnings + "/3");
			} else if (warnedPlayers.get(playerName) == 3) {
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

			Bukkit.getServer().broadcast(messages, "server.messages.ban");
		}
	}

}
