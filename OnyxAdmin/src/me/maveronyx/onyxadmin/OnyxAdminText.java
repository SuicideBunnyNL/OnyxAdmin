package me.maveronyx.onyxadmin;

import org.bukkit.entity.Player;

public class OnyxAdminText{

	OnyxAdmin plugin;
	
	public OnyxAdminText(OnyxAdmin instance){
		plugin = instance;
	}
	
	public void MessagePlayer(Player player, String msg) {
		player.sendMessage(msg);
	}
	
	public void broadcastServer(String msg){
		plugin.getServer().broadcastMessage(msg);
	}
	
}
