package com.vervedev.saplingheads.utils;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.scheduler.BukkitRunnable;

import com.vervedev.saplingheads.Main;
import com.vervedev.saplingheads.managers.CurrencyManager;
import com.vervedev.saplingheads.managers.PerkManager;
import com.vervedev.saplingheads.managers.RankManager;
import com.vervedev.saplingheads.managers.SkullManager;

public class Utils {

	public static Main plugin;

	public Utils(Main plugin) {
		this.plugin = plugin;
	}

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
			lore.add(Utils
					.chat("&e&l$" + Utils.formatter.format(RankManager.getRemainingCash(p)) + " Cash &r&eRemaining"));
		}
		if (RankManager.getRemainingChickenSkulls(p) > 0) {
			lore.add(Utils.chat("&f&l" + Utils.formatter.format(RankManager.getRemainingChickenSkulls(p))
					+ " Chicken &r&fSkulls Remaining"));
		}
		if (RankManager.getRemainingPigSkulls(p) > 0) {
			lore.add(Utils.chat("&d&l" + Utils.formatter.format(RankManager.getRemainingPigSkulls(p))
					+ " Pig &r&dSkulls Remaining"));
		}
		if (RankManager.getRemainingSheepSkulls(p) > 0) {
			lore.add(Utils.chat("&7&l" + Utils.formatter.format(RankManager.getRemainingSheepSkulls(p))
					+ " Sheep &r&7Skulls Remaining"));
		}
		if (RankManager.getRemainingCowSkulls(p) > 0) {
			lore.add(Utils.chat("&8&l" + Utils.formatter.format(RankManager.getRemainingCowSkulls(p))
					+ " Cow &r&8Skulls Remaining"));
		}
		if (RankManager.getRemainingZombieSkulls(p) > 0) {
			lore.add(Utils.chat("&2&l" + Utils.formatter.format(RankManager.getRemainingZombieSkulls(p))
					+ " Zombie &r&2Skulls Remaining"));
		}
		if (RankManager.getRemainingSkeletonSkulls(p) > 0) {
			lore.add(Utils.chat("&3&l" + Utils.formatter.format(RankManager.getRemainingSkeletonSkulls(p))
					+ " Skeleton &r&3Skulls Remaining"));
		}
		if (RankManager.getRemainingPigZombieSkulls(p) > 0) {
			lore.add(Utils.chat("&c&l" + Utils.formatter.format(RankManager.getRemainingPigZombieSkulls(p))
					+ " Zombie Pigman &r&cSkulls Remaining"));
		}
		if (RankManager.getRemainingSlimeSkulls(p) > 0) {
			lore.add(Utils.chat("&a&l" + Utils.formatter.format(RankManager.getRemainingSlimeSkulls(p))
					+ " Slime &r&aSkulls Remaining"));
		}
		if (RankManager.getRemainingCreeperSkulls(p) > 0) {
			lore.add(Utils.chat("&a&l" + Utils.formatter.format(RankManager.getRemainingCreeperSkulls(p))
					+ " Creeper &r&aSkulls Remaining"));
		}
		if (RankManager.getRemainingPandaSkulls(p) > 0) {
			lore.add(Utils.chat("&f&l" + Utils.formatter.format(RankManager.getRemainingPandaSkulls(p))
					+ " Pan&7&lda &r&aSkulls Remaining"));
		}
		meta.setLore(lore);
		item.setItemMeta(meta);

		inv.setItem(invSlot - 1, item);
		return item;
	}

	public static ItemStack createGreenRankupItem(Inventory inv, String materialString, int amount, int invSlot,
			String displayName, Player p) {

		ItemStack item;
		List<String> lore = new ArrayList();

		item = new ItemStack(Material.matchMaterial(materialString), amount);

		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(Utils.chat(displayName));
		if (RankManager.getRank(p) != 10) {
			lore.add(Utils.chat("&7You are currently eligible to &2rankup&7! The"));
			lore.add(Utils.chat("&7following will be removed from your vault!"));
			lore.add("");
			if (RankManager.getRequiredCash(p) > 0) {
				lore.add(Utils.chat("&e&l$" + Utils.formatter.format(RankManager.getRequiredCash(p)) + " Cash"));
			}
			if (RankManager.getRequiredChickenSkulls(p) > 0) {
				lore.add(Utils.chat("&f&l" + Utils.formatter.format(RankManager.getRequiredChickenSkulls(p))
						+ " Chicken &r&fSkulls"));
			}
			if (RankManager.getRequiredPigSkulls(p) > 0) {
				lore.add(Utils.chat(
						"&d&l" + Utils.formatter.format(RankManager.getRequiredPigSkulls(p)) + " Pig &r&dSkulls"));
			}
			if (RankManager.getRequiredSheepSkulls(p) > 0) {
				lore.add(Utils.chat(
						"&7&l" + Utils.formatter.format(RankManager.getRequiredSheepSkulls(p)) + " Sheep &r&7Skulls"));
			}
			if (RankManager.getRequiredCowSkulls(p) > 0) {
				lore.add(Utils.chat(
						"&8&l" + Utils.formatter.format(RankManager.getRequiredCowSkulls(p)) + " Cow &r&8Skulls"));
			}
			if (RankManager.getRequiredZombieSkulls(p) > 0) {
				lore.add(Utils.chat("&2&l" + Utils.formatter.format(RankManager.getRequiredZombieSkulls(p))
						+ " Zombie &r&2Skulls"));
			}
			if (RankManager.getRequiredSkeletonSkulls(p) > 0) {
				lore.add(Utils.chat("&3&l" + Utils.formatter.format(RankManager.getRequiredSkeletonSkulls(p))
						+ " Skeleton &r&3Skulls"));
			}
			if (RankManager.getRequiredPigZombieSkulls(p) > 0) {
				lore.add(Utils.chat("&c&l" + Utils.formatter.format(RankManager.getRequiredPigZombieSkulls(p))
						+ " Zombie Pigman &r&cSkulls"));
			}
			if (RankManager.getRequiredSlimeSkulls(p) > 0) {
				lore.add(Utils.chat(
						"&a&l" + Utils.formatter.format(RankManager.getRequiredSlimeSkulls(p)) + " Slime &r&aSkulls"));
			}
			if (RankManager.getRequiredCreeperSkulls(p) > 0) {
				lore.add(Utils.chat("&a&l" + Utils.formatter.format(RankManager.getRequiredCreeperSkulls(p))
						+ " Creeper &r&aSkulls"));
			}
			if (RankManager.getRequiredPandaSkulls(p) > 0) {
				lore.add(Utils.chat("&f&l" + Utils.formatter.format(RankManager.getRequiredPandaSkulls(p))
						+ " Pan&7&lda &r&aSkulls"));
			}
			lore.add("");
			lore.add(Utils.chat("&aClick to Proceed to the Next Rank!"));
		} else {
			lore.add(Utils.chat("&7You are currently the &eMAX RANK!"));
			lore.add(Utils.chat("&7stay tuned for more ranks to come!!"));
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

	public static ItemStack createItemHead(Inventory inv, String owner, int amount, int invSlot, String displayName,
			String... loreString) {

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

	public static Location getRandomLocation(Player p, World w, int minValue, int maxValue, int yaw, int pitch) {
		while (true) {
			int xk = (int) (Math.random() * minValue + 1);
			int yk = (int) (Math.random() * maxValue + 1);
			int x = xk;
			int z = yk;
			int y = w.getHighestBlockYAt(x, z) + 1;
			Block highest = w.getBlockAt(x, y - 2, z);
			if (highest.getType() != Material.WATER) {
				Location l = new Location(w, x, y, z);
				l.setYaw(yaw);
				l.setPitch(pitch);
				return l;
			}
		}
	}
	
	public static void resetSeasonStatus() {
		for (OfflinePlayer p : Bukkit.getOfflinePlayers()) {
			RankManager.ranks.remove(p.getUniqueId().toString());
			CurrencyManager.perkCredits.remove(p.getUniqueId().toString());
			SkullManager.chicken.remove(p.getUniqueId().toString() + "chicken");
			SkullManager.pig.remove(p.getUniqueId().toString() + "pig");
			SkullManager.sheep.remove(p.getUniqueId().toString() + "sheep");
			SkullManager.cow.remove(p.getUniqueId().toString() + "cow");
			SkullManager.zombie.remove(p.getUniqueId().toString() + "zombie");
			SkullManager.skeleton.remove(p.getUniqueId().toString() + "skeleton");
			SkullManager.zpigman.remove(p.getUniqueId().toString() + "pigzombie");
			SkullManager.slime.remove(p.getUniqueId().toString() + "slime");
			SkullManager.creeper.remove(p.getUniqueId().toString() + "creeper");
			SkullManager.panda.remove(p.getUniqueId().toString() + "panda");
			PerkManager.perks.remove(p.getUniqueId() + "JellyLegs");
			PerkManager.perks.remove(p.getUniqueId() + "JellyLegs_Enabled");
			PerkManager.perks.remove(p.getUniqueId() + "Nocturnal");
			PerkManager.perks.remove(p.getUniqueId() + "Nocturnal_Enabled");
			Bukkit.broadcastMessage("removed" + p.getName());
		}
	}

	@SuppressWarnings("deprecation")
	public static void saveAllPlayerData() {
		plugin.getServer().getScheduler().scheduleAsyncRepeatingTask(plugin, new Runnable() {
			@Override
			public void run() {
				try {
					for (OfflinePlayer p : Bukkit.getOfflinePlayers()) {
						RankManager.saveRanks(p);
						SkullManager.saveSkullFiles(p);
						PerkManager.savePerks(p);
						CurrencyManager.savePerkCredits(p);
					}
					for (Player op : Bukkit.getOnlinePlayers()) {
						if (op.isOp()) {
							op.sendMessage(Utils.chat("&7&lSaplingHeads &8> &7All Data has been successfully saved!"));
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}, 0, 18000);
	}
}
