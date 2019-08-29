package com.vervedev.saplingheads.listeners;

import java.text.DecimalFormat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.vervedev.saplingheads.Main;
import com.vervedev.saplingheads.managers.PlayerManager;
import com.vervedev.saplingheads.managers.RankManager;
import com.vervedev.saplingheads.managers.SkullManager;
import com.vervedev.saplingheads.managers.SpawnerManager;
import com.vervedev.saplingheads.ui.SkullVaultUI;
import com.vervedev.saplingheads.utils.Utils;

import net.citizensnpcs.api.event.NPCLeftClickEvent;
import net.citizensnpcs.api.event.NPCRightClickEvent;

public class NPCClick implements Listener {

	private Main plugin;

	public NPCClick(Main plugin) {
		this.plugin = plugin;

		Bukkit.getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onLeftClick(NPCLeftClickEvent e) {

		Player p = e.getClicker();

		if (e.getNPC().getName().contains(ChatColor.stripColor("Skull Vault"))) {
			p.openInventory(SkullVaultUI.GUI(p));
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onRightClick(NPCRightClickEvent e) {

		Player p = e.getClicker();
		DecimalFormat formatter = new DecimalFormat("#,###");

		if (e.getNPC().getName().contains(ChatColor.stripColor("Skull Vault"))) {
			if (!p.isSneaking()) {
				if (p.getItemInHand() != null) {
					if (p.getItemInHand().hasItemMeta()) {
						if (ChatColor.stripColor(p.getItemInHand().getItemMeta().getDisplayName())
								.equalsIgnoreCase(Utils.chat("Chicken Skull"))) {
							p.getInventory().getItemInHand()
									.setAmount(p.getInventory().getItemInHand().getAmount() - 1);
							p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.vault.chicken")
									.replaceAll("<amount>", Integer.toString(1))));
							SkullManager.addChickenSkull(p, 1);
						} else if (ChatColor.stripColor(p.getItemInHand().getItemMeta().getDisplayName())
								.equalsIgnoreCase(Utils.chat("Pig Skull"))) {
							p.getInventory().getItemInHand()
									.setAmount(p.getInventory().getItemInHand().getAmount() - 1);
							p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.vault.pig")
									.replaceAll("<amount>", Integer.toString(1))));
							SkullManager.addPigSkull(p, 1);
						} else if (ChatColor.stripColor(p.getItemInHand().getItemMeta().getDisplayName())
								.equalsIgnoreCase(Utils.chat("Sheep Skull"))) {
							p.getInventory().getItemInHand()
									.setAmount(p.getInventory().getItemInHand().getAmount() - 1);
							p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.vault.sheep")
									.replaceAll("<amount>", Integer.toString(1))));
							SkullManager.addSheepSkull(p, 1);
						} else if (ChatColor.stripColor(p.getItemInHand().getItemMeta().getDisplayName())
								.equalsIgnoreCase(Utils.chat("Cow Skull"))) {
							p.getInventory().getItemInHand()
									.setAmount(p.getInventory().getItemInHand().getAmount() - 1);
							p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.vault.cow")
									.replaceAll("<amount>", Integer.toString(1))));
							SkullManager.addCowSkull(p, 1);
						} else if (ChatColor.stripColor(p.getItemInHand().getItemMeta().getDisplayName())
								.equalsIgnoreCase(Utils.chat("Zombie Skull"))) {
							p.getInventory().getItemInHand()
									.setAmount(p.getInventory().getItemInHand().getAmount() - 1);
							p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.vault.zombie")
									.replaceAll("<amount>", Integer.toString(1))));
							SkullManager.addZombieSkull(p, 1);
						} else if (ChatColor.stripColor(p.getItemInHand().getItemMeta().getDisplayName())
								.equalsIgnoreCase(Utils.chat("Skeleton Skull"))) {
							p.getInventory().getItemInHand()
									.setAmount(p.getInventory().getItemInHand().getAmount() - 1);
							p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.vault.skeleton")
									.replaceAll("<amount>", Integer.toString(1))));
							SkullManager.addSkeletonSkull(p, 1);
						} else if (ChatColor.stripColor(p.getItemInHand().getItemMeta().getDisplayName())
								.equalsIgnoreCase(Utils.chat("Zombie Pigman Skull"))) {
							p.getInventory().getItemInHand()
									.setAmount(p.getInventory().getItemInHand().getAmount() - 1);
							p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.vault.zpigman")
									.replaceAll("<amount>", Integer.toString(1))));
							SkullManager.addPigZombieSkull(p, 1);
						} else if (ChatColor.stripColor(p.getItemInHand().getItemMeta().getDisplayName())
								.equalsIgnoreCase(Utils.chat("Slime Skull"))) {
							p.getInventory().getItemInHand()
									.setAmount(p.getInventory().getItemInHand().getAmount() - 1);
							p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.vault.slime")
									.replaceAll("<amount>", Integer.toString(1))));
							SkullManager.addSlimeSkull(p, 1);
						} else if (ChatColor.stripColor(p.getItemInHand().getItemMeta().getDisplayName())
								.equalsIgnoreCase(Utils.chat("Creeper Skull"))) {
							p.getInventory().getItemInHand()
									.setAmount(p.getInventory().getItemInHand().getAmount() - 1);
							p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.vault.creeper")
									.replaceAll("<amount>", Integer.toString(1))));
							SkullManager.addCreeperSkull(p, 1);
						} else if (ChatColor.stripColor(p.getItemInHand().getItemMeta().getDisplayName())
								.equalsIgnoreCase(Utils.chat("Panda Skull"))) {
							p.getInventory().getItemInHand()
									.setAmount(p.getInventory().getItemInHand().getAmount() - 1);
							p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.vault.panda")
									.replaceAll("<amount>", Integer.toString(1))));
							SkullManager.addPandaSkull(p, 1);
						} else {
							p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.vault.no_skull_in_hand")));
						}
					} else {
						p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.vault.no_skull_in_hand")));
					}
				} else {
					p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.vault.no_skull_in_hand")));
				}
			} else {
				if (p.getItemInHand() != null) {
					if (p.getItemInHand().hasItemMeta()) {
						int maxAmount = p.getItemInHand().getAmount();
						if (ChatColor.stripColor(p.getItemInHand().getItemMeta().getDisplayName())
								.equalsIgnoreCase(Utils.chat("Chicken Skull"))) {
							p.getInventory().getItemInHand()
									.setAmount(p.getInventory().getItemInHand().getAmount() - maxAmount);
							p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.sneakvault.chicken")
									.replaceAll("<amount>", Integer.toString(maxAmount))));
							SkullManager.addChickenSkull(p, maxAmount);
						} else if (ChatColor.stripColor(p.getItemInHand().getItemMeta().getDisplayName())
								.equalsIgnoreCase(Utils.chat("Pig Skull"))) {
							p.getInventory().getItemInHand()
									.setAmount(p.getInventory().getItemInHand().getAmount() - maxAmount);
							p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.sneakvault.pig")
									.replaceAll("<amount>", Integer.toString(maxAmount))));
							SkullManager.addPigSkull(p, maxAmount);
						} else if (ChatColor.stripColor(p.getItemInHand().getItemMeta().getDisplayName())
								.equalsIgnoreCase(Utils.chat("Sheep Skull"))) {
							p.getInventory().getItemInHand()
									.setAmount(p.getInventory().getItemInHand().getAmount() - maxAmount);
							p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.sneakvault.sheep")
									.replaceAll("<amount>", Integer.toString(maxAmount))));
							SkullManager.addSheepSkull(p, maxAmount);
						} else if (ChatColor.stripColor(p.getItemInHand().getItemMeta().getDisplayName())
								.equalsIgnoreCase(Utils.chat("Cow Skull"))) {
							p.getInventory().getItemInHand()
									.setAmount(p.getInventory().getItemInHand().getAmount() - maxAmount);
							p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.sneakvault.cow")
									.replaceAll("<amount>", Integer.toString(maxAmount))));
							SkullManager.addCowSkull(p, maxAmount);
						} else if (ChatColor.stripColor(p.getItemInHand().getItemMeta().getDisplayName())
								.equalsIgnoreCase(Utils.chat("Zombie Skull"))) {
							p.getInventory().getItemInHand()
									.setAmount(p.getInventory().getItemInHand().getAmount() - maxAmount);
							p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.sneakvault.zombie")
									.replaceAll("<amount>", Integer.toString(maxAmount))));
							SkullManager.addZombieSkull(p, maxAmount);
						} else if (ChatColor.stripColor(p.getItemInHand().getItemMeta().getDisplayName())
								.equalsIgnoreCase(Utils.chat("Skeleton Skull"))) {
							p.getInventory().getItemInHand()
									.setAmount(p.getInventory().getItemInHand().getAmount() - maxAmount);
							p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.sneakvault.skeleton")
									.replaceAll("<amount>", Integer.toString(maxAmount))));
							SkullManager.addSkeletonSkull(p, maxAmount);
						} else if (ChatColor.stripColor(p.getItemInHand().getItemMeta().getDisplayName())
								.equalsIgnoreCase(Utils.chat("Zombie Pigman Skull"))) {
							p.getInventory().getItemInHand()
									.setAmount(p.getInventory().getItemInHand().getAmount() - maxAmount);
							p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.sneakvault.zpigman")
									.replaceAll("<amount>", Integer.toString(maxAmount))));
							SkullManager.addPigZombieSkull(p, maxAmount);
						} else if (ChatColor.stripColor(p.getItemInHand().getItemMeta().getDisplayName())
								.equalsIgnoreCase(Utils.chat("Slime Skull"))) {
							p.getInventory().getItemInHand()
									.setAmount(p.getInventory().getItemInHand().getAmount() - maxAmount);
							p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.sneakvault.slime")
									.replaceAll("<amount>", Integer.toString(maxAmount))));
							SkullManager.addSlimeSkull(p, maxAmount);
						} else if (ChatColor.stripColor(p.getItemInHand().getItemMeta().getDisplayName())
								.equalsIgnoreCase(Utils.chat("Creeper Skull"))) {
							p.getInventory().getItemInHand()
									.setAmount(p.getInventory().getItemInHand().getAmount() - maxAmount);
							p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.sneakvault.creeper")
									.replaceAll("<amount>", Integer.toString(maxAmount))));
							SkullManager.addCreeperSkull(p, maxAmount);
						} else if (ChatColor.stripColor(p.getItemInHand().getItemMeta().getDisplayName())
								.equalsIgnoreCase(Utils.chat("Panda Skull"))) {
							p.getInventory().getItemInHand()
									.setAmount(p.getInventory().getItemInHand().getAmount() - maxAmount);
							p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.sneakvault.panda")
									.replaceAll("<amount>", Integer.toString(maxAmount))));
							SkullManager.addPandaSkull(p, maxAmount);
						} else {
							p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.vault.no_skull_in_hand")));
						}
					} else {
						p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.vault.no_skull_in_hand")));
					}
				} else {
					p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.vault.no_skull_in_hand")));
				}
			}
		} else if (e.getNPC().getName().equalsIgnoreCase(ChatColor.stripColor("Chicken"))) {
			if (RankManager.getRank(p) >= 1) {
				if (Main.econ.getBalance(p) >= SpawnerManager.getChickenSpawnerPrice()) {
					if (PlayerManager.playerInventoryFull(p) == false
							|| PlayerManager.spawnerSlotAvailable(p, "Chicken Spawner")) {
						Main.econ.withdrawPlayer(p, SpawnerManager.getChickenSpawnerPrice());
						p.getInventory().addItem(SpawnerManager.spawnerType("Chicken"));
						p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.spawner_npc.chicken")
								.replaceAll("<amount>", formatter.format(SpawnerManager.getChickenSpawnerPrice()))));
						return;
					} else {
						p.sendMessage(Utils.chat(
								"&a&lSaplingMC &8> &7You currently have a &efull inventory&7. Please try again later!"));
					}
				} else {
					p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.spawner_npc.no_funds")));
				}
			} else {
				p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.spawner_npc.low_rank")
						.replaceAll("<needed_rank>", "&f&lChicken&r")));
			}
		} else if (e.getNPC().getName().equalsIgnoreCase(ChatColor.stripColor("Pig"))) {
			if (RankManager.getRank(p) >= 2) {
				if (Main.econ.getBalance(p) >= SpawnerManager.getChickenSpawnerPrice()) {
					if (PlayerManager.playerInventoryFull(p) == false
							|| PlayerManager.spawnerSlotAvailable(p, "Pig Spawner")) {
						Main.econ.withdrawPlayer(p, SpawnerManager.getPigSpawnerPrice());
						p.getInventory().addItem(SpawnerManager.spawnerType("Pig"));
						p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.spawner_npc.pig")
								.replaceAll("<amount>", formatter.format(SpawnerManager.getPigSpawnerPrice()))));
						return;
					} else {
						p.sendMessage(Utils.chat(
								"&a&lSaplingMC &8> &7You currently have a &efull inventory&7. Please try again later!"));
					}
				} else {
					p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.spawner_npc.no_funds")));
				}
			} else {
				p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.spawner_npc.low_rank")
						.replaceAll("<needed_rank>", "&d&lPig&r")));
			}
		} else if (e.getNPC().getName().equalsIgnoreCase(ChatColor.stripColor("Sheep"))) {
			if (RankManager.getRank(p) >= 3) {
				if (Main.econ.getBalance(p) >= SpawnerManager.getSheepSpawnerPrice()) {
					if (PlayerManager.playerInventoryFull(p) == false
							|| PlayerManager.spawnerSlotAvailable(p, "Sheep Spawner")) {
						Main.econ.withdrawPlayer(p, SpawnerManager.getSheepSpawnerPrice());
						p.getInventory().addItem(SpawnerManager.spawnerType("Sheep"));
						p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.spawner_npc.sheep")
								.replaceAll("<amount>", formatter.format(SpawnerManager.getSheepSpawnerPrice()))));
						return;
					} else {
						p.sendMessage(Utils.chat(
								"&a&lSaplingMC &8> &7You currently have a &efull inventory&7. Please try again later!"));
					}
				} else {
					p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.spawner_npc.no_funds")));
				}
			} else {
				p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.spawner_npc.low_rank")
						.replaceAll("<needed_rank>", "&7&lSheep&r")));
			}
		} else if (e.getNPC().getName().equalsIgnoreCase(ChatColor.stripColor("Cow"))) {
			if (RankManager.getRank(p) >= 4) {
				if (Main.econ.getBalance(p) >= SpawnerManager.getCowSpawnerPrice()) {
					if (PlayerManager.playerInventoryFull(p) == false
							|| PlayerManager.spawnerSlotAvailable(p, "Cow Spawner")) {
						Main.econ.withdrawPlayer(p, SpawnerManager.getCowSpawnerPrice());
						p.getInventory().addItem(SpawnerManager.spawnerType("Cow"));
						p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.spawner_npc.cow")
								.replaceAll("<amount>", formatter.format(SpawnerManager.getCowSpawnerPrice()))));
						return;
					} else {
						p.sendMessage(Utils.chat(
								"&a&lSaplingMC &8> &7You currently have a &efull inventory&7. Please try again later!"));
					}
				} else {
					p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.spawner_npc.no_funds")));
				}
			} else {
				p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.spawner_npc.low_rank")
						.replaceAll("<needed_rank>", "&8&lCow&r")));
			}
		} else if (e.getNPC().getName().equalsIgnoreCase(ChatColor.stripColor("Zombie"))) {
			if (RankManager.getRank(p) >= 5) {
				if (Main.econ.getBalance(p) >= SpawnerManager.getZombieSpawnerPrice()) {
					if (PlayerManager.playerInventoryFull(p) == false
							|| PlayerManager.spawnerSlotAvailable(p, "Zombie Spawner")) {
						Main.econ.withdrawPlayer(p, SpawnerManager.getZombieSpawnerPrice());
						p.getInventory().addItem(SpawnerManager.spawnerType("Zombie"));
						p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.spawner_npc.zombie")
								.replaceAll("<amount>", formatter.format(SpawnerManager.getZombieSpawnerPrice()))));
						return;
					} else {
						p.sendMessage(Utils.chat(
								"&a&lSaplingMC &8> &7You currently have a &efull inventory&7. Please try again later!"));
					}
				} else {
					p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.spawner_npc.no_funds")));
				}
			} else {
				p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.spawner_npc.low_rank")
						.replaceAll("<needed_rank>", "&2&lZombie&r")));
			}
		} else if (e.getNPC().getName().equalsIgnoreCase(ChatColor.stripColor("Skeleton"))) {
			if (RankManager.getRank(p) >= 6) {
				if (Main.econ.getBalance(p) >= SpawnerManager.getSkeletonSpawnerPrice()) {
					if (PlayerManager.playerInventoryFull(p) == false
							|| PlayerManager.spawnerSlotAvailable(p, "Skeleton Spawner")) {
						Main.econ.withdrawPlayer(p, SpawnerManager.getSkeletonSpawnerPrice());
						p.getInventory().addItem(SpawnerManager.spawnerType("Skeleton"));
						p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.spawner_npc.skeleton")
								.replaceAll("<amount>", formatter.format(SpawnerManager.getSkeletonSpawnerPrice()))));
						return;
					} else {
						p.sendMessage(Utils.chat(
								"&a&lSaplingMC &8> &7You currently have a &efull inventory&7. Please try again later!"));
					}
				} else {
					p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.spawner_npc.no_funds")));
				}
			} else {
				p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.spawner_npc.low_rank")
						.replaceAll("<needed_rank>", "&3&lSkeleton&r")));
			}
		} else if (e.getNPC().getName().equalsIgnoreCase(ChatColor.stripColor("PigZombie"))) {
			if (RankManager.getRank(p) >= 7) {
				if (Main.econ.getBalance(p) >= SpawnerManager.getPigZombieSpawnerPrice()) {
					if (PlayerManager.playerInventoryFull(p) == false
							|| PlayerManager.spawnerSlotAvailable(p, "Zombie Pigman Spawner")) {
						Main.econ.withdrawPlayer(p, SpawnerManager.getPigZombieSpawnerPrice());
						p.getInventory().addItem(SpawnerManager.spawnerType("PigZombie"));
						p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.spawner_npc.pigzombie")
								.replaceAll("<amount>", formatter.format(SpawnerManager.getPigZombieSpawnerPrice()))));
						return;
					} else {
						p.sendMessage(Utils.chat(
								"&a&lSaplingMC &8> &7You currently have a &efull inventory&7. Please try again later!"));
					}
				} else {
					p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.spawner_npc.no_funds")));
				}
			} else {
				p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.spawner_npc.low_rank")
						.replaceAll("<needed_rank>", "&c&lZombie Pigman&r")));
			}
		} else if (e.getNPC().getName().equalsIgnoreCase(ChatColor.stripColor("Slime"))) {
			if (RankManager.getRank(p) >= 8) {
				if (Main.econ.getBalance(p) >= SpawnerManager.getSlimeSpawnerPrice()) {
					if (PlayerManager.playerInventoryFull(p) == false
							|| PlayerManager.spawnerSlotAvailable(p, "Slime Spawner")) {
						Main.econ.withdrawPlayer(p, SpawnerManager.getSlimeSpawnerPrice());
						p.getInventory().addItem(SpawnerManager.spawnerType("Slime"));
						p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.spawner_npc.slime")
								.replaceAll("<amount>", formatter.format(SpawnerManager.getSlimeSpawnerPrice()))));
						return;
					} else {
						p.sendMessage(Utils.chat(
								"&a&lSaplingMC &8> &7You currently have a &efull inventory&7. Please try again later!"));
					}
				} else {
					p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.spawner_npc.no_funds")));
				}
			} else {
				p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.spawner_npc.low_rank")
						.replaceAll("<needed_rank>", "&a&lSlime&r")));
			}
		} else if (e.getNPC().getName().equalsIgnoreCase(ChatColor.stripColor("Creeper"))) {
			if (RankManager.getRank(p) >= 9) {
				if (Main.econ.getBalance(p) >= SpawnerManager.getCreeperSpawnerPrice()) {
					if (PlayerManager.playerInventoryFull(p) == false
							|| PlayerManager.spawnerSlotAvailable(p, "Creeper Spawner")) {
						Main.econ.withdrawPlayer(p, SpawnerManager.getCreeperSpawnerPrice());
						p.getInventory().addItem(SpawnerManager.spawnerType("Creeper"));
						p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.spawner_npc.creeper")
								.replaceAll("<amount>", formatter.format(SpawnerManager.getCreeperSpawnerPrice()))));
						return;
					} else {
						p.sendMessage(Utils.chat(
								"&a&lSaplingMC &8> &7You currently have a &efull inventory&7. Please try again later!"));
					}
				} else {
					p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.spawner_npc.no_funds")));
				}
			} else {
				p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.spawner_npc.low_rank")
						.replaceAll("<needed_rank>", "&a&lCreeper&r")));
			}
		} else if (e.getNPC().getName().equalsIgnoreCase(ChatColor.stripColor("Panda"))) {
			if (RankManager.getRank(p) >= 10) {
				if (Main.econ.getBalance(p) >= SpawnerManager.getPandaSpawnerPrice()) {
					if (PlayerManager.playerInventoryFull(p) == false
							|| PlayerManager.spawnerSlotAvailable(p, "Panda Spawner")) {
						Main.econ.withdrawPlayer(p, SpawnerManager.getPandaSpawnerPrice());
						p.getInventory().addItem(SpawnerManager.spawnerType("Panda"));
						p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.spawner_npc.panda")
								.replaceAll("<amount>", formatter.format(SpawnerManager.getPandaSpawnerPrice()))));
						return;
					} else {
						p.sendMessage(Utils.chat(
								"&a&lSaplingMC &8> &7You currently have a &efull inventory&7. Please try again later!"));
					}
				} else {
					p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.spawner_npc.no_funds")));
				}
			} else {
				p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.spawner_npc.low_rank")
						.replaceAll("<needed_rank>", "&f&lPan&7&lda&r")));
			}
		}
	}
}
