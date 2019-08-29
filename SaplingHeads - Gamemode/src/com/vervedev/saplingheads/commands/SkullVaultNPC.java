package com.vervedev.saplingheads.commands;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_14_R1.entity.CraftPlayer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import com.vervedev.saplingheads.Main;
import com.vervedev.saplingheads.utils.Utils;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.npc.NPCRegistry;
import net.minecraft.server.v1_14_R1.EntityPlayer;

public class SkullVaultNPC implements CommandExecutor {
	
	private Main plugin;
	
	public SkullVaultNPC(Main plugin) {
		this.plugin = plugin;
		
		plugin.getCommand("setvaultnpc").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (!(sender instanceof Player)) {
			sender.sendMessage(Utils.chat("&eOnly players may execute this command!"));
			return true;
		}
		
		Player p = (Player) sender;
		
		if (p.hasPermission("saplingheads.setnpc")) {
			
			
			double x = p.getLocation().getX();
			double y = p.getLocation().getY();
			double z = p.getLocation().getZ();
			
			int pitch = (int) p.getLocation().getPitch();
			int yaw = (int) p.getLocation().getYaw();
			
			World world = p.getWorld();
			
			Location l = new Location(world, x,y,z);
			l.setPitch(pitch);
			l.setYaw(yaw);
			
			NPCRegistry registry = CitizensAPI.getNPCRegistry();
			NPC npc = registry.createNPC(EntityType.WANDERING_TRADER, "Skull Vault");
			npc.setName(Utils.chat("&6Skull Vault"));
			npc.spawn(l);
			return true;
		} else {
			p.sendMessage(Utils.chat("&cYou do not have permission to execute this command!"));
		}
		return false;
	}

}
