package com.vervedev.saplingheads.ui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.vervedev.saplingheads.managers.RankManager;
import com.vervedev.saplingheads.managers.SkullManager;
import com.vervedev.saplingheads.utils.Utils;

public class SkullVaultUI {

	public static Inventory inv;
	public static String inventory_name;
	public static int inv_rows = 6 * 9;

	public static void initialize() {
		inventory_name = Utils.chat("&6&lSkull Vault");

		inv = Bukkit.createInventory(null, inv_rows);
	}

	public static Inventory GUI(Player p) {

		Inventory toReturn = Bukkit.createInventory(null, inv_rows, inventory_name);

		Utils.createItemHead(inv, p.getName(), 1, 50, p.getName() + "'s &eRank Menu", "&7Use this menu to rankup",
				"&7to gain access to special perks", "&7and abilities!", "",
				"&6Current Rank: &e" + RankManager.getRankString(p));

		Utils.createItemHead(inv, "thepoup", 1, 21, "&f&lChicken Skulls",
				"&7You currently have &f" + SkullManager.getChickenSkullAmount(p) + " skulls!", "",
				"&7If you would like to redeem perk credits", "&7click &aRedeem Perk Credits&7!");

		Utils.createItemHead(inv, "Pig", 1, 22, "&d&lPig Skulls",
				"&7You currently have &d" + SkullManager.getPigSkullAmount(p) + " skulls!", "",
				"&7If you would like to redeem perk credits", "&7click &aRedeem Perk Credits&7!");

		Utils.createItemHead(inv, "mhf_sheep", 1, 23, "&7&lSheep Skulls",
				"&7You currently have &7" + SkullManager.getSheepSkullAmount(p) + " skulls!", "",
				"&7If you would like to redeem perk credits", "&7click &aRedeem Perk Credits&7!");

		Utils.createItemHead(inv, "mhf_cow", 1, 24, "&8&lCow Skulls",
				"&7You currently have &8" + SkullManager.getCowSkullAmount(p) + " skulls!", "",
				"&7If you would like to redeem perk credits", "&7click &aRedeem Perk Credits&7!");

		Utils.createItemHead(inv, "mhf_zombie", 1, 25, "&2&lZombie Skulls",
				"&7You currently have &2" + SkullManager.getZombieSkullAmount(p) + " skulls!", "",
				"&7If you would like to redeem perk credits", "&7click &aRedeem Perk Credits&7!");

		Utils.createItemHead(inv, "mhf_skeleton", 1, 30, "&3&lSkeleton Skulls",
				"&7You currently have &3" + SkullManager.getSkeletonSkullAmount(p) + " skulls!", "",
				"&7If you would like to redeem perk credits", "&7click &aRedeem Perk Credits&7!");

		Utils.createItemHead(inv, "mhf_pigzombie", 1, 31, "&c&lZombie Pigman Skulls",
				"&7You currently have &c" + SkullManager.getPigZombieSkullAmount(p) + " skulls!", "",
				"&7If you would like to redeem perk credits", "&7click &aRedeem Perk Credits&7!");

		Utils.createItemHead(inv, "mhf_slime", 1, 32, "&a&lSlime Skulls",
				"&7You currently have &a" + SkullManager.getSlimeSkullAmount(p) + " skulls!", "",
				"&7If you would like to redeem perk credits", "&7click &aRedeem Perk Credits&7!");

		Utils.createItemHead(inv, "mhf_creeper", 1, 33, "&a&lCreeper Skulls",
				"&7You currently have &a" + SkullManager.getCreeperSkullAmount(p) + " skulls!", "",
				"&7If you would like to redeem perk credits", "&7click &aRedeem Perk Credits&7!");

		Utils.createItemHead(inv, "can", 1, 34, "&f&lPan&8&lda &f&lSkulls",
				"&7You currently have &f" + SkullManager.getPandaSkullAmount(p) + " skulls!", "",
				"&7If you would like to redeem perk credits", "&7click &aRedeem Perk Credits&7!");

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

		toReturn.setContents(inv.getContents());
		return toReturn;
	}

	public static void clicked(Player p, int slot, ItemStack clicked, Inventory inv) {
	}
}
