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

	public static void saveChickens(OfflinePlayer p) throws FileNotFoundException, IOException {

		// Creates the output stream, specify the correct file
		File file = new File("SaplingData/chicken.dat");
		ObjectOutputStream output = new ObjectOutputStream(new GZIPOutputStream(new FileOutputStream(file)));

		if (chicken.get(p.getUniqueId().toString()) != null) {
			chicken.put(p.getUniqueId().toString(), chicken.get(p.getUniqueId().toString()));
		}

		try {
			// Write the map to the output stream, then close
			output.writeObject(chicken);
			output.flush();
			output.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public static void loadChickens(OfflinePlayer p) throws FileNotFoundException, IOException, ClassNotFoundException {
		// Create the input stream
		File file = new File("SaplingData/chicken.dat");
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
	}

	public static int getChickenSkullAmount(Player p) {
		if (chicken.get(p.getUniqueId().toString()) != null) {
			return chicken.get(p.getUniqueId().toString());
		} else {
			return 0;
		}
	}

	public static void addChickenSkull(Player p, int amount) {
		if (chicken.get(p.getUniqueId().toString()) != null) {
			chicken.put(p.getUniqueId().toString(), chicken.get(p.getUniqueId().toString()) + amount);
		} else {
			chicken.put(p.getUniqueId().toString(), amount);
		}
	}

	public static void removeChickenSkull(Player p, int amount) {
		if (chicken.get(p.getUniqueId().toString()) != null) {
			chicken.put(p.getUniqueId().toString(), chicken.get(p.getUniqueId().toString()) - amount);
		} else {
			chicken.put(p.getUniqueId().toString(), amount);
		}
	}

	/*
	 * Pig Data
	 */

	public static void savePig(OfflinePlayer p) throws FileNotFoundException, IOException {

		// Creates the output stream, specify the correct file
		File file = new File("SaplingData/pig.dat");
		ObjectOutputStream output = new ObjectOutputStream(new GZIPOutputStream(new FileOutputStream(file)));

		if (pig.get(p.getUniqueId().toString()) != null) {
			pig.put(p.getUniqueId().toString(), pig.get(p.getUniqueId().toString()));
		}

		try {
			// Write the map to the output stream, then close
			output.writeObject(pig);
			output.flush();
			output.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public static void loadPig(OfflinePlayer p) throws FileNotFoundException, IOException, ClassNotFoundException {
		// Create the input stream
		File file = new File("SaplingData/pig.dat");
		ObjectInputStream input = new ObjectInputStream(new GZIPInputStream(new FileInputStream(file)));
		// Reads the first object in
		Object readObject = input.readObject();
		input.close();

		if (!(readObject instanceof HashMap))
			throw new IOException("Data is not a hashmap");
		pig = (HashMap<String, Integer>) readObject;
		// Prints out everything in the map.
		for (String key : pig.keySet()) {
			pig.put(key, pig.get(key));
		}
	}

	public static int getPigSkullAmount(Player p) {
		if (pig.get(p.getUniqueId().toString()) != null) {
			return pig.get(p.getUniqueId().toString());
		} else {
			return 0;
		}
	}

	public static void addPigSkull(Player p, int amount) {
		if (pig.get(p.getUniqueId().toString()) != null) {
			pig.put(p.getUniqueId().toString(), pig.get(p.getUniqueId().toString()) + amount);
		} else {
			pig.put(p.getUniqueId().toString(), amount);
		}
	}

	public static void removePigSkull(Player p, int amount) {
		if (pig.get(p.getUniqueId().toString()) != null) {
			pig.put(p.getUniqueId().toString(), pig.get(p.getUniqueId().toString()) - amount);
		} else {
			pig.put(p.getUniqueId().toString(), amount);
		}
	}

	/*
	 * Sheep Data
	 */

	public static void saveSheep(OfflinePlayer p) throws FileNotFoundException, IOException {

		// Creates the output stream, specify the correct file
		File file = new File("SaplingData/sheep.dat");
		ObjectOutputStream output = new ObjectOutputStream(new GZIPOutputStream(new FileOutputStream(file)));

		if (sheep.get(p.getUniqueId().toString()) != null) {
			sheep.put(p.getUniqueId().toString(), sheep.get(p.getUniqueId().toString()));
		}

		try {
			// Write the map to the output stream, then close
			output.writeObject(sheep);
			output.flush();
			output.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public static void loadSheep(OfflinePlayer p) throws FileNotFoundException, IOException, ClassNotFoundException {
		// Create the input stream
		File file = new File("SaplingData/sheep.dat");
		ObjectInputStream input = new ObjectInputStream(new GZIPInputStream(new FileInputStream(file)));
		// Reads the first object in
		Object readObject = input.readObject();
		input.close();

		if (!(readObject instanceof HashMap))
			throw new IOException("Data is not a hashmap");
		sheep = (HashMap<String, Integer>) readObject;
		// Prints out everything in the map.
		for (String key : sheep.keySet()) {
			sheep.put(key, sheep.get(key));
		}
	}

	public static int getSheepSkullAmount(Player p) {
		if (sheep.get(p.getUniqueId().toString()) != null) {
			return sheep.get(p.getUniqueId().toString());
		} else {
			return 0;
		}
	}

	public static void addSheepSkull(Player p, int amount) {
		if (sheep.get(p.getUniqueId().toString()) != null) {
			sheep.put(p.getUniqueId().toString(), sheep.get(p.getUniqueId().toString()) + amount);
		} else {
			sheep.put(p.getUniqueId().toString(), amount);
		}
	}

	public static void removeSheepSkull(Player p, int amount) {
		if (sheep.get(p.getUniqueId().toString()) != null) {
			sheep.put(p.getUniqueId().toString(), sheep.get(p.getUniqueId().toString()) - amount);
		} else {
			sheep.put(p.getUniqueId().toString(), amount);
		}
	}

	/*
	 * Cow Data
	 */

	public static void saveCow(OfflinePlayer p) throws FileNotFoundException, IOException {

		// Creates the output stream, specify the correct file
		File file = new File("SaplingData/cow.dat");
		ObjectOutputStream output = new ObjectOutputStream(new GZIPOutputStream(new FileOutputStream(file)));

		if (cow.get(p.getUniqueId().toString()) != null) {
			cow.put(p.getUniqueId().toString(), cow.get(p.getUniqueId().toString()));
		}

		try {
			// Write the map to the output stream, then close
			output.writeObject(cow);
			output.flush();
			output.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public static void loadCow(OfflinePlayer p) throws FileNotFoundException, IOException, ClassNotFoundException {
		// Create the input stream
		File file = new File("SaplingData/cow.dat");
		ObjectInputStream input = new ObjectInputStream(new GZIPInputStream(new FileInputStream(file)));
		// Reads the first object in
		Object readObject = input.readObject();
		input.close();

		if (!(readObject instanceof HashMap))
			throw new IOException("Data is not a hashmap");
		cow = (HashMap<String, Integer>) readObject;
		// Prints out everything in the map.
		for (String key : cow.keySet()) {
			cow.put(key, cow.get(key));
		}
	}

	public static int getCowSkullAmount(Player p) {
		if (cow.get(p.getUniqueId().toString()) != null) {
			return cow.get(p.getUniqueId().toString());
		} else {
			return 0;
		}
	}

	public static void addCowSkull(Player p, int amount) {
		if (cow.get(p.getUniqueId().toString()) != null) {
			cow.put(p.getUniqueId().toString(), cow.get(p.getUniqueId().toString()) + amount);
		} else {
			cow.put(p.getUniqueId().toString(), amount);
		}
	}

	public static void removeCowSkull(Player p, int amount) {
		if (cow.get(p.getUniqueId().toString()) != null) {
			cow.put(p.getUniqueId().toString(), cow.get(p.getUniqueId().toString()) - amount);
		} else {
			cow.put(p.getUniqueId().toString(), amount);
		}
	}

	/*
	 * Zombie Data
	 */

	public static void saveZombie(OfflinePlayer p) throws FileNotFoundException, IOException {

		// Creates the output stream, specify the correct file
		File file = new File("SaplingData/zombie.dat");
		ObjectOutputStream output = new ObjectOutputStream(new GZIPOutputStream(new FileOutputStream(file)));

		if (zombie.get(p.getUniqueId().toString()) != null) {
			zombie.put(p.getUniqueId().toString(), zombie.get(p.getUniqueId().toString()));
		}

		try {
			// Write the map to the output stream, then close
			output.writeObject(zombie);
			output.flush();
			output.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public static void loadZombie(OfflinePlayer p) throws FileNotFoundException, IOException, ClassNotFoundException {
		// Create the input stream
		File file = new File("SaplingData/zombie.dat");
		ObjectInputStream input = new ObjectInputStream(new GZIPInputStream(new FileInputStream(file)));
		// Reads the first object in
		Object readObject = input.readObject();
		input.close();

		if (!(readObject instanceof HashMap))
			throw new IOException("Data is not a hashmap");
		zombie = (HashMap<String, Integer>) readObject;
		// Prints out everything in the map.
		for (String key : zombie.keySet()) {
			zombie.put(key, zombie.get(key));
		}
	}

	public static int getZombieSkullAmount(Player p) {
		if (zombie.get(p.getUniqueId().toString()) != null) {
			return zombie.get(p.getUniqueId().toString());
		} else {
			return 0;
		}
	}

	public static void addZombieSkull(Player p, int amount) {
		if (zombie.get(p.getUniqueId().toString()) != null) {
			zombie.put(p.getUniqueId().toString(), zombie.get(p.getUniqueId().toString()) + amount);
		} else {
			zombie.put(p.getUniqueId().toString(), amount);
		}
	}

	public static void removeZombieSkull(Player p, int amount) {
		if (zombie.get(p.getUniqueId().toString()) != null) {
			zombie.put(p.getUniqueId().toString(), zombie.get(p.getUniqueId().toString()) - amount);
		} else {
			zombie.put(p.getUniqueId().toString(), amount);
		}
	}

	/*
	 * Skeleton Data
	 */

	public static void saveSkeleton(OfflinePlayer p) throws FileNotFoundException, IOException {

		// Creates the output stream, specify the correct file
		File file = new File("SaplingData/skeleton.dat");
		ObjectOutputStream output = new ObjectOutputStream(new GZIPOutputStream(new FileOutputStream(file)));

		if (skeleton.get(p.getUniqueId().toString()) != null) {
			skeleton.put(p.getUniqueId().toString(), skeleton.get(p.getUniqueId().toString()));
		}

		try {
			// Write the map to the output stream, then close
			output.writeObject(skeleton);
			output.flush();
			output.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public static void loadSkeleton(OfflinePlayer p) throws FileNotFoundException, IOException, ClassNotFoundException {
		// Create the input stream
		File file = new File("SaplingData/skeleton.dat");
		ObjectInputStream input = new ObjectInputStream(new GZIPInputStream(new FileInputStream(file)));
		// Reads the first object in
		Object readObject = input.readObject();
		input.close();

		if (!(readObject instanceof HashMap))
			throw new IOException("Data is not a hashmap");
		skeleton = (HashMap<String, Integer>) readObject;
		// Prints out everything in the map.
		for (String key : skeleton.keySet()) {
			skeleton.put(key, skeleton.get(key));
		}
	}

	public static int getSkeletonSkullAmount(Player p) {
		if (skeleton.get(p.getUniqueId().toString()) != null) {
			return skeleton.get(p.getUniqueId().toString());
		} else {
			return 0;
		}
	}

	public static void addSkeletonSkull(Player p, int amount) {
		if (skeleton.get(p.getUniqueId().toString()) != null) {
			skeleton.put(p.getUniqueId().toString(), skeleton.get(p.getUniqueId().toString()) + amount);
		} else {
			skeleton.put(p.getUniqueId().toString(), amount);
		}
	}

	public static void removeSkeletonSkull(Player p, int amount) {
		if (skeleton.get(p.getUniqueId().toString()) != null) {
			skeleton.put(p.getUniqueId().toString(), skeleton.get(p.getUniqueId().toString()) - amount);
		} else {
			skeleton.put(p.getUniqueId().toString(), amount);
		}
	}

	/*
	 * Zombie Pigman Data
	 */

	public static void savePigZombie(OfflinePlayer p) throws FileNotFoundException, IOException {

		// Creates the output stream, specify the correct file
		File file = new File("SaplingData/zpigman.dat");
		ObjectOutputStream output = new ObjectOutputStream(new GZIPOutputStream(new FileOutputStream(file)));

		if (zpigman.get(p.getUniqueId().toString()) != null) {
			zpigman.put(p.getUniqueId().toString(), zpigman.get(p.getUniqueId().toString()));
		}

		try {
			// Write the map to the output stream, then close
			output.writeObject(zpigman);
			output.flush();
			output.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public static void loadPigZombie(OfflinePlayer p)
			throws FileNotFoundException, IOException, ClassNotFoundException {
		// Create the input stream
		File file = new File("SaplingData/zpigman.dat");
		ObjectInputStream input = new ObjectInputStream(new GZIPInputStream(new FileInputStream(file)));
		// Reads the first object in
		Object readObject = input.readObject();
		input.close();

		if (!(readObject instanceof HashMap))
			throw new IOException("Data is not a hashmap");
		zpigman = (HashMap<String, Integer>) readObject;
		// Prints out everything in the map.
		for (String key : zpigman.keySet()) {
			zpigman.put(key, zpigman.get(key));
		}
	}

	public static int getPigZombieSkullAmount(Player p) {
		if (zpigman.get(p.getUniqueId().toString()) != null) {
			return zpigman.get(p.getUniqueId().toString());
		} else {
			return 0;
		}
	}

	public static void addPigZombieSkull(Player p, int amount) {
		if (zpigman.get(p.getUniqueId().toString()) != null) {
			zpigman.put(p.getUniqueId().toString(), zpigman.get(p.getUniqueId().toString()) + amount);
		} else {
			zpigman.put(p.getUniqueId().toString(), amount);
		}
	}

	public static void removePigZombieSkull(Player p, int amount) {
		if (zpigman.get(p.getUniqueId().toString()) != null) {
			zpigman.put(p.getUniqueId().toString(), zpigman.get(p.getUniqueId().toString()) - amount);
		} else {
			zpigman.put(p.getUniqueId().toString(), amount);
		}
	}

	/*
	 * Slime Data
	 */

	public static void saveSlime(OfflinePlayer p) throws FileNotFoundException, IOException {

		// Creates the output stream, specify the correct file
		File file = new File("SaplingData/slime.dat");
		ObjectOutputStream output = new ObjectOutputStream(new GZIPOutputStream(new FileOutputStream(file)));

		if (slime.get(p.getUniqueId().toString()) != null) {
			slime.put(p.getUniqueId().toString(), slime.get(p.getUniqueId().toString()));
		}

		try {
			// Write the map to the output stream, then close
			output.writeObject(slime);
			output.flush();
			output.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public static void loadSlime(OfflinePlayer p) throws FileNotFoundException, IOException, ClassNotFoundException {
		// Create the input stream
		File file = new File("SaplingData/slime.dat");
		ObjectInputStream input = new ObjectInputStream(new GZIPInputStream(new FileInputStream(file)));
		// Reads the first object in
		Object readObject = input.readObject();
		input.close();

		if (!(readObject instanceof HashMap))
			throw new IOException("Data is not a hashmap");
		slime = (HashMap<String, Integer>) readObject;
		// Prints out everything in the map.
		for (String key : slime.keySet()) {
			slime.put(key, slime.get(key));
		}
	}

	public static int getSlimeSkullAmount(Player p) {
		if (slime.get(p.getUniqueId().toString()) != null) {
			return slime.get(p.getUniqueId().toString());
		} else {
			return 0;
		}
	}

	public static void addSlimeSkull(Player p, int amount) {
		if (slime.get(p.getUniqueId().toString()) != null) {
			slime.put(p.getUniqueId().toString(), slime.get(p.getUniqueId().toString()) + amount);
		} else {
			slime.put(p.getUniqueId().toString(), amount);
		}
	}

	public static void removeSlimeSkull(Player p, int amount) {
		if (slime.get(p.getUniqueId().toString()) != null) {
			slime.put(p.getUniqueId().toString(), slime.get(p.getUniqueId().toString()) - amount);
		} else {
			slime.put(p.getUniqueId().toString(), amount);
		}
	}

	/*
	 * Creeper Data
	 */

	public static void saveCreeper(OfflinePlayer p) throws FileNotFoundException, IOException {

		// Creates the output stream, specify the correct file
		File file = new File("SaplingData/creeper.dat");
		ObjectOutputStream output = new ObjectOutputStream(new GZIPOutputStream(new FileOutputStream(file)));

		if (creeper.get(p.getUniqueId().toString()) != null) {
			creeper.put(p.getUniqueId().toString(), creeper.get(p.getUniqueId().toString()));
		}

		try {
			// Write the map to the output stream, then close
			output.writeObject(creeper);
			output.flush();
			output.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public static void loadCreeper(OfflinePlayer p) throws FileNotFoundException, IOException, ClassNotFoundException {
		// Create the input stream
		File file = new File("SaplingData/creeper.dat");
		ObjectInputStream input = new ObjectInputStream(new GZIPInputStream(new FileInputStream(file)));
		// Reads the first object in
		Object readObject = input.readObject();
		input.close();

		if (!(readObject instanceof HashMap))
			throw new IOException("Data is not a hashmap");
		creeper = (HashMap<String, Integer>) readObject;
		// Prints out everything in the map.
		for (String key : creeper.keySet()) {
			creeper.put(key, creeper.get(key));
		}
	}

	public static int getCreeperSkullAmount(Player p) {
		if (creeper.get(p.getUniqueId().toString()) != null) {
			return creeper.get(p.getUniqueId().toString());
		} else {
			return 0;
		}
	}

	public static void addCreeperSkull(Player p, int amount) {
		if (creeper.get(p.getUniqueId().toString()) != null) {
			creeper.put(p.getUniqueId().toString(), creeper.get(p.getUniqueId().toString()) + amount);
		} else {
			creeper.put(p.getUniqueId().toString(), amount);
		}
	}

	public static void removeCreeperSkull(Player p, int amount) {
		if (creeper.get(p.getUniqueId().toString()) != null) {
			creeper.put(p.getUniqueId().toString(), creeper.get(p.getUniqueId().toString()) - amount);
		} else {
			creeper.put(p.getUniqueId().toString(), amount);
		}
	}

	/*
	 * Panda Data
	 */

	public static void savePanda(OfflinePlayer p) throws FileNotFoundException, IOException {

		// Creates the output stream, specify the correct file
		File file = new File("SaplingData/panda.dat");
		ObjectOutputStream output = new ObjectOutputStream(new GZIPOutputStream(new FileOutputStream(file)));

		if (panda.get(p.getUniqueId().toString()) != null) {
			panda.put(p.getUniqueId().toString(), panda.get(p.getUniqueId().toString()));
		}

		try {
			// Write the map to the output stream, then close
			output.writeObject(panda);
			output.flush();
			output.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public static void loadPanda(OfflinePlayer p) throws FileNotFoundException, IOException, ClassNotFoundException {
		// Create the input stream
		File file = new File("SaplingData/panda.dat");
		ObjectInputStream input = new ObjectInputStream(new GZIPInputStream(new FileInputStream(file)));
		// Reads the first object in
		Object readObject = input.readObject();
		input.close();

		if (!(readObject instanceof HashMap))
			throw new IOException("Data is not a hashmap");
		panda = (HashMap<String, Integer>) readObject;
		// Prints out everything in the map.
		for (String key : panda.keySet()) {
			panda.put(key, panda.get(key));
		}
	}

	public static int getPandaSkullAmount(Player p) {
		if (panda.get(p.getUniqueId().toString()) != null) {
			return panda.get(p.getUniqueId().toString());
		} else {
			return 0;
		}
	}

	public static void addPandaSkull(Player p, int amount) {
		if (panda.get(p.getUniqueId().toString()) != null) {
			panda.put(p.getUniqueId().toString(), panda.get(p.getUniqueId().toString()) + amount);
		} else {
			panda.put(p.getUniqueId().toString(), amount);
		}
	}

	public static void removePandaSkull(Player p, int amount) {
		if (panda.get(p.getUniqueId().toString()) != null) {
			panda.put(p.getUniqueId().toString(), panda.get(p.getUniqueId().toString()) - amount);
		} else {
			panda.put(p.getUniqueId().toString(), amount);
		}
	}

	public static ItemStack getPandaSkull() {

		List<String> lore2 = new ArrayList<String>();

		SkullMeta skull2 = (SkullMeta) Bukkit.getItemFactory().getItemMeta(Material.PLAYER_HEAD);
		ItemStack stack2 = new ItemStack(Material.PLAYER_HEAD, 1, (short) 3);
		skull2.setOwner("can");
		skull2.setDisplayName(Utils.chat("&f&lPan&7&lda &f&lSkull"));
		lore2.clear();
		lore2.add(Utils.chat("&7Do /rankup to open the main menu"));
		lore2.add(Utils.chat("&7click &evault skulls &7to vault"));
		lore2.add(Utils.chat("&7all of the skulls in your inventory!"));
		skull2.setLore(lore2);
		stack2.setItemMeta(skull2);

		int minInt2 = 1;
		int maxInt2 = 100;
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
				ItemStack stack = new ItemStack(Material.PLAYER_HEAD, 1, (short) 3);
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
				ItemStack stack = new ItemStack(Material.PLAYER_HEAD, 1, (short) 3);
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
				ItemStack stack = new ItemStack(Material.PLAYER_HEAD, 1, (short) 3);
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
		ItemStack chickenSkull = new ItemStack(Material.PLAYER_HEAD, amount, (short) 3);
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
		ItemStack stack = new ItemStack(Material.PLAYER_HEAD, amount, (short) 3);
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
		ItemStack stack = new ItemStack(Material.PLAYER_HEAD, amount, (short) 3);
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
		ItemStack stack = new ItemStack(Material.PLAYER_HEAD, amount, (short) 3);
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
		ItemStack stack = new ItemStack(Material.PLAYER_HEAD, amount, (short) 3);
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
		ItemStack stack = new ItemStack(Material.PLAYER_HEAD, amount, (short) 3);
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
		ItemStack stack = new ItemStack(Material.PLAYER_HEAD, amount, (short) 3);
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
		ItemStack stack = new ItemStack(Material.PLAYER_HEAD, amount, (short) 3);
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
		ItemStack stack = new ItemStack(Material.PLAYER_HEAD, amount, (short) 3);
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
		ItemStack stack2 = new ItemStack(Material.PLAYER_HEAD, amount, (short) 3);
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