package com.vervedev.saplingheads.utils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import com.vervedev.saplingheads.managers.RankManager;

public class Utils {
	
	public static DecimalFormat formatter = new DecimalFormat("#,###");

	public static String chat(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}

	public static ItemStack createItem(Inventory inv, String materialString, int amount, int invSlot,
			String displayName, String... loreString) {

		ItemStack item;
		List<String> lore = new ArrayList();

		item = new ItemStack(Material.matchMaterial(materialString), amount);

		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(Utils.chat(displayName));
		for (String s : loreString) {
			lore.add(Utils.chat(s));
		}
		meta.setLore(lore);
		item.setItemMeta(meta);

		inv.setItem(invSlot - 1, item);
		return item;
	}
	
	public static ItemStack createRankupItem(Inventory inv, String materialString, int amount, int invSlot,
			String displayName, Player p) {

		ItemStack item;
		List<String> lore = new ArrayList();

		item = new ItemStack(Material.matchMaterial(materialString), amount);

		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(Utils.chat(displayName));
		lore.add(Utils.chat("&7In order to rankup to the next level"));
		lore.add(Utils.chat("&7you must &ecollect &7the following:"));
		lore.add("");
		if (RankManager.getRemainingCash(p) > 0) {
			lore.add(Utils.chat("&e&l$" + Utils.formatter.format(RankManager.getRemainingCash(p)) + " Cash &r&eRemaining"));
		}
		if (RankManager.getRemainingChickenSkulls(p) > 0) {
			lore.add(Utils.chat("&f&l" + Utils.formatter.format(RankManager.getRemainingChickenSkulls(p)) + " Chicken &r&fSkulls Remaining"));
		}
		if (RankManager.getRemainingPigSkulls(p) > 0) {
			lore.add(Utils.chat("&d&l" + Utils.formatter.format(RankManager.getRemainingPigSkulls(p)) + " Pig &r&dSkulls Remaining"));
		}
		if (RankManager.getRemainingSheepSkulls(p) > 0) {
			lore.add(Utils.chat("&7&l" + Utils.formatter.format(RankManager.getRemainingSheepSkulls(p)) + " Sheep &r&7Skulls Remaining"));
		}
		if (RankManager.getRemainingCowSkulls(p) > 0) {
			lore.add(Utils.chat("&8&l" + Utils.formatter.format(RankManager.getRemainingCowSkulls(p)) + " Cow &r&8Skulls Remaining"));
		}
		if (RankManager.getRemainingZombieSkulls(p) > 0) {
			lore.add(Utils.chat("&2&l" + Utils.formatter.format(RankManager.getRemainingZombieSkulls(p)) + " Zombie &r&2Skulls Remaining"));
		}
		if (RankManager.getRemainingSkeletonSkulls(p) > 0) {
			lore.add(Utils.chat("&3&l" + Utils.formatter.format(RankManager.getRemainingSkeletonSkulls(p)) + " Skeleton &r&3Skulls Remaining"));
		}
		if (RankManager.getRemainingPigZombieSkulls(p) > 0) {
			lore.add(Utils.chat("&c&l" + Utils.formatter.format(RankManager.getRemainingPigZombieSkulls(p)) + " Zombie Pigman &r&cSkulls Remaining"));
		}
		if (RankManager.getRemainingSlimeSkulls(p) > 0) {
			lore.add(Utils.chat("&a&l" + Utils.formatter.format(RankManager.getRemainingSlimeSkulls(p)) + " Slime &r&aSkulls Remaining"));
		}
		if (RankManager.getRemainingCreeperSkulls(p) > 0) {
			lore.add(Utils.chat("&a&l" + Utils.formatter.format(RankManager.getRemainingCreeperSkulls(p)) + " Creeper &r&aSkulls Remaining"));
		}
		if (RankManager.getRemainingPandaSkulls(p) > 0) {
			lore.add(Utils.chat("&f&l" + Utils.formatter.format(RankManager.getRemainingPandaSkulls(p)) + " Pan&7&lda &r&aSkulls Remaining"));
		}
		meta.setLore(lore);
		item.setItemMeta(meta);

		inv.setItem(invSlot - 1, item);
		return item;
	}

	public static ItemStack createItemByte(Inventory inv, String materialString, int byteId, int amount, int invSlot,
			String displayName, String... loreString) {

		ItemStack item;
		List<String> lore = new ArrayList();

		item = new ItemStack(Material.matchMaterial(materialString), amount, (short) byteId);

		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(Utils.chat(displayName));
		for (String s : loreString) {
			lore.add(Utils.chat(s));
		}
		meta.setLore(lore);
		item.setItemMeta(meta);

		inv.setItem(invSlot - 1, item);
		return item;
	}

	public static ItemStack createItemHead(Inventory inv, String owner, int amount, int invSlot,
			String displayName, String... loreString) {

		List<String> lore = new ArrayList();

		SkullMeta skull = (SkullMeta) Bukkit.getItemFactory().getItemMeta(Material.PLAYER_HEAD);
		ItemStack stack = new ItemStack(Material.PLAYER_HEAD, 1, (short) 3);
		skull.setDisplayName(Utils.chat(displayName));
		for (String s : loreString) {
			lore.add(Utils.chat(s));
		}
		skull.setLore(lore);
		skull.setOwner(owner);
		stack.setItemMeta(skull);
		inv.setItem(invSlot - 1, stack);
		return stack;
	}
}
