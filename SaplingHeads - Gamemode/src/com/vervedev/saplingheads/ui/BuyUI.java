package com.vervedev.saplingheads.ui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.vervedev.saplingheads.utils.Utils;

public class BuyUI {

	public static Inventory inv;
	public static String inventory_name;
	public static int inv_rows = 1 * 9;

	public static void initialize() {
		inventory_name = Utils.chat("&c&lSapling Shop");

		inv = Bukkit.createInventory(null, inv_rows);
	}

	/*
	 * S S S S S S S S S
	 */

	public static Inventory GUI(Player p) {

		Inventory toReturn = Bukkit.createInventory(null, inv_rows, inventory_name);

		Utils.createItemHead(inv, p.getName(), 1, 4, "&6&lDonator Ranks",
				"&7View all the donator ranks that &a&lSaplingMC", "&7has to offer!");

		Utils.createItem(inv, "TRIPWIRE_HOOK", 1, 6, "&a&lCrate Keys",
				"&7Purchase &6crate keys &7for a chance to win",
				"&7tons of amazing prizes such as: &eRanks&7, &eCash&7, &eSpawners&7,", "&ePerks &7and much more!");

		toReturn.setContents(inv.getContents());
		return toReturn;

	}

	public static void clicked(Player p, int slot, ItemStack clicked, Inventory inv) {
		if (ChatColor.stripColor(clicked.getItemMeta().getDisplayName()).equalsIgnoreCase("Donator Ranks")) {

		}
	}

}
