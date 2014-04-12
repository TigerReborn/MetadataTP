package me.TigerReborn.MetadataTP;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class TeleportRequestDenyCommand implements CommandExecutor{

	Plugin plugin;
	public TeleportRequestDenyCommand(Plugin pl){
		this.plugin = pl;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) { return true; }
		
		if(cmd.getName().equalsIgnoreCase("tprequestdeny")){
			Player player = (Player) sender;
			
			if(!(player.hasMetadata(IMetadataTP.TELEPORT_REQUEST_METADATA))){
				player.sendMessage(IMetadataTP.COLOR_CODE + "c" + "You do not have any pending requests.");
				return true;
			}
		TeleportRequest req = (TeleportRequest) player.getMetadata(IMetadataTP.TELEPORT_REQUEST_METADATA).get(0).value();
		req.getPlayerTo().sendMessage(IMetadataTP.COLOR_CODE + "c" + " teleport request from " + req.getFrom() + " was denied.");
		req.getPlayerFrom().sendMessage(IMetadataTP.COLOR_CODE + "c" + player.getName() + " has denied your teleport request");
		
		player.removeMetadata(IMetadataTP.TELEPORT_REQUEST_METADATA, plugin);
		}
		return true;
	}
}
