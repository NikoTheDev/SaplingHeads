package com.vervedev.saplingheads.ui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.vervedev.saplingheads.Main;
import com.vervedev.saplingheads.managers.CurrencyManager;
import com.vervedev.saplingheads.managers.PerkManager;
import com.vervedev.saplingheads.managers.RankManager;
import com.vervedev.saplingheads.perks.JellyLegs;
import com.vervedev.saplingheads.perks.Nocturnal;
import com.vervedev.saplingheads.utils.Utils;

public class PerkUI {

	public static Inventory inv;
	public static String inventory_name;
	public static int inv_rows = 1 * 9;

	public static Main plugin;

	public PerkUI(Main plugin) {
		this.plugin = plugin;

	}

	public static void initialize() {
		inventory_name = Utils.chat("&9&lPerk Menu");

		inv = Bukkit.createInventory(null, inv_rows);
	}

	public static Inventory GUI(Player p) {

		Inventory toReturn = Bukkit.createInventory(null, inv_rows, inventory_name);
		
		Utils.createItemHead(inv, p.getName(), 1, 9, p.getName() + "'s &ePerk Credits", "&7Use this menu to unlock",
				"&7special abilities to use throughout the", "&7server!", "",
				"&9Perk Credits: &e" + CurrencyManager.getPerkCredits(p));

		if (PerkManager.checkPerkUnlocked(p, "jellylegs")) {
			if (PerkManager.checkPerkActive(p, "jellylegs")) {
				Utils.createItem(inv, "GREEN_STAINED_GLASS_PANE", 1, 1, "&d&lJellyLegs &8(&aEnabled&8)",
						"&7Disable all fall damage with this", "&7perk! One of the best perks on the server",
						"&7for PvP!", "", "&aCurrently Activated &8(&7Click to &cDisable&8)");
			} else {
				Utils.createItem(inv, "YELLOW_STAINED_GLASS_PANE", 1, 1, "&d&lJellyLegs &8(&eDisabled&8)",
						"&7Disable all fall damage with this", "&7perk! One of the best perks on the server",
						"&7for PvP!", "", "&cCurrently Disabled &8(&7Click to &aEnable&8)");
			}
		} else {
			Utils.createItem(inv, "BLACK_STAINED_GLASS_PANE", 1, 1, "&d&lJellyLegs &8(&7LOCKED&8)",
					"&7Disable all fall damage with this", "&7perk! One of the best perks on the server", "&7for PvP!",
					"", "&9Requires &fChicken &7rank and &e5 Perk Credits &7to unlock!");
		}

		if (PerkManager.checkPerkUnlocked(p, "nocturnal")) {
			if (PerkManager.checkPerkActive(p, "nocturnal")) {
				Utils.createItem(inv, "GREEN_STAINED_GLASS_PANE", 1, 2, "&5&lNocturnal &8(&aEnabled&8)",
						"&7Enable NightVision with this perk! Great for", "&7seeing in the dark and finding enemeies",
						"", "&aCurrently Activated &8(&7Click to &cDisable&8)");
			} else {
				Utils.createItem(inv, "YELLOW_STAINED_GLASS_PANE", 1, 2, "&5&lNocturnal &8(&eDisabled&8)",
						"&7Enable NightVision with this perk! Great for", "&7seeing in the dark and finding enemeies",
						"", "&cCurrently Disabled &8(&7Click to &aEnable&8)");
			}
		} else {
			Utils.createItem(inv, "BLACK_STAINED_GLASS_PANE", 1, 2, "&5&lNocturnal &8(&7LOCKED&8)",
					"&7Enable NightVision with this perk! Great for", "&7seeing in the dark and finding enemeies",
					"", "&9Requires &dPig &7rank and &e10 Perk Credits &7to unlock!");
		}

		toReturn.setContents(inv.getContents());
		return toReturn;

	}

	public static void clicked(Player p, int slot, ItemStack clicked, Inventory inv) {
		if (ChatColor.stripColor(clicked.getItemMeta().getDisplayName()).equalsIgnoreCase("JellyLegs (LOCKED)")) {
			if (RankManager.getRank(p) >= 1) {
				if (CurrencyManager.getPerkCredits(p) >= 5) {
					CurrencyManager.removePerkCredits(p, 5);
					JellyLegs.addJellyLegs(p);
					p.updateInventory();
					p.openInventory(PerkUI.GUI(p));
				} else {
					p.sendMessage(Utils.chat(
							"&9&lPerks &8> &7You do not have sufficent &9Perk Credits &7to unlock this &eperk&7!"));
				}
			} else {
				p.sendMessage(
						Utils.chat("&9&lPerks &8> &7You must have &f&lChicken &7unlocked to purchase this perk!"));
			}
		} else if (ChatColor.stripColor(clicked.getItemMeta().getDisplayName())
				.equalsIgnoreCase("JellyLegs (Disabled)")) {
			JellyLegs.enableJellyLegs(p);
			p.updateInventory();
			p.openInventory(PerkUI.GUI(p));
		} else if (ChatColor.stripColor(clicked.getItemMeta().getDisplayName())
				.equalsIgnoreCase("JellyLegs (Enabled)")) {
			JellyLegs.disableJellyLegs(p);
			p.updateInventory();
			p.openInventory(PerkUI.GUI(p));
		}

		if (ChatColor.stripColor(clicked.getItemMeta().getDisplayName()).equalsIgnoreCase("Nocturnal (LOCKED)")) {
			if (RankManager.getRank(p) >= 2) {
				if (CurrencyManager.getPerkCredits(p) >= 10) {
					CurrencyManager.removePerkCredits(p, 10);
					Nocturnal.addNocturnal(p);
					p.updateInventory();
					p.openInventory(PerkUI.GUI(p));
				} else {
					p.sendMessage(Utils.chat(
							"&9&lPerks &8> &7You do not have sufficent &9Perk Credits &7to unlock this &eperk&7!"));
				}
			} else {
				p.sendMessage(Utils.chat("&9&lPerks &8> &7You must have &d&lPig &7unlocked to purchase this perk!"));
			}
		} else if (ChatColor.stripColor(clicked.getItemMeta().getDisplayName())
				.equalsIgnoreCase("Nocturnal (Disabled)")) {
			Nocturnal.enableNocturnal(p);
			p.updateInventory();
			p.openInventory(PerkUI.GUI(p));
		} else if (ChatColor.stripColor(clicked.getItemMeta().getDisplayName())
				.equalsIgnoreCase("Nocturnal (Enabled)")) {
			Nocturnal.disableNocturnal(p);
			p.updateInventory();
			p.openInventory(PerkUI.GUI(p));
		}
	}
}