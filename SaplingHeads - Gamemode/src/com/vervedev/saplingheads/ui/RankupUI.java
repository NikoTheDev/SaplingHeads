package com.vervedev.saplingheads.ui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.vervedev.saplingheads.Main;
import com.vervedev.saplingheads.managers.RankManager;
import com.vervedev.saplingheads.utils.Utils;

public class RankupUI {

	public static Inventory inv;
	public static String inventory_name;
	public static int inv_rows = 6 * 9;
	
	private Main plugin;
	
	public RankupUI(Main plugin) {
		this.plugin = plugin;
		
		initialize();
	}

	public static void initialize() {
		inventory_name = Utils.chat("&2&lRankup Menu");

		inv = Bukkit.createInventory(null, inv_rows);
	}

	public static Inventory GUI(Player p) {

		Inventory toReturn = Bukkit.createInventory(null, inv_rows, inventory_name);

		Utils.createItemHead(inv, p.getName(), 1, 50, p.getName() + "'s &eRank Menu", "&7Use this menu to rankup",
				"&7to gain access to special perks", "&7and abilities!", "",
				"&6Current Rank: &e" + RankManager.getRankString(p));

		if (RankManager.getRank(p) >= 1) {
			Utils.createItemHead(inv, "thepoup", 1, 21, "&f&lChicken Rank", "&7You currently have access to all",
					"&fChicken Perks &7To view your perks", "&7simply do &9/perks&7!");
		} else {
			Utils.createItemHead(inv, "thepoup", 1, 21, "&f&lChicken Rank &8(&c&lLOCKED&8)",
					"",
					"&eCollect enough skulls and cash in order to rankup!");
		}

		if (RankManager.getRank(p) >= 2) {
			Utils.createItemHead(inv, "Pig", 1, 22, "&d&lPig Rank", "&7You currently have access to all",
					"&dPig Perks &7To view your perks", "&7simply do &9perks&7!");
		} else {
			Utils.createItemHead(
					inv, "Pig", 1, 22, "&d&lPig Rank &8(&c&lLOCKED&8)",
					"",
					"&eCollect enough skulls and cash in order to rankup!");
		}
		if (RankManager.getRank(p) >= 3) {
			Utils.createItemHead(inv, "mhf_sheep", 1, 23, "&7&lSheep Rank", "&7You currently have access to all",
					"&7Sheep Perks &7To view your perks", "&7simply do &9/perks&7!");
		} else {
			Utils.createItemHead(
					inv, "mhf_sheep", 1, 23, "&7&lSheep Rank &8(&c&lLOCKED&8)", 
					"",
					"&eCollect enough skulls and cash in order to rankup!");
		}
		if (RankManager.getRank(p) >= 4) {
			Utils.createItemHead(inv, "mhf_cow", 1, 24, "&8&lCow Rank", "&7You currently have access to all",
					"&8Cow Perks &7To view your perks", "&7simply do &9/perks&7!");
		} else {
			Utils.createItemHead(
					inv, "mhf_cow", 1, 24, "&8&lCow Rank &8(&c&lLOCKED&8)", 
					"",
					"&eCollect enough skulls and cash in order to rankup!");
		}
		if (RankManager.getRank(p) >= 5) {
			Utils.createItemHead(inv, "mhf_zombie", 1, 25, "&2&lZombie Rank", "&7You currently have access to all",
					"&2Zombie Perks &7To view your perks", "&7simply do &9/perks&7!");
		} else {
			Utils.createItemHead(
					inv, "mhf_zombie", 1, 25, "&2&lZombie Rank &8(&c&lLOCKED&8)", 
					"",
					"&eCollect enough skulls and cash in order to rankup!");
		}
		if (RankManager.getRank(p) >= 6) {
			Utils.createItemHead(inv, "mhf_skeleton", 1, 30, "&3&lSkeleton Rank", "&7You currently have access to all",
					"&3Skeleton Perks &7To view your perks", "&7simply do &9/perks&7!");
		} else {
			Utils.createItemHead(inv, "mhf_skeleton", 1, 30, "&3&lSkeleton Rank &8(&c&lLOCKED&8)",
					"", "&eCollect enough skulls in order to rankup!");
		}
		if (RankManager.getRank(p) >= 7) {
			Utils.createItemHead(inv, "mhf_pigzombie", 1, 31, "&c&lZombie Pigman Rank",
					"&7You currently have access to all", "&cZombie Pigman Perks &7To view your perks",
					"&7simply do &9/perks&7!");
		} else {
			Utils.createItemHead(inv, "mhf_pigzombie", 1, 31, "&c&lZombie Pigman Rank &8(&c&lLOCKED&8)",
					"", "&eCollect enough skulls in order to rankup!");
		}
		if (RankManager.getRank(p) >= 8) {
			Utils.createItemHead(inv, "mhf_slime", 1, 32, "&a&lSlime Rank", "&7You currently have access to all",
					"&aSlime Perks &7To view your perks", "&7simply do &9/perks&7!");
		} else {
			Utils.createItemHead(
					inv, "mhf_slime", 1, 32, "&a&lSlime Rank &8(&c&lLOCKED&8)", 
					"", "&eCollect enough skulls in order to rankup!");
		}
		if (RankManager.getRank(p) >= 9) {
			Utils.createItemHead(inv, "mhf_creeper", 1, 33, "&a&lCreeper Rank", "&7You currently have access to all",
					"&aCreeper Perks &7To view your perks", "&7simply do &9/perks&7!");
		} else {
			Utils.createItemHead(
					inv, "mhf_creeper", 1, 33, "&a&lCreeper Rank &8(&c&lLOCKED&8)", 
					"", "&eCollect enough skulls in order to rankup!");
		}
		if (RankManager.getRank(p) >= 10) {
			Utils.createItemHead(inv, "can", 1, 34, "&f&lPan&8&lda &f&lRank", "&7You currently have access to all",
					"&fPan&7da &fPerks &7To view your perks", "&7simply do &9/perks&7!");
		} else {
			Utils.createItemHead(
					inv, "can", 1, 34, "&f&lPan&8&lda &f&lRank &8(&c&lLOCKED&8)",
					"", "&eCollect enough skulls in order to rankup!");
		}
		Utils.createItem(inv, "GRAY_STAINED_GLASS_PANE", 1, 1, "&7", "");
		Utils.createItem(inv, "GRAY_STAINED_GLASS_PANE", 1, 2, "&7", "");
		Utils.createItem(inv, "GRAY_STAINED_GLASS_PANE", 1, 3, "&7", "");
		Utils.createItem(inv, "GRAY_STAINED_GLASS_PANE", 1, 4, "&7", "");
		Utils.createItem(inv, "GRAY_STAINED_GLASS_PANE", 1, 5, "&7", "");
		Utils.createItem(inv, "GRAY_STAINED_GLASS_PANE", 1, 6, "&7", "");
		Utils.createItem(inv, "GRAY_STAINED_GLASS_PANE", 1, 7, "&7", "");
		Utils.createItem(inv, "GRAY_STAINED_GLASS_PANE", 1, 8, "&7", "");
		Utils.createItem(inv, "GRAY_STAINED_GLASS_PANE", 1, 9, "&7", "");
		Utils.createItem(inv, "GRAY_STAINED_GLASS_PANE", 1, 10, "&7", "");
		Utils.createItem(inv, "GRAY_STAINED_GLASS_PANE", 1, 18, "&7", "");
		Utils.createItem(inv, "GRAY_STAINED_GLASS_PANE", 1, 19, "&7", "");
		Utils.createItem(inv, "GRAY_STAINED_GLASS_PANE", 1, 27, "&7", "");
		Utils.createItem(inv, "GRAY_STAINED_GLASS_PANE", 1, 28, "&7", "");
		Utils.createItem(inv, "GRAY_STAINED_GLASS_PANE", 1, 36, "&7", "");
		Utils.createItem(inv, "GRAY_STAINED_GLASS_PANE", 1, 37, "&7", "");
		Utils.createItem(inv, "GRAY_STAINED_GLASS_PANE", 1, 45, "&7", "");
		Utils.createItem(inv, "GRAY_STAINED_GLASS_PANE", 1, 46, "&7", "");
		Utils.createItem(inv, "GRAY_STAINED_GLASS_PANE", 1, 47, "&7", "");
		Utils.createItem(inv, "GRAY_STAINED_GLASS_PANE", 1, 48, "&7", "");
		Utils.createItem(inv, "GRAY_STAINED_GLASS_PANE", 1, 54, "&7", "");
		Utils.createItem(inv, "GRAY_STAINED_GLASS_PANE", 1, 53, "&7", "");
		Utils.createItem(inv, "GRAY_STAINED_GLASS_PANE", 1, 52, "&7", "");

		Utils.createItem(inv, "RED_STAINED_GLASS_PANE", 1, 49, "&c&lClose Menu", "&7Click this button to exit the",
				"&2&lRankup Menu&7!");

		if (RankManager.eligibleToRankup(p) == true) {
			Utils.createGreenRankupItem(inv, "GREEN_STAINED_GLASS_PANE", 1, 51, "&2&lRankup", p);
		} else {
			Utils.createRankupItem(inv, "YELLOW_STAINED_GLASS_PANE", 1, 51, "&6&lRankup", p);
		}
		toReturn.setContents(inv.getContents());
		return toReturn;

	}

	public static void clicked(Player p, int slot, ItemStack clicked, Inventory inv) {
		if (ChatColor.stripColor(clicked.getItemMeta().getDisplayName()).equalsIgnoreCase("Rankup")) {
			RankManager.rankupPlayer(p);
		} else if (ChatColor.stripColor(clicked.getItemMeta().getDisplayName()).equalsIgnoreCase("Close Menu")) {
			p.closeInventory();
		}
	}
}
