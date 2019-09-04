package com.vervedev.saplingheads.perks;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import com.vervedev.saplingheads.Main;
import com.vervedev.saplingheads.managers.PerkManager;
import com.vervedev.saplingheads.utils.Utils;

public class JellyLegs implements Listener {

	public Main plugin;

	public JellyLegs(Main plugin) {
		this.plugin = plugin;

		Bukkit.getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onDamage(EntityDamageEvent e) {
		if (e.getEntity().getType() == EntityType.PLAYER) {
			if (e.getCause() == DamageCause.FALL) {
				Player p = (Player) e.getEntity();
				if (PerkManager.checkPerkActive(p, "jellylegs")) {
					e.setCancelled(true);
				}
			}
		}
	}

	public static void addJellyLegs(Player p) {
		PerkManager.perks.put("jellylegs" + p.getUniqueId().toString(), true);
		p.sendMessage(Utils.chat("&9&lPerks &8> &7You have successfully &aunlocked &7the &dJellyLegs Perk&7!"));
	}

	public static void enableJellyLegs(Player p) {
		PerkManager.perks.put("jellylegs_enabled" + p.getUniqueId().toString(), true);
		p.sendMessage(Utils.chat("&9&lPerks &8> &7You have successfully &aenabled &7the perk &dJellyLegs&7!"));
	}

	public static void disableJellyLegs(Player p) {
		PerkManager.perks.remove("jellylegs_enabled" + p.getUniqueId().toString());
		p.sendMessage(Utils.chat("&9&lPerks &8> &7You have successfully &edisabled &7the perk &dJellyLegs&7!"));
	}
}
