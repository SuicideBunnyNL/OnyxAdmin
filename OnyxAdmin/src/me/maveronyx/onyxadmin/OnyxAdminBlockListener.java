package me.maveronyx.onyxadmin;

import org.bukkit.event.block.BlockListener;

public class OnyxAdminBlockListener extends BlockListener {

	OnyxAdmin plugin;
	
	public OnyxAdminBlockListener(OnyxAdmin instance) {
		plugin = instance;
	}

}
