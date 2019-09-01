package com.vervedev.saplingheads.ui.donatorshop;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.vervedev.saplingheads.Main;
import com.vervedev.saplingheads.utils.Utils;

public class CrateKeyUI {

	public static Inventory inv;
	public static String inventory_name;
	public static int inv_rows = 4 * 9;

	private static Main plugin;

	private static String standardMessage = "\n&8&m----------------------------------------&r\n\n&7If you would like to purchase a &9&lStandard Key&7, click the following link:\n&9"
			+ plugin.getConfig().getString("messages.donorshop.crates.standard_website_link")
			+ "\n\n&8&m----------------------------------------&r\n\n";

	private static String premiumMessage = "\n&8&m----------------------------------------&r\n\n&7If you would like to purchase a &e&lPremium Key&7, click the following link:\n&a"
			+ plugin.getConfig().getString("messages.donorshop.crates.premium_website_link")
			+ "\n\n&8&m----------------------------------------&r\n\n";

	private static String enchantedMessage = "\n&8&m----------------------------------------&r\n\n&7If you would like to purchase a &5&lEnchanted Key&7, click the following link:\n&9"
			+ plugin.getConfig().getString("messages.donorshop.crates.enchanted_website_link")
			+ "\n\n&8&m----------------------------------------&r\n\n";

	private static String magmaMessage = "\n&8&m----------------------------------------&r\n\n&7If you would like to purchase a &c&lMagma Key&7, click the following link:\n&e"
			+ plugin.getConfig().getString("messages.donorshop.crates.magma_website_link")
			+ "\n\n&8&m----------------------------------------&r\n\n";

	private static String websiteMessage = "\n&8&m----------------------------------------&r\n\n&7If you would like to viw our &6Full Website, click the following link:\n&6"
			+ plugin.getConfig().getString("messages.website_link")
			+ "\n\n&8&m----------------------------------------&r\n\n";

	public CrateKeyUI(Main plugin) {
		this.plugin = plugin;

		initialize();
	}

	public static void initialize() {
		inventory_name = Utils.chat("&6&lDonator Ranks");

		inv = Bukkit.createInventory(null, inv_rows);
	}

	public static Inventory GUI(Player p) {

		Inventory toReturn = Bukkit.createInventory(null, inv_rows, inventory_name);

		Utils.createItemHead(inv, "TRIPWIRE_HOOK", 1, 12, "&9&lStandard Key",
				"&7Description Coming Soon!");

		Utils.createItemHead(inv, "TRIPWIRE_HOOK", 1, 13, "&e&lPremium Key",
				"&7Description Coming Soon!");

		Utils.createItemHead(inv, "TRIPWIRE_HOOK", 1, 14, "&5&lEnchanted Key",
				"&7Description Coming Soon!");

		Utils.createItemHead(inv, "TRIPWIRE_HOOK", 1, 15, "&c&lMagma Key",
				"&7Description Coming Soon!");

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
		if (ChatColor.stripColor(clicked.getItemMeta().getDisplayName()).equalsIgnoreCase("(Elite) " + p.getName())) {
			p.closeInventory();
			p.sendMessage(Utils.chat(standardMessage));
		} else if (ChatColor.stripColor(clicked.getItemMeta().getDisplayName())
				.equalsIgnoreCase("(Hero) " + p.getName())) {
			p.closeInventory();
			p.sendMessage(Utils.chat(premiumMessage));
		} else if (ChatColor.stripColor(clicked.getItemMeta().getDisplayName())
				.equalsIgnoreCase("(Champion) " + p.getName())) {
			p.closeInventory();
			p.sendMessage(Utils.chat(enchantedMessage));
		} else if (ChatColor.stripColor(clicked.getItemMeta().getDisplayName())
				.equalsIgnoreCase("(Legend) " + p.getName())) {
			p.closeInventory();
			p.sendMessage(Utils.chat(magmaMessage));
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
