package me.maveronyx.onyxadmin;

import java.util.HashMap;
import java.util.logging.Logger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import com.mysql.jdbc.log.Log;

public class OnyxAdminCommandExecutor implements CommandExecutor {

	HashMap<String, Integer> commandMap = new HashMap<String, Integer>();
	String cmdPrefix = "onyx";
	OnyxAdmin plugin;
	FileConfiguration config = plugin.getConfig();
	Logger log = Logger.getLogger("Minecraft");
	
	public OnyxAdminCommandExecutor(OnyxAdmin instance) {

		plugin = instance;

		// Register all commands and map them
		commandMap.put("warn", 1);
		commandMap.put("kick", 2);
		commandMap.put("ban", 3);
//		commandMap.put("freezeall", 4);
//		commandMap.put("burnall", 5);
//		commandMap.put("explodeall", 6);
//		commandMap.put("freezeplayer", 7);
//		commandMap.put("burnplayer", 8);
//		commandMap.put("explodeplayer", 9);
//		commandMap.put("give", 10);
//		commandMap.put("take", 11);
//		commandMap.put("toggletnt", 12);
//		commandMap.put("removetnt", 13);
//		commandMap.put("tp", 14);
//		commandMap.put("tpall", 15);
//		commandMap.put("togglebuild", 16);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {

		boolean cmdSuccess = false;
		if (sender instanceof Player) {

			AdminCommands adminCommands = new AdminCommands();

			String cmd = command.getName().toLowerCase();

			if (commandMap.containsKey(cmd)) {
				Integer commandId = commandMap.get(cmd);
				
				log.info("Command: " + commandId);
				
				switch (commandId) {
				case 1:

					
					if (args[0] != null) {

						
						
						Player target = plugin.getServer().getPlayer(args[0]);

						if (canBePunished(target)) {
							adminCommands.warnPlayer(target, sender,
									config.getString("messages.player.warn"));
						} else {
							sender.sendMessage("That player cannot be warned");
						}
					} else {
						cmdSuccess = false;
					}
					cmdSuccess = true;
					break;

				case 2:
					if (args[0] != null) {

						Player target = plugin.getServer().getPlayer(args[0]);

						if (canBePunished(target)) {
							adminCommands.kickPlayer(target, sender,
									config.getString("messages.player.kick"));
						} else {
							sender.sendMessage("That player cannot be kicked");
						}
					} else {
						cmdSuccess = false;
					}
					cmdSuccess = true;
					break;
					
					
				case 3:
					if (args[0] != null) {

						Player target = plugin.getServer().getPlayer(args[0]);

						if (canBePunished(target)) {
							adminCommands.banPlayer(target, sender,
									config.getString("messages.player.ban"));
						} else {
							sender.sendMessage("That player cannot be banned");
						}
					} else {
						cmdSuccess = false;
					}
					cmdSuccess = true;
					break;
				}

			}

		}

		return cmdSuccess;
	}

	private boolean canBePunished(Player player) {
		if (player.isOnline() && !player.isBanned() && !player.isOp()) {
			return true;
		} else {
			return false;
		}
	}

}
