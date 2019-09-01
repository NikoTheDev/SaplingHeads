package com.vervedev.saplingheads.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import com.vervedev.saplingheads.Main;
import com.vervedev.saplingheads.ui.RankupUI;
import com.vervedev.saplingheads.ui.SkullVaultUI;
import com.vervedev.saplingheads.ui.donatorshop.BuyUI;
import com.vervedev.saplingheads.ui.donatorshop.CrateKeyUI;
import com.vervedev.saplingheads.ui.donatorshop.DonatorRankUI;

public class InventoryClick implements Listener {

	private Main plugin;

	public InventoryClick(Main plugin) {
		this.plugin = plugin;

		Bukkit.getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onClick(InventoryClickEvent e) {
		String title = e.getView().getTitle();
		if (e.getCurrentItem() == null) {
			return;
		}
		if (title.equals(RankupUI.inventory_name)) {
			e.setCancelled(true);
			RankupUI.clicked((Player) e.getWhoClicked(), e.getSlot(), e.getCurrentItem(), e.getInventory());
		} else if (title.equals(SkullVaultUI.inventory_name)) {
			e.setCancelled(true);
			SkullVaultUI.clicked((Player) e.getWhoClicked(), e.getSlot(), e.getCurrentItem(), e.getInventory());
		} else if (title.equals(BuyUI.inventory_name)) {
			e.setCancelled(true);
			BuyUI.clicked((Player) e.getWhoClicked(), e.getSlot(), e.getCurrentItem(), e.getInventory());
		} else if (title.equals(DonatorRankUI.inventory_name)) {
			e.setCancelled(true);
			DonatorRankUI.clicked((Player) e.getWhoClicked(), e.getSlot(), e.getCurrentItem(), e.getInventory());
		} else if (title.equals(CrateKeyUI.inventory_name)) {
			e.setCancelled(true);
			CrateKeyUI.clicked((Player) e.getWhoClicked(), e.getSlot(), e.getCurrentItem(), e.getInventory());
		}
	}
}
