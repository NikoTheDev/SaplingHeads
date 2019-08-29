package com.vervedev.saplingheads.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.vervedev.saplingheads.Main;
import com.vervedev.saplingheads.ui.SkullVaultUI;
import com.vervedev.saplingheads.utils.Utils;

public class SkullVault implements CommandExecutor {

	private Main plugin;

	public SkullVault(Main plugin) {
		this.plugin = plugin;

		plugin.getCommand("skullvault").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (!(sender instanceof Player)) {
			sender.sendMessage(Utils.chat("&eOnly players may execute this command!"));
			return true;
		}

		Player p = (Player) sender;

		if (p.hasPermission("saplingheads.skullvault")) {
			p.openInventory(SkullVaultUI.GUI(p));
			return true;
		} else {
			p.sendMessage(Utils.chat("&cYou do not have permission to execute this command!"));
		}
		return false;
	}

}
