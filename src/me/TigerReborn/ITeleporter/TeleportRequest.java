package me.TigerReborn.ITeleporter;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;


public class TeleportRequest {
	
	private String from, to;
	private RequestType type;
	
	public TeleportRequest(String from, String to){
		this(from, to, RequestType.TELEPORT_TO_REQUEST);
	}	
	
	public TeleportRequest(String from, String to, RequestType type){
		this.from = from;
		this.to = to;
		this.type = type;
	}
	
	public RequestType getType(){
		return type;
	}
	
	public String getFrom(){
		return from;
	}
	
	public String getTo(){
		return to;
	}
	
	
	public Player getPlayerFrom(){
		//TODO: Implement UUID usage
		return Bukkit.getPlayerExact(from);
	}
	

	public Player getPlayerTo(){
		//TODO: Implement UUID usage
		return Bukkit.getPlayerExact(to);
	}

}
