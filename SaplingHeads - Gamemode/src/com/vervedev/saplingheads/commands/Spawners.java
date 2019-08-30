package com.vervedev.saplingheads.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.vervedev.saplingheads.Main;
import com.vervedev.saplingheads.managers.PlayerManager;
import com.vervedev.saplingheads.managers.SpawnerManager;
import com.vervedev.saplingheads.utils.Utils;

public class Spawners implements CommandExecutor {

	private Main plugin;

	public Spawners(Main plugin) {
		this.plugin = plugin;

		plugin.getCommand("spawners").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (sender.hasPermission("saplingheads.spawners")) {
			if (args.length == 0) {
				sender.sendMessage(Utils.chat("&7&lSpawners &8> &6/spawners give <player> <type>"));
				return true;
			} else if (args.length == 1) {
				if (args[0].equalsIgnoreCase("give")) {
					sender.sendMessage(Utils.chat("&7&lSpawners &8> &6/spawners give <player> <type>"));
					return true;
				} else {
					sender.sendMessage(
							Utils.chat("&7&lSpawners &8> &7Incorrect Usage! &6/spawners give <player> <type>"));
					return true;
				}
			} else if (args.length == 2) {
				Player p = Bukkit.getPlayer(args[1]);

				if (p != null) {
					sender.sendMessage(Utils.chat(
							"&7&lSpawners &8> &7Please specify a spawner type! &6/spawners give <player> <type>"));
					return true;
				} else {
					sender.sendMessage(Utils.chat("&7&lSpawners &8> &7The player &e" + args[1]
							+ " &7could not be found! &6/spawners give <player> <type>"));
					return true;
				}
			} else if (args.length == 3) {
				Player p = Bukkit.getPlayer(args[1]);

				if (p != null) {
					if (PlayerManager.playerInventoryFull(p) == false) {
						p.getInventory().addItem(SpawnerManager.spawnerType(args[2]));
						sender.sendMessage(Utils.chat("&7&lSpawners &8> &7You have successfully given the player " + p.getDisplayName() + " &7a &6" + args[2] + " spawener!"));
						p.sendMessage(Utils.chat("&7&lSpawners &8> &7You have received a &6" + args[2] + " spawner!"));
						return true;
					} else {
						p.getLocation().getWorld().dropItemNaturally(p.getLocation(), SpawnerManager.spawnerType(args[2]));
						sender.sendMessage(Utils.chat("&7&lSpawners &8> &7You have successfully given the player " + p.getDisplayName() + " &7a &6" + args[2] + " spawner!"));
						p.sendMessage(Utils.chat("&7&lSpawners &8> &7You have received a &6" + args[2] + " spawner! &7Your inventory was full thus the item was dropped on the ground!"));
					}
				} else {
					sender.sendMessage(Utils.chat("&7&lSpawners &8> &7The player &e" + args[1]
							+ " &7could not be found! &6/spawners give <player> <type>"));
					return true;
				}
			}
		}
		return false;
	}

}
