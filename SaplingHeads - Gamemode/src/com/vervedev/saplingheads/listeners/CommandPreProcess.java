package com.vervedev.saplingheads.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import com.vervedev.saplingheads.Main;
import com.vervedev.saplingheads.ui.BuyUI;

public class CommandPreProcess implements Listener {
	
	private Main plugin;
	
	public CommandPreProcess(Main plugin) {
		this.plugin = plugin;
		
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}


	@EventHandler
	public void preProcess(PlayerCommandPreprocessEvent e) {

		if (e.getMessage().equalsIgnoreCase("/buy")) {
			e.setCancelled(true);
			Player p = (Player) e.getPlayer();

			p.openInventory(BuyUI.GUI(p));
		}
	}
}
