package com.vervedev.saplingheads.managers;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.vervedev.saplingheads.Main;
import com.vervedev.saplingheads.utils.Utils;

public class PlayerManager {

	private Main plugin;
	
	public static HashMap<String, Integer> playerTotalSkulls = new HashMap<String, Integer>();

	public PlayerManager(Main plugin) {
		this.plugin = plugin;
	}

	public static boolean playerInventoryFull(Player p) {
		if (p.getInventory().firstEmpty() == -1) {
			return true;
		}
		return false;
	}

	public static boolean spawnerSlotAvailable(Player p, String spawnerDisplayName) {
		for (ItemStack item : p.getInventory().getContents()) {
			if (item != null) {
				if (item.hasItemMeta()) {
					if (ChatColor.stripColor(item.getItemMeta().getDisplayName()).contains(spawnerDisplayName)) {
						if (item.getAmount() != 64) {
							p.sendMessage(Utils.chat("found item"));
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	public static void calculateTotalSkulls() {
		for (OfflinePlayer p : Bukkit.getOfflinePlayers()) {
			playerTotalSkulls.put(p.getUniqueId().toString(), getTotalSkulls(p));
		}
	}

	public static int getTotalSkulls(OfflinePlayer p) {
		return SkullManager.getChickenSkullAmount(p) + SkullManager.getPigSkullAmount(p)
				+ SkullManager.getSheepSkullAmount(p) + SkullManager.getCowSkullAmount(p)
				+ SkullManager.getZombieSkullAmount(p) + SkullManager.getSkeletonSkullAmount(p)
				+ SkullManager.getPigZombieSkullAmount(p) + SkullManager.getSlimeSkullAmount(p)
				+ SkullManager.getCreeperSkullAmount(p) + SkullManager.getPandaSkullAmount(p);
	}
}
