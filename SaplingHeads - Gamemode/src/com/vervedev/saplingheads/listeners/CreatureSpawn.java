package com.vervedev.saplingheads.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

import com.vervedev.saplingheads.Main;

public class CreatureSpawn implements Listener {

	private Main plugin;

	public CreatureSpawn(Main plugin) {
		this.plugin = plugin;

		Bukkit.getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onCreatureSpawn(CreatureSpawnEvent e) {

		Entity creature = e.getEntity();

		if (e.getSpawnReason() == SpawnReason.NATURAL) {
			if (creature.getType() == EntityType.PHANTOM) {
				e.setCancelled(true);
			} else if (creature.getType() == EntityType.CREEPER) {
				e.setCancelled(true);
			} 
		}
	}
}
