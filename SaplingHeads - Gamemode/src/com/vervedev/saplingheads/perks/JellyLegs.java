package com.vervedev.saplingheads.perks;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import com.vervedev.saplingheads.Main;
import com.vervedev.saplingheads.managers.PerkManager;
import com.vervedev.saplingheads.utils.Utils;

public class JellyLegs implements Listener {
	
	private static ArrayList<String> perks = new ArrayList<String>();

	private Main plugin;

	public JellyLegs(Main plugin) {
		this.plugin = plugin;

		Bukkit.getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onDamage(EntityDamageEvent e) {
		if (e.getEntity() instanceof Player) {
			if (e.getCause() == DamageCause.FALL) {
				Player p = (Player) e.getEntity();
				String uuid = p.getUniqueId().toString();
				if (PerkManager.perks.get(uuid).contains("jellylegs_enabled")) {
					e.setCancelled(true);
				}
			}
		}
	}

	public static void addJellyLegs(Player p) {
		PerkManager.perks.put(p.getUniqueId().toString(), perks);
		p.sendMessage(Utils.chat("&9&lPerks &8> &7You have &asuccessfully &7unlocked the &dJellyLegs Perk&7!"));
	}

	public static void removeJellyLegs(Player p) {
		PerkManager.perks.get(p.getUniqueId().toString()).remove("jellylegs_enabled");
	}
}
