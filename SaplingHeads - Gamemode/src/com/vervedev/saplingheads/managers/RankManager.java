package com.vervedev.saplingheads.managers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;

import com.vervedev.saplingheads.Main;
import com.vervedev.saplingheads.ui.RankupUI;
import com.vervedev.saplingheads.utils.Utils;

public class RankManager {

	private static Main plugin;

	public static Map<String, Integer> ranks = new HashMap<String, Integer>();

	public RankManager(Main plugin) {
		this.plugin = plugin;
	}

	public static void saveRanks(OfflinePlayer p) throws FileNotFoundException, IOException {

		// Creates the output stream, specify the correct file
		File file = new File("SaplingData/skulldata.dat");
		ObjectOutputStream output = new ObjectOutputStream(new GZIPOutputStream(new FileOutputStream(file)));

		if (ranks.get(p.getUniqueId().toString()) != null) {
			ranks.put(p.getUniqueId().toString(), ranks.get(p.getUniqueId().toString()));
		} else {
			
		}

		try {
			// Write the map to the output stream, then close
			output.writeObject(ranks);
			output.flush();
			output.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

	}

	@SuppressWarnings("unchecked")
	public static void loadRanks(OfflinePlayer p) throws FileNotFoundException, IOException, ClassNotFoundException {
		// Create the input stream
		File file = new File("SaplingData/skulldata.dat");
		ObjectInputStream input = new ObjectInputStream(new GZIPInputStream(new FileInputStream(file)));
		// Reads the first object in
		Object readObject = input.readObject();
		input.close();

		if (!(readObject instanceof HashMap))
			throw new IOException("Data is not a hashmap");
		ranks = (HashMap<String, Integer>) readObject;
		// Prints out everything in the map.
		for (String key : ranks.keySet()) {
			ranks.put(key, ranks.get(key));
		}
	}

	public static String getRankString(Player p) {
		UUID uuid = p.getUniqueId();
		String uuids = uuid.toString();
		if (ranks.get(uuids) == null || ranks.get(uuids) == 0) {
			return "None";
		} else if (ranks.get(uuids) == 1) {
			return "Chicken";
		} else if (ranks.get(uuids) == 2) {
			return "Pig";
		} else if (ranks.get(uuids) == 3) {
			return "Sheep";
		} else if (ranks.get(uuids) == 4) {
			return "Cow";
		} else if (ranks.get(uuids) == 5) {
			return "Zombie";
		} else if (ranks.get(uuids) == 6) {
			return "Skeleton";
		} else if (ranks.get(uuids) == 7) {
			return "Zombie Pigman";
		} else if (ranks.get(uuids) == 8) {
			return "Slime";
		} else if (ranks.get(uuids) == 9) {
			return "Creeper";
		} else if (ranks.get(uuids) == 10) {
			return "Panda";
		}
		return "null";
	}

	public static int getRank(Player p) {
		UUID uuid = p.getUniqueId();
		String uuids = uuid.toString();
		if (ranks.get(uuids) == null) {
			return 0;
		} else {
			return ranks.get(uuids);
		}
	}

	public static void setRank(OfflinePlayer p, int rankInt) {
		if (rankInt <= 10) {
			ranks.put(p.getUniqueId().toString(), rankInt);
		} else {
			System.out.println(Utils.chat("&6&lRank out of bounds &e(SetRank: 110)"));
		}
	}

	public static void rankupPlayer(Player p) {
		if (eligibleToRankup(p)) {
			int rankInt = 0;
			if (ranks.get(p.getUniqueId().toString()) != null) {
				rankInt = ranks.get(p.getUniqueId().toString());
			} else {
				rankInt = 0;
			}
			if (rankInt < 10) {
				int newRank = rankInt + 1;
				ranks.put(p.getUniqueId().toString(), newRank);
				if (newRank == 1) {
					p.updateInventory();
					p.openInventory(RankupUI.GUI(p));
					int requiredCash = plugin.getConfig().getInt("Rankup_Requirements.chicken.cash");
					int requiredChickenSkulls = plugin.getConfig().getInt("Rankup_Requirements.chicken.chicken_skulls");
					int requiredPigSkulls = plugin.getConfig().getInt("Rankup_Requirements.chicken.pig_skulls");
					int requiredSheepSkulls = plugin.getConfig().getInt("Rankup_Requirements.chicken.sheep_skulls");
					int requiredCowSkulls = plugin.getConfig().getInt("Rankup_Requirements.chicken.cow_skulls");
					int requiredZombieSkulls = plugin.getConfig().getInt("Rankup_Requirements.chicken.zombie_skulls");
					int requiredSkeletonSkulls = plugin.getConfig()
							.getInt("Rankup_Requirements.chicken.skeleton_skulls");
					int requiredPigZombieSkulls = plugin.getConfig()
							.getInt("Rankup_Requirements.chicken.pigzombie_skulls");
					int requiredSlimeSkulls = plugin.getConfig().getInt("Rankup_Requirements.chicken.slime_skulls");
					int requiredCreeperSkulls = plugin.getConfig().getInt("Rankup_Requirements.chicken.creeper_skulls");
					int requiredPandaSkulls = plugin.getConfig().getInt("Rankup_Requirements.chicken.panda_skulls");
					Main.econ.withdrawPlayer(p, requiredCash);
					SkullManager.removeChickenSkull(p, requiredChickenSkulls);
					SkullManager.removePigSkull(p, requiredPigSkulls);
					SkullManager.removeSheepSkull(p, requiredSheepSkulls);
					SkullManager.removeCowSkull(p, requiredCowSkulls);
					SkullManager.removeZombieSkull(p, requiredZombieSkulls);
					SkullManager.removeSkeletonSkull(p, requiredSkeletonSkulls);
					SkullManager.removePigZombieSkull(p, requiredPigZombieSkulls);
					SkullManager.removeSlimeSkull(p, requiredSlimeSkulls);
					SkullManager.removeCreeperSkull(p, requiredCreeperSkulls);
					SkullManager.removePandaSkull(p, requiredPandaSkulls);
					launchFirework(p, 10, "white");
					p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.rankup.chicken")));
					Bukkit.broadcastMessage(Utils.chat(plugin.getConfig().getString("messages.rankup.chicken_broadcast")
							.replaceAll("<playername>", p.getDisplayName())));
				} else if (newRank == 2) {
					p.updateInventory();
					p.openInventory(RankupUI.GUI(p));
					int requiredCash = plugin.getConfig().getInt("Rankup_Requirements.pig.cash");
					int requiredChickenSkulls = plugin.getConfig().getInt("Rankup_Requirements.pig.chicken_skulls");
					int requiredPigSkulls = plugin.getConfig().getInt("Rankup_Requirements.pig.pig_skulls");
					int requiredSheepSkulls = plugin.getConfig().getInt("Rankup_Requirements.pig.sheep_skulls");
					int requiredCowSkulls = plugin.getConfig().getInt("Rankup_Requirements.pig.cow_skulls");
					int requiredZombieSkulls = plugin.getConfig().getInt("Rankup_Requirements.pig.zombie_skulls");
					int requiredSkeletonSkulls = plugin.getConfig().getInt("Rankup_Requirements.pig.skeleton_skulls");
					int requiredPigZombieSkulls = plugin.getConfig().getInt("Rankup_Requirements.pig.pigzombie_skulls");
					int requiredSlimeSkulls = plugin.getConfig().getInt("Rankup_Requirements.pig.slime_skulls");
					int requiredCreeperSkulls = plugin.getConfig().getInt("Rankup_Requirements.pig.creeper_skulls");
					int requiredPandaSkulls = plugin.getConfig().getInt("Rankup_Requirements.pig.panda_skulls");
					Main.econ.withdrawPlayer(p, requiredCash);
					SkullManager.removeChickenSkull(p, requiredChickenSkulls);
					SkullManager.removePigSkull(p, requiredPigSkulls);
					SkullManager.removeSheepSkull(p, requiredSheepSkulls);
					SkullManager.removeCowSkull(p, requiredCowSkulls);
					SkullManager.removeZombieSkull(p, requiredZombieSkulls);
					SkullManager.removeSkeletonSkull(p, requiredSkeletonSkulls);
					SkullManager.removePigZombieSkull(p, requiredPigZombieSkulls);
					SkullManager.removeSlimeSkull(p, requiredSlimeSkulls);
					SkullManager.removeCreeperSkull(p, requiredCreeperSkulls);
					SkullManager.removePandaSkull(p, requiredPandaSkulls);
					launchFirework(p, 10, "pink");
					p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.rankup.pig")));
					Bukkit.broadcastMessage(Utils.chat(plugin.getConfig().getString("messages.rankup.pig_broadcast")
							.replaceAll("<playername>", p.getDisplayName())));
				} else if (newRank == 3) {
					p.updateInventory();
					p.openInventory(RankupUI.GUI(p));
					int requiredCash = plugin.getConfig().getInt("Rankup_Requirements.sheep.cash");
					int requiredChickenSkulls = plugin.getConfig().getInt("Rankup_Requirements.sheep.chicken_skulls");
					int requiredPigSkulls = plugin.getConfig().getInt("Rankup_Requirements.sheep.pig_skulls");
					int requiredSheepSkulls = plugin.getConfig().getInt("Rankup_Requirements.sheep.sheep_skulls");
					int requiredCowSkulls = plugin.getConfig().getInt("Rankup_Requirements.sheep.cow_skulls");
					int requiredZombieSkulls = plugin.getConfig().getInt("Rankup_Requirements.sheep.zombie_skulls");
					int requiredSkeletonSkulls = plugin.getConfig().getInt("Rankup_Requirements.sheep.skeleton_skulls");
					int requiredPigZombieSkulls = plugin.getConfig()
							.getInt("Rankup_Requirements.sheep.pigzombie_skulls");
					int requiredSlimeSkulls = plugin.getConfig().getInt("Rankup_Requirements.sheep.slime_skulls");
					int requiredCreeperSkulls = plugin.getConfig().getInt("Rankup_Requirements.sheep.creeper_skulls");
					int requiredPandaSkulls = plugin.getConfig().getInt("Rankup_Requirements.sheep.panda_skulls");
					Main.econ.withdrawPlayer(p, requiredCash);
					SkullManager.removeChickenSkull(p, requiredChickenSkulls);
					SkullManager.removePigSkull(p, requiredPigSkulls);
					SkullManager.removeSheepSkull(p, requiredSheepSkulls);
					SkullManager.removeCowSkull(p, requiredCowSkulls);
					SkullManager.removeZombieSkull(p, requiredZombieSkulls);
					SkullManager.removeSkeletonSkull(p, requiredSkeletonSkulls);
					SkullManager.removePigZombieSkull(p, requiredPigZombieSkulls);
					SkullManager.removeSlimeSkull(p, requiredSlimeSkulls);
					SkullManager.removeCreeperSkull(p, requiredCreeperSkulls);
					SkullManager.removePandaSkull(p, requiredPandaSkulls);
					launchFirework(p, 10, "gray");
					p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.rankup.sheep")));
					Bukkit.broadcastMessage(Utils.chat(plugin.getConfig().getString("messages.rankup.sheep_broadcast")
							.replaceAll("<playername>", p.getDisplayName())));
				} else if (newRank == 4) {
					p.updateInventory();
					p.openInventory(RankupUI.GUI(p));
					int requiredCash = plugin.getConfig().getInt("Rankup_Requirements.cow.cash");
					int requiredChickenSkulls = plugin.getConfig().getInt("Rankup_Requirements.cow.chicken_skulls");
					int requiredPigSkulls = plugin.getConfig().getInt("Rankup_Requirements.cow.pig_skulls");
					int requiredSheepSkulls = plugin.getConfig().getInt("Rankup_Requirements.cow.sheep_skulls");
					int requiredCowSkulls = plugin.getConfig().getInt("Rankup_Requirements.cow.cow_skulls");
					int requiredZombieSkulls = plugin.getConfig().getInt("Rankup_Requirements.cow.zombie_skulls");
					int requiredSkeletonSkulls = plugin.getConfig().getInt("Rankup_Requirements.cow.skeleton_skulls");
					int requiredPigZombieSkulls = plugin.getConfig().getInt("Rankup_Requirements.cow.pigzombie_skulls");
					int requiredSlimeSkulls = plugin.getConfig().getInt("Rankup_Requirements.cow.slime_skulls");
					int requiredCreeperSkulls = plugin.getConfig().getInt("Rankup_Requirements.cow.creeper_skulls");
					int requiredPandaSkulls = plugin.getConfig().getInt("Rankup_Requirements.cow.panda_skulls");
					Main.econ.withdrawPlayer(p, requiredCash);
					SkullManager.removeChickenSkull(p, requiredChickenSkulls);
					SkullManager.removePigSkull(p, requiredPigSkulls);
					SkullManager.removeSheepSkull(p, requiredSheepSkulls);
					SkullManager.removeCowSkull(p, requiredCowSkulls);
					SkullManager.removeZombieSkull(p, requiredZombieSkulls);
					SkullManager.removeSkeletonSkull(p, requiredSkeletonSkulls);
					SkullManager.removePigZombieSkull(p, requiredPigZombieSkulls);
					SkullManager.removeSlimeSkull(p, requiredSlimeSkulls);
					SkullManager.removeCreeperSkull(p, requiredCreeperSkulls);
					SkullManager.removePandaSkull(p, requiredPandaSkulls);
					launchFirework(p, 10, "darkgray");
					p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.rankup.cow")));
					Bukkit.broadcastMessage(Utils.chat(plugin.getConfig().getString("messages.rankup.cow_broadcast")
							.replaceAll("<playername>", p.getDisplayName())));
				} else if (newRank == 5) {
					p.updateInventory();
					p.openInventory(RankupUI.GUI(p));
					int requiredCash = plugin.getConfig().getInt("Rankup_Requirements.zombie.cash");
					int requiredChickenSkulls = plugin.getConfig().getInt("Rankup_Requirements.zombie.chicken_skulls");
					int requiredPigSkulls = plugin.getConfig().getInt("Rankup_Requirements.zombie.pig_skulls");
					int requiredSheepSkulls = plugin.getConfig().getInt("Rankup_Requirements.zombie.sheep_skulls");
					int requiredCowSkulls = plugin.getConfig().getInt("Rankup_Requirements.zombie.cow_skulls");
					int requiredZombieSkulls = plugin.getConfig().getInt("Rankup_Requirements.zombie.zombie_skulls");
					int requiredSkeletonSkulls = plugin.getConfig()
							.getInt("Rankup_Requirements.zombie.skeleton_skulls");
					int requiredPigZombieSkulls = plugin.getConfig()
							.getInt("Rankup_Requirements.zombie.pigzombie_skulls");
					int requiredSlimeSkulls = plugin.getConfig().getInt("Rankup_Requirements.zombie.slime_skulls");
					int requiredCreeperSkulls = plugin.getConfig().getInt("Rankup_Requirements.zombie.creeper_skulls");
					int requiredPandaSkulls = plugin.getConfig().getInt("Rankup_Requirements.zombie.panda_skulls");
					Main.econ.withdrawPlayer(p, requiredCash);
					SkullManager.removeChickenSkull(p, requiredChickenSkulls);
					SkullManager.removePigSkull(p, requiredPigSkulls);
					SkullManager.removeSheepSkull(p, requiredSheepSkulls);
					SkullManager.removeCowSkull(p, requiredCowSkulls);
					SkullManager.removeZombieSkull(p, requiredZombieSkulls);
					SkullManager.removeSkeletonSkull(p, requiredSkeletonSkulls);
					SkullManager.removePigZombieSkull(p, requiredPigZombieSkulls);
					SkullManager.removeSlimeSkull(p, requiredSlimeSkulls);
					SkullManager.removeCreeperSkull(p, requiredCreeperSkulls);
					SkullManager.removePandaSkull(p, requiredPandaSkulls);
					launchFirework(p, 10, "green");
					p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.rankup.zombie")));
					Bukkit.broadcastMessage(Utils.chat(plugin.getConfig().getString("messages.rankup.zombie_broadcast")
							.replaceAll("<playername>", p.getDisplayName())));
				} else if (newRank == 6) {
					p.updateInventory();
					p.openInventory(RankupUI.GUI(p));
					int requiredCash = plugin.getConfig().getInt("Rankup_Requirements.skeleton.cash");
					int requiredChickenSkulls = plugin.getConfig()
							.getInt("Rankup_Requirements.skeleton.chicken_skulls");
					int requiredPigSkulls = plugin.getConfig().getInt("Rankup_Requirements.skeleton.pig_skulls");
					int requiredSheepSkulls = plugin.getConfig().getInt("Rankup_Requirements.skeleton.sheep_skulls");
					int requiredCowSkulls = plugin.getConfig().getInt("Rankup_Requirements.skeleton.cow_skulls");
					int requiredZombieSkulls = plugin.getConfig().getInt("Rankup_Requirements.skeleton.zombie_skulls");
					int requiredSkeletonSkulls = plugin.getConfig()
							.getInt("Rankup_Requirements.skeleton.skeleton_skulls");
					int requiredPigZombieSkulls = plugin.getConfig()
							.getInt("Rankup_Requirements.skeleton.pigzombie_skulls");
					int requiredSlimeSkulls = plugin.getConfig().getInt("Rankup_Requirements.skeleton.slime_skulls");
					int requiredCreeperSkulls = plugin.getConfig()
							.getInt("Rankup_Requirements.skeleton.creeper_skulls");
					int requiredPandaSkulls = plugin.getConfig().getInt("Rankup_Requirements.skeleton.panda_skulls");
					Main.econ.withdrawPlayer(p, requiredCash);
					SkullManager.removeChickenSkull(p, requiredChickenSkulls);
					SkullManager.removePigSkull(p, requiredPigSkulls);
					SkullManager.removeSheepSkull(p, requiredSheepSkulls);
					SkullManager.removeCowSkull(p, requiredCowSkulls);
					SkullManager.removeZombieSkull(p, requiredZombieSkulls);
					SkullManager.removeSkeletonSkull(p, requiredSkeletonSkulls);
					SkullManager.removePigZombieSkull(p, requiredPigZombieSkulls);
					SkullManager.removeSlimeSkull(p, requiredSlimeSkulls);
					SkullManager.removeCreeperSkull(p, requiredCreeperSkulls);
					SkullManager.removePandaSkull(p, requiredPandaSkulls);
					launchFirework(p, 10, "aqua");
					p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.rankup.skeleton")));
					Bukkit.broadcastMessage(
							Utils.chat(plugin.getConfig().getString("messages.rankup.skeleton_broadcast")
									.replaceAll("<playername>", p.getDisplayName())));
				} else if (newRank == 7) {
					p.updateInventory();
					p.openInventory(RankupUI.GUI(p));
					int requiredCash = plugin.getConfig().getInt("Rankup_Requirements.pigzombie.cash");
					int requiredChickenSkulls = plugin.getConfig()
							.getInt("Rankup_Requirements.pigzombie.chicken_skulls");
					int requiredPigSkulls = plugin.getConfig().getInt("Rankup_Requirements.pigzombie.pig_skulls");
					int requiredSheepSkulls = plugin.getConfig().getInt("Rankup_Requirements.pigzombie.sheep_skulls");
					int requiredCowSkulls = plugin.getConfig().getInt("Rankup_Requirements.pigzombie.cow_skulls");
					int requiredZombieSkulls = plugin.getConfig().getInt("Rankup_Requirements.pigzombie.zombie_skulls");
					int requiredSkeletonSkulls = plugin.getConfig()
							.getInt("Rankup_Requirements.pigzombie.skeleton_skulls");
					int requiredPigZombieSkulls = plugin.getConfig()
							.getInt("Rankup_Requirements.pigzombie.pigzombie_skulls");
					int requiredSlimeSkulls = plugin.getConfig().getInt("Rankup_Requirements.pigzombie.slime_skulls");
					int requiredCreeperSkulls = plugin.getConfig()
							.getInt("Rankup_Requirements.pigzombie.creeper_skulls");
					int requiredPandaSkulls = plugin.getConfig().getInt("Rankup_Requirements.pigzombie.panda_skulls");
					Main.econ.withdrawPlayer(p, requiredCash);
					SkullManager.removeChickenSkull(p, requiredChickenSkulls);
					SkullManager.removePigSkull(p, requiredPigSkulls);
					SkullManager.removeSheepSkull(p, requiredSheepSkulls);
					SkullManager.removeCowSkull(p, requiredCowSkulls);
					SkullManager.removeZombieSkull(p, requiredZombieSkulls);
					SkullManager.removeSkeletonSkull(p, requiredSkeletonSkulls);
					SkullManager.removePigZombieSkull(p, requiredPigZombieSkulls);
					SkullManager.removeSlimeSkull(p, requiredSlimeSkulls);
					SkullManager.removeCreeperSkull(p, requiredCreeperSkulls);
					SkullManager.removePandaSkull(p, requiredPandaSkulls);
					launchFirework(p, 10, "red");
					p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.rankup.pigzombie")));
					Bukkit.broadcastMessage(
							Utils.chat(plugin.getConfig().getString("messages.rankup.pigzombie_broadcast")
									.replaceAll("<playername>", p.getDisplayName())));
				} else if (newRank == 8) {
					p.updateInventory();
					p.openInventory(RankupUI.GUI(p));
					int requiredCash = plugin.getConfig().getInt("Rankup_Requirements.slime.cash");
					int requiredChickenSkulls = plugin.getConfig().getInt("Rankup_Requirements.slime.chicken_skulls");
					int requiredPigSkulls = plugin.getConfig().getInt("Rankup_Requirements.slime.pig_skulls");
					int requiredSheepSkulls = plugin.getConfig().getInt("Rankup_Requirements.slime.sheep_skulls");
					int requiredCowSkulls = plugin.getConfig().getInt("Rankup_Requirements.slime.cow_skulls");
					int requiredZombieSkulls = plugin.getConfig().getInt("Rankup_Requirements.slime.zombie_skulls");
					int requiredSkeletonSkulls = plugin.getConfig().getInt("Rankup_Requirements.slime.skeleton_skulls");
					int requiredPigZombieSkulls = plugin.getConfig()
							.getInt("Rankup_Requirements.slime.pigzombie_skulls");
					int requiredSlimeSkulls = plugin.getConfig().getInt("Rankup_Requirements.slime.slime_skulls");
					int requiredCreeperSkulls = plugin.getConfig().getInt("Rankup_Requirements.slime.creeper_skulls");
					int requiredPandaSkulls = plugin.getConfig().getInt("Rankup_Requirements.slime.panda_skulls");
					Main.econ.withdrawPlayer(p, requiredCash);
					SkullManager.removeChickenSkull(p, requiredChickenSkulls);
					SkullManager.removePigSkull(p, requiredPigSkulls);
					SkullManager.removeSheepSkull(p, requiredSheepSkulls);
					SkullManager.removeCowSkull(p, requiredCowSkulls);
					SkullManager.removeZombieSkull(p, requiredZombieSkulls);
					SkullManager.removeSkeletonSkull(p, requiredSkeletonSkulls);
					SkullManager.removePigZombieSkull(p, requiredPigZombieSkulls);
					SkullManager.removeSlimeSkull(p, requiredSlimeSkulls);
					SkullManager.removeCreeperSkull(p, requiredCreeperSkulls);
					SkullManager.removePandaSkull(p, requiredPandaSkulls);
					launchFirework(p, 10, "lime");
					p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.rankup.slime")));
					Bukkit.broadcastMessage(Utils.chat(plugin.getConfig().getString("messages.rankup.slime_broadcast")
							.replaceAll("<playername>", p.getDisplayName())));
				} else if (newRank == 9) {
					p.updateInventory();
					p.openInventory(RankupUI.GUI(p));
					int requiredCash = plugin.getConfig().getInt("Rankup_Requirements.creeper.cash");
					int requiredChickenSkulls = plugin.getConfig().getInt("Rankup_Requirements.creeper.chicken_skulls");
					int requiredPigSkulls = plugin.getConfig().getInt("Rankup_Requirements.creeper.pig_skulls");
					int requiredSheepSkulls = plugin.getConfig().getInt("Rankup_Requirements.creeper.sheep_skulls");
					int requiredCowSkulls = plugin.getConfig().getInt("Rankup_Requirements.creeper.cow_skulls");
					int requiredZombieSkulls = plugin.getConfig().getInt("Rankup_Requirements.creeper.zombie_skulls");
					int requiredSkeletonSkulls = plugin.getConfig()
							.getInt("Rankup_Requirements.creeper.skeleton_skulls");
					int requiredPigZombieSkulls = plugin.getConfig()
							.getInt("Rankup_Requirements.creeper.pigzombie_skulls");
					int requiredSlimeSkulls = plugin.getConfig().getInt("Rankup_Requirements.creeper.slime_skulls");
					int requiredCreeperSkulls = plugin.getConfig().getInt("Rankup_Requirements.creeper.creeper_skulls");
					int requiredPandaSkulls = plugin.getConfig().getInt("Rankup_Requirements.creeper.panda_skulls");
					Main.econ.withdrawPlayer(p, requiredCash);
					SkullManager.removeChickenSkull(p, requiredChickenSkulls);
					SkullManager.removePigSkull(p, requiredPigSkulls);
					SkullManager.removeSheepSkull(p, requiredSheepSkulls);
					SkullManager.removeCowSkull(p, requiredCowSkulls);
					SkullManager.removeZombieSkull(p, requiredZombieSkulls);
					SkullManager.removeSkeletonSkull(p, requiredSkeletonSkulls);
					SkullManager.removePigZombieSkull(p, requiredPigZombieSkulls);
					SkullManager.removeSlimeSkull(p, requiredSlimeSkulls);
					SkullManager.removeCreeperSkull(p, requiredCreeperSkulls);
					SkullManager.removePandaSkull(p, requiredPandaSkulls);
					launchFirework(p, 10, "lime");
					p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.rankup.creeper")));
					Bukkit.broadcastMessage(Utils.chat(plugin.getConfig().getString("messages.rankup.creeper_broadcast")
							.replaceAll("<playername>", p.getDisplayName())));
				} else if (newRank == 10) {
					p.updateInventory();
					p.openInventory(RankupUI.GUI(p));
					int requiredCash = plugin.getConfig().getInt("Rankup_Requirements.panda.cash");
					int requiredChickenSkulls = plugin.getConfig().getInt("Rankup_Requirements.panda.chicken_skulls");
					int requiredPigSkulls = plugin.getConfig().getInt("Rankup_Requirements.panda.pig_skulls");
					int requiredSheepSkulls = plugin.getConfig().getInt("Rankup_Requirements.panda.sheep_skulls");
					int requiredCowSkulls = plugin.getConfig().getInt("Rankup_Requirements.panda.cow_skulls");
					int requiredZombieSkulls = plugin.getConfig().getInt("Rankup_Requirements.panda.zombie_skulls");
					int requiredSkeletonSkulls = plugin.getConfig()
							.getInt("Rankup_Requirements.panda.skeleton_skulls");
					int requiredPigZombieSkulls = plugin.getConfig()
							.getInt("Rankup_Requirements.panda.pigzombie_skulls");
					int requiredSlimeSkulls = plugin.getConfig().getInt("Rankup_Requirements.panda.slime_skulls");
					int requiredCreeperSkulls = plugin.getConfig().getInt("Rankup_Requirements.panda.creeper_skulls");
					int requiredPandaSkulls = plugin.getConfig().getInt("Rankup_Requirements.panda.panda_skulls");
					Main.econ.withdrawPlayer(p, requiredCash);
					SkullManager.removeChickenSkull(p, requiredChickenSkulls);
					SkullManager.removePigSkull(p, requiredPigSkulls);
					SkullManager.removeSheepSkull(p, requiredSheepSkulls);
					SkullManager.removeCowSkull(p, requiredCowSkulls);
					SkullManager.removeZombieSkull(p, requiredZombieSkulls);
					SkullManager.removeSkeletonSkull(p, requiredSkeletonSkulls);
					SkullManager.removePigZombieSkull(p, requiredPigZombieSkulls);
					SkullManager.removeSlimeSkull(p, requiredSlimeSkulls);
					SkullManager.removeCreeperSkull(p, requiredCreeperSkulls);
					SkullManager.removePandaSkull(p, requiredPandaSkulls);
					launchFirework(p, 10, "gray");
					launchFirework(p, 10, "white");
					p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.rankup.panda")));
					Bukkit.broadcastMessage(Utils.chat(plugin.getConfig().getString("messages.rankup.panda_broadcast")
							.replaceAll("<playername>", p.getDisplayName())));
				}
			} else {
				p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.rankup.max_rank")));
			}
		} else {
			p.sendMessage(Utils.chat(
					"&2&lRankup &8> &7You are &cnot &7currently eligble to rankup! Please do &e/rankup &7to check the requirements!"));
		}
	}

	public static boolean eligibleToRankup(Player p) {
		if (getRank(p) == 0 /* Rankup to Chicken Rank */) {
			int requiredCash = plugin.getConfig().getInt("Rankup_Requirements.chicken.cash");
			int requiredChickenSkulls = plugin.getConfig().getInt("Rankup_Requirements.chicken.chicken_skulls");
			int requiredPigSkulls = plugin.getConfig().getInt("Rankup_Requirements.chicken.pig_skulls");
			int requiredSheepSkulls = plugin.getConfig().getInt("Rankup_Requirements.chicken.sheep_skulls");
			int requiredCowSkulls = plugin.getConfig().getInt("Rankup_Requirements.chicken.cow_skulls");
			int requiredZombieSkulls = plugin.getConfig().getInt("Rankup_Requirements.chicken.zombie_skulls");
			int requiredSkeletonSkulls = plugin.getConfig().getInt("Rankup_Requirements.chicken.skeleton_skulls");
			int requiredPigZombieSkulls = plugin.getConfig().getInt("Rankup_Requirements.chicken.pigzombie_skulls");
			int requiredSlimeSkulls = plugin.getConfig().getInt("Rankup_Requirements.chicken.slime_skulls");
			int requiredCreeperSkulls = plugin.getConfig().getInt("Rankup_Requirements.chicken.creeper_skulls");
			int requiredPandaSkulls = plugin.getConfig().getInt("Rankup_Requirements.chicken.panda_skulls");

			if (Main.econ.getBalance(p) >= requiredCash
					&& SkullManager.getChickenSkullAmount(p) >= requiredChickenSkulls
					&& SkullManager.getPigSkullAmount(p) >= requiredPigSkulls
					&& SkullManager.getSheepSkullAmount(p) >= requiredSheepSkulls
					&& SkullManager.getCowSkullAmount(p) >= requiredCowSkulls
					&& SkullManager.getZombieSkullAmount(p) >= requiredZombieSkulls
					&& SkullManager.getSkeletonSkullAmount(p) >= requiredSkeletonSkulls
					&& SkullManager.getPigZombieSkullAmount(p) >= requiredPigZombieSkulls
					&& SkullManager.getSlimeSkullAmount(p) >= requiredSlimeSkulls
					&& SkullManager.getCreeperSkullAmount(p) >= requiredCreeperSkulls
					&& SkullManager.getPandaSkullAmount(p) >= requiredPandaSkulls) {
				return true;
			}
		} else if (getRank(p) == 1 /* Rankup to Pig Rank */) {
			int requiredCash = plugin.getConfig().getInt("Rankup_Requirements.pig.cash");
			int requiredChickenSkulls = plugin.getConfig().getInt("Rankup_Requirements.pig.chicken_skulls");
			int requiredPigSkulls = plugin.getConfig().getInt("Rankup_Requirements.pig.pig_skulls");
			int requiredSheepSkulls = plugin.getConfig().getInt("Rankup_Requirements.pig.sheep_skulls");
			int requiredCowSkulls = plugin.getConfig().getInt("Rankup_Requirements.pig.cow_skulls");
			int requiredZombieSkulls = plugin.getConfig().getInt("Rankup_Requirements.pig.zombie_skulls");
			int requiredSkeletonSkulls = plugin.getConfig().getInt("Rankup_Requirements.pig.skeleton_skulls");
			int requiredPigZombieSkulls = plugin.getConfig().getInt("Rankup_Requirements.pig.pigzombie_skulls");
			int requiredSlimeSkulls = plugin.getConfig().getInt("Rankup_Requirements.pig.slime_skulls");
			int requiredCreeperSkulls = plugin.getConfig().getInt("Rankup_Requirements.pig.creeper_skulls");
			int requiredPandaSkulls = plugin.getConfig().getInt("Rankup_Requirements.pig.panda_skulls");

			if (Main.econ.getBalance(p) >= requiredCash
					&& SkullManager.getChickenSkullAmount(p) >= requiredChickenSkulls
					&& SkullManager.getPigSkullAmount(p) >= requiredPigSkulls
					&& SkullManager.getSheepSkullAmount(p) >= requiredSheepSkulls
					&& SkullManager.getCowSkullAmount(p) >= requiredCowSkulls
					&& SkullManager.getZombieSkullAmount(p) >= requiredZombieSkulls
					&& SkullManager.getSkeletonSkullAmount(p) >= requiredSkeletonSkulls
					&& SkullManager.getPigZombieSkullAmount(p) >= requiredPigZombieSkulls
					&& SkullManager.getSlimeSkullAmount(p) >= requiredSlimeSkulls
					&& SkullManager.getCreeperSkullAmount(p) >= requiredCreeperSkulls
					&& SkullManager.getPandaSkullAmount(p) >= requiredPandaSkulls) {
				return true;
			}
		} else if (getRank(p) == 2 /* Rankup to Sheep Rank */) {
			int requiredCash = plugin.getConfig().getInt("Rankup_Requirements.sheep.cash");
			int requiredChickenSkulls = plugin.getConfig().getInt("Rankup_Requirements.sheep.chicken_skulls");
			int requiredPigSkulls = plugin.getConfig().getInt("Rankup_Requirements.sheep.pig_skulls");
			int requiredSheepSkulls = plugin.getConfig().getInt("Rankup_Requirements.sheep.sheep_skulls");
			int requiredCowSkulls = plugin.getConfig().getInt("Rankup_Requirements.sheep.cow_skulls");
			int requiredZombieSkulls = plugin.getConfig().getInt("Rankup_Requirements.sheep.zombie_skulls");
			int requiredSkeletonSkulls = plugin.getConfig().getInt("Rankup_Requirements.sheep.skeleton_skulls");
			int requiredPigZombieSkulls = plugin.getConfig().getInt("Rankup_Requirements.sheep.pigzombie_skulls");
			int requiredSlimeSkulls = plugin.getConfig().getInt("Rankup_Requirements.sheep.slime_skulls");
			int requiredCreeperSkulls = plugin.getConfig().getInt("Rankup_Requirements.sheep.creeper_skulls");
			int requiredPandaSkulls = plugin.getConfig().getInt("Rankup_Requirements.sheep.panda_skulls");

			if (Main.econ.getBalance(p) >= requiredCash
					&& SkullManager.getChickenSkullAmount(p) >= requiredChickenSkulls
					&& SkullManager.getPigSkullAmount(p) >= requiredPigSkulls
					&& SkullManager.getSheepSkullAmount(p) >= requiredSheepSkulls
					&& SkullManager.getCowSkullAmount(p) >= requiredCowSkulls
					&& SkullManager.getZombieSkullAmount(p) >= requiredZombieSkulls
					&& SkullManager.getSkeletonSkullAmount(p) >= requiredSkeletonSkulls
					&& SkullManager.getPigZombieSkullAmount(p) >= requiredPigZombieSkulls
					&& SkullManager.getSlimeSkullAmount(p) >= requiredSlimeSkulls
					&& SkullManager.getCreeperSkullAmount(p) >= requiredCreeperSkulls
					&& SkullManager.getPandaSkullAmount(p) >= requiredPandaSkulls) {
				return true;
			}
		} else if (getRank(p) == 3 /* Rankup to Cow Rank */) {
			int requiredCash = plugin.getConfig().getInt("Rankup_Requirements.cow.cash");
			int requiredChickenSkulls = plugin.getConfig().getInt("Rankup_Requirements.cow.chicken_skulls");
			int requiredPigSkulls = plugin.getConfig().getInt("Rankup_Requirements.cow.pig_skulls");
			int requiredSheepSkulls = plugin.getConfig().getInt("Rankup_Requirements.cow.sheep_skulls");
			int requiredCowSkulls = plugin.getConfig().getInt("Rankup_Requirements.cow.cow_skulls");
			int requiredZombieSkulls = plugin.getConfig().getInt("Rankup_Requirements.cow.zombie_skulls");
			int requiredSkeletonSkulls = plugin.getConfig().getInt("Rankup_Requirements.cow.skeleton_skulls");
			int requiredPigZombieSkulls = plugin.getConfig().getInt("Rankup_Requirements.cow.pigzombie_skulls");
			int requiredSlimeSkulls = plugin.getConfig().getInt("Rankup_Requirements.cow.slime_skulls");
			int requiredCreeperSkulls = plugin.getConfig().getInt("Rankup_Requirements.cow.creeper_skulls");
			int requiredPandaSkulls = plugin.getConfig().getInt("Rankup_Requirements.cow.panda_skulls");

			if (Main.econ.getBalance(p) >= requiredCash
					&& SkullManager.getChickenSkullAmount(p) >= requiredChickenSkulls
					&& SkullManager.getPigSkullAmount(p) >= requiredPigSkulls
					&& SkullManager.getSheepSkullAmount(p) >= requiredSheepSkulls
					&& SkullManager.getCowSkullAmount(p) >= requiredCowSkulls
					&& SkullManager.getZombieSkullAmount(p) >= requiredZombieSkulls
					&& SkullManager.getSkeletonSkullAmount(p) >= requiredSkeletonSkulls
					&& SkullManager.getPigZombieSkullAmount(p) >= requiredPigZombieSkulls
					&& SkullManager.getSlimeSkullAmount(p) >= requiredSlimeSkulls
					&& SkullManager.getCreeperSkullAmount(p) >= requiredCreeperSkulls
					&& SkullManager.getPandaSkullAmount(p) >= requiredPandaSkulls) {
				p.sendMessage("Eligble for cow");
				return true;
			}
		} else if (getRank(p) == 4 /* Rankup to Zombie Rank */) {
			int requiredCash = plugin.getConfig().getInt("Rankup_Requirements.zombie.cash");
			int requiredChickenSkulls = plugin.getConfig().getInt("Rankup_Requirements.zombie.chicken_skulls");
			int requiredPigSkulls = plugin.getConfig().getInt("Rankup_Requirements.zombie.pig_skulls");
			int requiredSheepSkulls = plugin.getConfig().getInt("Rankup_Requirements.zombie.sheep_skulls");
			int requiredCowSkulls = plugin.getConfig().getInt("Rankup_Requirements.zombie.cow_skulls");
			int requiredZombieSkulls = plugin.getConfig().getInt("Rankup_Requirements.zombie.zombie_skulls");
			int requiredSkeletonSkulls = plugin.getConfig().getInt("Rankup_Requirements.zombie.skeleton_skulls");
			int requiredPigZombieSkulls = plugin.getConfig().getInt("Rankup_Requirements.zombie.pigzombie_skulls");
			int requiredSlimeSkulls = plugin.getConfig().getInt("Rankup_Requirements.zombie.slime_skulls");
			int requiredCreeperSkulls = plugin.getConfig().getInt("Rankup_Requirements.zombie.creeper_skulls");
			int requiredPandaSkulls = plugin.getConfig().getInt("Rankup_Requirements.zombie.panda_skulls");

			if (Main.econ.getBalance(p) >= requiredCash
					&& SkullManager.getChickenSkullAmount(p) >= requiredChickenSkulls
					&& SkullManager.getPigSkullAmount(p) >= requiredPigSkulls
					&& SkullManager.getSheepSkullAmount(p) >= requiredSheepSkulls
					&& SkullManager.getCowSkullAmount(p) >= requiredCowSkulls
					&& SkullManager.getZombieSkullAmount(p) >= requiredZombieSkulls
					&& SkullManager.getSkeletonSkullAmount(p) >= requiredSkeletonSkulls
					&& SkullManager.getPigZombieSkullAmount(p) >= requiredPigZombieSkulls
					&& SkullManager.getSlimeSkullAmount(p) >= requiredSlimeSkulls
					&& SkullManager.getCreeperSkullAmount(p) >= requiredCreeperSkulls
					&& SkullManager.getPandaSkullAmount(p) >= requiredPandaSkulls) {
				return true;
			}
		} else if (getRank(p) == 5 /* Rankup to Skeleton Rank */) {
			int requiredCash = plugin.getConfig().getInt("Rankup_Requirements.skeleton.cash");
			int requiredChickenSkulls = plugin.getConfig().getInt("Rankup_Requirements.skeleton.chicken_skulls");
			int requiredPigSkulls = plugin.getConfig().getInt("Rankup_Requirements.skeleton.pig_skulls");
			int requiredSheepSkulls = plugin.getConfig().getInt("Rankup_Requirements.skeleton.sheep_skulls");
			int requiredCowSkulls = plugin.getConfig().getInt("Rankup_Requirements.skeleton.cow_skulls");
			int requiredZombieSkulls = plugin.getConfig().getInt("Rankup_Requirements.skeleton.zombie_skulls");
			int requiredSkeletonSkulls = plugin.getConfig().getInt("Rankup_Requirements.skeleton.skeleton_skulls");
			int requiredPigZombieSkulls = plugin.getConfig().getInt("Rankup_Requirements.skeleton.pigzombie_skulls");
			int requiredSlimeSkulls = plugin.getConfig().getInt("Rankup_Requirements.skeleton.slime_skulls");
			int requiredCreeperSkulls = plugin.getConfig().getInt("Rankup_Requirements.skeleton.creeper_skulls");
			int requiredPandaSkulls = plugin.getConfig().getInt("Rankup_Requirements.skeleton.panda_skulls");

			if (Main.econ.getBalance(p) >= requiredCash
					&& SkullManager.getChickenSkullAmount(p) >= requiredChickenSkulls
					&& SkullManager.getPigSkullAmount(p) >= requiredPigSkulls
					&& SkullManager.getSheepSkullAmount(p) >= requiredSheepSkulls
					&& SkullManager.getCowSkullAmount(p) >= requiredCowSkulls
					&& SkullManager.getZombieSkullAmount(p) >= requiredZombieSkulls
					&& SkullManager.getSkeletonSkullAmount(p) >= requiredSkeletonSkulls
					&& SkullManager.getPigZombieSkullAmount(p) >= requiredPigZombieSkulls
					&& SkullManager.getSlimeSkullAmount(p) >= requiredSlimeSkulls
					&& SkullManager.getCreeperSkullAmount(p) >= requiredCreeperSkulls
					&& SkullManager.getPandaSkullAmount(p) >= requiredPandaSkulls) {
				return true;
			}
		} else if (getRank(p) == 6 /* Rankup to PigZombie Rank */) {
			int requiredCash = plugin.getConfig().getInt("Rankup_Requirements.pigzombie.cash");
			int requiredChickenSkulls = plugin.getConfig().getInt("Rankup_Requirements.pigzombie.chicken_skulls");
			int requiredPigSkulls = plugin.getConfig().getInt("Rankup_Requirements.pigzombie.pig_skulls");
			int requiredSheepSkulls = plugin.getConfig().getInt("Rankup_Requirements.pigzombie.sheep_skulls");
			int requiredCowSkulls = plugin.getConfig().getInt("Rankup_Requirements.pigzombie.cow_skulls");
			int requiredZombieSkulls = plugin.getConfig().getInt("Rankup_Requirements.pigzombie.zombie_skulls");
			int requiredSkeletonSkulls = plugin.getConfig().getInt("Rankup_Requirements.pigzombie.skeleton_skulls");
			int requiredPigZombieSkulls = plugin.getConfig().getInt("Rankup_Requirements.pigzombie.pigzombie_skulls");
			int requiredSlimeSkulls = plugin.getConfig().getInt("Rankup_Requirements.pigzombie.slime_skulls");
			int requiredCreeperSkulls = plugin.getConfig().getInt("Rankup_Requirements.pigzombie.creeper_skulls");
			int requiredPandaSkulls = plugin.getConfig().getInt("Rankup_Requirements.pigzombie.panda_skulls");

			if (Main.econ.getBalance(p) >= requiredCash
					&& SkullManager.getChickenSkullAmount(p) >= requiredChickenSkulls
					&& SkullManager.getPigSkullAmount(p) >= requiredPigSkulls
					&& SkullManager.getSheepSkullAmount(p) >= requiredSheepSkulls
					&& SkullManager.getCowSkullAmount(p) >= requiredCowSkulls
					&& SkullManager.getZombieSkullAmount(p) >= requiredZombieSkulls
					&& SkullManager.getSkeletonSkullAmount(p) >= requiredSkeletonSkulls
					&& SkullManager.getPigZombieSkullAmount(p) >= requiredPigZombieSkulls
					&& SkullManager.getSlimeSkullAmount(p) >= requiredSlimeSkulls
					&& SkullManager.getCreeperSkullAmount(p) >= requiredCreeperSkulls
					&& SkullManager.getPandaSkullAmount(p) >= requiredPandaSkulls) {
				return true;
			}
		} else if (getRank(p) == 7 /* Rankup to Slime Rank */) {
			int requiredCash = plugin.getConfig().getInt("Rankup_Requirements.slime.cash");
			int requiredChickenSkulls = plugin.getConfig().getInt("Rankup_Requirements.slime.chicken_skulls");
			int requiredPigSkulls = plugin.getConfig().getInt("Rankup_Requirements.slime.pig_skulls");
			int requiredSheepSkulls = plugin.getConfig().getInt("Rankup_Requirements.slime.sheep_skulls");
			int requiredCowSkulls = plugin.getConfig().getInt("Rankup_Requirements.slime.cow_skulls");
			int requiredZombieSkulls = plugin.getConfig().getInt("Rankup_Requirements.slime.zombie_skulls");
			int requiredSkeletonSkulls = plugin.getConfig().getInt("Rankup_Requirements.slime.skeleton_skulls");
			int requiredPigZombieSkulls = plugin.getConfig().getInt("Rankup_Requirements.slime.pigzombie_skulls");
			int requiredSlimeSkulls = plugin.getConfig().getInt("Rankup_Requirements.slime.slime_skulls");
			int requiredCreeperSkulls = plugin.getConfig().getInt("Rankup_Requirements.slime.creeper_skulls");
			int requiredPandaSkulls = plugin.getConfig().getInt("Rankup_Requirements.slime.panda_skulls");

			if (Main.econ.getBalance(p) >= requiredCash
					&& SkullManager.getChickenSkullAmount(p) >= requiredChickenSkulls
					&& SkullManager.getPigSkullAmount(p) >= requiredPigSkulls
					&& SkullManager.getSheepSkullAmount(p) >= requiredSheepSkulls
					&& SkullManager.getCowSkullAmount(p) >= requiredCowSkulls
					&& SkullManager.getZombieSkullAmount(p) >= requiredZombieSkulls
					&& SkullManager.getSkeletonSkullAmount(p) >= requiredSkeletonSkulls
					&& SkullManager.getPigZombieSkullAmount(p) >= requiredPigZombieSkulls
					&& SkullManager.getSlimeSkullAmount(p) >= requiredSlimeSkulls
					&& SkullManager.getCreeperSkullAmount(p) >= requiredCreeperSkulls
					&& SkullManager.getPandaSkullAmount(p) >= requiredPandaSkulls) {
				return true;
			}
		} else if (getRank(p) == 8 /* Rankup to Creeper Rank */) {
			int requiredCash = plugin.getConfig().getInt("Rankup_Requirements.creeper.cash");
			int requiredChickenSkulls = plugin.getConfig().getInt("Rankup_Requirements.creeper.chicken_skulls");
			int requiredPigSkulls = plugin.getConfig().getInt("Rankup_Requirements.creeper.pig_skulls");
			int requiredSheepSkulls = plugin.getConfig().getInt("Rankup_Requirements.creeper.sheep_skulls");
			int requiredCowSkulls = plugin.getConfig().getInt("Rankup_Requirements.creeper.cow_skulls");
			int requiredZombieSkulls = plugin.getConfig().getInt("Rankup_Requirements.creeper.zombie_skulls");
			int requiredSkeletonSkulls = plugin.getConfig().getInt("Rankup_Requirements.creeper.skeleton_skulls");
			int requiredPigZombieSkulls = plugin.getConfig().getInt("Rankup_Requirements.creeper.pigzombie_skulls");
			int requiredSlimeSkulls = plugin.getConfig().getInt("Rankup_Requirements.creeper.slime_skulls");
			int requiredCreeperSkulls = plugin.getConfig().getInt("Rankup_Requirements.creeper.creeper_skulls");
			int requiredPandaSkulls = plugin.getConfig().getInt("Rankup_Requirements.creeper.panda_skulls");

			if (Main.econ.getBalance(p) >= requiredCash
					&& SkullManager.getChickenSkullAmount(p) >= requiredChickenSkulls
					&& SkullManager.getPigSkullAmount(p) >= requiredPigSkulls
					&& SkullManager.getSheepSkullAmount(p) >= requiredSheepSkulls
					&& SkullManager.getCowSkullAmount(p) >= requiredCowSkulls
					&& SkullManager.getZombieSkullAmount(p) >= requiredZombieSkulls
					&& SkullManager.getSkeletonSkullAmount(p) >= requiredSkeletonSkulls
					&& SkullManager.getPigZombieSkullAmount(p) >= requiredPigZombieSkulls
					&& SkullManager.getSlimeSkullAmount(p) >= requiredSlimeSkulls
					&& SkullManager.getCreeperSkullAmount(p) >= requiredCreeperSkulls
					&& SkullManager.getPandaSkullAmount(p) >= requiredPandaSkulls) {
				return true;
			}
		} else if (getRank(p) == 9 /* Rankup to Panda Rank */) {
			int requiredCash = plugin.getConfig().getInt("Rankup_Requirements.panda.cash");
			int requiredChickenSkulls = plugin.getConfig().getInt("Rankup_Requirements.panda.chicken_skulls");
			int requiredPigSkulls = plugin.getConfig().getInt("Rankup_Requirements.panda.pig_skulls");
			int requiredSheepSkulls = plugin.getConfig().getInt("Rankup_Requirements.panda.sheep_skulls");
			int requiredCowSkulls = plugin.getConfig().getInt("Rankup_Requirements.panda.cow_skulls");
			int requiredZombieSkulls = plugin.getConfig().getInt("Rankup_Requirements.panda.zombie_skulls");
			int requiredSkeletonSkulls = plugin.getConfig().getInt("Rankup_Requirements.panda.skeleton_skulls");
			int requiredPigZombieSkulls = plugin.getConfig().getInt("Rankup_Requirements.panda.pigzombie_skulls");
			int requiredSlimeSkulls = plugin.getConfig().getInt("Rankup_Requirements.panda.slime_skulls");
			int requiredCreeperSkulls = plugin.getConfig().getInt("Rankup_Requirements.panda.creeper_skulls");
			int requiredPandaSkulls = plugin.getConfig().getInt("Rankup_Requirements.panda.panda_skulls");

			if (Main.econ.getBalance(p) >= requiredCash
					&& SkullManager.getChickenSkullAmount(p) >= requiredChickenSkulls
					&& SkullManager.getPigSkullAmount(p) >= requiredPigSkulls
					&& SkullManager.getSheepSkullAmount(p) >= requiredSheepSkulls
					&& SkullManager.getCowSkullAmount(p) >= requiredCowSkulls
					&& SkullManager.getZombieSkullAmount(p) >= requiredZombieSkulls
					&& SkullManager.getSkeletonSkullAmount(p) >= requiredSkeletonSkulls
					&& SkullManager.getPigZombieSkullAmount(p) >= requiredPigZombieSkulls
					&& SkullManager.getSlimeSkullAmount(p) >= requiredSlimeSkulls
					&& SkullManager.getCreeperSkullAmount(p) >= requiredCreeperSkulls
					&& SkullManager.getPandaSkullAmount(p) >= requiredPandaSkulls) {
				return true;
			}
		}
		return false;
	}

	public static double getRequiredCash(Player p) {
		if (getRank(p) == 0 /* Chicken Rank */) {
			int requiredCash = plugin.getConfig().getInt("Rankup_Requirements.chicken.cash");
			return requiredCash;
		} else if (getRank(p) == 1 /* Pig Rank */) {
			int requiredCash = plugin.getConfig().getInt("Rankup_Requirements.pig.cash");
			return requiredCash;
		} else if (getRank(p) == 2 /* Sheep Rank */) {
			int requiredCash = plugin.getConfig().getInt("Rankup_Requirements.sheep.cash");
			return requiredCash;
		} else if (getRank(p) == 3 /* Cow Rank */) {
			int requiredCash = plugin.getConfig().getInt("Rankup_Requirements.cow.cash");
			return requiredCash;
		} else if (getRank(p) == 4 /* Zombie Rank */) {
			int requiredCash = plugin.getConfig().getInt("Rankup_Requirements.zombie.cash");
			return requiredCash;
		} else if (getRank(p) == 5 /* Skeleton Rank */) {
			int requiredCash = plugin.getConfig().getInt("Rankup_Requirements.skeleton.cash");
			return requiredCash;
		} else if (getRank(p) == 6 /* Zombie Pigman Rank */) {
			int requiredCash = plugin.getConfig().getInt("Rankup_Requirements.pigzombie.cash");
			return requiredCash;
		} else if (getRank(p) == 7 /* Slime Rank */) {
			int requiredCash = plugin.getConfig().getInt("Rankup_Requirements.slime.cash");
			return requiredCash;
		} else if (getRank(p) == 8 /* Creeper Rank */) {
			int requiredCash = plugin.getConfig().getInt("Rankup_Requirements.creeper.cash");
			return requiredCash;
		} else if (getRank(p) == 9 /* Panda Rank */) {
			int requiredCash = plugin.getConfig().getInt("Rankup_Requirements.panda.cash");
			return requiredCash;
		} else if (getRank(p) == 10 /* Cow Rank */) {
			return 0;
		} else {
			int requiredCash = plugin.getConfig().getInt("Rankup_Requirements.chicken.cash");
			return requiredCash;
		}
	}

	public static int getRequiredChickenSkulls(Player p) {
		if (getRank(p) == 0 /* Chicken Rank */) {
			int requiredChickenSkulls = plugin.getConfig().getInt("Rankup_Requirements.chicken.chicken_skulls");
			return requiredChickenSkulls;
		} else if (getRank(p) == 1 /* Pig Rank */) {
			int requiredChickenSkulls = plugin.getConfig().getInt("Rankup_Requirements.pig.chicken_skulls");
			return requiredChickenSkulls;
		} else if (getRank(p) == 2 /* Sheep Rank */) {
			int requiredChickenSkulls = plugin.getConfig().getInt("Rankup_Requirements.sheep.chicken_skulls");
			return requiredChickenSkulls;
		} else if (getRank(p) == 3 /* Cow Rank */) {
			int requiredChickenSkulls = plugin.getConfig().getInt("Rankup_Requirements.cow.chicken_skulls");
			return requiredChickenSkulls;
		} else if (getRank(p) == 4 /* Zombie Rank */) {
			int requiredChickenSkulls = plugin.getConfig().getInt("Rankup_Requirements.zombie.chicken_skulls");
			return requiredChickenSkulls;
		} else if (getRank(p) == 5 /* Skeleton Rank */) {
			int requiredChickenSkulls = plugin.getConfig().getInt("Rankup_Requirements.skeleton.chicken_skulls");
			return requiredChickenSkulls;
		} else if (getRank(p) == 6 /* Zombie Pigman Rank */) {
			int requiredChickenSkulls = plugin.getConfig().getInt("Rankup_Requirements.pigzombie.chicken_skulls");
			return requiredChickenSkulls;
		} else if (getRank(p) == 7 /* Slime Rank */) {
			int requiredChickenSkulls = plugin.getConfig().getInt("Rankup_Requirements.slime.chicken_skulls");
			return requiredChickenSkulls;
		} else if (getRank(p) == 8 /* Creeper Rank */) {
			int requiredChickenSkulls = plugin.getConfig().getInt("Rankup_Requirements.creeper.chicken_skulls");
			return requiredChickenSkulls;
		} else if (getRank(p) == 9 /* Panda Rank */) {
			int requiredChickenSkulls = plugin.getConfig().getInt("Rankup_Requirements.panda.chicken_skulls");
			return requiredChickenSkulls;
		} else if (getRank(p) == 10 /* Cow Rank */) {
			return 0;
		} else {
			int requiredChickenSkulls = plugin.getConfig().getInt("Rankup_Requirements.chicken.chicken_skulls");
			return requiredChickenSkulls;
		}
	}

	public static int getRequiredPigSkulls(Player p) {
		if (getRank(p) == 0 /* Chicken Rank */) {
			int requiredPigSkulls = plugin.getConfig().getInt("Rankup_Requirements.chicken.pig_skulls");
			return requiredPigSkulls;
		} else if (getRank(p) == 1 /* Pig Rank */) {
			int requiredPigSkulls = plugin.getConfig().getInt("Rankup_Requirements.pig.pig_skulls");
			return requiredPigSkulls;
		} else if (getRank(p) == 2 /* Sheep Rank */) {
			int requiredPigSkulls = plugin.getConfig().getInt("Rankup_Requirements.sheep.pig_skulls");
			return requiredPigSkulls;
		} else if (getRank(p) == 3 /* Cow Rank */) {
			int requiredPigSkulls = plugin.getConfig().getInt("Rankup_Requirements.cow.pig_skulls");
			return requiredPigSkulls;
		} else if (getRank(p) == 4 /* Zombie Rank */) {
			int requiredPigSkulls = plugin.getConfig().getInt("Rankup_Requirements.zombie.pig_skulls");
			return requiredPigSkulls;
		} else if (getRank(p) == 5 /* Skeleton Rank */) {
			int requiredPigSkulls = plugin.getConfig().getInt("Rankup_Requirements.skeleton.pig_skulls");
			return requiredPigSkulls;
		} else if (getRank(p) == 6 /* Zombie Pigman Rank */) {
			int requiredPigSkulls = plugin.getConfig().getInt("Rankup_Requirements.pigzombie.pig_skulls");
			return requiredPigSkulls;
		} else if (getRank(p) == 7 /* Slime Rank */) {
			int requiredPigSkulls = plugin.getConfig().getInt("Rankup_Requirements.slime.pig_skulls");
			return requiredPigSkulls;
		} else if (getRank(p) == 8 /* Creeper Rank */) {
			int requiredPigSkulls = plugin.getConfig().getInt("Rankup_Requirements.creeper.pig_skulls");
			return requiredPigSkulls;
		} else if (getRank(p) == 9 /* Panda Rank */) {
			int requiredPigSkulls = plugin.getConfig().getInt("Rankup_Requirements.panda.pig_skulls");
			return requiredPigSkulls;
		} else if (getRank(p) == 10 /* Cow Rank */) {
			return 0;
		} else {
			int requiredPigSkulls = plugin.getConfig().getInt("Rankup_Requirements.chicken.pig_skulls");
			return requiredPigSkulls;
		}
	}

	public static int getRequiredSheepSkulls(Player p) {
		if (getRank(p) == 0 /* Chicken Rank */) {
			int requiredSheepSkulls = plugin.getConfig().getInt("Rankup_Requirements.chicken.sheep_skulls");
			return requiredSheepSkulls;
		} else if (getRank(p) == 1 /* Sheep Rank */) {
			int requiredSheepSkulls = plugin.getConfig().getInt("Rankup_Requirements.pig.sheep_skulls");
			return requiredSheepSkulls;
		} else if (getRank(p) == 2 /* Sheep Rank */) {
			int requiredSheepSkulls = plugin.getConfig().getInt("Rankup_Requirements.sheep.sheep_skulls");
			return requiredSheepSkulls;
		} else if (getRank(p) == 3 /* Cow Rank */) {
			int requiredSheepSkulls = plugin.getConfig().getInt("Rankup_Requirements.cow.sheep_skulls");
			return requiredSheepSkulls;
		} else if (getRank(p) == 4 /* Zombie Rank */) {
			int requiredSheepSkulls = plugin.getConfig().getInt("Rankup_Requirements.zombie.sheep_skulls");
			return requiredSheepSkulls;
		} else if (getRank(p) == 5 /* Skeleton Rank */) {
			int requiredSheepSkulls = plugin.getConfig().getInt("Rankup_Requirements.skeleton.sheep_skulls");
			return requiredSheepSkulls;
		} else if (getRank(p) == 6 /* Zombie Sheepman Rank */) {
			int requiredSheepSkulls = plugin.getConfig().getInt("Rankup_Requirements.pigzombie.sheep_skulls");
			return requiredSheepSkulls;
		} else if (getRank(p) == 7 /* Slime Rank */) {
			int requiredSheepSkulls = plugin.getConfig().getInt("Rankup_Requirements.slime.sheep_skulls");
			return requiredSheepSkulls;
		} else if (getRank(p) == 8 /* Creeper Rank */) {
			int requiredSheepSkulls = plugin.getConfig().getInt("Rankup_Requirements.creeper.sheep_skulls");
			return requiredSheepSkulls;
		} else if (getRank(p) == 9 /* Panda Rank */) {
			int requiredSheepSkulls = plugin.getConfig().getInt("Rankup_Requirements.panda.sheep_skulls");
			return requiredSheepSkulls;
		} else if (getRank(p) == 10 /* Cow Rank */) {
			return 0;
		} else {
			int requiredSheepSkulls = plugin.getConfig().getInt("Rankup_Requirements.chicken.sheep_skulls");
			return requiredSheepSkulls;
		}
	}

	public static int getRequiredCowSkulls(Player p) {
		if (getRank(p) == 0 /* Chicken Rank */) {
			int requiredCowSkulls = plugin.getConfig().getInt("Rankup_Requirements.chicken.cow_skulls");
			return requiredCowSkulls;
		} else if (getRank(p) == 1 /* Sheep Rank */) {
			int requiredCowSkulls = plugin.getConfig().getInt("Rankup_Requirements.pig.cow_skulls");
			return requiredCowSkulls;
		} else if (getRank(p) == 2 /* Sheep Rank */) {
			int requiredCowSkulls = plugin.getConfig().getInt("Rankup_Requirements.sheep.cow_skulls");
			return requiredCowSkulls;
		} else if (getRank(p) == 3 /* Cow Rank */) {
			int requiredCowSkulls = plugin.getConfig().getInt("Rankup_Requirements.cow.cow_skulls");
			return requiredCowSkulls;
		} else if (getRank(p) == 4 /* Zombie Rank */) {
			int requiredCowSkulls = plugin.getConfig().getInt("Rankup_Requirements.zombie.cow_skulls");
			return requiredCowSkulls;
		} else if (getRank(p) == 5 /* Skeleton Rank */) {
			int requiredCowSkulls = plugin.getConfig().getInt("Rankup_Requirements.skeleton.cow_skulls");
			return requiredCowSkulls;
		} else if (getRank(p) == 6 /* Zombie Sheepman Rank */) {
			int requiredCowSkulls = plugin.getConfig().getInt("Rankup_Requirements.pigzombie.cow_skulls");
			return requiredCowSkulls;
		} else if (getRank(p) == 7 /* Slime Rank */) {
			int requiredCowSkulls = plugin.getConfig().getInt("Rankup_Requirements.slime.cow_skulls");
			return requiredCowSkulls;
		} else if (getRank(p) == 8 /* Creeper Rank */) {
			int requiredCowSkulls = plugin.getConfig().getInt("Rankup_Requirements.creeper.cow_skulls");
			return requiredCowSkulls;
		} else if (getRank(p) == 9 /* Panda Rank */) {
			int requiredCowSkulls = plugin.getConfig().getInt("Rankup_Requirements.panda.cow_skulls");
			return requiredCowSkulls;
		} else if (getRank(p) == 10 /* Cow Rank */) {
			return 0;
		} else {
			int requiredCowSkulls = plugin.getConfig().getInt("Rankup_Requirements.chicken.cow_skulls");
			return requiredCowSkulls;
		}
	}

	public static int getRequiredZombieSkulls(Player p) {
		if (getRank(p) == 0 /* Chicken Rank */) {
			int requiredZombieSkulls = plugin.getConfig().getInt("Rankup_Requirements.chicken.zombie_skulls");
			return requiredZombieSkulls;
		} else if (getRank(p) == 1 /* Sheep Rank */) {
			int requiredZombieSkulls = plugin.getConfig().getInt("Rankup_Requirements.pig.zombie_skulls");
			return requiredZombieSkulls;
		} else if (getRank(p) == 2 /* Sheep Rank */) {
			int requiredZombieSkulls = plugin.getConfig().getInt("Rankup_Requirements.sheep.zombie_skulls");
			return requiredZombieSkulls;
		} else if (getRank(p) == 3 /* Cow Rank */) {
			int requiredZombieSkulls = plugin.getConfig().getInt("Rankup_Requirements.cow.zombie_skulls");
			return requiredZombieSkulls;
		} else if (getRank(p) == 4 /* Zombie Rank */) {
			int requiredZombieSkulls = plugin.getConfig().getInt("Rankup_Requirements.zombie.zombie_skulls");
			return requiredZombieSkulls;
		} else if (getRank(p) == 5 /* Skeleton Rank */) {
			int requiredZombieSkulls = plugin.getConfig().getInt("Rankup_Requirements.skeleton.zombie_skulls");
			return requiredZombieSkulls;
		} else if (getRank(p) == 6 /* Zombie Sheepman Rank */) {
			int requiredZombieSkulls = plugin.getConfig().getInt("Rankup_Requirements.pigzombie.zombie_skulls");
			return requiredZombieSkulls;
		} else if (getRank(p) == 7 /* Slime Rank */) {
			int requiredZombieSkulls = plugin.getConfig().getInt("Rankup_Requirements.slime.zombie_skulls");
			return requiredZombieSkulls;
		} else if (getRank(p) == 8 /* Creeper Rank */) {
			int requiredZombieSkulls = plugin.getConfig().getInt("Rankup_Requirements.creeper.zombie_skulls");
			return requiredZombieSkulls;
		} else if (getRank(p) == 9 /* Panda Rank */) {
			int requiredZombieSkulls = plugin.getConfig().getInt("Rankup_Requirements.panda.zombie_skulls");
			return requiredZombieSkulls;
		} else if (getRank(p) == 10 /* Cow Rank */) {
			return 0;
		} else {
			int requiredZombieSkulls = plugin.getConfig().getInt("Rankup_Requirements.chicken.zombie_skulls");
			return requiredZombieSkulls;
		}
	}

	public static int getRequiredSkeletonSkulls(Player p) {
		if (getRank(p) == 0 /* Chicken Rank */) {
			int requiredSkeletonSkulls = plugin.getConfig().getInt("Rankup_Requirements.chicken.skeleton_skulls");
			return requiredSkeletonSkulls;
		} else if (getRank(p) == 1 /* Sheep Rank */) {
			int requiredSkeletonSkulls = plugin.getConfig().getInt("Rankup_Requirements.pig.skeleton_skulls");
			return requiredSkeletonSkulls;
		} else if (getRank(p) == 2 /* Sheep Rank */) {
			int requiredSkeletonSkulls = plugin.getConfig().getInt("Rankup_Requirements.sheep.skeleton_skulls");
			return requiredSkeletonSkulls;
		} else if (getRank(p) == 3 /* Cow Rank */) {
			int requiredSkeletonSkulls = plugin.getConfig().getInt("Rankup_Requirements.cow.skeleton_skulls");
			return requiredSkeletonSkulls;
		} else if (getRank(p) == 4 /* Zombie Rank */) {
			int requiredSkeletonSkulls = plugin.getConfig().getInt("Rankup_Requirements.zombie.skeleton_skulls");
			return requiredSkeletonSkulls;
		} else if (getRank(p) == 5 /* Skeleton Rank */) {
			int requiredSkeletonSkulls = plugin.getConfig().getInt("Rankup_Requirements.skeleton.skeleton_skulls");
			return requiredSkeletonSkulls;
		} else if (getRank(p) == 6 /* Zombie Sheepman Rank */) {
			int requiredSkeletonSkulls = plugin.getConfig().getInt("Rankup_Requirements.pigzombie.skeleton_skulls");
			return requiredSkeletonSkulls;
		} else if (getRank(p) == 7 /* Slime Rank */) {
			int requiredSkeletonSkulls = plugin.getConfig().getInt("Rankup_Requirements.slime.skeleton_skulls");
			return requiredSkeletonSkulls;
		} else if (getRank(p) == 8 /* Creeper Rank */) {
			int requiredSkeletonSkulls = plugin.getConfig().getInt("Rankup_Requirements.creeper.skeleton_skulls");
			return requiredSkeletonSkulls;
		} else if (getRank(p) == 9 /* Panda Rank */) {
			int requiredSkeletonSkulls = plugin.getConfig().getInt("Rankup_Requirements.panda.skeleton_skulls");
			return requiredSkeletonSkulls;
		} else if (getRank(p) == 10 /* Cow Rank */) {
			return 0;
		} else {
			int requiredSkeletonSkulls = plugin.getConfig().getInt("Rankup_Requirements.chicken.skeleton_skulls");
			return requiredSkeletonSkulls;
		}
	}

	public static int getRequiredPigZombieSkulls(Player p) {
		if (getRank(p) == 0 /* Chicken Rank */) {
			int requiredPigZombieSkulls = plugin.getConfig().getInt("Rankup_Requirements.chicken.pigzombie_skulls");
			return requiredPigZombieSkulls;
		} else if (getRank(p) == 1 /* Sheep Rank */) {
			int requiredPigZombieSkulls = plugin.getConfig().getInt("Rankup_Requirements.pig.pigzombie_skulls");
			return requiredPigZombieSkulls;
		} else if (getRank(p) == 2 /* Sheep Rank */) {
			int requiredPigZombieSkulls = plugin.getConfig().getInt("Rankup_Requirements.sheep.pigzombie_skulls");
			return requiredPigZombieSkulls;
		} else if (getRank(p) == 3 /* Cow Rank */) {
			int requiredPigZombieSkulls = plugin.getConfig().getInt("Rankup_Requirements.cow.pigzombie_skulls");
			return requiredPigZombieSkulls;
		} else if (getRank(p) == 4 /* Zombie Rank */) {
			int requiredPigZombieSkulls = plugin.getConfig().getInt("Rankup_Requirements.zombie.pigzombie_skulls");
			return requiredPigZombieSkulls;
		} else if (getRank(p) == 5 /* Skeleton Rank */) {
			int requiredPigZombieSkulls = plugin.getConfig().getInt("Rankup_Requirements.skeleton.pigzombie_skulls");
			return requiredPigZombieSkulls;
		} else if (getRank(p) == 6 /* Zombie Sheepman Rank */) {
			int requiredPigZombieSkulls = plugin.getConfig().getInt("Rankup_Requirements.pigzombie.pigzombie_skulls");
			return requiredPigZombieSkulls;
		} else if (getRank(p) == 7 /* Slime Rank */) {
			int requiredPigZombieSkulls = plugin.getConfig().getInt("Rankup_Requirements.slime.pigzombie_skulls");
			return requiredPigZombieSkulls;
		} else if (getRank(p) == 8 /* Creeper Rank */) {
			int requiredPigZombieSkulls = plugin.getConfig().getInt("Rankup_Requirements.creeper.pigzombie_skulls");
			return requiredPigZombieSkulls;
		} else if (getRank(p) == 9 /* Panda Rank */) {
			int requiredPigZombieSkulls = plugin.getConfig().getInt("Rankup_Requirements.panda.pigzombie_skulls");
			return requiredPigZombieSkulls;
		} else if (getRank(p) == 10 /* Cow Rank */) {
			return 0;
		} else {
			int requiredPigZombieSkulls = plugin.getConfig().getInt("Rankup_Requirements.chicken.pigzombie_skulls");
			return requiredPigZombieSkulls;
		}
	}

	public static int getRequiredSlimeSkulls(Player p) {
		if (getRank(p) == 0 /* Chicken Rank */) {
			int requiredSlimeSkulls = plugin.getConfig().getInt("Rankup_Requirements.chicken.slime_skulls");
			return requiredSlimeSkulls;
		} else if (getRank(p) == 1 /* Sheep Rank */) {
			int requiredSlimeSkulls = plugin.getConfig().getInt("Rankup_Requirements.pig.slime_skulls");
			return requiredSlimeSkulls;
		} else if (getRank(p) == 2 /* Sheep Rank */) {
			int requiredSlimeSkulls = plugin.getConfig().getInt("Rankup_Requirements.sheep.slime_skulls");
			return requiredSlimeSkulls;
		} else if (getRank(p) == 3 /* Cow Rank */) {
			int requiredSlimeSkulls = plugin.getConfig().getInt("Rankup_Requirements.cow.slime_skulls");
			return requiredSlimeSkulls;
		} else if (getRank(p) == 4 /* Zombie Rank */) {
			int requiredSlimeSkulls = plugin.getConfig().getInt("Rankup_Requirements.zombie.slime_skulls");
			return requiredSlimeSkulls;
		} else if (getRank(p) == 5 /* Skeleton Rank */) {
			int requiredSlimeSkulls = plugin.getConfig().getInt("Rankup_Requirements.skeleton.slime_skulls");
			return requiredSlimeSkulls;
		} else if (getRank(p) == 6 /* Zombie Sheepman Rank */) {
			int requiredSlimeSkulls = plugin.getConfig().getInt("Rankup_Requirements.pigzombie.slime_skulls");
			return requiredSlimeSkulls;
		} else if (getRank(p) == 7 /* Slime Rank */) {
			int requiredSlimeSkulls = plugin.getConfig().getInt("Rankup_Requirements.slime.slime_skulls");
			return requiredSlimeSkulls;
		} else if (getRank(p) == 8 /* Creeper Rank */) {
			int requiredSlimeSkulls = plugin.getConfig().getInt("Rankup_Requirements.creeper.slime_skulls");
			return requiredSlimeSkulls;
		} else if (getRank(p) == 9 /* Panda Rank */) {
			int requiredSlimeSkulls = plugin.getConfig().getInt("Rankup_Requirements.panda.slime_skulls");
			return requiredSlimeSkulls;
		} else if (getRank(p) == 10 /* Cow Rank */) {
			return 0;
		} else {
			int requiredSlimeSkulls = plugin.getConfig().getInt("Rankup_Requirements.chicken.slime_skulls");
			return requiredSlimeSkulls;
		}
	}

	public static int getRequiredCreeperSkulls(Player p) {
		if (getRank(p) == 0 /* Chicken Rank */) {
			int requiredCreeperSkulls = plugin.getConfig().getInt("Rankup_Requirements.chicken.creeper_skulls");
			return requiredCreeperSkulls;
		} else if (getRank(p) == 1 /* Sheep Rank */) {
			int requiredCreeperSkulls = plugin.getConfig().getInt("Rankup_Requirements.pig.creeper_skulls");
			return requiredCreeperSkulls;
		} else if (getRank(p) == 2 /* Sheep Rank */) {
			int requiredCreeperSkulls = plugin.getConfig().getInt("Rankup_Requirements.sheep.creeper_skulls");
			return requiredCreeperSkulls;
		} else if (getRank(p) == 3 /* Cow Rank */) {
			int requiredCreeperSkulls = plugin.getConfig().getInt("Rankup_Requirements.cow.creeper_skulls");
			return requiredCreeperSkulls;
		} else if (getRank(p) == 4 /* Zombie Rank */) {
			int requiredCreeperSkulls = plugin.getConfig().getInt("Rankup_Requirements.zombie.creeper_skulls");
			return requiredCreeperSkulls;
		} else if (getRank(p) == 5 /* Skeleton Rank */) {
			int requiredCreeperSkulls = plugin.getConfig().getInt("Rankup_Requirements.skeleton.creeper_skulls");
			return requiredCreeperSkulls;
		} else if (getRank(p) == 6 /* Zombie Sheepman Rank */) {
			int requiredCreeperSkulls = plugin.getConfig().getInt("Rankup_Requirements.pigzombie.creeper_skulls");
			return requiredCreeperSkulls;
		} else if (getRank(p) == 7 /* Slime Rank */) {
			int requiredCreeperSkulls = plugin.getConfig().getInt("Rankup_Requirements.slime.creeper_skulls");
			return requiredCreeperSkulls;
		} else if (getRank(p) == 8 /* Creeper Rank */) {
			int requiredCreeperSkulls = plugin.getConfig().getInt("Rankup_Requirements.creeper.creeper_skulls");
			return requiredCreeperSkulls;
		} else if (getRank(p) == 9 /* Panda Rank */) {
			int requiredCreeperSkulls = plugin.getConfig().getInt("Rankup_Requirements.panda.creeper_skulls");
			return requiredCreeperSkulls;
		} else if (getRank(p) == 10 /* Cow Rank */) {
			return 0;
		} else {
			int requiredCreeperSkulls = plugin.getConfig().getInt("Rankup_Requirements.chicken.creeper_skulls");
			return requiredCreeperSkulls;
		}
	}

	public static int getRequiredPandaSkulls(Player p) {
		if (getRank(p) == 0 /* Chicken Rank */) {
			int requiredPandaSkulls = plugin.getConfig().getInt("Rankup_Requirements.chicken.panda_skulls");
			return requiredPandaSkulls;
		} else if (getRank(p) == 1 /* Sheep Rank */) {
			int requiredPandaSkulls = plugin.getConfig().getInt("Rankup_Requirements.pig.panda_skulls");
			return requiredPandaSkulls;
		} else if (getRank(p) == 2 /* Sheep Rank */) {
			int requiredPandaSkulls = plugin.getConfig().getInt("Rankup_Requirements.sheep.panda_skulls");
			return requiredPandaSkulls;
		} else if (getRank(p) == 3 /* Cow Rank */) {
			int requiredPandaSkulls = plugin.getConfig().getInt("Rankup_Requirements.cow.panda_skulls");
			return requiredPandaSkulls;
		} else if (getRank(p) == 4 /* Zombie Rank */) {
			int requiredPandaSkulls = plugin.getConfig().getInt("Rankup_Requirements.zombie.panda_skulls");
			return requiredPandaSkulls;
		} else if (getRank(p) == 5 /* Skeleton Rank */) {
			int requiredPandaSkulls = plugin.getConfig().getInt("Rankup_Requirements.skeleton.panda_skulls");
			return requiredPandaSkulls;
		} else if (getRank(p) == 6 /* Zombie Sheepman Rank */) {
			int requiredPandaSkulls = plugin.getConfig().getInt("Rankup_Requirements.pigzombie.panda_skulls");
			return requiredPandaSkulls;
		} else if (getRank(p) == 7 /* Slime Rank */) {
			int requiredPandaSkulls = plugin.getConfig().getInt("Rankup_Requirements.slime.panda_skulls");
			return requiredPandaSkulls;
		} else if (getRank(p) == 8 /* Creeper Rank */) {
			int requiredPandaSkulls = plugin.getConfig().getInt("Rankup_Requirements.creeper.panda_skulls");
			return requiredPandaSkulls;
		} else if (getRank(p) == 9 /* Panda Rank */) {
			int requiredPandaSkulls = plugin.getConfig().getInt("Rankup_Requirements.panda.panda_skulls");
			return requiredPandaSkulls;
		} else if (getRank(p) == 10 /* Cow Rank */) {
			return 0;
		} else {
			int requiredPandaSkulls = plugin.getConfig().getInt("Rankup_Requirements.chicken.panda_skulls");
			return requiredPandaSkulls;
		}
	}

	/*
	 * Remaining integers Skulls
	 */

	public static double getRemainingCash(Player p) {
		if (Main.econ.getBalance(p) > getRequiredCash(p)) {
			return 0;
		} else {
			return (getRequiredCash(p) - Main.econ.getBalance(p));
		}
	}

	public static int getRemainingChickenSkulls(Player p) {
		if (SkullManager.getChickenSkullAmount(p) > getRequiredChickenSkulls(p)) {
			return 0;
		} else {
			return getRequiredChickenSkulls(p) - SkullManager.getChickenSkullAmount(p);
		}
	}

	public static int getRemainingPigSkulls(Player p) {
		if (SkullManager.getPigSkullAmount(p) > getRequiredPigSkulls(p)) {
			return 0;
		} else {
			return getRequiredPigSkulls(p) - SkullManager.getPigSkullAmount(p);
		}
	}

	public static int getRemainingSheepSkulls(Player p) {
		if (SkullManager.getSheepSkullAmount(p) > getRequiredSheepSkulls(p)) {
			return 0;
		} else {
			return getRequiredSheepSkulls(p) - SkullManager.getSheepSkullAmount(p);
		}
	}

	public static int getRemainingCowSkulls(Player p) {
		if (SkullManager.getCowSkullAmount(p) > getRequiredCowSkulls(p)) {
			return 0;
		} else {
			return getRequiredCowSkulls(p) - SkullManager.getCowSkullAmount(p);
		}
	}

	public static int getRemainingZombieSkulls(Player p) {
		if (SkullManager.getZombieSkullAmount(p) > getRequiredZombieSkulls(p)) {
			return 0;
		} else {
			return getRequiredZombieSkulls(p) - SkullManager.getZombieSkullAmount(p);
		}
	}

	public static int getRemainingSkeletonSkulls(Player p) {
		if (SkullManager.getSkeletonSkullAmount(p) > getRequiredSkeletonSkulls(p)) {
			return 0;
		} else {
			return getRequiredSkeletonSkulls(p) - SkullManager.getSkeletonSkullAmount(p);
		}
	}

	public static int getRemainingPigZombieSkulls(Player p) {
		if (SkullManager.getPigZombieSkullAmount(p) > getRequiredPigZombieSkulls(p)) {
			return 0;
		} else {
			return getRequiredPigZombieSkulls(p) - SkullManager.getPigZombieSkullAmount(p);
		}
	}

	public static int getRemainingSlimeSkulls(Player p) {
		if (SkullManager.getSlimeSkullAmount(p) > getRequiredSlimeSkulls(p)) {
			return 0;
		} else {
			return getRequiredSlimeSkulls(p) - SkullManager.getSlimeSkullAmount(p);
		}
	}

	public static int getRemainingCreeperSkulls(Player p) {
		if (SkullManager.getCreeperSkullAmount(p) > getRequiredCreeperSkulls(p)) {
			return 0;
		} else {
			return getRequiredCreeperSkulls(p) - SkullManager.getCreeperSkullAmount(p);
		}
	}

	public static int getRemainingPandaSkulls(Player p) {
		if (SkullManager.getPandaSkullAmount(p) > getRequiredPandaSkulls(p)) {
			return 0;
		} else {
			return getRequiredPandaSkulls(p) - SkullManager.getPandaSkullAmount(p);
		}
	}

	public static void launchFirework(Player p, int velocity, String colour) {
		double x = p.getLocation().getX();
		double y = p.getLocation().getY();
		double z = p.getLocation().getZ();
		World world = p.getLocation().getWorld();

		Location loc = new Location(world, x, y + 5, z);
		Firework fw = (Firework) loc.getWorld().spawnEntity(loc, EntityType.FIREWORK);
		FireworkMeta fwm = fw.getFireworkMeta();

		fwm.setPower(velocity);

		if (colour.equalsIgnoreCase("white")) {
			fwm.addEffect(FireworkEffect.builder().withColor(Color.WHITE).flicker(true).build());
		} else if (colour.equalsIgnoreCase("pink")) {
			fwm.addEffect(FireworkEffect.builder().withColor(Color.FUCHSIA).flicker(true).build());
		} else if (colour.equalsIgnoreCase("gray")) {
			fwm.addEffect(FireworkEffect.builder().withColor(Color.SILVER).flicker(true).build());
		} else if (colour.equalsIgnoreCase("darkgray")) {
			fwm.addEffect(FireworkEffect.builder().withColor(Color.GRAY).flicker(true).build());
		} else if (colour.equalsIgnoreCase("darkgreen")) {
			fwm.addEffect(FireworkEffect.builder().withColor(Color.GREEN).flicker(true).build());
		} else if (colour.equalsIgnoreCase("lime")) {
			fwm.addEffect(FireworkEffect.builder().withColor(Color.LIME).flicker(true).build());
		} else if (colour.equalsIgnoreCase("red")) {
			fwm.addEffect(FireworkEffect.builder().withColor(Color.RED).flicker(true).build());
		} else if (colour.equalsIgnoreCase("aqua")) {
			fwm.addEffect(FireworkEffect.builder().withColor(Color.AQUA).flicker(true).build());
		} else if (colour.equalsIgnoreCase("gold")) {
			fwm.addEffect(FireworkEffect.builder().withColor(Color.ORANGE).flicker(true).build());
		}
		fw.setFireworkMeta(fwm);
		fw.detonate();

		for (int i = 0; i < 4; i++) {
			Firework fw2 = (Firework) loc.getWorld().spawnEntity(loc, EntityType.FIREWORK);
			fw2.setFireworkMeta(fwm);
		}
	}
}
