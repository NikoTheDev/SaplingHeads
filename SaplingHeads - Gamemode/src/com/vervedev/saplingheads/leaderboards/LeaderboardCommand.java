package com.vervedev.saplingheads.leaderboards;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.vervedev.saplingheads.Main;
import com.vervedev.saplingheads.managers.PlayerManager;
import com.vervedev.saplingheads.utils.Utils;

public class LeaderboardCommand implements CommandExecutor {
	
	public Main plugin;
	
	public LeaderboardCommand(Main plugin) {
		this.plugin = plugin;
		
		plugin.getCommand("leaderboard").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (!(sender instanceof Player)) {
			sender.sendMessage(Utils.chat("&eOnly players may execute this command!"));
			return true;
		}

		Player p = (Player) sender;

		if (p.hasPermission("saplingcore.leaderboard")) {
			PlayerManager.calculateTotalSkulls();
			p.sendMessage(Utils.chat("&c===== Top 3 players ===== "));
			Map<String, Integer> map = new HashMap<>();
			for (String key : PlayerManager.playerTotalSkulls.keySet()) {
				map.put(key, PlayerManager.playerTotalSkulls.get(key));
			}
			 ValueComparator bvc =  new ValueComparator(map);
	          TreeMap<String, Integer> sorted_map = new TreeMap<String, Integer>(bvc);
	           sorted_map.putAll(map);
	 
	          for(int i = 1; i < 4; i ++){
	              Entry<String, Integer> e = sorted_map.pollFirstEntry();
	              String pname = e.getKey();
	              UUID uniqueId = UUID.fromString(pname);
	              OfflinePlayer player = Bukkit.getOfflinePlayer(uniqueId);
	              int score = e.getValue();
	              p.sendMessage(i + ". " + ChatColor.GOLD  + player.getName() + ": " + ChatColor.WHITE + score);
	          }
	 
		} else {
			p.sendMessage(Utils.chat("&cYou do not have permission to execute this command!"));
		}

		return false;
	}

}
