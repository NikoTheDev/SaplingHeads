package com.vervedev.saplingheads.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.vervedev.saplingheads.Main;
import com.vervedev.saplingheads.utils.Utils;

public class SetSpawn implements CommandExecutor {
	
	private static Main plugin;
	
	public SetSpawn(Main plugin) {
		this.plugin = plugin;
		
		plugin.getCommand("setspawntwo").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (!(sender instanceof Player)) {
			sender.sendMessage(Utils.chat("&eOnly players may execute this command"));
			return true;
		}
		
		Player p = (Player) sender;
		
		if (p.hasPermission("saplingheads.setspawn")) {
			setSpawn(p);
			return true;
		} else {
			p.sendMessage(Utils.chat("&cYou do not have permission to execute this command!"));
		}
		
		return false;
	}
	
	public static void setSpawn(Player p) {
		double x = p.getLocation().getX();
		double y = p.getLocation().getY();
		double z = p.getLocation().getZ();
		String world = p.getLocation().getWorld().getName();
		
		int pitch = (int) p.getLocation().getPitch();
		int yaw = (int) p.getLocation().getYaw();
		
		plugin.getConfig().set("spawn.x", x);
		plugin.getConfig().set("spawn.y", y);
		plugin.getConfig().set("spawn.z", z);
		plugin.getConfig().set("spawn.world", world);
		plugin.getConfig().set("spawn.pitch", pitch);
		plugin.getConfig().set("spawn.yaw", yaw);
		plugin.saveConfig();
		p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.spawn.set_spawn_message")));
		
	}
}
