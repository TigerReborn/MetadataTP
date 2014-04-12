package me.TigerReborn.MetadataTP;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;

public class TeleportCommand implements CommandExecutor{

	Plugin plugin;
	public TeleportCommand(Plugin pl){
		plugin = pl;
	}
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) { return true; }
		
		if(cmd.getName().equalsIgnoreCase("tprequest")){
			if(args.length != 1){
				sender.sendMessage(IMetadataTP.COLOR_CODE + "c" + "Invalid Arguements. Correct usage is /" + cmd.getName() + " <player>");
				return true;
			}
			//TODO: Implement UUID Usage
			Player player = Bukkit.getPlayerExact(args[0]);
			
			if(player == null){
				sender.sendMessage(IMetadataTP.COLOR_CODE + "c" + "Unknown Player "+ args[0]);
				return true;
			}
			TeleportRequest req = sendTeleportRequest((Player) sender, player);
			sender.sendMessage(IMetadataTP.COLOR_CODE + "a" + "Sent teleport request to " + req.getTo());
			player.sendMessage(IMetadataTP.COLOR_CODE + "a" + sender.getName() +" has requested to teleport to you.\n To accept this, type /tpraccept\n To deny, type /tprdeny");
		}
		
		return true;
	}
	
	
	public TeleportRequest sendTeleportRequest(Player from, Player to){
		TeleportRequest req = new TeleportRequest(from.getName(), to.getName());
		to.setMetadata(IMetadataTP.TELEPORT_REQUEST_METADATA, new FixedMetadataValue(plugin, req));
		return req;
	}

}
