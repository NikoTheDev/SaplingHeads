package com.vervedev.saplingheads.commands;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.vervedev.saplingheads.Main;
import com.vervedev.saplingheads.utils.Utils;

import me.lucko.luckperms.LuckPerms;
import me.lucko.luckperms.api.LuckPermsApi;

public class RankCheck implements CommandExecutor {

	private Main plugin;

	public RankCheck(Main plugin) {
		this.plugin = plugin;

		plugin.getCommand("rankcheck").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (sender.hasPermission("saplingheads.rankcheck")) {
			if (args.length == 0) {
				sender.sendMessage(Utils.chat("&a&lSaplingMC &8> &7/rankcheck &e<player> &9<new_rank>"));
				return true;
			} else if (args.length == 1) {
				OfflinePlayer p = Bukkit.getOfflinePlayer(args[0]);

				if (p != null && p.hasPlayedBefore()) {
					sender.sendMessage(Utils.chat(
							"&a&lSaplingMC &8> &7Please enter a rank! /rankcheck &e" + p.getName() + " &9<new_rank>"));
					return true;
				} else {
					sender.sendMessage(
							Utils.chat("&a&lSaplingMC &8> &7The player &e" + args[0] + " &ccould not be found&7!"));
				}
			} else if (args.length == 2) {
				OfflinePlayer p = Bukkit.getOfflinePlayer(args[0]);

				if (p != null && p.hasPlayedBefore()) {
					LuckPermsApi api = LuckPerms.getApi();
					if (args[1].equalsIgnoreCase("elite")) {
						if (!api.getUser(p.getName()).getPrimaryGroup().equalsIgnoreCase("Hero")
								&& !api.getUser(p.getName()).getPrimaryGroup().equalsIgnoreCase("Champion")
								&& !api.getUser(p.getName()).getPrimaryGroup().equalsIgnoreCase("Legend")
								&& !api.getUser(p.getName()).getPrimaryGroup().equalsIgnoreCase("Legend+")
								&& !api.getUser(p.getName()).getPrimaryGroup().equalsIgnoreCase("Admin")
								&& !api.getUser(p.getName()).getPrimaryGroup().equalsIgnoreCase("Developer")
								&& !api.getUser(p.getName()).getPrimaryGroup().equalsIgnoreCase("Owner")) {
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
									"lp user " + p.getName() + " parent set Elite");
							sender.sendMessage(Utils.chat("&a&lSaplingMC &8> &7You have successfully set the rank of &6"
									+ p.getName() + " &7to &2Elite!"));
							return true;
						} else {
							sender.sendMessage(Utils.chat("&a&lSaplingMC &8> &7The player &e" + p.getName()
									+ " &7currently has a better rank!"));
						}
					} else if (args[1].equalsIgnoreCase("hero")) {
						if (!api.getUser(p.getName()).getPrimaryGroup().equalsIgnoreCase("Champion")
								&& !api.getUser(p.getName()).getPrimaryGroup().equalsIgnoreCase("Legend")
								&& !api.getUser(p.getName()).getPrimaryGroup().equalsIgnoreCase("Legend+")
								&& !api.getUser(p.getName()).getPrimaryGroup().equalsIgnoreCase("Admin")
								&& !api.getUser(p.getName()).getPrimaryGroup().equalsIgnoreCase("Developer")
								&& !api.getUser(p.getName()).getPrimaryGroup().equalsIgnoreCase("Owner")) {
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
									"lp user " + p.getName() + " parent set Hero");
							sender.sendMessage(Utils.chat("&a&lSaplingMC &8> &7You have successfully set the rank of &6"
									+ p.getName() + " &7to &3Hero!"));
							return true;
						} else {
							sender.sendMessage(Utils.chat("&a&lSaplingMC &8> &7The player &e" + p.getName()
									+ " &7currently has a better rank!"));
						}
					} else if (args[1].equalsIgnoreCase("champion")) {
						if (!api.getUser(p.getName()).getPrimaryGroup().equalsIgnoreCase("Legend")
								&& !api.getUser(p.getName()).getPrimaryGroup().equalsIgnoreCase("Legend+")
								&& !api.getUser(p.getName()).getPrimaryGroup().equalsIgnoreCase("Admin")
								&& !api.getUser(p.getName()).getPrimaryGroup().equalsIgnoreCase("Developer")
								&& !api.getUser(p.getName()).getPrimaryGroup().equalsIgnoreCase("Owner")) {
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
									"lp user " + p.getName() + " parent set Champion");
							sender.sendMessage(Utils.chat("&a&lSaplingMC &8> &7You have successfully set the rank of &6"
									+ p.getName() + " &7to &9Champion!"));
							return true;
						} else {
							sender.sendMessage(Utils.chat("&a&lSaplingMC &8> &7The player &e" + p.getName()
									+ " &7currently has a better rank!"));
						}
					} else if (args[1].equalsIgnoreCase("legend")) {
						if (!api.getUser(p.getName()).getPrimaryGroup().equalsIgnoreCase("Legend+")
								&& !api.getUser(p.getName()).getPrimaryGroup().equalsIgnoreCase("Admin")
								&& !api.getUser(p.getName()).getPrimaryGroup().equalsIgnoreCase("Developer")
								&& !api.getUser(p.getName()).getPrimaryGroup().equalsIgnoreCase("Owner")) {
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
									"lp user " + p.getName() + " parent set Legend");
							sender.sendMessage(Utils.chat("&a&lSaplingMC &8> &7You have successfully set the rank of &6"
									+ p.getName() + " &7to &eLegend!"));
							return true;
						} else {
							sender.sendMessage(Utils.chat("&a&lSaplingMC &8> &7The player &e" + p.getName()
									+ " &7currently has a better rank!"));
						}
					} else if (args[1].equalsIgnoreCase("legend+")
							&& !api.getUser(p.getName()).getPrimaryGroup().equalsIgnoreCase("Admin")
							&& !api.getUser(p.getName()).getPrimaryGroup().equalsIgnoreCase("Developer")
							&& !api.getUser(p.getName()).getPrimaryGroup().equalsIgnoreCase("Owner")) {
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
								"lp user " + p.getName() + " parent set Legend+");
						sender.sendMessage(Utils.chat("&a&lSaplingMC &8> &7You have successfully set the rank of &6"
								+ p.getName() + " &7to &eLegend!"));
						return true;
					} else {
						sender.sendMessage(
								Utils.chat("&a&lSaplingMC &8> &7The rank &9" + args[1] + " &ccould not be found&7!"));
					}
				} else {
					sender.sendMessage(
							Utils.chat("&a&lSaplingMC &8> &7The player &e" + args[0] + " &ccould not be found&7!"));
				}
			} else if (args.length > 2) {
				sender.sendMessage(
						Utils.chat("&a&lSaplingMC &8> &cToo many arguments! &7/rankcheck &e<player> &9<new_rank>"));
				return true;
			}
		} else {
			sender.sendMessage(Utils.chat("&cYou do not have permission to execute this command!"));
		}

		return false;
	}
}
