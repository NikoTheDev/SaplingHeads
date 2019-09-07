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
import com.vervedev.saplingheads.leaderboards.LeaderboardCommand;
import com.vervedev.saplingheads.listeners.CommandPreProcess;
import com.vervedev.saplingheads.listeners.CreatureSpawn;
import com.vervedev.saplingheads.listeners.EntityDeath;
import com.vervedev.saplingheads.listeners.InventoryClick;
import com.vervedev.saplingheads.listeners.NPCClick;
import com.vervedev.saplingheads.listeners.PingList;
import com.vervedev.saplingheads.managers.CraftingManager;
import com.vervedev.saplingheads.managers.CurrencyManager;
import com.vervedev.saplingheads.managers.DonationManager;
import com.vervedev.saplingheads.managers.PerkManager;
import com.vervedev.saplingheads.managers.PlayerManager;
import com.vervedev.saplingheads.managers.RankManager;
import com.vervedev.saplingheads.managers.SkullManager;
import com.vervedev.saplingheads.managers.SpawnerManager;
import com.vervedev.saplingheads.managers.tutorial.TutorialManager;
import com.vervedev.saplingheads.perks.JellyLegs;
import com.vervedev.saplingheads.perks.Nocturnal;
import com.vervedev.saplingheads.ui.PerkUI;
import com.vervedev.saplingheads.ui.RankupUI;
import com.vervedev.saplingheads.ui.SkullVaultUI;
import com.vervedev.saplingheads.ui.donatorshop.BuyUI;
import com.vervedev.saplingheads.ui.donatorshop.CrateKeyUI;
import com.vervedev.saplingheads.ui.donatorshop.DonatorRankUI;
import com.vervedev.saplingheads.utils.Utils;

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
		registerPerks();
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
	
    public void sendOnlineStatus() {
    }
    
    public void sendOfflineStatus() {
    }

	/*
	 * Save Ranks
	 */

	public void saveFiles() throws FileNotFoundException, IOException {
		for (OfflinePlayer p : Bukkit.getOfflinePlayers()) {
			RankManager.saveRanks(p);
			SkullManager.saveSkullFiles(p);
			PerkManager.savePerks(p);
			CurrencyManager.savePerkCredits(p);
		}
	}

	public void loadFiles() throws FileNotFoundException, ClassNotFoundException, IOException {
		for (OfflinePlayer p : Bukkit.getOfflinePlayers()) {
			RankManager.loadRanks(p);
			SkullManager.loadSkullFiles(p);
			PerkManager.loadPerks(p);
			CurrencyManager.loadPerkCredits(p);
		}
	}

	public void registerCommands() {
		new LeaderboardCommand(this);
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
		BuyUI.initialize();
		CrateKeyUI.initialize();
		DonatorRankUI.initialize();
		/*
		 * DonatorShopUI End
		 */
		RankupUI.initialize();
		SkullVaultUI.initialize();
		PerkUI.initialize();
	}

	public void registerManagers() {
		new Utils(this);
		new RankManager(this);
		new SkullManager(this);
		new SpawnerManager(this);
		new PlayerManager(this);
		new CraftingManager(this);
		new DonationManager(this);
		//new TutorialManager(this);
		new PerkManager(this);
		new CurrencyManager(this);
	}

	public void registerPerks() {
		new JellyLegs(this);
		new Nocturnal(this);
	}
}
