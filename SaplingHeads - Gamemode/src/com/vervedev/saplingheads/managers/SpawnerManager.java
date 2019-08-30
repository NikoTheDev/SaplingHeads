package com.vervedev.saplingheads.managers;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.vervedev.saplingheads.Main;
import com.vervedev.saplingheads.utils.Utils;

public class SpawnerManager implements Listener {

	public static Main plugin;

	public SpawnerManager(Main plugin) {
		this.plugin = plugin;

		Bukkit.getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {

		Block block = e.getBlock();
		Material blockMaterial = block.getType();
		Player p = e.getPlayer();
		if (blockMaterial == Material.SPAWNER) {
			List<String> blacklist = plugin.getConfig().getStringList("Spawner_Item_Blacklist.items");
			for (String blackListed : blacklist) {
				if (p.getItemInHand().getType() == Material.getMaterial(blackListed.toString())) {
					p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.spawners.punch_message")));
					e.setCancelled(true);
					return;
				}

			}
			CreatureSpawner spawner = (CreatureSpawner) block.getState();
			if (spawner.getSpawnedType() == EntityType.CHICKEN) {
				if (RankManager.getRank(p) >= 1) {
					e.getBlock().getLocation().getWorld().dropItemNaturally(block.getLocation(),
							spawnerType("Chicken"));
				} else {
					e.setCancelled(true);
					p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.spawners.low_rank")
							.replaceAll("<needed_rank>", "&f&lChicken&r")));
				}
			} else if (spawner.getSpawnedType() == EntityType.PIG) {
				if (RankManager.getRank(p) >= 2) {
					e.getBlock().getLocation().getWorld().dropItemNaturally(block.getLocation(), spawnerType("Pig"));
				} else {
					e.setCancelled(true);
					p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.spawners.low_rank")
							.replaceAll("<needed_rank>", "&d&lPig&r")));
				}
			} else if (spawner.getSpawnedType() == EntityType.SHEEP) {
				if (RankManager.getRank(p) >= 3) {
					e.getBlock().getLocation().getWorld().dropItemNaturally(block.getLocation(), spawnerType("Sheep"));
				} else {
					e.setCancelled(true);
					p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.spawners.low_rank")
							.replaceAll("<needed_rank>", "&7&lSheep&r")));
				}
			} else if (spawner.getSpawnedType() == EntityType.COW) {
				if (RankManager.getRank(p) >= 4) {
					e.getBlock().getLocation().getWorld().dropItemNaturally(block.getLocation(), spawnerType("Cow"));
				} else {
					e.setCancelled(true);
					p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.spawners.low_rank")
							.replaceAll("<needed_rank>", "&8&lCow&r")));
				}
			} else if (spawner.getSpawnedType() == EntityType.ZOMBIE) {
				if (RankManager.getRank(p) >= 5) {
					e.getBlock().getLocation().getWorld().dropItemNaturally(block.getLocation(), spawnerType("Zombie"));
				} else {
					e.setCancelled(true);
					p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.spawners.low_rank")
							.replaceAll("<needed_rank>", "&2&lZombie&r")));
				}
			} else if (spawner.getSpawnedType() == EntityType.SKELETON) {
				if (RankManager.getRank(p) >= 6) {
					e.getBlock().getLocation().getWorld().dropItemNaturally(block.getLocation(),
							spawnerType("Skeleton"));
				} else {
					e.setCancelled(true);
					p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.spawners.low_rank")
							.replaceAll("<needed_rank>", "&3&lSkeleton&r")));
				}
			} else if (spawner.getSpawnedType() == EntityType.PIG_ZOMBIE) {
				if (RankManager.getRank(p) >= 7) {
					e.getBlock().getLocation().getWorld().dropItemNaturally(block.getLocation(),
							spawnerType("PigZombie"));
				} else {
					e.setCancelled(true);
					p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.spawners.low_rank")
							.replaceAll("<needed_rank>", "&c&lZombie Pigman&r")));
				}
			} else if (spawner.getSpawnedType() == EntityType.SLIME) {
				if (RankManager.getRank(p) >= 8) {
					e.getBlock().getLocation().getWorld().dropItemNaturally(block.getLocation(), spawnerType("Slime"));
				} else {
					e.setCancelled(true);
					p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.spawners.low_rank")
							.replaceAll("<needed_rank>", "&a&lSlime&r")));
				}
			} else if (spawner.getSpawnedType() == EntityType.CREEPER) {
				if (RankManager.getRank(p) >= 9) {
					e.getBlock().getLocation().getWorld().dropItemNaturally(block.getLocation(),
							spawnerType("Creeper"));
				} else {
					e.setCancelled(true);
					p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.spawners.low_rank")
							.replaceAll("<needed_rank>", "&a&lCreeper&r")));
				}
			} else if (spawner.getSpawnedType() == EntityType.PANDA) {
				if (RankManager.getRank(p) == 10) {
					e.getBlock().getLocation().getWorld().dropItemNaturally(block.getLocation(), spawnerType("Panda"));
				} else {
					e.setCancelled(true);
					p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.spawners.low_rank")
							.replaceAll("<needed_rank>", "&f&lPan&7&lda&r")));
				}
			}
		}
	}

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {

		Block block = e.getBlock();
		Material blockMaterial = block.getType();
		Player p = e.getPlayer();
		if (blockMaterial == Material.SPAWNER) {
			CreatureSpawner spawner = (CreatureSpawner) block.getState();
			if (ChatColor.stripColor(p.getItemInHand().getItemMeta().getDisplayName())
					.equalsIgnoreCase(Utils.chat("Chicken Spawner"))) {
				if (RankManager.getRank(p) >= 1) {
					spawner.setSpawnedType(EntityType.CHICKEN);
					spawner.update();
				} else {
					e.setCancelled(true);
					p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.spawners.low_rank_place")
							.replace("<needed_rank>", "&f&lChicken&r")));
				}
			} else if (ChatColor.stripColor(p.getItemInHand().getItemMeta().getDisplayName())
					.equalsIgnoreCase("Pig Spawner")) {
				if (RankManager.getRank(p) >= 2) {
					spawner.setSpawnedType(EntityType.PIG);
					spawner.update();
				} else {
					e.setCancelled(true);
					p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.spawners.low_rank_place")
							.replace("<needed_rank>", "&d&lPig&r")));
				}
			} else if (ChatColor.stripColor(p.getItemInHand().getItemMeta().getDisplayName())
					.equalsIgnoreCase("Sheep Spawner")) {
				if (RankManager.getRank(p) >= 3) {
					spawner.setSpawnedType(EntityType.SHEEP);
					spawner.update();
				} else {
					e.setCancelled(true);
					p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.spawners.low_rank_place")
							.replace("<needed_rank>", "&7&lSheep&r")));
				}
			} else if (ChatColor.stripColor(p.getItemInHand().getItemMeta().getDisplayName())
					.equalsIgnoreCase("Cow Spawner")) {
				if (RankManager.getRank(p) >= 4) {
					spawner.setSpawnedType(EntityType.COW);
					spawner.update();
				} else {
					e.setCancelled(true);
					p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.spawners.low_rank_place")
							.replace("<needed_rank>", "&8&lCow&r")));
				}
			} else if (ChatColor.stripColor(p.getItemInHand().getItemMeta().getDisplayName())
					.equalsIgnoreCase("Zombie Spawner")) {
				if (RankManager.getRank(p) >= 5) {
					spawner.setSpawnedType(EntityType.ZOMBIE);
					spawner.update();
				} else {
					e.setCancelled(true);
					p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.spawners.low_rank_place")
							.replace("<needed_rank>", "&2&lZombie&r")));
				}
			} else if (ChatColor.stripColor(p.getItemInHand().getItemMeta().getDisplayName())
					.equalsIgnoreCase("Skeleton Spawner")) {
				if (RankManager.getRank(p) >= 6) {
					spawner.setSpawnedType(EntityType.SKELETON);
					spawner.update();
				} else {
					e.setCancelled(true);
					p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.spawners.low_rank_place")
							.replace("<needed_rank>", "&3&lSkeleton&r")));
				}
			} else if (ChatColor.stripColor(p.getItemInHand().getItemMeta().getDisplayName())
					.equalsIgnoreCase("Zombie Pigman Spawner")) {
				if (RankManager.getRank(p) >= 7) {
					spawner.setSpawnedType(EntityType.PIG_ZOMBIE);
					spawner.update();
				} else {
					e.setCancelled(true);
					p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.spawners.low_rank_place")
							.replace("<needed_rank>", "&c&lZombie Pigman&r")));
				}
			} else if (ChatColor.stripColor(p.getItemInHand().getItemMeta().getDisplayName())
					.equalsIgnoreCase("Slime Spawner")) {
				if (RankManager.getRank(p) >= 8) {
					spawner.setSpawnedType(EntityType.SLIME);
					spawner.update();
				} else {
					e.setCancelled(true);
					p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.spawners.low_rank_place")
							.replace("<needed_rank>", "&a&lSlime&r")));
				}
			} else if (ChatColor.stripColor(p.getItemInHand().getItemMeta().getDisplayName())
					.equalsIgnoreCase("Creeper Spawner")) {
				if (RankManager.getRank(p) >= 9) {
					spawner.setSpawnedType(EntityType.CREEPER);
					spawner.update();
				} else {
					e.setCancelled(true);
					p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.spawners.low_rank_place")
							.replace("<needed_rank>", "&a&lSlime&r")));
				}
			} else if (ChatColor.stripColor(p.getItemInHand().getItemMeta().getDisplayName())
					.equalsIgnoreCase("Panda Spawner")) {
				if (RankManager.getRank(p) == 10) {
					spawner.setSpawnedType(EntityType.PANDA);
					spawner.update();
				} else {
					e.setCancelled(true);
					p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.spawners.low_rank_place")
							.replace("<needed_rank>", "&f&lPan&7&lda&r")));
				}
			} else if (ChatColor.stripColor(p.getItemInHand().getItemMeta().getDisplayName())
					.equalsIgnoreCase("Blank Spawner")) {
				e.setCancelled(true);
				p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.spawners.blank")));
			}
		}
	}

	public static ItemStack spawnerType(String spawnerType) {
		if (spawnerType.equalsIgnoreCase("Chicken")) {

			List<String> lore = new ArrayList<String>();

			ItemMeta spawnerMeta = (ItemMeta) Bukkit.getItemFactory().getItemMeta(Material.SPAWNER);
			ItemStack spawner = new ItemStack(Material.SPAWNER, 1);
			spawnerMeta.setDisplayName(Utils.chat("&f&lChicken Spawner"));
			lore.add(Utils.chat("&7You must have &fChicken &7unlocked in"));
			lore.add(Utils.chat("&7order to place this spawner! Do &e/rankup"));
			lore.add(Utils.chat("&7to check if you are eligble to rankup!"));
			spawnerMeta.setLore(lore);
			spawner.setItemMeta(spawnerMeta);

			return spawner;
		} else if (spawnerType.equalsIgnoreCase("Pig")) {
			List<String> lore = new ArrayList<String>();

			ItemMeta spawnerMeta = (ItemMeta) Bukkit.getItemFactory().getItemMeta(Material.SPAWNER);
			ItemStack spawner = new ItemStack(Material.SPAWNER, 1);
			spawnerMeta.setDisplayName(Utils.chat("&d&lPig Spawner"));
			lore.add(Utils.chat("&7You must have &dPig &7unlocked in"));
			lore.add(Utils.chat("&7order to place this spawner! Do &e/rankup"));
			lore.add(Utils.chat("&7to check if you are eligble to rankup!"));
			spawnerMeta.setLore(lore);
			spawner.setItemMeta(spawnerMeta);

			return spawner;
		} else if (spawnerType.equalsIgnoreCase("Sheep")) {
			List<String> lore = new ArrayList<String>();

			ItemMeta spawnerMeta = (ItemMeta) Bukkit.getItemFactory().getItemMeta(Material.SPAWNER);
			ItemStack spawner = new ItemStack(Material.SPAWNER, 1);
			spawnerMeta.setDisplayName(Utils.chat("&7&lSheep Spawner"));
			lore.add(Utils.chat("&7You must have &7Sheep &7unlocked in"));
			lore.add(Utils.chat("&7order to place this spawner! Do &e/rankup"));
			lore.add(Utils.chat("&7to check if you are eligble to rankup!"));
			spawnerMeta.setLore(lore);
			spawner.setItemMeta(spawnerMeta);

			return spawner;
		} else if (spawnerType.equalsIgnoreCase("Cow")) {
			List<String> lore = new ArrayList<String>();

			ItemMeta spawnerMeta = (ItemMeta) Bukkit.getItemFactory().getItemMeta(Material.SPAWNER);
			ItemStack spawner = new ItemStack(Material.SPAWNER, 1);
			spawnerMeta.setDisplayName(Utils.chat("&8&lCow Spawner"));
			lore.add(Utils.chat("&7You must have &8Cow &7unlocked in"));
			lore.add(Utils.chat("&7order to place this spawner! Do &e/rankup"));
			lore.add(Utils.chat("&7to check if you are eligble to rankup!"));
			spawnerMeta.setLore(lore);
			spawner.setItemMeta(spawnerMeta);

			return spawner;
		} else if (spawnerType.equalsIgnoreCase("Zombie")) {
			List<String> lore = new ArrayList<String>();

			ItemMeta spawnerMeta = (ItemMeta) Bukkit.getItemFactory().getItemMeta(Material.SPAWNER);
			ItemStack spawner = new ItemStack(Material.SPAWNER, 1);
			spawnerMeta.setDisplayName(Utils.chat("&2&lZombie Spawner"));
			lore.add(Utils.chat("&7You must have &2Zombie &7unlocked in"));
			lore.add(Utils.chat("&7order to place this spawner! Do &e/rankup"));
			lore.add(Utils.chat("&7to check if you are eligble to rankup!"));
			spawnerMeta.setLore(lore);
			spawner.setItemMeta(spawnerMeta);

			return spawner;
		} else if (spawnerType.equalsIgnoreCase("Skeleton")) {
			List<String> lore = new ArrayList<String>();

			ItemMeta spawnerMeta = (ItemMeta) Bukkit.getItemFactory().getItemMeta(Material.SPAWNER);
			ItemStack spawner = new ItemStack(Material.SPAWNER, 1);
			spawnerMeta.setDisplayName(Utils.chat("&3&lSkeleton Spawner"));
			lore.add(Utils.chat("&7You must have &3Skeleton &7unlocked in"));
			lore.add(Utils.chat("&7order to place this spawner! Do &e/rankup"));
			lore.add(Utils.chat("&7to check if you are eligble to rankup!"));
			spawnerMeta.setLore(lore);
			spawner.setItemMeta(spawnerMeta);

			return spawner;
		} else if (spawnerType.equalsIgnoreCase("PigZombie")) {
			List<String> lore = new ArrayList<String>();

			ItemMeta spawnerMeta = (ItemMeta) Bukkit.getItemFactory().getItemMeta(Material.SPAWNER);
			ItemStack spawner = new ItemStack(Material.SPAWNER, 1);
			spawnerMeta.setDisplayName(Utils.chat("&c&lZombie Pigman Spawner"));
			lore.add(Utils.chat("&7You must have &cZombie Pigman &7unlocked in"));
			lore.add(Utils.chat("&7order to place this spawner! Do &e/rankup"));
			lore.add(Utils.chat("&7to check if you are eligble to rankup!"));
			spawnerMeta.setLore(lore);
			spawner.setItemMeta(spawnerMeta);

			return spawner;
		} else if (spawnerType.equalsIgnoreCase("Slime")) {
			List<String> lore = new ArrayList<String>();

			ItemMeta spawnerMeta = (ItemMeta) Bukkit.getItemFactory().getItemMeta(Material.SPAWNER);
			ItemStack spawner = new ItemStack(Material.SPAWNER, 1);
			spawnerMeta.setDisplayName(Utils.chat("&a&lSlime Spawner"));
			lore.add(Utils.chat("&7You must have &aSlime &7unlocked in"));
			lore.add(Utils.chat("&7order to place this spawner! Do &e/rankup"));
			lore.add(Utils.chat("&7to check if you are eligble to rankup!"));
			spawnerMeta.setLore(lore);
			spawner.setItemMeta(spawnerMeta);

			return spawner;
		} else if (spawnerType.equalsIgnoreCase("Creeper")) {
			List<String> lore = new ArrayList<String>();

			ItemMeta spawnerMeta = (ItemMeta) Bukkit.getItemFactory().getItemMeta(Material.SPAWNER);
			ItemStack spawner = new ItemStack(Material.SPAWNER, 1);
			spawnerMeta.setDisplayName(Utils.chat("&a&lCreeper Spawner"));
			lore.add(Utils.chat("&7You must have &aCreeper &7unlocked in"));
			lore.add(Utils.chat("&7order to place this spawner! Do &e/rankup"));
			lore.add(Utils.chat("&7to check if you are eligble to rankup!"));
			spawnerMeta.setLore(lore);
			spawner.setItemMeta(spawnerMeta);

			return spawner;
		} else if (spawnerType.equalsIgnoreCase("Panda")) {
			List<String> lore = new ArrayList<String>();

			ItemMeta spawnerMeta = (ItemMeta) Bukkit.getItemFactory().getItemMeta(Material.SPAWNER);
			ItemStack spawner = new ItemStack(Material.SPAWNER, 1);
			spawnerMeta.setDisplayName(Utils.chat("&f&lPan&7&lda &f&lSpawner"));
			lore.add(Utils.chat("&7You must have &f&lPan&7&lda &7unlocked in"));
			lore.add(Utils.chat("&7order to place this spawner! Do &e/rankup"));
			lore.add(Utils.chat("&7to check if you are eligble to rankup!"));
			spawnerMeta.setLore(lore);
			spawner.setItemMeta(spawnerMeta);

			return spawner;
		} else if (spawnerType.equalsIgnoreCase("blank")) {
			List<String> lore = new ArrayList<String>();

			ItemMeta spawnerMeta = (ItemMeta) Bukkit.getItemFactory().getItemMeta(Material.SPAWNER);
			ItemStack spawner = new ItemStack(Material.SPAWNER, 1);
			spawnerMeta.setDisplayName(Utils.chat("&7&lBlank Spawner"));
			lore.add(Utils.chat("&eCraft a spawner &7by surrounding this"));
			lore.add(Utils.chat("&7blank spawner with &astacks &7of your"));
			lore.add(Utils.chat("&7preferred spawner mob skulls!"));
			spawnerMeta.setLore(lore);
			spawner.setItemMeta(spawnerMeta);

			return spawner;
		} else {
			System.out.print(Utils.chat("&eSaplingHeads - Invalid spawner type in RankManager"));
			return null;
		}
	}

	public static int getChickenSpawnerPrice() {
		return plugin.getConfig().getInt("SpawnerShop.prices.chicken");
	}

	public static int getPigSpawnerPrice() {
		return plugin.getConfig().getInt("SpawnerShop.prices.pig");
	}

	public static int getSheepSpawnerPrice() {
		return plugin.getConfig().getInt("SpawnerShop.prices.sheep");
	}

	public static int getCowSpawnerPrice() {
		return plugin.getConfig().getInt("SpawnerShop.prices.cow");
	}

	public static int getZombieSpawnerPrice() {
		return plugin.getConfig().getInt("SpawnerShop.prices.zombie");
	}

	public static int getSkeletonSpawnerPrice() {
		return plugin.getConfig().getInt("SpawnerShop.prices.skeleton");
	}

	public static int getPigZombieSpawnerPrice() {
		return plugin.getConfig().getInt("SpawnerShop.prices.pigzombie");
	}

	public static int getSlimeSpawnerPrice() {
		return plugin.getConfig().getInt("SpawnerShop.prices.slime");
	}

	public static int getCreeperSpawnerPrice() {
		return plugin.getConfig().getInt("SpawnerShop.prices.creeper");
	}

	public static int getPandaSpawnerPrice() {
		return plugin.getConfig().getInt("SpawnerShop.prices.panda");
	}
}
