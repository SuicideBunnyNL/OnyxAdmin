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
	
	public String colorText(String msg){
		
		String colors[] = {"red", "blue", "green", "pink", "yellow", "aqua", "darkgreen", "navy", "white", "black", "gold"};
		
		
		return msg;
	}
	
}
