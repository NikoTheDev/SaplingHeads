package com.vervedev.saplingheads.commands;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.vervedev.saplingheads.Main;
import com.vervedev.saplingheads.managers.RankManager;
import com.vervedev.saplingheads.managers.SpawnerManager;
import com.vervedev.saplingheads.utils.Utils;

public class SetRank implements CommandExecutor {
	
	private Main plugin;
	
	public SetRank(Main plugin) {
		this.plugin = plugin;
		
		plugin.getCommand("setrank").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (sender.hasPermission("saplingheads.setrank")) {
			if (args.length == 0) {
				sender.sendMessage(Utils.chat("&6Please specify a playername as well as a rank! &e/setrank <player> <rank>"));
				if (sender instanceof Player) {
					Player p = (Player) sender;
					
					p.getInventory().addItem(SpawnerManager.spawnerType("blank"));
				}
			} else if (args.length == 1) {
				@SuppressWarnings("deprecation")
				OfflinePlayer player = Bukkit.getOfflinePlayer(args[0]);
				if (player != null) {

					sender.sendMessage(Utils.chat("&6Please specify a rank! &e/setrank <player> <rank>"));
				} else {
					sender.sendMessage(Utils.chat("&a&lSaplingMC &8> &7The player &e" + args[0] + " &7could not be found!"));
				}
			} else if (args.length == 2) {
				if (Integer.parseInt(args[1]) <= 10) {
					RankManager.setRank(Bukkit.getOfflinePlayer(args[0]), Integer.parseInt(args[1]));
					sender.sendMessage(Utils.chat("&a&lSaplingMC &8> &7You have successfully set the rank of &e" + args[0] + " &7to &6level " + args[1]));
				} else {
					sender.sendMessage(Utils.chat("&a&lSaplingMC &8> &7Rank out of bounds, please use numbers &60 &7- &610"));
				}
			}
		}
		return false;
	}

}
