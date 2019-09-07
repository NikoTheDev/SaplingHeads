package com.vervedev.saplingheads.perks;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.vervedev.saplingheads.Main;
import com.vervedev.saplingheads.managers.PerkManager;
import com.vervedev.saplingheads.utils.Utils;

public class Nocturnal {

	public Main plugin;

	private static String perkname = "nocturnal";

	public Nocturnal(Main plugin) {
		this.plugin = plugin;
		enableNocturnalReload();
	}

	public static void addNocturnal(Player p) {
		PerkManager.perks.put(perkname + p.getUniqueId().toString(), true);
		p.sendMessage(Utils.chat("&9&lPerks &8> &7You have successfully &aunlocked &7the &5Nocturnal Perk&7!"));
	}

	public static void enableNocturnal(Player p) {
		PerkManager.perks.put(perkname + "_enabled" + p.getUniqueId().toString(), true);
		p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 1), true);
		p.sendMessage(Utils.chat("&9&lPerks &8> &7You have successfully &aenabled &7the perk &5Nocturnal&7!"));
	}

	public static void disableNocturnal(Player p) {
		PerkManager.perks.remove(perkname + "_enabled" + p.getUniqueId().toString());
		p.removePotionEffect(PotionEffectType.NIGHT_VISION);
		p.sendMessage(Utils.chat("&9&lPerks &8> &7You have successfully &edisabled &7the perk &5Nocturnal&7!"));
	}

	public void enableNocturnalReload() {
		for (Player onlinePlayers : Bukkit.getOnlinePlayers()) {
			if (PerkManager.checkPerkUnlocked(onlinePlayers, "Nocturnal")) {
				if (PerkManager.checkPerkActive(onlinePlayers, "nocturnal") == true) {
					onlinePlayers.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 1),
							true);
				}
			}
		}
	}
}