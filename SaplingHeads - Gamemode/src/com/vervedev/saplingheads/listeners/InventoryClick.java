package com.vervedev.saplingheads.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.vervedev.saplingheads.Main;
import com.vervedev.saplingheads.ui.PerkUI;
import com.vervedev.saplingheads.ui.RankupUI;
import com.vervedev.saplingheads.ui.SkullVaultUI;
import com.vervedev.saplingheads.ui.donatorshop.BuyUI;
import com.vervedev.saplingheads.ui.donatorshop.CrateKeyUI;
import com.vervedev.saplingheads.ui.donatorshop.DonatorRankUI;
import com.vervedev.saplingheads.utils.Utils;

public class InventoryClick implements Listener {

	private Main plugin;

	public InventoryClick(Main plugin) {
		this.plugin = plugin;

		Bukkit.getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if (!e.isCancelled()) {
			HumanEntity ent = e.getWhoClicked();

			// not really necessary
			if (ent instanceof Player) {
				Player player = (Player) ent;
				Inventory inv = e.getInventory();

				// see if the event is about an anvil
				if (inv instanceof AnvilInventory) {
					InventoryView view = e.getView();
					int rawSlot = e.getRawSlot();

					// compare the raw slot with the inventory view to make sure we are talking
					// about the upper inventory
					if (rawSlot == view.convertSlot(rawSlot)) {
						/*
						 * slot 0 = left item slot slot 1 = right item slot slot 2 = result item slot
						 * 
						 * see if the player clicked in the result item slot of the anvil inventory
						 */
						if (rawSlot == 2) {
							/*
							 * get the current item in the result slot I think inv.getItem(rawSlot) would be
							 * possible too
							 */
							ItemStack item = e.getCurrentItem();

							// check if there is an item in the result slot
							if (item != null) {
								ItemMeta meta = item.getItemMeta();

								// it is possible that the item does not have meta data
								if (meta != null) {
									// see whether the item is beeing renamed
									if (meta.hasDisplayName()) {
										String displayName = meta.getDisplayName().toLowerCase();
										if (ChatColor.stripColor(displayName).endsWith("skull")) {
											e.setCancelled(true);
											player.sendMessage(Utils.chat("&7&lAnvils &8> &7You are unable to rename items that contain 'skull'"));
										}
									}
								}
							}
						}
					}
				}
			}
		}
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
		} else if (title.equals(PerkUI.inventory_name)) {
			e.setCancelled(true);
			PerkUI.clicked((Player) e.getWhoClicked(), e.getSlot(), e.getCurrentItem(), e.getInventory());
		}
	}
}
