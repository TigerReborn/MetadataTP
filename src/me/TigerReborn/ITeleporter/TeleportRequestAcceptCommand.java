package me.TigerReborn.ITeleporter;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class TeleportRequestAcceptCommand implements CommandExecutor{

	Plugin plugin;
	public TeleportRequestAcceptCommand(Plugin pl){
		this.plugin = pl;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) { return true; }
		
		if(cmd.getName().equalsIgnoreCase("tprequestaccept")){
			Player player = (Player) sender;
			
			if(!(player.hasMetadata(ITeleporter.TELEPORT_REQUEST_METADATA))){
				player.sendMessage(ITeleporter.COLOR_CODE + "c" + "You do not have any pending requests.");
				return true;
			}
		TeleportRequest req = (TeleportRequest) player.getMetadata(ITeleporter.TELEPORT_REQUEST_METADATA).get(0).value();
		player.removeMetadata(ITeleporter.TELEPORT_REQUEST_METADATA, plugin);
		switch(req.getType()){
			case TELEPORT_TO_REQUEST:
				acceptTeleportRequest(req);
				return true;
			case TELEPORT_HERE_REQUEST:
				acceptTeleportHereRequest(req);
				return true;
		}
		
		}
		return true;
	}
	public void acceptTeleportHereRequest(TeleportRequest req){
		Player to = req.getPlayerTo();
		Player from = req.getPlayerFrom();
		
		from.sendMessage(ITeleporter.COLOR_CODE + "a" + to.getName() + " has accepted your teleport request. Teleporting");
		to.sendMessage(ITeleporter.COLOR_CODE + "a" + "Accepted request from " + from.getName());
		
		to.teleport(from);
		
	}
	public void acceptTeleportRequest(TeleportRequest req){
		Player to = req.getPlayerTo();
		Player from = req.getPlayerFrom();
		from.sendMessage(ITeleporter.COLOR_CODE + "a" + to.getName() + " has accepted your teleport request. Teleporting");
		to.sendMessage(ITeleporter.COLOR_CODE + "a" + "Accepted request from " + from.getName());
		
		from.teleport(to);

	}
}
