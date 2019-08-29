package com.vervedev.saplingheads.ui;

import java.text.DecimalFormat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.vervedev.saplingheads.managers.RankManager;
import com.vervedev.saplingheads.managers.SkullManager;
import com.vervedev.saplingheads.utils.Utils;

public class RankupUI {

	public static Inventory inv;
	public static String inventory_name;
	public static int inv_rows = 6 * 9;

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
			if (!RankManager.eligibleToRankup(p)) {
				Utils.createItemHead(
						inv, "thepoup", 1, 21, "&f&lChicken Rank &8(&c&lLOCKED&8)", "&7This rank requires a total of &f"
								+ RankManager.getRequiredChickenSkulls(p) + " chicken skulls to rankup!",
						"", "&eCollect enough skulls in order to rankup!");
			} else {
				Utils.createItemHead(inv, "thepoup", 1, 21, "&f&lChicken Rank &8(&c&lLOCKED&8)",
						"&7You currently have &f " + RankManager.getRequiredChickenSkulls(p) + " skulls!", "",
						"&eCollect enough skulls in order to rankup!");
			}
		}

		if (RankManager.getRank(p) >= 2) {
			Utils.createItemHead(inv, "Pig", 1, 22, "&d&lPig Rank", "&7You currently have access to all",
					"&dPig Perks &7To view your perks", "&7simply do &9perks&7!");
		} else {
			Utils.createItemHead(inv, "Pig", 1, 22, "&d&lPig Rank &8(&c&lLOCKED&8)",
					"&7You currently have &f " + RankManager.getRequiredPigSkulls(p) + " skulls!", "",
					"&eCollect enough skulls in order to rankup!");
		}
		if (RankManager.getRank(p) >= 3) {
			Utils.createItemHead(inv, "mhf_sheep", 1, 23, "&7&lSheep Rank", "&7You currently have access to all",
					"&7Sheep Perks &7To view your perks", "&7simply do &9/perks&7!");
		} else {
			Utils.createItemHead(inv, "mhf_sheep", 1, 23, "&7&lSheep Rank &8(&c&lLOCKED&8)",
					"&7You currently have &f " + RankManager.getRequiredSheepSkulls(p) + " skulls!", "",
					"&eCollect enough skulls in order to rankup!");
		}
		if (RankManager.getRank(p) >= 4) {
			Utils.createItemHead(inv, "mhf_cow", 1, 24, "&8&lCow Rank",
					"&7You currently have &8" + SkullManager.getCowSkullAmount(p) + " skulls!", "",
					"&7If you would like to redeem perk credits", "&7click &aRedeem Perk Credits&7!");
		} else {
			Utils.createItemHead(inv, "mhf_cow", 1, 24, "&8&lCow Rank &8(&c&lLOCKED&8)",
					"&7You currently have &8" + SkullManager.getCowSkullAmount(p) + " skulls!", "",
					"&7If you would like to redeem perk credits", "&7click &aRedeem Perk Credits&7!");
		}
		if (RankManager.getRank(p) >= 5) {
			Utils.createItemHead(inv, "mhf_zombie", 1, 25, "&2&lZombie Rank",
					"&7You currently have &2" + SkullManager.getZombieSkullAmount(p) + " skulls!", "",
					"&7If you would like to redeem perk credits", "&7click &aRedeem Perk Credits&7!");
		} else {
			Utils.createItemHead(inv, "mhf_zombie", 1, 25, "&2&lZombie Rank &8(&c&lLOCKED&8)",
					"&7You currently have &2" + SkullManager.getZombieSkullAmount(p) + " skulls!", "",
					"&7If you would like to redeem perk credits", "&7click &aRedeem Perk Credits&7!");
		}
		if (RankManager.getRank(p) >= 6) {
			Utils.createItemHead(inv, "mhf_skeleton", 1, 30, "&3&lSkeleton Rank",
					"&7You currently have &3" + SkullManager.getSkeletonSkullAmount(p) + " skulls!", "",
					"&7If you would like to redeem perk credits", "&7click &aRedeem Perk Credits&7!");
		} else {
			Utils.createItemHead(inv, "mhf_skeleton", 1, 30, "&3&lSkeleton Rank &8(&c&lLOCKED&8)",
					"&7You currently have &3" + SkullManager.getSkeletonSkullAmount(p) + " skulls!", "",
					"&7If you would like to redeem perk credits", "&7click &aRedeem Perk Credits&7!");
		}
		if (RankManager.getRank(p) >= 7) {
			Utils.createItemHead(inv, "mhf_pigzombie", 1, 31, "&c&lZombie Pigman Rank",
					"&7You currently have &c" + SkullManager.getPigZombieSkullAmount(p) + " skulls!", "",
					"&7If you would like to redeem perk credits", "&7click &aRedeem Perk Credits&7!");
		} else {
			Utils.createItemHead(inv, "mhf_pigzombie", 1, 31, "&c&lZombie Pigman Rank &8(&c&lLOCKED&8)",
					"&7You currently have &c" + SkullManager.getPigZombieSkullAmount(p) + " skulls!", "",
					"&7If you would like to redeem perk credits", "&7click &aRedeem Perk Credits&7!");
		}
		if (RankManager.getRank(p) >= 8) {
			Utils.createItemHead(inv, "mhf_slime", 1, 32, "&a&lSlime Rank",
					"&7You currently have &a" + SkullManager.getSlimeSkullAmount(p) + " skulls!", "",
					"&7If you would like to redeem perk credits", "&7click &aRedeem Perk Credits&7!");
		} else {
			Utils.createItemHead(inv, "mhf_slime", 1, 32, "&a&lSlime Rank &8(&c&lLOCKED&8)",
					"&7You currently have &a" + SkullManager.getSlimeSkullAmount(p) + " skulls!", "",
					"&7If you would like to redeem perk credits", "&7click &aRedeem Perk Credits&7!");
		}
		if (RankManager.getRank(p) >= 9) {
			Utils.createItemHead(inv, "mhf_creeper", 1, 33, "&a&lCreeper Rank",
					"&7You currently have &a" + SkullManager.getCreeperSkullAmount(p) + " skulls!", "",
					"&7If you would like to redeem perk credits", "&7click &aRedeem Perk Credits&7!");
		} else {
			Utils.createItemHead(inv, "mhf_creeper", 1, 33, "&a&lCreeper Rank &8(&c&lLOCKED&8)",
					"&7You currently have &a" + SkullManager.getCreeperSkullAmount(p) + " skulls!", "",
					"&7If you would like to redeem perk credits", "&7click &aRedeem Perk Credits&7!");
		}
		if (RankManager.getRank(p) >= 10) {
			Utils.createItemHead(inv, "can", 1, 34, "&f&lPan&8&lda &f&lRank",
					"&7You currently have &f" + SkullManager.getPandaSkullAmount(p) + " skulls!", "",
					"&7If you would like to redeem perk credits", "&7click &aRedeem Perk Credits&7!");
		} else {
			Utils.createItemHead(inv, "can", 1, 34, "&f&lPan&8&lda &f&lRank &8(&c&lLOCKED&8)",
					"&7You currently have &f" + SkullManager.getPandaSkullAmount(p) + " skulls!", "",
					"&7If you would like to redeem perk credits", "&7click &aRedeem Perk Credits&7!");
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
			Utils.createItem(inv, "GREEN_STAINED_GLASS_PANE", 1, 51, "&2&lRankup",
					"&7You are currently eligible to &2&lRankup&7! Click this",
					"&7button to advance to the next level!");
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
