package me.TigerReborn.ITeleporter;

import org.bukkit.plugin.java.JavaPlugin;

public class ITeleporter extends JavaPlugin{
	public static final String COLOR_CODE = "§";
	public static final String TELEPORT_REQUEST_METADATA = "teleportRequest";
	
	@Override
	public void onEnable(){
		//TODO: Implement UUID Usage
		getCommand("tprequest").setExecutor(new TeleportCommand(this));
		getCommand("tphererequest").setExecutor(new TeleportHereCommand(this));
		getCommand("tprequestaccept").setExecutor(new TeleportRequestAcceptCommand(this));
		getCommand("tprequestdeny").setExecutor(new TeleportRequestDenyCommand(this));
	}
}
