package me.TigerReborn.ITeleporter;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;

public class TeleportHereCommand implements CommandExecutor{

	Plugin plugin;
	public TeleportHereCommand(Plugin pl){ this.plugin = pl; }
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) { return true; }
		
		if(cmd.getName().equalsIgnoreCase("tphererequest")){
			if(args.length != 1){
				sender.sendMessage(ITeleporter.COLOR_CODE + "c" + "Invalid Arguements. Correct usage is /" + cmd.getName() + " <player>");
				return true;
			}
			//TODO: Implement UUID Usage
			Player player = Bukkit.getPlayerExact(args[0]);
			
			if(player == null){
				sender.sendMessage(ITeleporter.COLOR_CODE + "c" + "Unknown Player "+ args[0]);
				return true;
			}
			TeleportRequest req = sendTeleportHereRequest((Player) sender, player);
			sender.sendMessage(ITeleporter.COLOR_CODE + "a" + "Sent teleport here request to " + req.getTo());
			player.sendMessage(ITeleporter.COLOR_CODE + "a" + sender.getName() + " has requested that you teleport to them.\n To accept this, type /tpraccept\n To deny, type /tprdeny");
		}
		return true;
	}
	public TeleportRequest sendTeleportHereRequest(Player from, Player to){
		TeleportRequest req = new TeleportRequest(from.getName(), to.getName(), RequestType.TELEPORT_HERE_REQUEST);
		to.setMetadata(ITeleporter.TELEPORT_REQUEST_METADATA, new FixedMetadataValue(plugin, req));
		return req;
	}

}
