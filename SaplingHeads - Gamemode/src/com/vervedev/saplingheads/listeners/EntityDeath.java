package com.vervedev.saplingheads.listeners;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import com.vervedev.saplingheads.Main;
import com.vervedev.saplingheads.managers.SkullManager;
import com.vervedev.saplingheads.utils.Utils;

public class EntityDeath implements Listener {

	private Main plugin;

	public EntityDeath(Main plugin) {
		this.plugin = plugin;

		Bukkit.getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onChickenDeath(EntityDeathEvent e) {

		if (e.getEntity().getType() == EntityType.CHICKEN) {
			List<String> lore = new ArrayList<String>();

			SkullMeta skull = (SkullMeta) Bukkit.getItemFactory().getItemMeta(Material.PLAYER_HEAD);
			ItemStack stack = new ItemStack(Material.PLAYER_HEAD, 1, (short) 3);
			skull.setOwner("ThePoup");
			skull.setDisplayName(Utils.chat("&f&lChicken Skull"));
			lore.clear();
			lore.add(Utils.chat("&7Do /rankup to open the main menu"));
			lore.add(Utils.chat("&7click &evault skulls &7to vault"));
			lore.add(Utils.chat("&7all of the skulls in your inventory!"));
			skull.setLore(lore);
			stack.setItemMeta(skull);
			e.getEntity().getLocation().getWorld().dropItemNaturally(e.getEntity().getLocation(), stack);
		} else if (e.getEntity().getType() == EntityType.PIG) {
			List<String> lore = new ArrayList<String>();

			SkullMeta skull = (SkullMeta) Bukkit.getItemFactory().getItemMeta(Material.PLAYER_HEAD);
			ItemStack stack = new ItemStack(Material.PLAYER_HEAD, 1, (short) 3);
			skull.setOwner("Pig");
			skull.setDisplayName(Utils.chat("&d&lPig Skull"));
			lore.clear();
			lore.add(Utils.chat("&7Do /rankup to open the main menu"));
			lore.add(Utils.chat("&7click &evault skulls &7to vault"));
			lore.add(Utils.chat("&7all of the skulls in your inventory!"));
			skull.setLore(lore);
			stack.setItemMeta(skull);
			e.getEntity().getLocation().getWorld().dropItemNaturally(e.getEntity().getLocation(), stack);
		} else if (e.getEntity().getType() == EntityType.SHEEP) {
			List<String> lore = new ArrayList<String>();

			SkullMeta skull = (SkullMeta) Bukkit.getItemFactory().getItemMeta(Material.PLAYER_HEAD);
			ItemStack stack = new ItemStack(Material.PLAYER_HEAD, 1, (short) 3);
			skull.setOwner("mhf_sheep");
			skull.setDisplayName(Utils.chat("&7&lSheep Skull"));
			lore.clear();
			lore.add(Utils.chat("&7Do /rankup to open the main menu"));
			lore.add(Utils.chat("&7click &evault skulls &7to vault"));
			lore.add(Utils.chat("&7all of the skulls in your inventory!"));
			skull.setLore(lore);
			stack.setItemMeta(skull);
			e.getEntity().getLocation().getWorld().dropItemNaturally(e.getEntity().getLocation(), stack);
		} else if (e.getEntity().getType() == EntityType.COW) {
			List<String> lore = new ArrayList<String>();

			SkullMeta skull = (SkullMeta) Bukkit.getItemFactory().getItemMeta(Material.PLAYER_HEAD);
			ItemStack stack = new ItemStack(Material.PLAYER_HEAD, 1, (short) 3);
			skull.setOwner("Cow");
			skull.setDisplayName(Utils.chat("&8&lCow Skull"));
			lore.clear();
			lore.add(Utils.chat("&7Do /rankup to open the main menu"));
			lore.add(Utils.chat("&7click &evault skulls &7to vault"));
			lore.add(Utils.chat("&7all of the skulls in your inventory!"));
			skull.setLore(lore);
			stack.setItemMeta(skull);
			e.getEntity().getLocation().getWorld().dropItemNaturally(e.getEntity().getLocation(), stack);
		} else if (e.getEntity().getType() == EntityType.ZOMBIE) {
			List<String> lore = new ArrayList<String>();

			SkullMeta skull = (SkullMeta) Bukkit.getItemFactory().getItemMeta(Material.PLAYER_HEAD);
			ItemStack stack = new ItemStack(Material.PLAYER_HEAD, 1, (short) 3);
			skull.setOwner("zombie");
			skull.setDisplayName(Utils.chat("&2&lZombie Skull"));
			lore.clear();
			lore.add(Utils.chat("&7Do /rankup to open the main menu"));
			lore.add(Utils.chat("&7click &evault skulls &7to vault"));
			lore.add(Utils.chat("&7all of the skulls in your inventory!"));
			skull.setLore(lore);
			stack.setItemMeta(skull);
			e.getEntity().getLocation().getWorld().dropItemNaturally(e.getEntity().getLocation(), stack);
		} else if (e.getEntity().getType() == EntityType.SKELETON) {
			List<String> lore = new ArrayList<String>();

			SkullMeta skull = (SkullMeta) Bukkit.getItemFactory().getItemMeta(Material.PLAYER_HEAD);
			ItemStack stack = new ItemStack(Material.PLAYER_HEAD, 1, (short) 3);
			skull.setOwner("skeleton");
			skull.setDisplayName(Utils.chat("&3&lSkeleton Skull"));
			lore.clear();
			lore.add(Utils.chat("&7Do /rankup to open the main menu"));
			lore.add(Utils.chat("&7click &evault skulls &7to vault"));
			lore.add(Utils.chat("&7all of the skulls in your inventory!"));
			skull.setLore(lore);
			stack.setItemMeta(skull);
			e.getEntity().getLocation().getWorld().dropItemNaturally(e.getEntity().getLocation(), stack);
		} else if (e.getEntity().getType() == EntityType.PIG_ZOMBIE) {
			List<String> lore = new ArrayList<String>();

			SkullMeta skull = (SkullMeta) Bukkit.getItemFactory().getItemMeta(Material.PLAYER_HEAD);
			ItemStack stack = new ItemStack(Material.PLAYER_HEAD, 1, (short) 3);
			skull.setOwner("pigman");
			skull.setDisplayName(Utils.chat("&c&lZombie Pigman Skull"));
			lore.clear();
			lore.add(Utils.chat("&7Do /rankup to open the main menu"));
			lore.add(Utils.chat("&7click &evault skulls &7to vault"));
			lore.add(Utils.chat("&7all of the skulls in your inventory!"));
			skull.setLore(lore);
			stack.setItemMeta(skull);
			e.getEntity().getLocation().getWorld().dropItemNaturally(e.getEntity().getLocation(), stack);
		} else if (e.getEntity().getType() == EntityType.SLIME) {
			List<String> lore = new ArrayList<String>();

			SkullMeta skull = (SkullMeta) Bukkit.getItemFactory().getItemMeta(Material.PLAYER_HEAD);
			ItemStack stack = new ItemStack(Material.PLAYER_HEAD, 1, (short) 3);
			skull.setOwner("talaknor");
			skull.setDisplayName(Utils.chat("&a&lSlime Skull"));
			lore.clear();
			lore.add(Utils.chat("&7Do /rankup to open the main menu"));
			lore.add(Utils.chat("&7click &evault skulls &7to vault"));
			lore.add(Utils.chat("&7all of the skulls in your inventory!"));
			skull.setLore(lore);
			stack.setItemMeta(skull);
			e.getEntity().getLocation().getWorld().dropItemNaturally(e.getEntity().getLocation(), stack);
		} else if (e.getEntity().getType() == EntityType.CREEPER) {
			List<String> lore = new ArrayList<String>();

			SkullMeta skull = (SkullMeta) Bukkit.getItemFactory().getItemMeta(Material.PLAYER_HEAD);
			ItemStack stack = new ItemStack(Material.PLAYER_HEAD, 1, (short) 3);
			skull.setOwner("creeper");
			skull.setDisplayName(Utils.chat("&a&lCreeper Skull"));
			lore.clear();
			lore.add(Utils.chat("&7Do /rankup to open the main menu"));
			lore.add(Utils.chat("&7click &evault skulls &7to vault"));
			lore.add(Utils.chat("&7all of the skulls in your inventory!"));
			skull.setLore(lore);
			stack.setItemMeta(skull);
			e.getEntity().getLocation().getWorld().dropItemNaturally(e.getEntity().getLocation(), stack);
		} else if (e.getEntity().getType() == EntityType.PANDA) {
			e.getEntity().getLocation().getWorld().dropItemNaturally(e.getEntity().getLocation(), SkullManager.getPandaSkull());
		}
	}
}
