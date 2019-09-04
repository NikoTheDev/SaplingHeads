package com.vervedev.saplingheads.managers;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import com.vervedev.saplingheads.Main;
import com.vervedev.saplingheads.utils.Utils;

public class DonationManager implements CommandExecutor, Listener {

	public static Main plugin;

	public DonationManager(Main plugin) {
		this.plugin = plugin;

		plugin.getCommand("bdonation").setExecutor(this);
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	public static ArrayList<Player> lightningImmune = new ArrayList<Player>();

	@EventHandler
	public void onDamage(EntityDamageEvent e) {
		if (e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			if (e.getCause() == DamageCause.LIGHTNING) {
				if (lightningImmune.contains(p)) {
					e.setCancelled(true);
				}
			}
		}
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		

		if (sender.hasPermission("saplingheads.broadcastdonation")) {
			if (args.length == 0) {
				sender.sendMessage(Utils.chat("&a&lSaplingShop &8> &7/bdonation &e<player> &9<message>"));
				return true;
			} else if (args.length == 1) {
				OfflinePlayer op = Bukkit.getOfflinePlayer(args[0]);
				if (op != null) {
					sender.sendMessage(
							Utils.chat("&a&lSaplingShop &8> &7/bdonation &e" + op.getName() + " &9<message>"));
					return true;
				} else {
					sender.sendMessage(
							Utils.chat("&a&lSaplingShop &8> &7The player &e" + args[0] + " &ccould not be found&7!"));
				}
			} else if (args.length > 1) {
				StringBuilder sb = new StringBuilder();
				for (int i = 1; i < args.length; i++) {
					if (i > 0)
						sb.append(" ");
					sb.append(args[i]);
				}
				Player p = Bukkit.getPlayer(args[0]);
				OfflinePlayer op = Bukkit.getOfflinePlayer(args[0]);
				if (p != null) {
					lightningImmune.add(p);
					Bukkit.broadcastMessage("");
					Bukkit.broadcastMessage(Utils.chat(
							"&a&lDonations &8> &6" + p.getDisplayName() + " &7has purchased&r" + sb.toString()));
					Bukkit.broadcastMessage("");
					processDonation(p);
					lightningImmune.remove(p);
					return true;
				} else {
					if (op != null) {
						Bukkit.broadcastMessage("");
						Bukkit.broadcastMessage(Utils.chat(
								"&a&lDonations &8> &6" + op.getName() + " &7has purchased&r" + sb.toString()));
						Bukkit.broadcastMessage("");
					} else {
						sender.sendMessage(Utils
								.chat("&a&lSaplingShop &8> &7The player &e" + args[0] + " &ccould not be found&7!"));
					}
				}
			}
		} else

		{
			sender.sendMessage(Utils.chat("&cYou do not have permission to execute this command!"));
		}

		return false;
	}

	public static void processDonation(Player p) {
		double x = p.getLocation().getX();
		double y = p.getLocation().getY();
		double z = p.getLocation().getZ();
		World world = p.getLocation().getWorld();

		Location l = new Location(world, x, y + 6, z);
		p.getLocation().getWorld().strikeLightning(l);

		RankManager.launchFirework(p, 2, "red");

		plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			public void run() {
				RankManager.launchFirework(p, 2, "aqua");
			}
		}, 10L);

		plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			public void run() {
				RankManager.launchFirework(p, 2, "gray");
			}
		}, 20L);

		plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			public void run() {
				RankManager.launchFirework(p, 2, "green");
			}
		}, 30L);

		plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			public void run() {
				RankManager.launchFirework(p, 2, "gold");
			}
		}, 40L);

	}

}
