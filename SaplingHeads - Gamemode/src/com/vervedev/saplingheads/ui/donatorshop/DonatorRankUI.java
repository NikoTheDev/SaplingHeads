package com.vervedev.saplingheads.ui.donatorshop;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.vervedev.saplingheads.Main;
import com.vervedev.saplingheads.utils.Utils;

public class DonatorRankUI {

	public static Inventory inv;
	public static String inventory_name;
	public static int inv_rows = 4 * 9;

	private static Main plugin;

	public DonatorRankUI(Main plugin) {
		this.plugin = plugin;
	}

	public static void initialize() {
		inventory_name = Utils.chat("&6&lDonator Ranks");

		inv = Bukkit.createInventory(null, inv_rows);
	}

	public static Inventory GUI(Player p) {

		Inventory toReturn = Bukkit.createInventory(null, inv_rows, inventory_name);

		Utils.createItemHead(inv, p.getName(), 1, 12, "&8(&2Elite&8) &2" + p.getName(),
				"&7You may purchase this rank in our shop! &eClick",
				"&7to recieve a link to this rank on our &2website&7!");

		Utils.createItemHead(inv, p.getName(), 1, 13, "&8(&3Hero&8) &3" + p.getName(),
				"&7You may purchase this rank in our shop! &eClick",
				"&7to recieve a link to this rank on our &3website&7!");

		Utils.createItemHead(inv, p.getName(), 1, 14, "&8(&9Champion&8) &9" + p.getName(),
				"&7You may purchase this rank in our shop! &eClick",
				"&7to recieve a link to this rank on our &9website&7!");

		Utils.createItemHead(inv, p.getName(), 1, 15, "&8(&eLegend&8) &e" + p.getName(),
				"&7You may purchase this rank in our shop! &eClick",
				"&7to recieve a link to this rank on our &ewebsite&7!");

		Utils.createItemHead(inv, p.getName(), 1, 16, "&8(&eLegend&c+&8) &c" + p.getName(),
				"&7You may purchase this rank in our shop! &eClick",
				"&7to recieve a link to this rank on our &cwebsite&7!");

		Utils.createItem(inv, "RED_STAINED_GLASS_PANE", 1, 31, "&c&lClose Menu",
				"&7&eClick &7this button to close the menu!");

		Utils.createItem(inv, "YELLOW_STAINED_GLASS_PANE", 1, 32, "&e&lBack to Main Menu",
				"&7&eClick &7this button to return to the", "&6Main Menu&7!");

		Utils.createItem(inv, "GREEN_STAINED_GLASS_PANE", 1, 32, "&a&lWebsite Link",
				"&7&eClick &7this button to receive the &2link", "&7to our full website!");

		toReturn.setContents(inv.getContents());
		return toReturn;
	}

	public static void clicked(Player p, int slot, ItemStack clicked, Inventory inv) {

		String eliteMessage = "\n&8&m----------------------------------------&r\n\n&7If you would like to purchase the &8(&2Elite&8) &2Rank&7, click the following link:\n&2"
				+ plugin.getConfig().getString("messages.donorshop.ranks.elite_website_link")
				+ "\n\n&8&m----------------------------------------&r\n\n";

		String heroMessage = "\n&8&m----------------------------------------&r\n\n&7If you would like to purchase the &8(&3Hero&8) &3Rank&7, click the following link:\n&3"
				+ plugin.getConfig().getString("messages.donorshop.ranks.hero_website_link")
				+ "\n\n&8&m----------------------------------------&r\n\n";

		String championMessage = "\n&8&m----------------------------------------&r\n\n&7If you would like to purchase the &8(&9Champion&8) &9Rank&7, click the following link:\n&9"
				+ plugin.getConfig().getString("messages.donorshop.ranks.champion_website_link")
				+ "\n\n&8&m----------------------------------------&r\n\n";

		String legendMessage = "\n&8&m----------------------------------------&r\n\n&7If you would like to purchase the &8(&eLegend&8) &eRank&7, click the following link:\n&e"
				+ plugin.getConfig().getString("messages.donorshop.ranks.legend_website_link")
				+ "\n\n&8&m----------------------------------------&r\n\n";

		String legendPlusMessage = "\n&8&m----------------------------------------&r\n\n&7If you would like to purchase the &8(&eLegend&c+&8) &cRank&7, click the following link:\n&c"
				+ plugin.getConfig().getString("messages.donorshop.ranks.legend_plus_website_link")
				+ "\n\n&8&m----------------------------------------&r\n\n";

		String websiteMessage = "\n&8&m----------------------------------------&r\n\n&7If you would like to view our &6Full Website, click the following link:\n&6"
				+ plugin.getConfig().getString("messages.donorshop.website_link")
				+ "\n\n&8&m----------------------------------------&r\n\n";
		if (ChatColor.stripColor(clicked.getItemMeta().getDisplayName()).equalsIgnoreCase("(Elite) " + p.getName())) {
			p.closeInventory();
			p.sendMessage(Utils.chat(eliteMessage));
		} else if (ChatColor.stripColor(clicked.getItemMeta().getDisplayName())
				.equalsIgnoreCase("(Hero) " + p.getName())) {
			p.closeInventory();
			p.sendMessage(Utils.chat(heroMessage));
		} else if (ChatColor.stripColor(clicked.getItemMeta().getDisplayName())
				.equalsIgnoreCase("(Champion) " + p.getName())) {
			p.closeInventory();
			p.sendMessage(Utils.chat(championMessage));
		} else if (ChatColor.stripColor(clicked.getItemMeta().getDisplayName())
				.equalsIgnoreCase("(Legend) " + p.getName())) {
			p.closeInventory();
			p.sendMessage(Utils.chat(legendMessage));
		} else if (ChatColor.stripColor(clicked.getItemMeta().getDisplayName())
				.equalsIgnoreCase("(Legend+) " + p.getName())) {
			p.closeInventory();
			p.sendMessage(Utils.chat(legendPlusMessage));
		} else if (ChatColor.stripColor(clicked.getItemMeta().getDisplayName()).equalsIgnoreCase("Website Link")) {
			p.closeInventory();
			p.sendMessage(Utils.chat(websiteMessage));
		} else if (ChatColor.stripColor(clicked.getItemMeta().getDisplayName()).equalsIgnoreCase("Close Menu")) {
			p.closeInventory();
		} else if (ChatColor.stripColor(clicked.getItemMeta().getDisplayName()).equalsIgnoreCase("Back to Main Menu")) {
			p.openInventory(BuyUI.GUI(p));
			p.updateInventory();
		}
	}

}
