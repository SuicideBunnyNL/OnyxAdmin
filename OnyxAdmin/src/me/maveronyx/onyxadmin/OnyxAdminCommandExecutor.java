package me.maveronyx.onyxadmin;

import java.util.HashMap;
import java.util.Set;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class OnyxAdminCommandExecutor implements CommandExecutor {

	HashMap<String, Integer> commandMap = new HashMap<String, Integer>();
	String cmdPrefix = "onyx";

	public OnyxAdminCommandExecutor() {
		
		// Register all commands and map them
		commandMap.put("kick", 1);
		commandMap.put("ban", 2);
		commandMap.put("freezechat", 3);
		commandMap.put("freezeall", 4);
		commandMap.put("burnall", 5);
		commandMap.put("explodeall", 6);
		commandMap.put("freezeplayer", 7);
		commandMap.put("burnplayer", 8);
		commandMap.put("explodeplayer", 9);
		commandMap.put("give", 10);
		commandMap.put("take", 11);
		commandMap.put("toggletnt", 12);
		commandMap.put("removetnt", 13);
		commandMap.put("tp", 14);
		commandMap.put("tpall", 15);
		commandMap.put("togglebuild", 16);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {

		if (sender instanceof Player) {
			String cmd = command.getName().toLowerCase();
		
			if(commandMap.containsKey(cmd)){
				Integer commandId = commandMap.get(cmd);
				
				switch(commandId){
				case 1:
					// Kick
					break;
				case 2:
					// Ban
					break;
				case 3:
					break;
				case 4:
					break;
				case 5:
					break;
				case 6:
					break;
				case 7:
					break;
				case 8:
					break;
				case 9:
					break;
				case 10:
					break;
				case 11:
					break;
				case 12:
					break;
				case 13:
					break;
				case 14:
					break;
				case 15:
					break;
				case 16:
					break;
					
					
				}
				
			}
			

		}

		return false;
	}

}
