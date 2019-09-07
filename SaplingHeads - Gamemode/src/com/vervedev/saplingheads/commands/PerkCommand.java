package com.vervedev.saplingheads.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.vervedev.saplingheads.Main;
import com.vervedev.saplingheads.ui.PerkUI;
import com.vervedev.saplingheads.utils.Utils;

public class PerkCommand implements CommandExecutor {
	
	private Main plugin;
	
	public PerkCommand(Main plugin) {
		this.plugin = plugin;
		
		plugin.getCommand("perks").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (!(sender instanceof Player)) {
			sender.sendMessage(Utils.chat("&eOnly players may execute this command!"));
			return true;
		}
		
		Player p = (Player) sender;
		
		if (p.hasPermission("saplingheads.perks")) {
			p.openInventory(PerkUI.GUI(p));
			return true;
		} else {
			p.sendMessage(Utils.chat("&cYou do not have permission to execute this command!"));
		}

		return false;
	}
}
