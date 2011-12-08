package me.maveronyx.onyxadmin;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerMoveEvent;

public class OnyxAdminPlayerListener extends PlayerListener {

	OnyxAdmin plugin;
	
	public OnyxAdminPlayerListener(OnyxAdmin instance) {
		plugin = instance;
	}
	
	public void onPlayerMove(PlayerMoveEvent event){
		Player player = event.getPlayer();
		
		if (plugin.frozenPlayers.contains(player.getDisplayName())) {
			event.setCancelled(true);
		}
	}

}
