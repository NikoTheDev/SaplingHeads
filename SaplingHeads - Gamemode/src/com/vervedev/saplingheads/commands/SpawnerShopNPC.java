package com.vervedev.saplingheads.commands;

import java.text.DecimalFormat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import com.vervedev.saplingheads.Main;
import com.vervedev.saplingheads.managers.SpawnerManager;
import com.vervedev.saplingheads.utils.Utils;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.npc.NPCRegistry;

public class SpawnerShopNPC implements CommandExecutor {

	private static Main plugin;

	public SpawnerShopNPC(Main plugin) {
		this.plugin = plugin;

		plugin.getCommand("spawnershopnpc").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (!(sender instanceof Player)) {
			sender.sendMessage(Utils.chat("&eOnly players may execute this command!"));
			return true;
		}

		Player p = (Player) sender;

		if (p.hasPermission("saplingheads.npc")) {
			if (args.length == 0) {
				p.sendMessage(Utils
						.chat("&a&lSaplingMC &8> &7You must enter a valid spawner NPC type! &e/spawnershopnpc <type>"));
				return true;
			} else if (args.length == 1) {
				if (args[0].equalsIgnoreCase("chicken")) {
					spawnSpawnerNPC(p, "Chicken");
					return true;
				} else if (args[0].equalsIgnoreCase("pig")) {
					spawnSpawnerNPC(p, "Pig");
					return true;
				} else if (args[0].equalsIgnoreCase("sheep")) {
					spawnSpawnerNPC(p, "Sheep");
					return true;
				} else if (args[0].equalsIgnoreCase("cow")) {
					spawnSpawnerNPC(p, "Cow");
					return true;
				} else if (args[0].equalsIgnoreCase("Zombie")) {
					spawnSpawnerNPC(p, "zombie");
					return true;
				} else if (args[0].equalsIgnoreCase("skeleton")) {
					spawnSpawnerNPC(p, "skeleton");
					return true;
				} else if (args[0].equalsIgnoreCase("pigzombie")) {
					spawnSpawnerNPC(p, "pigzombie");
					return true;
				} else if (args[0].equalsIgnoreCase("slime")) {
					spawnSpawnerNPC(p, "slime");
					return true;
				} else if (args[0].equalsIgnoreCase("creeper")) {
					spawnSpawnerNPC(p, "creeper");
					return true;
				} else if (args[0].equalsIgnoreCase("panda")) {
					spawnSpawnerNPC(p, "Panda");
					return true;
				} else if (args[0].equalsIgnoreCase("delete")) {
					for (Hologram hologram : HologramsAPI.getHolograms(plugin)) {

						Location playerLoc = p.getLocation();
						Location hologramLoc = hologram.getLocation();

						// Check if the distance between the locations are less than 10
						// and deletes the hologram if true
						if (playerLoc.distance(hologramLoc) < 4) {
							String path = "holograms." + ChatColor.stripColor(hologram.getLine(0).toString());
							plugin.getConfig().set(path, null);
							plugin.saveConfig();
							hologram.delete();
							return true;
						}
					}
				}
			} else if (args.length > 1) {
				p.sendMessage(Utils
						.chat("&a&lSaplingMC &8> &7You must enter a valid spawner NPC type! &e/spawnershopnpc <type>"));
				return true;
			}
		} else {
			p.sendMessage(Utils.chat("&cYou do not have permission to execute this command!"));
		}
		return false;
	}

	public void spawnSpawnerNPC(Player p, String spawnerType) {
		double x = p.getLocation().getX();
		double y = p.getLocation().getY();
		double z = p.getLocation().getZ();

		int pitch = (int) p.getLocation().getPitch();
		int yaw = (int) p.getLocation().getYaw();

		World world = p.getWorld();

		Location l = new Location(world, x, y, z);
		Location hologramLocation = new Location(world, x, y + 2.05, z);

		Location cowhologramLocation = new Location(world, x, y + 2.3, z);
		Location sheephologramLocation = new Location(world, x, y + 2.3, z);
		Location zombiehologramLocation = new Location(world, x, y + 2.8, z);

		Location slimehologramLocation = new Location(world, x, y + 2.25, z);

		l.setPitch(pitch);
		l.setYaw(yaw);

		DecimalFormat formatter = new DecimalFormat("#,###");
		int chickenString = plugin.getConfig().getInt("SpawnerShop.prices.chicken");

		NPCRegistry registry = CitizensAPI.getNPCRegistry();
		if (spawnerType.equalsIgnoreCase("Chicken")) {
			Hologram hologram = HologramsAPI.createHologram(plugin, hologramLocation);
			NPC npc = registry.createNPC(EntityType.CHICKEN, "Chicken");
			npc.spawn(l);
			Bukkit.dispatchCommand(p, "npc select");
			Bukkit.dispatchCommand(p, "npc name");
			hologram.appendTextLine(Utils.chat("&f&lChicken Spawner"));
			hologram.insertTextLine(1, Utils
					.chat("&7Right Click &8(&e$" + formatter.format(SpawnerManager.getChickenSpawnerPrice()) + "&8)"));
			saveHologram(p, ChatColor.stripColor(hologram.getLine(0).toString()), "&f&lChicken Spawner",
					"&7Right Click &8(&e$" + formatter.format(SpawnerManager.getChickenSpawnerPrice()) + "&8)",
					y + 2.05);
			p.sendMessage(Utils.chat("&a&lSaplingMC &8> &7You have successfully placed a &f&lChicken NPC&7!"));
		} else if (spawnerType.equalsIgnoreCase("Pig")) {
			Hologram hologram = HologramsAPI.createHologram(plugin, hologramLocation);
			NPC npc = registry.createNPC(EntityType.PIG, "Pig");
			npc.spawn(l);
			Bukkit.dispatchCommand(p, "npc select");
			Bukkit.dispatchCommand(p, "npc name");
			hologram.appendTextLine(Utils.chat("&d&lPig Spawner"));
			hologram.insertTextLine(1,
					Utils.chat("&7Right Click &8(&e$" + formatter.format(SpawnerManager.getPigSpawnerPrice()) + "&8)"));
			saveHologram(p, ChatColor.stripColor(hologram.getLine(0).toString()), "&d&lPig Spawner",
					"&7Right Click &8(&e$" + formatter.format(SpawnerManager.getPigSpawnerPrice()) + "&8)", y + 2.05);
			p.sendMessage(Utils.chat("&a&lSaplingMC &8> &7You have successfully placed a &d&lPig NPC&7!"));
		} else if (spawnerType.equalsIgnoreCase("Sheep")) {
			Hologram hologram = HologramsAPI.createHologram(plugin, sheephologramLocation);
			NPC npc = registry.createNPC(EntityType.SHEEP, "Sheep");
			npc.spawn(l);
			Bukkit.dispatchCommand(p, "npc select");
			Bukkit.dispatchCommand(p, "npc name");
			hologram.appendTextLine(Utils.chat("&7&lSheep Spawner"));
			hologram.insertTextLine(1, Utils
					.chat("&7Right Click &8(&e$" + formatter.format(SpawnerManager.getSheepSpawnerPrice()) + "&8)"));
			saveHologram(p, ChatColor.stripColor(hologram.getLine(0).toString()), "&7&lSheep Spawner",
					"&7Right Click &8(&e$" + formatter.format(SpawnerManager.getSheepSpawnerPrice()) + "&8)", y + 2.3);
			p.sendMessage(Utils.chat("&a&lSaplingMC &8> &7You have successfully placed a &7&lSheep NPC&7!"));
		} else if (spawnerType.equalsIgnoreCase("Cow")) {
			Hologram hologram = HologramsAPI.createHologram(plugin, cowhologramLocation);
			NPC npc = registry.createNPC(EntityType.COW, "Cow");
			npc.spawn(l);
			Bukkit.dispatchCommand(p, "npc select");
			Bukkit.dispatchCommand(p, "npc name");
			hologram.appendTextLine(Utils.chat("&8&lCow Spawner"));
			hologram.insertTextLine(1,
					Utils.chat("&7Right Click &8(&e$" + formatter.format(SpawnerManager.getCowSpawnerPrice()) + "&8)"));
			saveHologram(p, ChatColor.stripColor(hologram.getLine(0).toString()), "&8&lCow Spawner",
					"&7Right Click &8(&e$" + formatter.format(SpawnerManager.getCowSpawnerPrice()) + "&8)", y + 2.3);
			p.sendMessage(Utils.chat("&a&lSaplingMC &8> &7You have successfully placed a &8&lCow NPC&7!"));
		} else if (spawnerType.equalsIgnoreCase("zombie")) {
			Hologram hologram = HologramsAPI.createHologram(plugin, zombiehologramLocation);
			NPC npc = registry.createNPC(EntityType.ZOMBIE, "Zombie");
			npc.spawn(l);
			Bukkit.dispatchCommand(p, "npc select");
			Bukkit.dispatchCommand(p, "npc name");
			hologram.appendTextLine(Utils.chat("&2&lZombie Spawner"));
			hologram.insertTextLine(1, Utils
					.chat("&7Right Click &8(&e$" + formatter.format(SpawnerManager.getZombieSpawnerPrice()) + "&8)"));
			saveHologram(p, ChatColor.stripColor(hologram.getLine(0).toString()), "&2&lZombie Spawner",
					"&7Right Click &8(&e$" + formatter.format(SpawnerManager.getZombieSpawnerPrice()) + "&8)", y + 2.8);
			p.sendMessage(Utils.chat("&a&lSaplingMC &8> &7You have successfully placed a &2&lZombie NPC&7!"));
		} else if (spawnerType.equalsIgnoreCase("skeleton")) {
			Hologram hologram = HologramsAPI.createHologram(plugin, zombiehologramLocation);
			NPC npc = registry.createNPC(EntityType.SKELETON, "Skeleton");
			npc.spawn(l);
			Bukkit.dispatchCommand(p, "npc select");
			Bukkit.dispatchCommand(p, "npc name");
			hologram.appendTextLine(Utils.chat("&3&lSkeleton Spawner"));
			hologram.insertTextLine(1, Utils
					.chat("&7Right Click &8(&e$" + formatter.format(SpawnerManager.getSkeletonSpawnerPrice()) + "&8)"));
			saveHologram(p, ChatColor.stripColor(hologram.getLine(0).toString()), "&3&lSkeleton Spawner",
					"&7Right Click &8(&e$" + formatter.format(SpawnerManager.getSkeletonSpawnerPrice()) + "&8)",
					y + 2.8);
			p.sendMessage(Utils.chat("&a&lSaplingMC &8> &7You have successfully placed a &3&lSkeleton NPC&7!"));
		} else if (spawnerType.equalsIgnoreCase("pigzombie")) {
			Hologram hologram = HologramsAPI.createHologram(plugin, zombiehologramLocation);
			NPC npc = registry.createNPC(EntityType.PIG_ZOMBIE, "PigZombie");
			npc.spawn(l);
			Bukkit.dispatchCommand(p, "npc select");
			Bukkit.dispatchCommand(p, "npc name");
			hologram.appendTextLine(Utils.chat("&c&lZombie Pigman Spawner"));
			hologram.insertTextLine(1, Utils.chat(
					"&7Right Click &8(&e$" + formatter.format(SpawnerManager.getPigZombieSpawnerPrice()) + "&8)"));
			saveHologram(p, ChatColor.stripColor(hologram.getLine(0).toString()), "&c&lZombie Pigman Spawner",
					"&7Right Click &8(&e$" + formatter.format(SpawnerManager.getPigZombieSpawnerPrice()) + "&8)",
					y + 2.8);
			p.sendMessage(Utils.chat("&a&lSaplingMC &8> &7You have successfully placed a &c&lZombie Pigman NPC&7!"));
		} else if (spawnerType.equalsIgnoreCase("slime")) {
			Hologram hologram = HologramsAPI.createHologram(plugin, slimehologramLocation);
			NPC npc = registry.createNPC(EntityType.SLIME, "Slime");
			npc.spawn(l);
			Bukkit.dispatchCommand(p, "npc select");
			Bukkit.dispatchCommand(p, "npc name");
			hologram.appendTextLine(Utils.chat("&a&lSlime Spawner"));
			hologram.insertTextLine(1, Utils
					.chat("&7Right Click &8(&e$" + formatter.format(SpawnerManager.getSlimeSpawnerPrice()) + "&8)"));
			saveHologram(p, ChatColor.stripColor(hologram.getLine(0).toString()), "&a&lSlime Spawner",
					"&7Right Click &8(&e$" + formatter.format(SpawnerManager.getSlimeSpawnerPrice()) + "&8)", y + 2.25);
			p.sendMessage(Utils.chat("&a&lSaplingMC &8> &7You have successfully placed a &a&lSlime NPC&7!"));
		} else if (spawnerType.equalsIgnoreCase("creeper")) {
			Hologram hologram = HologramsAPI.createHologram(plugin, zombiehologramLocation);
			NPC npc = registry.createNPC(EntityType.CREEPER, "Creeper");
			npc.spawn(l);
			Bukkit.dispatchCommand(p, "npc select");
			Bukkit.dispatchCommand(p, "npc name");
			hologram.appendTextLine(Utils.chat("&a&lCreeper Spawner"));
			hologram.insertTextLine(1, Utils
					.chat("&7Right Click &8(&e$" + formatter.format(SpawnerManager.getCreeperSpawnerPrice()) + "&8)"));
			saveHologram(p, ChatColor.stripColor(hologram.getLine(0).toString()), "&a&lCreeper Spawner",
					"&7Right Click &8(&e$" + formatter.format(SpawnerManager.getCreeperSpawnerPrice()) + "&8)",
					y + 2.35);
			p.sendMessage(Utils.chat("&a&lSaplingMC &8> &7You have successfully placed a &a&lCreeper NPC&7!"));
		} else if (spawnerType.equalsIgnoreCase("panda")) {
			Hologram hologram = HologramsAPI.createHologram(plugin, slimehologramLocation);
			NPC npc = registry.createNPC(EntityType.PANDA, "Panda");
			npc.spawn(l);
			Bukkit.dispatchCommand(p, "npc select");
			Bukkit.dispatchCommand(p, "npc name");
			hologram.appendTextLine(Utils.chat("&f&lPan&7&lda &f&lSpawner"));
			hologram.insertTextLine(1, Utils
					.chat("&7Right Click &8(&e" + formatter.format(SpawnerManager.getPandaSpawnerPrice()) + "&8)"));
			saveHologram(p, ChatColor.stripColor(hologram.getLine(0).toString()), "&f&lPan&7&lda &f&lSpawner",
					"&7Right Click &8(&e$" + formatter.format(SpawnerManager.getPandaSpawnerPrice()) + "&8)", y + 2.25);
			p.sendMessage(Utils.chat("&a&lSaplingMC &8> &7You have successfully placed a &f&lPan&7&lda &f&lNPC&7!"));
		}
	}

	public static void saveHologram(Player p, String NPCid, String line1, String line2, double yInt) {
		double x = p.getLocation().getX();
		double z = p.getLocation().getZ();

		int pitch = (int) p.getLocation().getPitch();
		int yaw = (int) p.getLocation().getYaw();

		World world = p.getWorld();

		String path = "holograms." + NPCid + ".hologram";

		plugin.getConfig().set(path + ".x", x);
		plugin.getConfig().set(path + ".y", yInt);
		plugin.getConfig().set(path + ".z", z);
		plugin.getConfig().set(path + ".world", world.getName());
		plugin.getConfig().set(path + ".line1", line1);
		plugin.getConfig().set(path + ".line2", line2);
		plugin.saveConfig();
	}

	public static void loadHolograms() {
		for (Hologram hologram : HologramsAPI.getHolograms(plugin)) {
			if (hologram != null) {
				hologram.delete();
			}
		}
		ConfigurationSection configSection = plugin.getConfig().getConfigurationSection("holograms");
		for (String key : configSection.getKeys(false)) {
			if (key != null) {
				double x = plugin.getConfig().getDouble("holograms." + key + ".hologram.x");
				double y = plugin.getConfig().getDouble("holograms." + key + ".hologram.y");
				double z = plugin.getConfig().getDouble("holograms." + key + ".hologram.z");

				String world = plugin.getConfig().getString("holograms." + key + ".hologram.world");
				String line1 = plugin.getConfig().getString("holograms." + key + ".hologram.line1");
				String line2 = plugin.getConfig().getString("holograms." + key + ".hologram.line2");

				Location loc = new Location(Bukkit.getWorld(world), x, y, z);

				for (Hologram hologram : HologramsAPI.getHolograms(plugin)) {
					if (ChatColor.stripColor(hologram.getLine(0).toString()).equalsIgnoreCase(ChatColor
							.stripColor(plugin.getConfig().getString("holograms." + key + ".hologram.line1")))) {
						hologram.delete();
					}
				}

				Hologram hologram = HologramsAPI.createHologram(plugin, loc);
				hologram.appendTextLine(Utils.chat(line1));
				hologram.insertTextLine(1, Utils.chat(line2));
			}
		}
	}
}
