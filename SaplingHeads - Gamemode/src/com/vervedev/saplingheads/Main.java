package com.vervedev.saplingheads;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import com.vervedev.saplingheads.commands.Rankup;
import com.vervedev.saplingheads.commands.SetRank;
import com.vervedev.saplingheads.commands.SetSpawn;
import com.vervedev.saplingheads.commands.SkullVault;
import com.vervedev.saplingheads.commands.SkullVaultNPC;
import com.vervedev.saplingheads.commands.Spawn;
import com.vervedev.saplingheads.commands.SpawnerShopNPC;
import com.vervedev.saplingheads.commands.Spawners;
import com.vervedev.saplingheads.listeners.CommandPreProcess;
import com.vervedev.saplingheads.listeners.CreatureSpawn;
import com.vervedev.saplingheads.listeners.EntityDeath;
import com.vervedev.saplingheads.listeners.InventoryClick;
import com.vervedev.saplingheads.listeners.NPCClick;
import com.vervedev.saplingheads.listeners.PingList;
import com.vervedev.saplingheads.managers.CraftingManager;
import com.vervedev.saplingheads.managers.PlayerManager;
import com.vervedev.saplingheads.managers.RankManager;
import com.vervedev.saplingheads.managers.SkullManager;
import com.vervedev.saplingheads.managers.SpawnerManager;
import com.vervedev.saplingheads.ui.RankupUI;
import com.vervedev.saplingheads.ui.SkullVaultUI;
import com.vervedev.saplingheads.ui.donatorshop.BuyUI;

import net.milkbowl.vault.economy.Economy;

public class Main extends JavaPlugin {

	public static Economy econ = null;

	@Override
	public void onEnable() {
		saveDefaultConfig();	
		try {
			loadRanks();
			loadChickens();
			loadPigs();
			loadSheeps();
			loadCows();
			loadZombies();
			loadSkeletons();
			loadPigZombies();
			loadSlimes();
			loadCreepers();
			loadPandas();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		if (!setupEconomy()) {
			getLogger().severe(
					String.format("[SaplingHeads] - Disabled due to no Vault dependency found!", getDescription().getName()));
			getServer().getPluginManager().disablePlugin(this);
			return;
		}

		if (!Bukkit.getPluginManager().isPluginEnabled("HolographicDisplays")) {
			getLogger().severe("*** HolographicDisplays is not installed or not enabled. ***");
			getLogger().severe("*** This plugin will be disabled. ***");
			this.setEnabled(false);
			return;
		}
		registerManagers();
		registerUI();
		registerCommands();
		registerListeners();
		SpawnerShopNPC.loadHolograms();
	}

	@Override
	public void onDisable() {
		try {
			saveRanks();
			saveChickens();
			savePigs();
			saveSheeps();
			saveCows();
			saveZombies();
			saveSkeletons();
			savePigZombies();
			saveSlimes();
			saveCreepers();
			savePandas();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private boolean setupEconomy() {
		if (getServer().getPluginManager().getPlugin("Vault") == null) {
			return false;
		}
		RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
		if (rsp == null) {
			return false;
		}
		econ = rsp.getProvider();
		return econ != null;
	}
	
	/*
	 * Save Ranks
	 */
	
	public void saveRanks() throws FileNotFoundException, IOException {
		for (OfflinePlayer p : Bukkit.getOfflinePlayers()) {
			RankManager.saveRanks(p);
		}
	}
	
	public void loadRanks() throws FileNotFoundException, ClassNotFoundException, IOException {
		for (OfflinePlayer p : Bukkit.getOfflinePlayers()) {
			RankManager.loadRanks(p);
		}
	}
	
	/*
	 * Save Chicken Skulls
	 */
	
	public void saveChickens() throws FileNotFoundException, IOException {
		for (OfflinePlayer p : Bukkit.getOfflinePlayers()) {
			SkullManager.saveChickens(p);
		}
	}
	
	public void loadChickens() throws FileNotFoundException, ClassNotFoundException, IOException {
		for (OfflinePlayer p : Bukkit.getOfflinePlayers()) {
			SkullManager.loadChickens(p);
		}
	}
	
	/*
	 * Save Pig Skulls
	 */
	
	public void savePigs() throws FileNotFoundException, IOException {
		for (OfflinePlayer p : Bukkit.getOfflinePlayers()) {
			SkullManager.savePig(p);
		}
	}
	
	public void loadPigs() throws FileNotFoundException, ClassNotFoundException, IOException {
		for (OfflinePlayer p : Bukkit.getOfflinePlayers()) {
			SkullManager.loadPig(p);
		}
	}
	
	/*
	 * Save Sheep Skulls
	 */
	
	public void saveSheeps() throws FileNotFoundException, IOException {
		for (OfflinePlayer p : Bukkit.getOfflinePlayers()) {
			SkullManager.saveSheep(p);
		}
	}
	
	public void loadSheeps() throws FileNotFoundException, ClassNotFoundException, IOException {
		for (OfflinePlayer p : Bukkit.getOfflinePlayers()) {
			SkullManager.loadSheep(p);
		}
	}
	
	/*
	 * Save Cow Skulls
	 */
	
	public void saveCows() throws FileNotFoundException, IOException {
		for (OfflinePlayer p : Bukkit.getOfflinePlayers()) {
			SkullManager.saveCow(p);
		}
	}
	
	public void loadCows() throws FileNotFoundException, ClassNotFoundException, IOException {
		for (OfflinePlayer p : Bukkit.getOfflinePlayers()) {
			SkullManager.loadCow(p);
		}
	}
	
	/*
	 * Save Zombie Skulls
	 */
	
	public void saveZombies() throws FileNotFoundException, IOException {
		for (OfflinePlayer p : Bukkit.getOfflinePlayers()) {
			SkullManager.saveZombie(p);
		}
	}
	
	public void loadZombies() throws FileNotFoundException, ClassNotFoundException, IOException {
		for (OfflinePlayer p : Bukkit.getOfflinePlayers()) {
			SkullManager.loadZombie(p);
		}
	}
	
	/*
	 * Save Skeleton Skulls
	 */
	
	public void saveSkeletons() throws FileNotFoundException, IOException {
		for (OfflinePlayer p : Bukkit.getOfflinePlayers()) {
			SkullManager.saveSkeleton(p);
		}
	}
	
	public void loadSkeletons() throws FileNotFoundException, ClassNotFoundException, IOException {
		for (OfflinePlayer p : Bukkit.getOfflinePlayers()) {
			SkullManager.loadSkeleton(p);
		}
	}
	
	/*
	 * Save Zombie Pigman Skulls
	 */
	
	public void savePigZombies() throws FileNotFoundException, IOException {
		for (OfflinePlayer p : Bukkit.getOfflinePlayers()) {
			SkullManager.savePigZombie(p);
		}
	}
	
	public void loadPigZombies() throws FileNotFoundException, ClassNotFoundException, IOException {
		for (OfflinePlayer p : Bukkit.getOfflinePlayers()) {
			SkullManager.loadPigZombie(p);
		}
	}
	
	/*
	 * Save Slime Skulls
	 */
	
	public void saveSlimes() throws FileNotFoundException, IOException {
		for (OfflinePlayer p : Bukkit.getOfflinePlayers()) {
			SkullManager.saveSlime(p);
		}
	}
	
	public void loadSlimes() throws FileNotFoundException, ClassNotFoundException, IOException {
		for (OfflinePlayer p : Bukkit.getOfflinePlayers()) {
			SkullManager.loadSlime(p);
		}
	}
	
	/*
	 * Save Creeper Skulls
	 */
	
	public void saveCreepers() throws FileNotFoundException, IOException {
		for (OfflinePlayer p : Bukkit.getOfflinePlayers()) {
			SkullManager.saveCreeper(p);
		}
	}
	
	public void loadCreepers() throws FileNotFoundException, ClassNotFoundException, IOException {
		for (OfflinePlayer p : Bukkit.getOfflinePlayers()) {
			SkullManager.loadCreeper(p);
		}
	}
	
	/*
	 * Save Panda Skulls
	 */
	
	public void savePandas() throws FileNotFoundException, IOException {
		for (OfflinePlayer p : Bukkit.getOfflinePlayers()) {
			SkullManager.savePanda(p);
		}
	}
	
	public void loadPandas() throws FileNotFoundException, ClassNotFoundException, IOException {
		for (OfflinePlayer p : Bukkit.getOfflinePlayers()) {
			SkullManager.loadPanda(p);
		}
	}
	
	/*
	 * Save Currency
	 */
	
	public void registerCommands() {
		new Rankup(this);
		new SkullVaultNPC(this);
		new SetSpawn(this);
		new Spawn(this);
		new SkullVault(this);
		new SetRank(this);
		new SpawnerShopNPC(this);
		new Spawners(this);
	}
	
	public void registerListeners() {
		new CreatureSpawn(this);
		new EntityDeath(this);
		new InventoryClick(this);
		new NPCClick(this);
		new CommandPreProcess(this);
		new PingList(this);
	}
	
	public void registerUI() {
		RankupUI.initialize();
		SkullVaultUI.initialize();
		BuyUI.initialize();
	}
	
	public void registerManagers() {
		new RankManager(this);
		new SkullManager(this);
		new SpawnerManager(this);
		new PlayerManager(this);
		new CraftingManager(this);
	}
}
