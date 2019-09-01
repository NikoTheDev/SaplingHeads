package com.vervedev.saplingheads.ui.donatorshop;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.vervedev.saplingheads.Main;
import com.vervedev.saplingheads.utils.Utils;

public class BuyUI {

	public static Inventory inv;
	public static String inventory_name;
	public static int inv_rows = 1 * 9;

	public static Main plugin;

	public BuyUI(Main plugin) {
		this.plugin = plugin;

	}

	public static void initialize() {
		inventory_name = Utils.chat("&c&lSapling Shop");

		inv = Bukkit.createInventory(null, inv_rows);
	}
	

	public static Inventory GUI(Player p) {

		Inventory toReturn = Bukkit.createInventory(null, inv_rows, inventory_name);

		Utils.createItemHead(inv, p.getName(), 1, 4, "&6&lDonator Ranks",
				"&7View all the donator ranks that &a&lSaplingMC", "&7has to offer!");

		Utils.createItem(inv, "TRIPWIRE_HOOK", 1, 6, "&a&lCrate Keys", "&7Purchase &6crate keys &7for a chance to win",
				"&7tons of amazing prizes such as: &eRanks&7, &eCash&7, &eSpawners&7,", "&ePerks &7and much more!");

		Utils.createItem(inv, "RED_STAINED_GLASS_PANE", 1, 1, "&c&lClose Menu",
				"&7&eClick &7this button to close the menu!");

		Utils.createItem(inv, "GREEN_STAINED_GLASS_PANE", 1, 9, "&a&lWebsite Link",
				"&7&eClick &7this button to receive the &2link", "&7to our full website!");

		toReturn.setContents(inv.getContents());
		return toReturn;

	}

	public static void clicked(Player p, int slot, ItemStack clicked, Inventory inv) {
		String websiteMessage = "\n&8&m----------------------------------------&r\n\n&7If you would like to viw our &6Full Website, click the following link:\n&6"
				+ plugin.getConfig().getString("messages.website_link")
				+ "\n\n&8&m----------------------------------------&r\n\n";
		if (ChatColor.stripColor(clicked.getItemMeta().getDisplayName()).equalsIgnoreCase("Donator Ranks")) {
			p.openInventory(DonatorRankUI.GUI(p));
			p.updateInventory();
		} else if (ChatColor.stripColor(clicked.getItemMeta().getDisplayName()).equalsIgnoreCase("Crate Keys")) {
			p.openInventory(CrateKeyUI.GUI(p));
			p.updateInventory();
		} else if (ChatColor.stripColor(clicked.getItemMeta().getDisplayName()).equalsIgnoreCase("Close Menu")) {
			p.closeInventory();
		} else if (ChatColor.stripColor(clicked.getItemMeta().getDisplayName()).equalsIgnoreCase("Website Link")) {
			p.closeInventory();
			p.sendMessage(Utils.chat(websiteMessage));
		}
	}

}
