package me.maveronyx.onyxadmin;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class OnyxAdminCommandExecutor implements CommandExecutor {

	HashMap<String, Integer> commandMap = new HashMap<String, Integer>();
	AdminCommands adminCommands;
	OnyxAdmin plugin;
	FileConfiguration config;
	Logger log = Logger.getLogger("Minecraft");

	public OnyxAdminCommandExecutor(OnyxAdmin instance) {

		plugin = instance;

		config = instance.getConfig();

		// Register all commands and map them
		commandMap.put("warn", 1);
		commandMap.put("kick", 2);
		commandMap.put("ban", 3);
		commandMap.put("freeze", 4);
		commandMap.put("setWeather", 5);
		commandMap.put("setTime", 6);
		commandMap.put("unban", 7);
		// commandMap.put("burnplayer", 8);
		// commandMap.put("explodeplayer", 9);
		// commandMap.put("give", 10);
		// commandMap.put("take", 11);
		// commandMap.put("toggletnt", 12);
		// commandMap.put("removetnt", 13);
		// commandMap.put("tp", 14);
		// commandMap.put("tpall", 15);
		// commandMap.put("togglebuild", 16);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (sender instanceof Player) {

			adminCommands = new AdminCommands(plugin, config);
			
			String cmd = command.getName().toLowerCase();

			log.info("[OnyxAdmin] Command " + cmd + " called");

			if (commandMap.containsKey(cmd)) {
				Integer commandId = commandMap.get(cmd);

				switch (commandId) {
				case 1:

					if (args.length > 0) {

						if (args[0].equalsIgnoreCase("*")) {
							if (sender.hasPermission("onyxadmin.warn.all")) {

								// All online players
								Player[] targets = plugin.getServer().getOnlinePlayers();

								plugin.log.info("Players online: " + targets + " " + targets.length);
								
								if (targets.length > 0) {
									for (Player target : targets) {
										
										if (canBePunished(target)) {
											adminCommands.warnPlayer(target, sender, config.getString("messages.player.warned"));
										} else {
											log.info("No targets found");
											if (config.getBoolean("messages.enabled")) {
												sender.sendMessage(config.getString("messages.admin.player.offline"));
											}
										}
									}
								}
							} else {
								sender.sendMessage("You don't have the right permissions to use that command");
							}
							return true;

						} else {
							if (sender.hasPermission("onyxadmin.warn.player")) {
								// Single player
								Player target = plugin.getServer().getPlayer(args[0]);

								if (target != null && canBePunished(target)) {
									adminCommands.warnPlayer(target, sender, config.getString("messages.player.warned"));
								} else {
									if (config.getBoolean("messages.enabled")) {
										sender.sendMessage(config.getString("messages.admin.player.offline"));
									}
								}
							} else {
								sender.sendMessage("You don't have the right permissions to use that command");
							}
							return true;
						}
					}

				case 2:
					if (args.length > 0) {

						if (args[0].equalsIgnoreCase("*")) {
							if (sender.hasPermission("onyxadmin.kick.all")) {
								// All online players
								Player[] targets = plugin.getServer().getOnlinePlayers();

								if (targets.length > 0) {
									for (Player target : targets) {
										if (canBePunished(target)) {
											adminCommands.kickPlayer(target, sender, config.getString("messages.player.kick"));
										} else {
											log.info("No targets found");
											if (config.getBoolean("messages.enabled")) {
												sender.sendMessage(config.getString("messages.admin.player.offline"));
											}
										}
									}
								}
							} else {
								sender.sendMessage("You don't have the right permissions to use that command");
							}
							return true;

						} else {

							if (sender.hasPermission("onyxadmin.kick.player")) {
								// Single player
								Player target = plugin.getServer().getPlayer(args[0]);

								if (target != null && canBePunished(target)) {
									adminCommands.kickPlayer(target, sender, config.getString("messages.player.kick"));
								} else {
									if (config.getBoolean("messages.enabled")) {
										sender.sendMessage(config.getString("messages.admin.player.offline"));
									}
								}
							} else {
								sender.sendMessage("You don't have the right permissions to use that command");
							}
							return true;
						}
					}

				case 3:
					if (args.length > 0) {

						if (args[0].equalsIgnoreCase("*")) {
							if (sender.hasPermission("onyxadmin.ban.all")) {
								// All online players
								Player[] targets = plugin.getServer().getOnlinePlayers();

								if (targets.length > 0) {
									for (Player target : targets) {
										if (canBePunished(target)) {
											adminCommands.banPlayer(target, sender, config.getString("messages.player.ban"));
										} else {
											log.info("No targets found");
											if (config.getBoolean("messages.enabled")) {
												sender.sendMessage(config.getString("messages.admin.player.offline"));
											}
										}
									}
								}
							} else {
								sender.sendMessage("You don't have the right permissions to use that command");
							}
							return true;

						} else {
							if (sender.hasPermission("onyxadmin.ban.player")) {
								// Single player
								Player target = plugin.getServer().getPlayer(args[0]);

								if (target != null && canBePunished(target)) {
									adminCommands.banPlayer(target, sender, config.getString("messages.player.ban"));
								} else {
									if (config.getBoolean("messages.enabled")) {
										sender.sendMessage(config.getString("messages.admin.player.offline"));
									}
								}
							} else {
								sender.sendMessage("You don't have the right permissions to use that command");
							}
							return true;
						}
					}

				case 4:
					if (args.length > 0) {

						if (args[0].equalsIgnoreCase("*")) {
							if (sender.hasPermission("onyxadmin.freeze.all")) {
								// All online players
								Player[] targets = plugin.getServer().getOnlinePlayers();

								if (targets.length > 0) {
									for (Player target : targets) {
										if (canBePunished(target)) {
											adminCommands.freezePlayer(target, sender, config.getString("messages.player.frozen"));
										} else {
											log.info("No targets found");
											if (config.getBoolean("messages.enabled")) {
												sender.sendMessage(config.getString("messages.admin.player.offline"));
											}
										}
									}
								}
							} else {
								sender.sendMessage("You don't have the right permissions to use that command");
							}
							return true;

						} else {
							if (sender.hasPermission("onyxadmin.freeze.player")) {
								// Single player
								Player target = plugin.getServer().getPlayer(args[0]);

								if (target != null && canBePunished(target)) {
									adminCommands.freezePlayer(target, sender, config.getString("messages.player.freeze"));
								} else {
									if (config.getBoolean("messages.enabled")) {
										sender.sendMessage(config.getString("messages.admin.player.offline"));
									}
								}
							} else {
								sender.sendMessage("You don't have the right permissions to use that command");
							}
							return true;
						}
					}
					
				case 5:
					if (args.length > 0) {
						if (sender.hasPermission("onyxadmin.weather")) {
							List<World> thisWorld = plugin.getServer().getWorlds();
							
							for(World world: thisWorld){
								adminCommands.setWeather(world, args[0], null);
							}
							
						} else {
							sender.sendMessage("You don't have the right permissions to use that command");
						}
						return true;
					}
					
					
				case 6:
					if (args.length > 0) {
						if (sender.hasPermission("onyxadmin.time")) {
							List<World> thisWorld = plugin.getServer().getWorlds();
							
							for(World world: thisWorld){
								adminCommands.setTime(world, args[0], null);
							}
							
						} else {
							sender.sendMessage("You don't have the right permissions to use that command");
						}
						return true;
					}
					
				case 7:
					if (args.length > 0) {

						if (args[0].equalsIgnoreCase("*")) {
							if (sender.hasPermission("onyxadmin.unban.all")) {
								// All online players
								Set<OfflinePlayer> targets = plugin.getServer().getBannedPlayers();

								if (!targets.isEmpty()) {
									for (OfflinePlayer target : targets) {
											adminCommands.unbanPlayer(target, sender);
											
											if (config.getBoolean("messages.enabled")) {
												sender.sendMessage(config.getString("messages.admin.all.unbanned"));
											}
										}
									}
								}
							} else {
								sender.sendMessage("You don't have the right permissions to use that command");
							}
							return true;

						} else {
							if (sender.hasPermission("onyxadmin.unban.player")) {
								// Single player
								Player target = plugin.getServer().getPlayer(args[0]);

								if (target != null && target.isBanned()) {
									adminCommands.unbanPlayer(target, sender);
								} else {
									if (config.getBoolean("messages.enabled")) {
										sender.sendMessage(config.getString("messages.admin.player.unbanned"));
									}
								}
							} else {
								sender.sendMessage("You don't have the right permissions to use that command");
							}
							return true;
						}
					}
				}
			}
		
		return false;
	}

	/**
	 * Check if player can be used as target
	 * @param player
	 * @return
	 */
	private boolean canBePunished(Player player) {

		boolean exec = false;

		try {
			if (player.isOnline() && !player.isBanned() && !player.isOp()) {
				exec = true;
			}
		} catch (NullPointerException e) {
			exec = false;
		}

		return exec;
	}

}
