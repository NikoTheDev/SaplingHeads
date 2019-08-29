package com.vervedev.saplingheads.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import com.vervedev.saplingheads.Main;
import com.vervedev.saplingheads.utils.Utils;

public class PingList implements Listener {
	
	private Main plugin;
	
	public PingList(Main plugin) {
		this.plugin = plugin;
		
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onpingList(ServerListPingEvent e) {
		e.setMotd(Utils.chat(plugin.getConfig().getString("server_motd")));
	}
}
