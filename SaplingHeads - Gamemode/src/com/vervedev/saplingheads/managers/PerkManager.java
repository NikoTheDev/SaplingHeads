package com.vervedev.saplingheads.managers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import com.vervedev.saplingheads.Main;

public class PerkManager {

	public static Main plugin;

	public PerkManager(Main plugin) {
		this.plugin = plugin;
	}

	public static HashMap<String, Boolean> perks = new HashMap<String, Boolean>();

	/*
	 * Perk Data
	 */

	public static void savePerks(OfflinePlayer p) throws FileNotFoundException, IOException {

		// Creates the output stream, specify the correct file
		File file = new File("SaplingData/perks.dat");
		ObjectOutputStream output = new ObjectOutputStream(new GZIPOutputStream(new FileOutputStream(file)));

		if (perks.get(p.getUniqueId().toString()) != null) {
			perks.put(p.getUniqueId().toString(), perks.get(p.getUniqueId().toString()));
		}

		try {
			// Write the map to the output stream, then close
			output.writeObject(perks);
			output.flush();
			output.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public static void loadPerks(OfflinePlayer p) throws FileNotFoundException, IOException, ClassNotFoundException {
		// Create the input stream
		File file = new File("SaplingData/perks.dat");
		ObjectInputStream input = new ObjectInputStream(new GZIPInputStream(new FileInputStream(file)));
		// Reads the first object in
		Object readObject = input.readObject();
		input.close();

		if (!(readObject instanceof HashMap))
			throw new IOException("Data is not a hashmap");
		perks = (HashMap<String, Boolean>) readObject;
		// Prints out everything in the map.
		for (String key : perks.keySet()) {
			perks.put(key, perks.get(key));
		}
	}

	public static boolean checkPerkUnlocked(Player p, String perk) {
		String uuid = p.getUniqueId().toString();
		if (perks.get(perk + p.getUniqueId().toString()) == null) {
			return false;
		}
		if (perks.get(perk + uuid) == true) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean checkPerkActive(Player p, String perk) {
		String uuid = p.getUniqueId().toString();
		if (perks.get(perk + "_enabled" + uuid) == null) {
			return false;
		}
		if (perks.get(perk + "_enabled" + uuid) == true) {
			return true;
		} else {
			return false;
		}
	}
}
