package com.vervedev.saplingheads.managers.tutorial;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;

import com.vervedev.saplingheads.Main;
import com.vervedev.saplingheads.commands.Spawn;
import com.vervedev.saplingheads.utils.Utils;

public class TutorialManager implements CommandExecutor, Listener {

	public static Main plugin;

	private static HashMap<Player, Integer> destinationNumber = new HashMap<Player, Integer>();

	private static ArrayList<Player> inTutorial = new ArrayList<Player>();

	public TutorialManager(Main plugin) {
		this.plugin = plugin;

		plugin.getCommand("tutorial").setExecutor(this);
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (!(sender instanceof Player)) {
			sender.sendMessage(Utils.chat("&eOnly players may execute this command!"));
			return true;
		}

		Player p = (Player) sender;

		if (args.length == 0) {
			if (p.hasPermission("saplingheads.tutorial")) {
				startTutorial(p);
			} else {
				p.sendMessage(Utils.chat("&cYou do not have permission to execute this command!"));
			}
		} else if (args.length == 1) {
			if (p.hasPermission("saplingheads.settutorial")) {
				if (args[0].equalsIgnoreCase("setdest")) {
					p.sendMessage("");
					p.sendMessage(Utils.chat("&2&lTutorial &8> &7/tutorial &esetdest &6<destinationNumber>"));
					p.sendMessage("");
				} else if (args[0].equalsIgnoreCase("removedest")) {
					p.sendMessage("");
					p.sendMessage(Utils.chat("&2&lTutorial &8> &7/tutorial &eremovedest &6<destinationNumber>"));
					p.sendMessage("");
				} else {
					p.sendMessage("");
					p.sendMessage(Utils.chat("&2&lTutorial &8> &7You have entered an &cinvalid argument&7!"));
					p.sendMessage("");
				}
			} else {
				p.sendMessage(Utils.chat("&fUnknown command. Type \"/help\" for help."));
			}
		} else if (args.length == 2) {
			if (p.hasPermission("saplingheads.settutorial")) {
				int destinationNumber = Integer.parseInt(args[1]);

				if (args[0].equalsIgnoreCase("setdest")) {
					setTutorialDestination(p, destinationNumber);
					return true;
				} else if (args[0].equalsIgnoreCase("removedest")) {
					// remove destination
				} else {
					p.sendMessage("");
					p.sendMessage(Utils.chat("&2&lTutorial &8> &7You have entered an &cinvalid argument&7!"));
					p.sendMessage("");
				}
			} else {
				p.sendMessage(Utils.chat("&fUnknown command. Type \"/help\" for help."));
			}
		}
		return false;
	}

	public static void startTutorial(Player p) {

		p.setWalkSpeed(0);
		p.setFlySpeed(0);
		destinationNumber.put(p, 1);

		p.setGameMode(GameMode.SPECTATOR);

		new BukkitRunnable() {
			@Override
			public void run() {
				if (plugin.getConfig()
						.getConfigurationSection("tutorial.destinations." + destinationNumber.get(p)) != null) {
					Location destination = new Location(
							Bukkit.getWorld(plugin.getConfig()
									.getString("tutorial.destinations." + destinationNumber.get(p) + ".world")),
							plugin.getConfig().getDouble("tutorial.destinations." + destinationNumber.get(p) + ".x"),
							plugin.getConfig().getDouble("tutorial.destinations." + destinationNumber.get(p) + ".y"),
							plugin.getConfig().getDouble("tutorial.destinations." + destinationNumber.get(p) + ".z"));
					destination.setYaw(
							plugin.getConfig().getInt("tutorial.destinations." + destinationNumber.get(p) + ".yaw"));
					destination.setPitch(
							plugin.getConfig().getInt("tutorial.destinations." + destinationNumber.get(p) + ".pitch"));
					String tutorialMessage = plugin.getConfig()
							.getString("tutorial.destinations." + destinationNumber.get(p) + ".message");
					p.teleport(destination);
					int oldDesitnation = destinationNumber.get(p);
					int newDesitnation = oldDesitnation + 1;
					destinationNumber.put(p, newDesitnation);
					p.sendMessage("");
					p.sendMessage(Utils.chat(tutorialMessage));
					p.sendMessage("");
				} else {
					cancel();
					destinationNumber.remove(p);
					p.setWalkSpeed(0.2F);
					p.setFlySpeed(0.2F);
					p.setGameMode(GameMode.SURVIVAL);
					Spawn.sendPlayerToSpawn(p);
				}
			}
		}.runTaskTimer(plugin, 0L, 140L);
	}

	public static void setTutorialDestination(Player p, int destinationNumber) {
		double x = p.getLocation().getX();
		double y = p.getLocation().getY();
		double z = p.getLocation().getZ();
		String world = p.getLocation().getWorld().getName();

		int yaw = (int) p.getLocation().getYaw();
		int pitch = (int) p.getLocation().getPitch();

		if (plugin.getConfig().getConfigurationSection("tutorial.destinations." + destinationNumber) == null) {

			plugin.getConfig().set("tutorial.destinations." + destinationNumber + ".x", x);
			plugin.getConfig().set("tutorial.destinations." + destinationNumber + ".y", y);
			plugin.getConfig().set("tutorial.destinations." + destinationNumber + ".z", z);

			plugin.getConfig().set("tutorial.destinations." + destinationNumber + ".world", world);

			plugin.getConfig().set("tutorial.destinations." + destinationNumber + ".yaw", yaw);
			plugin.getConfig().set("tutorial.destinations." + destinationNumber + ".pitch", pitch);
			plugin.getConfig().set("tutorial.destinations." + destinationNumber + ".message",
					"Set this message in your config.yml under tutorial: destinations: #: message: ");
			plugin.saveConfig();
			p.sendMessage("");
			p.sendMessage(Utils
					.chat("&2&lTutorial &8> &7You have &asuccessfully &7set the spawn point for the destination: &6"
							+ destinationNumber));
			p.sendMessage("");
		} else {
			p.sendMessage("");
			p.sendMessage(
					Utils.chat("&2&lTutorial &8> &7The destination: &6" + destinationNumber + " &calready exists!"));
			p.sendMessage("");
		}
	}
}
