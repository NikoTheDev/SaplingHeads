package com.vervedev.saplingheads;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import com.vervedev.saplingheads.commands.PerkCommand;
import com.vervedev.saplingheads.commands.RankCheck;
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
import com.vervedev.saplingheads.managers.DonationManager;
import com.vervedev.saplingheads.managers.PerkManager;
import com.vervedev.saplingheads.managers.PlayerManager;
import com.vervedev.saplingheads.managers.RankManager;
import com.vervedev.saplingheads.managers.SkullManager;
import com.vervedev.saplingheads.managers.SpawnerManager;
import com.vervedev.saplingheads.managers.tutorial.TutorialManager;
import com.vervedev.saplingheads.perks.JellyLegs;
import com.vervedev.saplingheads.ui.PerkUI;
import com.vervedev.saplingheads.ui.RankupUI;
import com.vervedev.saplingheads.ui.SkullVaultUI;
import com.vervedev.saplingheads.ui.donatorshop.BuyUI;
import com.vervedev.saplingheads.ui.donatorshop.CrateKeyUI;
import com.vervedev.saplingheads.ui.donatorshop.DonatorRankUI;

import net.milkbowl.vault.economy.Economy;

public class Main extends JavaPlugin {

	public static Economy econ = null;

	@Override
	public void onEnable() {
		saveDefaultConfig();
		try {
			loadFiles();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		if (!setupEconomy()) {
			getLogger().severe(String.format("[SaplingHeads] - Disabled due to no Vault dependency found!",
					getDescription().getName()));
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
			saveFiles();
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (Player p : Bukkit.getOnlinePlayers()) {
			if (TutorialManager.tutorial.contains(p)) {
				TutorialManager.stopTutorial(p);
			}
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

	public void saveFiles() throws FileNotFoundException, IOException {
		for (OfflinePlayer p : Bukkit.getOfflinePlayers()) {
			RankManager.saveRanks(p);
			SkullManager.saveChickens(p);
			SkullManager.savePig(p);
			SkullManager.saveSheep(p);
			SkullManager.saveCow(p);
			SkullManager.saveZombie(p);
			SkullManager.saveSkeleton(p);
			SkullManager.savePigZombie(p);
			SkullManager.saveSlime(p);
			SkullManager.saveCreeper(p);
			SkullManager.savePanda(p);
			PerkManager.savePerks(p);
		}
	}

	public void loadFiles() throws FileNotFoundException, ClassNotFoundException, IOException {
		for (OfflinePlayer p : Bukkit.getOfflinePlayers()) {
			RankManager.loadRanks(p);
			SkullManager.loadChickens(p);
			SkullManager.loadPig(p);
			SkullManager.loadSheep(p);
			SkullManager.loadCow(p);
			SkullManager.loadZombie(p);
			SkullManager.loadSkeleton(p);
			SkullManager.loadPigZombie(p);
			SkullManager.loadSlime(p);
			SkullManager.loadCreeper(p);
			SkullManager.loadPanda(p);
			PerkManager.loadPerks(p);
		}
	}

	public void registerCommands() {
		new Rankup(this);
		new SkullVaultNPC(this);
		new SetSpawn(this);
		new Spawn(this);
		new SkullVault(this);
		new SetRank(this);
		new SpawnerShopNPC(this);
		new Spawners(this);
		new RankCheck(this);
		new PerkCommand(this);
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
		new RankupUI(this);
		new SkullVaultUI(this);
		/*
		 * DonatorShopUI Start
		 */
		new BuyUI(this);
		new DonatorRankUI(this);
		new CrateKeyUI(this);
		RankupUI.initialize();
		SkullVaultUI.initialize();
		BuyUI.initialize();
		CrateKeyUI.initialize();
		DonatorRankUI.initialize();
		PerkUI.initialize();
		/*
		 * DonatorShopUI End
		 */
	}

	public void registerManagers() {
		new RankManager(this);
		new SkullManager(this);
		new SpawnerManager(this);
		new PlayerManager(this);
	    new CraftingManager(this);
		new DonationManager(this);
		new TutorialManager(this);
		new PerkManager(this);
	}
	
	public void registerPerks() {
		new JellyLegs(this);
	}
}
