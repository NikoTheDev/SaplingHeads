package com.vervedev.saplingheads.managers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import com.vervedev.saplingheads.Main;
import com.vervedev.saplingheads.ui.SkullVaultUI;
import com.vervedev.saplingheads.utils.Utils;

public class CurrencyManager {

	public static Main plugin;

	public CurrencyManager(Main plugin) {
		this.plugin = plugin;
	}

	public static HashMap<String, Integer> perkCredits = new HashMap<String, Integer>();

	/*
	 * Perk Credits Data
	 */

	public static void savePerkCredits(OfflinePlayer p) throws FileNotFoundException, IOException {

		// Creates the output stream, specify the correct file
		File file = new File("SaplingData/perkcredits.dat");
		ObjectOutputStream output = new ObjectOutputStream(new GZIPOutputStream(new FileOutputStream(file)));

		if (perkCredits.get(p.getUniqueId().toString() + "perkcredits") != null) {
			perkCredits.put(p.getUniqueId().toString() + "perkcredits", perkCredits.get(p.getUniqueId().toString() + "perkcredits"));
		}

		try {
			// Write the map to the output stream, then close
			output.writeObject(perkCredits);
			output.flush();
			output.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public static void loadPerkCredits(OfflinePlayer p)
			throws FileNotFoundException, IOException, ClassNotFoundException {
		// Create the input stream
		File file = new File("SaplingData/perkcredits.dat");
		ObjectInputStream input = new ObjectInputStream(new GZIPInputStream(new FileInputStream(file)));
		// Reads the first object in
		Object readObject = input.readObject();
		input.close();

		if (!(readObject instanceof HashMap))
			throw new IOException("Data is not a hashmap");
		perkCredits = (HashMap<String, Integer>) readObject;
		// Prints out everything in the map.
		for (String key : perkCredits.keySet()) {
			perkCredits.put(key, perkCredits.get(key));
		}
	}

	public static int getPerkCredits(OfflinePlayer p) {
		if (perkCredits.get(p.getUniqueId().toString() + "perkcredits") == null) {
			return 0;
		}
		return perkCredits.get(p.getUniqueId().toString() + "perkcredits");
	}

	public static void addPerkCredits(Player p, int amount) {
		perkCredits.put(p.getUniqueId().toString() + "perkcredits", getPerkCredits(p) + amount);
	}

	public static void removePerkCredits(OfflinePlayer p, int amount) {
		perkCredits.put(p.getUniqueId().toString() + "perkcredits", getPerkCredits(p) - amount);
	}

	public static void convertSkullsToCredit(Player p) {
		int chickenSkulls = 1;
		int pigSkulls = 0;
		int sheepSkulls = 0;
		int cowSkulls = 0;
		int zombieSkulls = 0;
		int skeletonSkulls = 0;
		int pigzombieSkulls = 0;
		int slimeSkulls = 0;
		int creeperSkulls = 0;
		int pandaSkulls = 0;

		if (SkullManager.getChickenSkullAmount(p) >= chickenSkulls && SkullManager.getPigSkullAmount(p) >= pigSkulls
				&& SkullManager.getSheepSkullAmount(p) >= sheepSkulls && SkullManager.getCowSkullAmount(p) >= cowSkulls
				&& SkullManager.getZombieSkullAmount(p) >= zombieSkulls
				&& SkullManager.getSkeletonSkullAmount(p) >= skeletonSkulls
				&& SkullManager.getPigZombieSkullAmount(p) >= pigzombieSkulls
				&& SkullManager.getSlimeSkullAmount(p) >= slimeSkulls
				&& SkullManager.getCreeperSkullAmount(p) >= creeperSkulls
				&& SkullManager.getPandaSkullAmount(p) >= pandaSkulls) {
			if (chickenSkulls > 0) {
				SkullManager.removeChickenSkull(p, chickenSkulls);
			}
			if (pigSkulls > 0) {
				SkullManager.removePigSkull(p, pigSkulls);
			}
			if (sheepSkulls > 0) {
				SkullManager.removeSheepSkull(p, sheepSkulls);
			}
			if (cowSkulls > 0) {
				SkullManager.removeCowSkull(p, cowSkulls);
			}
			if (zombieSkulls > 0) {
				SkullManager.removeZombieSkull(p, zombieSkulls);
			}
			if (skeletonSkulls > 0) {
				SkullManager.removeSkeletonSkull(p, skeletonSkulls);
			}
			if (pigzombieSkulls > 0) {
				SkullManager.removePigZombieSkull(p, pigzombieSkulls);
			}
			if (slimeSkulls > 0) {
				SkullManager.removeSlimeSkull(p, slimeSkulls);
			}
			if (creeperSkulls > 0) {
				SkullManager.removeCreeperSkull(p, creeperSkulls);
			}
			if (pandaSkulls > 0) {
				SkullManager.removePandaSkull(p, pandaSkulls);
			}
			CurrencyManager.addPerkCredits(p, 1);
			p.sendMessage(
					Utils.chat("&9&lPerks &8> &7You have successfully converted &6skulls &7for &91 Perk Credit&7!"));
			p.openInventory(SkullVaultUI.GUI(p));
			p.updateInventory();
		} else {
			p.sendMessage(Utils.chat(
					"&a&lSaplingMC &8> &7You &cdo not &7have sufficient skulls to convert into &9Perk Credits&7!"));
		}
	}
}
