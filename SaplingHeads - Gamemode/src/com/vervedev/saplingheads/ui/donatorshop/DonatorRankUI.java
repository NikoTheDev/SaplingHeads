package com.vervedev.saplingheads.ui.donatorshop;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.vervedev.saplingheads.utils.Utils;

public class DonatorRankUI {

	public static Inventory inv;
	public static String inventory_name;
	public static int inv_rows = 1 * 9;

	public static void initialize() {
		inventory_name = Utils.chat("&6&lDonator Ranks");

		inv = Bukkit.createInventory(null, inv_rows);
	}

	/*
	 * X X X X X X X X X X E H C L L+ X X X X X X X X X X X X
	 */

	public static Inventory GUI(Player p) {

		Inventory toReturn = Bukkit.createInventory(null, inv_rows, inventory_name);

		Utils.createItemHead(inv, p.getName(), 1, 4, "&8(&2Elite&8) &a" + p.getName(),
				"&7You may purchase this rank in our shop! &eClick",
				"&7to recieve a link to this Rank on our &2Shop&7!");

		Utils.createItem(inv, "TRIPWIRE_HOOK", 1, 6, "&8(&aHero&8) &a" + p.getName(),
				"&7You may purchase this rank in our shop! &eClick",
				"&7to recieve a link to this Rank on our &aShop&7!");

		toReturn.setContents(inv.getContents());
		return toReturn;

	}

	public static void clicked(Player p, int slot, ItemStack clicked, Inventory inv) {
		if (ChatColor.stripColor(clicked.getItemMeta().getDisplayName()).equalsIgnoreCase("Donator Ranks")) {

		}
	}

}
