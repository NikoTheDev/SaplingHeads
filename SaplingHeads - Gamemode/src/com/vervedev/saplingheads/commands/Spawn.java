package com.vervedev.saplingheads.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.vervedev.saplingheads.Main;
import com.vervedev.saplingheads.utils.Utils;

public class Spawn {
	
	private static Main plugin;
	
	public Spawn(Main plugin) {
		this.plugin = plugin;
	}
	
	public static void sendPlayerToSpawn(Player p) {
		double x = plugin.getConfig().getDouble("spawn.x");
		double y = plugin.getConfig().getDouble("spawn.y");
		double z = plugin.getConfig().getDouble("spawn.z");
		World world = Bukkit.getWorld(plugin.getConfig().getString("spawn.world"));
		
		int pitch = (int) plugin.getConfig().getDouble("spawn.pitch");
		int yaw = (int) plugin.getConfig().getDouble("spawn.yaw");
		
		Location spawn = new Location(world, x, y ,z);
		spawn.setPitch(pitch);
		spawn.setYaw(yaw);
		p.teleport(spawn);
		p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.spawn.spawn_message")));
	}

}
