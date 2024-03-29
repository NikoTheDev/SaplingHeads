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
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import com.vervedev.saplingheads.Main;
import com.vervedev.saplingheads.utils.Utils;

public class SkullManager {

	public static Map<String, Integer> chicken = new HashMap<String, Integer>();
	public static Map<String, Integer> pig = new HashMap<String, Integer>();
	public static Map<String, Integer> sheep = new HashMap<String, Integer>();
	public static Map<String, Integer> cow = new HashMap<String, Integer>();
	public static Map<String, Integer> zombie = new HashMap<String, Integer>();
	public static Map<String, Integer> skeleton = new HashMap<String, Integer>();
	public static Map<String, Integer> zpigman = new HashMap<String, Integer>();
	public static Map<String, Integer> slime = new HashMap<String, Integer>();
	public static Map<String, Integer> creeper = new HashMap<String, Integer>();
	public static Map<String, Integer> panda = new HashMap<String, Integer>();

	private static Main plugin;

	public SkullManager(Main plugin) {
		this.plugin = plugin;
	}

	/*
	 * Chicken Data
	 */

	public static void saveSkullFiles(OfflinePlayer p) throws FileNotFoundException, IOException {

		// Creates the output stream, specify the correct file
		File file = new File("SaplingData/skulls.dat");
		ObjectOutputStream output = new ObjectOutputStream(new GZIPOutputStream(new FileOutputStream(file)));

		if (chicken.get(p.getUniqueId().toString() + "chicken") != null) {
			chicken.put(p.getUniqueId().toString() + "chicken", chicken.get(p.getUniqueId().toString() + "chicken"));
		}
		
		if (pig.get(p.getUniqueId().toString() + "pig") != null) {
			pig.put(p.getUniqueId().toString() + "pig", pig.get(p.getUniqueId().toString() + "pig"));
		}
		
		if (sheep.get(p.getUniqueId().toString() + "sheep") != null) {
			sheep.put(p.getUniqueId().toString() + "sheep", sheep.get(p.getUniqueId().toString() + "sheep"));
		}
		
		if (cow.get(p.getUniqueId().toString() + "cow") != null) {
			cow.put(p.getUniqueId().toString() + "cow", cow.get(p.getUniqueId().toString() + "cow"));
		}
		
		if (zombie.get(p.getUniqueId().toString() + "sheep") != null) {
			zombie.put(p.getUniqueId().toString() + "sheep", zombie.get(p.getUniqueId().toString() + "sheep"));
		}

		if (skeleton.get(p.getUniqueId().toString() + "skeleton") != null) {
			skeleton.put(p.getUniqueId().toString() + "skeleton", skeleton.get(p.getUniqueId().toString() + "skeleton"));
		}
		
		if (zpigman.get(p.getUniqueId().toString() + "pigzombie") != null) {
			zpigman.put(p.getUniqueId().toString() + "pigzombie", zpigman.get(p.getUniqueId().toString() + "pigzombie"));
		}
		
		if (slime.get(p.getUniqueId().toString() + "slime") != null) {
			slime.put(p.getUniqueId().toString() + "slime", slime.get(p.getUniqueId().toString() + "slime"));
		}
		
		if (creeper.get(p.getUniqueId().toString() + "creeper") != null) {
			creeper.put(p.getUniqueId().toString() + "creeper", creeper.get(p.getUniqueId().toString() + "creeper"));
		}
		
		if (panda.get(p.getUniqueId().toString() + "panda") != null) {
			panda.put(p.getUniqueId().toString() + "panda", panda.get(p.getUniqueId().toString() + "panda"));
		}
		
		try {
			// Write the map to the output stream, then close
			output.writeObject(chicken);
			output.writeObject(pig);
			output.writeObject(sheep);
			output.writeObject(cow);
			output.writeObject(zombie);
			output.writeObject(skeleton);
			output.writeObject(zpigman);
			output.writeObject(slime);
			output.writeObject(creeper);
			output.writeObject(panda);
			output.flush();
			output.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public static void loadSkullFiles(OfflinePlayer p) throws FileNotFoundException, IOException, ClassNotFoundException {
		// Create the input stream
		File file = new File("SaplingData/skulls.dat");
		ObjectInputStream input = new ObjectInputStream(new GZIPInputStream(new FileInputStream(file)));
		// Reads the first object in
		Object readObject = input.readObject();
		input.close();

		if (!(readObject instanceof HashMap))
			throw new IOException("Data is not a hashmap");
		chicken = (HashMap<String, Integer>) readObject;
		// Prints out everything in the map.
		for (String key : chicken.keySet()) {
			chicken.put(key, chicken.get(key));
		}
		
		pig = (HashMap<String, Integer>) readObject;
		// Prints out everything in the map.
		for (String key : pig.keySet()) {
			pig.put(key, pig.get(key));
		}
		
		sheep = (HashMap<String, Integer>) readObject;
		// Prints out everything in the map.
		for (String key : sheep.keySet()) {
			sheep.put(key, sheep.get(key));
		}
		
		cow = (HashMap<String, Integer>) readObject;
		// Prints out everything in the map.
		for (String key : cow.keySet()) {
			cow.put(key, cow.get(key));
		}
		
		zombie = (HashMap<String, Integer>) readObject;
		// Prints out everything in the map.
		for (String key : zombie.keySet()) {
			zombie.put(key, zombie.get(key));
		}
		
		skeleton = (HashMap<String, Integer>) readObject;
		// Prints out everything in the map.
		for (String key : skeleton.keySet()) {
			skeleton.put(key, skeleton.get(key));
		}
		
		zpigman = (HashMap<String, Integer>) readObject;
		// Prints out everything in the map.
		for (String key : zpigman.keySet()) {
			zpigman.put(key, zpigman.get(key));
		}
		
		slime = (HashMap<String, Integer>) readObject;
		// Prints out everything in the map.
		for (String key : slime.keySet()) {
			slime.put(key, slime.get(key));
		}
		
		creeper = (HashMap<String, Integer>) readObject;
		// Prints out everything in the map.
		for (String key : creeper.keySet()) {
			creeper.put(key, creeper.get(key));
		}
		
		panda = (HashMap<String, Integer>) readObject;
		// Prints out everything in the map.
		for (String key : panda.keySet()) {
			panda.put(key, panda.get(key));
		}
	}

	public static int getChickenSkullAmount(OfflinePlayer p) {
		if (chicken.get(p.getUniqueId().toString() + "chicken") != null) {
			return chicken.get(p.getUniqueId().toString() + "chicken");
		} else {
			return 0;
		}
	}

	public static void addChickenSkull(OfflinePlayer p, int amount) {
		if (chicken.get(p.getUniqueId().toString() + "chicken") != null) {
			chicken.put(p.getUniqueId().toString() + "chicken", chicken.get(p.getUniqueId().toString() + "chicken") + amount);
		} else {
			chicken.put(p.getUniqueId().toString() + "chicken", amount);
		}
	}

	public static void removeChickenSkull(OfflinePlayer p, int amount) {
		if (chicken.get(p.getUniqueId().toString() + "chicken") != null) {
			chicken.put(p.getUniqueId().toString() + "chicken", chicken.get(p.getUniqueId().toString() + "chicken") - amount);
		} else {
			chicken.put(p.getUniqueId().toString() + "chicken", amount);
		}
	}

	/*
	 * Pig Data
	 */

	public static int getPigSkullAmount(OfflinePlayer p) {
		if (pig.get(p.getUniqueId().toString() + "pig") != null) {
			return pig.get(p.getUniqueId().toString() + "pig");
		} else {
			return 0;
		}
	}

	public static void addPigSkull(OfflinePlayer p, int amount) {
		if (pig.get(p.getUniqueId().toString() + "pig") != null) {
			pig.put(p.getUniqueId().toString() + "pig", pig.get(p.getUniqueId().toString() + "pig") + amount);
		} else {
			pig.put(p.getUniqueId().toString() + "pig", amount);
		}
	}

	public static void removePigSkull(OfflinePlayer p, int amount) {
		if (pig.get(p.getUniqueId().toString() + "pig") != null) {
			pig.put(p.getUniqueId().toString() + "pig", pig.get(p.getUniqueId().toString() + "pig") - amount);
		} else {
			pig.put(p.getUniqueId().toString() + "pig", amount);
		}
	}

	/*
	 * Sheep Data
	 */

	public static int getSheepSkullAmount(OfflinePlayer p) {
		if (sheep.get(p.getUniqueId().toString() + "sheep") != null) {
			return sheep.get(p.getUniqueId().toString() + "sheep");
		} else {
			return 0;
		}
	}

	public static void addSheepSkull(OfflinePlayer p, int amount) {
		if (sheep.get(p.getUniqueId().toString() + "sheep") != null) {
			sheep.put(p.getUniqueId().toString() + "sheep", sheep.get(p.getUniqueId().toString() + "sheep") + amount);
		} else {
			sheep.put(p.getUniqueId().toString() + "sheep", amount);
		}
	}

	public static void removeSheepSkull(OfflinePlayer p, int amount) {
		if (sheep.get(p.getUniqueId().toString() + "sheep") != null) {
			sheep.put(p.getUniqueId().toString() + "sheep", sheep.get(p.getUniqueId().toString() + "sheep") - amount);
		} else {
			sheep.put(p.getUniqueId().toString() + "sheep", amount);
		}
	}

	/*
	 * Cow Data
	 */

	public static int getCowSkullAmount(OfflinePlayer p) {
		if (cow.get(p.getUniqueId().toString() + "cow") != null) {
			return cow.get(p.getUniqueId().toString() + "cow");
		} else {
			return 0;
		}
	}

	public static void addCowSkull(OfflinePlayer p, int amount) {
		if (cow.get(p.getUniqueId().toString() + "cow") != null) {
			cow.put(p.getUniqueId().toString() + "cow", cow.get(p.getUniqueId().toString() + "cow") + amount);
		} else {
			cow.put(p.getUniqueId().toString() + "cow", amount);
		}
	}

	public static void removeCowSkull(OfflinePlayer p, int amount) {
		if (cow.get(p.getUniqueId().toString() + "cow") != null) {
			cow.put(p.getUniqueId().toString() + "cow", cow.get(p.getUniqueId().toString() + "cow") - amount);
		} else {
			cow.put(p.getUniqueId().toString() + "cow", amount);
		}
	}

	/*
	 * Zombie Data
	 */

	public static int getZombieSkullAmount(OfflinePlayer p) {
		if (zombie.get(p.getUniqueId().toString() + "zombie") != null) {
			return zombie.get(p.getUniqueId().toString() + "zombie");
		} else {
			return 0;
		}
	}

	public static void addZombieSkull(OfflinePlayer p, int amount) {
		if (zombie.get(p.getUniqueId().toString() + "zombie") != null) {
			zombie.put(p.getUniqueId().toString() + "zombie", zombie.get(p.getUniqueId().toString() + "zombie") + amount);
		} else {
			zombie.put(p.getUniqueId().toString() + "zombie", amount);
		}
	}

	public static void removeZombieSkull(OfflinePlayer p, int amount) {
		if (zombie.get(p.getUniqueId().toString() + "zombie") != null) {
			zombie.put(p.getUniqueId().toString() + "zombie", zombie.get(p.getUniqueId().toString() + "zombie") - amount);
		} else {
			zombie.put(p.getUniqueId().toString() + "zombie", amount);
		}
	}

	/*
	 * Skeleton Data
	 */

	public static int getSkeletonSkullAmount(OfflinePlayer p) {
		if (skeleton.get(p.getUniqueId().toString() + "skeleton") != null) {
			return skeleton.get(p.getUniqueId().toString() + "skeleton");
		} else {
			return 0;
		}
	}

	public static void addSkeletonSkull(OfflinePlayer p, int amount) {
		if (skeleton.get(p.getUniqueId().toString() + "skeleton") != null) {
			skeleton.put(p.getUniqueId().toString() + "skeleton", skeleton.get(p.getUniqueId().toString() + "skeleton") + amount);
		} else {
			skeleton.put(p.getUniqueId().toString() + "skeleton", amount);
		}
	}

	public static void removeSkeletonSkull(OfflinePlayer p, int amount) {
		if (skeleton.get(p.getUniqueId().toString() + "skeleton") != null) {
			skeleton.put(p.getUniqueId().toString() + "skeleton", skeleton.get(p.getUniqueId().toString() + "skeleton") - amount);
		} else {
			skeleton.put(p.getUniqueId().toString() + "skeleton", amount);
		}
	}

	/*
	 * Zombie Pigman Data
	 */

	public static int getPigZombieSkullAmount(OfflinePlayer p) {
		if (zpigman.get(p.getUniqueId().toString() + "pigzombie") != null) {
			return zpigman.get(p.getUniqueId().toString() + "pigzombie");
		} else {
			return 0;
		}
	}

	public static void addPigZombieSkull(OfflinePlayer p, int amount) {
		if (zpigman.get(p.getUniqueId().toString() + "pigzombie") != null) {
			zpigman.put(p.getUniqueId().toString() + "pigzombie", zpigman.get(p.getUniqueId().toString() + "pigzombie") + amount);
		} else {
			zpigman.put(p.getUniqueId().toString() + "pigzombie", amount);
		}
	}

	public static void removePigZombieSkull(OfflinePlayer p, int amount) {
		if (zpigman.get(p.getUniqueId().toString() + "pigzombie") != null) {
			zpigman.put(p.getUniqueId().toString() + "pigzombie", zpigman.get(p.getUniqueId().toString() + "pigzombie") - amount);
		} else {
			zpigman.put(p.getUniqueId().toString() + "pigzombie", amount);
		}
	}

	/*
	 * Slime Data
	 */

	public static int getSlimeSkullAmount(OfflinePlayer p) {
		if (slime.get(p.getUniqueId().toString() + "slime") != null) {
			return slime.get(p.getUniqueId().toString() + "slime");
		} else {
			return 0;
		}
	}

	public static void addSlimeSkull(OfflinePlayer p, int amount) {
		if (slime.get(p.getUniqueId().toString() + "slime") != null) {
			slime.put(p.getUniqueId().toString() + "slime", slime.get(p.getUniqueId().toString() + "slime") + amount);
		} else {
			slime.put(p.getUniqueId().toString() + "slime", amount);
		}
	}

	public static void removeSlimeSkull(OfflinePlayer p, int amount) {
		if (slime.get(p.getUniqueId().toString() + "slime") != null) {
			slime.put(p.getUniqueId().toString() + "slime", slime.get(p.getUniqueId().toString() + "slime") - amount);
		} else {
			slime.put(p.getUniqueId().toString() + "slime", amount);
		}
	}

	/*
	 * Creeper Data
	 */

	public static int getCreeperSkullAmount(OfflinePlayer p) {
		if (creeper.get(p.getUniqueId().toString() + "creeper") != null) {
			return creeper.get(p.getUniqueId().toString() + "creeper");
		} else {
			return 0;
		}
	}

	public static void addCreeperSkull(OfflinePlayer p, int amount) {
		if (creeper.get(p.getUniqueId().toString() + "creeper") != null) {
			creeper.put(p.getUniqueId().toString() + "creeper", creeper.get(p.getUniqueId().toString() + "creeper") + amount);
		} else {
			creeper.put(p.getUniqueId().toString() + "creeper", amount);
		}
	}

	public static void removeCreeperSkull(OfflinePlayer p, int amount) {
		if (creeper.get(p.getUniqueId().toString() + "creeper") != null) {
			creeper.put(p.getUniqueId().toString() + "creeper", creeper.get(p.getUniqueId().toString() + "creeper") - amount);
		} else {
			creeper.put(p.getUniqueId().toString() + "creeper", amount);
		}
	}

	/*
	 * Panda Data
	 */

	public static int getPandaSkullAmount(OfflinePlayer p) {
		if (panda.get(p.getUniqueId().toString() + "panda") != null) {
			return panda.get(p.getUniqueId().toString() + "panda");
		} else {
			return 0;
		}
	}

	public static void addPandaSkull(OfflinePlayer p, int amount) {
		if (panda.get(p.getUniqueId().toString() + "panda") != null) {
			panda.put(p.getUniqueId().toString() + "panda", panda.get(p.getUniqueId().toString() + "panda") + amount);
		} else {
			panda.put(p.getUniqueId().toString() + "panda", amount);
		}
	}

	public static void removePandaSkull(OfflinePlayer p, int amount) {
		if (panda.get(p.getUniqueId().toString() + "panda") != null) {
			panda.put(p.getUniqueId().toString() + "panda", panda.get(p.getUniqueId().toString() + "panda") - amount);
		} else {
			panda.put(p.getUniqueId().toString() + "panda", amount);
		}
	}

	public static ItemStack getPandaSkull() {

		List<String> lore2 = new ArrayList<String>();

		SkullMeta skull2 = (SkullMeta) Bukkit.getItemFactory().getItemMeta(Material.PLAYER_HEAD);
		ItemStack stack2 = new ItemStack(Material.PLAYER_HEAD, 1);
		skull2.setOwner("can");
		skull2.setDisplayName(Utils.chat("&f&lPan&7&lda &f&lSkull"));
		lore2.clear();
		lore2.add(Utils.chat("&7Do /rankup to open the main menu"));
		lore2.add(Utils.chat("&7click &evault skulls &7to vault"));
		lore2.add(Utils.chat("&7all of the skulls in your inventory!"));
		skull2.setLore(lore2);
		stack2.setItemMeta(skull2);

		int minInt2 = 1;
		int maxInt2 = 500;
		Random random2 = new Random();
		int randomInt2 = random2.nextInt((maxInt2 - minInt2) + 1) + minInt2;

		if (randomInt2 <= plugin.getConfig().getInt("spawner_configuration.panda_owner_skull_drop_percent")) {

			int minInt = 1;
			int maxInt = 3;
			Random random = new Random();
			int randomInt = random.nextInt((maxInt - minInt) + 1) + minInt;

			if (randomInt == 1) {
				List<String> lore = new ArrayList<String>();

				SkullMeta skull = (SkullMeta) Bukkit.getItemFactory().getItemMeta(Material.PLAYER_HEAD);
				ItemStack stack = new ItemStack(Material.PLAYER_HEAD, 1);
				skull.setOwner("VerveDev");
				skull.setDisplayName(Utils
						.chat("&6&lDeveloper &r&6" + Bukkit.getOfflinePlayer("VerveDev").getName() + "'s &7Skull"));
				lore.clear();
				lore.add(Utils.chat("&7You have found the &6Developer Skull!"));
				lore.add(Utils.chat("&7Visit the VIP shop to redeem your"));
				lore.add(Utils.chat("&7price! Do &a/warp vip &7to visit the shop!"));
				skull.setLore(lore);
				stack.setItemMeta(skull);
				return stack;
			} else if (randomInt == 2) {
				List<String> lore = new ArrayList<String>();

				SkullMeta skull = (SkullMeta) Bukkit.getItemFactory().getItemMeta(Material.PLAYER_HEAD);
				ItemStack stack = new ItemStack(Material.PLAYER_HEAD, 1);
				skull.setOwner("zSmu");
				skull.setDisplayName(
						Utils.chat("&c&lOwner &r&c" + Bukkit.getOfflinePlayer("Zsmu").getName() + "'s &7Skull"));
				lore.clear();
				lore.add(Utils.chat("&7You have found the &cOwner Skull!"));
				lore.add(Utils.chat("&7Visit the VIP shop to redeem your"));
				lore.add(Utils.chat("&7price! Do &a/warp vip &7to visit the shop!"));
				skull.setLore(lore);
				stack.setItemMeta(skull);
				return stack;
			} else if (randomInt == 3) {
				List<String> lore = new ArrayList<String>();

				SkullMeta skull = (SkullMeta) Bukkit.getItemFactory().getItemMeta(Material.PLAYER_HEAD);
				ItemStack stack = new ItemStack(Material.PLAYER_HEAD, 1);
				skull.setOwner("Dogs4Chow");
				skull.setDisplayName(
						Utils.chat("&c&lOwner &r&c" + Bukkit.getOfflinePlayer("Dogs4Chow").getName() + "'s &7Skull"));
				lore.clear();
				lore.add(Utils.chat("&7You have found the &cOwner Skull!"));
				lore.add(Utils.chat("&7Visit the VIP shop to redeem your"));
				lore.add(Utils.chat("&7price! Do &a/warp vip &7to visit the shop!"));
				skull.setLore(lore);
				stack.setItemMeta(skull);
				return stack;
			}
		}
		return stack2;
	}

	public static ItemStack chickenSkull(int amount) {
		List<String> lore = new ArrayList<String>();

		SkullMeta skull = (SkullMeta) Bukkit.getItemFactory().getItemMeta(Material.PLAYER_HEAD);
		ItemStack chickenSkull = new ItemStack(Material.PLAYER_HEAD, amount);
		skull.setOwner("ThePoup");
		skull.setDisplayName(Utils.chat("&f&lChicken Skull"));
		lore.clear();
		lore.add(Utils.chat("&7Do /rankup to open the main menu"));
		lore.add(Utils.chat("&7click &evault skulls &7to vault"));
		lore.add(Utils.chat("&7all of the skulls in your inventory!"));
		skull.setLore(lore);
		chickenSkull.setItemMeta(skull);
		return chickenSkull;
	}

	public static ItemStack pigSkull(int amount) {
		List<String> lore = new ArrayList<String>();

		SkullMeta skull = (SkullMeta) Bukkit.getItemFactory().getItemMeta(Material.PLAYER_HEAD);
		ItemStack stack = new ItemStack(Material.PLAYER_HEAD, amount);
		skull.setOwner("Pig");
		skull.setDisplayName(Utils.chat("&d&lPig Skull"));
		lore.clear();
		lore.add(Utils.chat("&7Do /rankup to open the main menu"));
		lore.add(Utils.chat("&7click &evault skulls &7to vault"));
		lore.add(Utils.chat("&7all of the skulls in your inventory!"));
		skull.setLore(lore);
		stack.setItemMeta(skull);
		return stack;
	}

	public static ItemStack sheepSkull(int amount) {
		List<String> lore = new ArrayList<String>();

		SkullMeta skull = (SkullMeta) Bukkit.getItemFactory().getItemMeta(Material.PLAYER_HEAD);
		ItemStack stack = new ItemStack(Material.PLAYER_HEAD, amount);
		skull.setOwner("mhf_sheep");
		skull.setDisplayName(Utils.chat("&7&lSheep Skull"));
		lore.clear();
		lore.add(Utils.chat("&7Do /rankup to open the main menu"));
		lore.add(Utils.chat("&7click &evault skulls &7to vault"));
		lore.add(Utils.chat("&7all of the skulls in your inventory!"));
		skull.setLore(lore);
		stack.setItemMeta(skull);
		return stack;
	}

	public static ItemStack cowSkull(int amount) {
		List<String> lore = new ArrayList<String>();

		SkullMeta skull = (SkullMeta) Bukkit.getItemFactory().getItemMeta(Material.PLAYER_HEAD);
		ItemStack stack = new ItemStack(Material.PLAYER_HEAD, amount);
		skull.setOwner("Cow");
		skull.setDisplayName(Utils.chat("&8&lCow Skull"));
		lore.clear();
		lore.add(Utils.chat("&7Do /rankup to open the main menu"));
		lore.add(Utils.chat("&7click &evault skulls &7to vault"));
		lore.add(Utils.chat("&7all of the skulls in your inventory!"));
		skull.setLore(lore);
		stack.setItemMeta(skull);
		return stack;
	}

	public static ItemStack zombieSkull(int amount) {
		List<String> lore = new ArrayList<String>();

		SkullMeta skull = (SkullMeta) Bukkit.getItemFactory().getItemMeta(Material.PLAYER_HEAD);
		ItemStack stack = new ItemStack(Material.PLAYER_HEAD, amount);
		skull.setOwner("zombie");
		skull.setDisplayName(Utils.chat("&2&lZombie Skull"));
		lore.clear();
		lore.add(Utils.chat("&7Do /rankup to open the main menu"));
		lore.add(Utils.chat("&7click &evault skulls &7to vault"));
		lore.add(Utils.chat("&7all of the skulls in your inventory!"));
		skull.setLore(lore);
		stack.setItemMeta(skull);
		return stack;
	}

	public static ItemStack skeletonSkull(int amount) {
		List<String> lore = new ArrayList<String>();

		SkullMeta skull = (SkullMeta) Bukkit.getItemFactory().getItemMeta(Material.PLAYER_HEAD);
		ItemStack stack = new ItemStack(Material.PLAYER_HEAD, amount);
		skull.setOwner("skeleton");
		skull.setDisplayName(Utils.chat("&3&lSkeleton Skull"));
		lore.clear();
		lore.add(Utils.chat("&7Do /rankup to open the main menu"));
		lore.add(Utils.chat("&7click &evault skulls &7to vault"));
		lore.add(Utils.chat("&7all of the skulls in your inventory!"));
		skull.setLore(lore);
		stack.setItemMeta(skull);
		return stack;
	}

	public static ItemStack pigZombieSkull(int amount) {
		List<String> lore = new ArrayList<String>();

		SkullMeta skull = (SkullMeta) Bukkit.getItemFactory().getItemMeta(Material.PLAYER_HEAD);
		ItemStack stack = new ItemStack(Material.PLAYER_HEAD, amount);
		skull.setOwner("pigman");
		skull.setDisplayName(Utils.chat("&c&lZombie Pigman Skull"));
		lore.clear();
		lore.add(Utils.chat("&7Do /rankup to open the main menu"));
		lore.add(Utils.chat("&7click &evault skulls &7to vault"));
		lore.add(Utils.chat("&7all of the skulls in your inventory!"));
		skull.setLore(lore);
		stack.setItemMeta(skull);
		return stack;
	}

	public static ItemStack slimeSkull(int amount) {
		List<String> lore = new ArrayList<String>();

		SkullMeta skull = (SkullMeta) Bukkit.getItemFactory().getItemMeta(Material.PLAYER_HEAD);
		ItemStack stack = new ItemStack(Material.PLAYER_HEAD, amount);
		skull.setOwner("talaknor");
		skull.setDisplayName(Utils.chat("&a&lSlime Skull"));
		lore.clear();
		lore.add(Utils.chat("&7Do /rankup to open the main menu"));
		lore.add(Utils.chat("&7click &evault skulls &7to vault"));
		lore.add(Utils.chat("&7all of the skulls in your inventory!"));
		skull.setLore(lore);
		stack.setItemMeta(skull);
		return stack;
	}

	public static ItemStack creeperSkull(int amount) {
		List<String> lore = new ArrayList<String>();

		SkullMeta skull = (SkullMeta) Bukkit.getItemFactory().getItemMeta(Material.PLAYER_HEAD);
		ItemStack stack = new ItemStack(Material.PLAYER_HEAD, amount);
		skull.setOwner("creeper");
		skull.setDisplayName(Utils.chat("&a&lCreeper Skull"));
		lore.clear();
		lore.add(Utils.chat("&7Do /rankup to open the main menu"));
		lore.add(Utils.chat("&7click &evault skulls &7to vault"));
		lore.add(Utils.chat("&7all of the skulls in your inventory!"));
		skull.setLore(lore);
		stack.setItemMeta(skull);
		return stack;
	}

	public static ItemStack pandaSkull(int amount) {
		List<String> lore2 = new ArrayList<String>();

		SkullMeta skull2 = (SkullMeta) Bukkit.getItemFactory().getItemMeta(Material.PLAYER_HEAD);
		ItemStack stack2 = new ItemStack(Material.PLAYER_HEAD, amount);
		skull2.setOwner("can");
		skull2.setDisplayName(Utils.chat("&f&lPan&7&lda &f&lSkull"));
		lore2.clear();
		lore2.add(Utils.chat("&7Do /rankup to open the main menu"));
		lore2.add(Utils.chat("&7click &evault skulls &7to vault"));
		lore2.add(Utils.chat("&7all of the skulls in your inventory!"));
		skull2.setLore(lore2);
		stack2.setItemMeta(skull2);
		return stack2;
	}
}