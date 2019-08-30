package com.vervedev.saplingheads.managers;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;

import com.vervedev.saplingheads.Main;

public class CraftingManager implements Listener {

	private Main plugin;

	public CraftingManager(Main plugin) {
		this.plugin = plugin;

		Bukkit.getPluginManager().registerEvents(this, plugin);
		addRecipes();
	}

	public void addRecipes() {
		addChickenRecipe();
		addPigRecipe();
		addSheepRecipe();
		addCowRecipe();
		addZombieRecipe();
		addSkeletonRecipe();
		addPigZombieRecipe();
		addSlimeRecipe();
		addCreeperRecipe();
		addPandaRecipe();
	}

	@EventHandler
	public void onCraftEvent(PrepareItemCraftEvent e) {
		
		if (e.getRecipe() == null) {
			return;
		}
		
		if (e.getRecipe().getResult().hasItemMeta() != true) {
			return;
		}
		if (e.getRecipe().getResult().getItemMeta() == null) {
			return;
		}
		
		if (e.getRecipe().getResult() == null) {
			return;
		}
		
		
		
		if (ChatColor.stripColor(e.getRecipe().getResult().getItemMeta().getDisplayName()).contains("Spawner")) {
			ItemStack firstSlot = (ItemStack) e.getInventory().getMatrix()[0];
			ItemStack secondSlot = (ItemStack) e.getInventory().getMatrix()[1];
			ItemStack thirdSlot = (ItemStack) e.getInventory().getMatrix()[2];
			ItemStack fourthSlot = (ItemStack) e.getInventory().getMatrix()[3];
			ItemStack fifthSlot = (ItemStack) e.getInventory().getMatrix()[4];
			ItemStack sixthSlot = (ItemStack) e.getInventory().getMatrix()[5];
			ItemStack seventhSlot = (ItemStack) e.getInventory().getMatrix()[6];
			ItemStack eightSlot = (ItemStack) e.getInventory().getMatrix()[7];
			ItemStack nineSlot = (ItemStack) e.getInventory().getMatrix()[8];

			if (firstSlot.getAmount() != SkullManager.chickenSkull(64).getAmount()
					|| secondSlot.getAmount() != SkullManager.chickenSkull(64).getAmount()
					|| thirdSlot.getAmount() != SkullManager.chickenSkull(64).getAmount()
					|| fourthSlot.getAmount() != SkullManager.chickenSkull(64).getAmount()
					|| fifthSlot.getAmount() != SpawnerManager.spawnerType("blank").getAmount()
					|| sixthSlot.getAmount() != SkullManager.chickenSkull(64).getAmount()
					|| seventhSlot.getAmount() != SkullManager.chickenSkull(64).getAmount()
					|| eightSlot.getAmount() != SkullManager.chickenSkull(64).getAmount()
					|| nineSlot.getAmount() != SkullManager.chickenSkull(64).getAmount()) {
				e.getInventory().setResult(null);
			}
		} else {
			return;
		}
	}

	@EventHandler
	public void onCraftEvent(CraftItemEvent e) {
		if (ChatColor.stripColor(e.getRecipe().getResult().getItemMeta().getDisplayName()).contains("Spawner")) {
			ItemStack firstSlot = (ItemStack) e.getInventory().getMatrix()[0];
			ItemStack secondSlot = (ItemStack) e.getInventory().getMatrix()[1];
			ItemStack thirdSlot = (ItemStack) e.getInventory().getMatrix()[2];
			ItemStack fourthSlot = (ItemStack) e.getInventory().getMatrix()[3];
			ItemStack fifthSlot = (ItemStack) e.getInventory().getMatrix()[4];
			ItemStack sixthSlot = (ItemStack) e.getInventory().getMatrix()[5];
			ItemStack seventhSlot = (ItemStack) e.getInventory().getMatrix()[6];
			ItemStack eightSlot = (ItemStack) e.getInventory().getMatrix()[7];
			ItemStack nineSlot = (ItemStack) e.getInventory().getMatrix()[8];

			if (firstSlot.getAmount() != SkullManager.chickenSkull(64).getAmount()
					|| secondSlot.getAmount() != SkullManager.chickenSkull(64).getAmount()
					|| thirdSlot.getAmount() != SkullManager.chickenSkull(64).getAmount()
					|| fourthSlot.getAmount() != SkullManager.chickenSkull(64).getAmount()
					|| fifthSlot.getAmount() != SpawnerManager.spawnerType("blank").getAmount()
					|| sixthSlot.getAmount() != SkullManager.chickenSkull(64).getAmount()
					|| seventhSlot.getAmount() != SkullManager.chickenSkull(64).getAmount()
					|| eightSlot.getAmount() != SkullManager.chickenSkull(64).getAmount()
					|| nineSlot.getAmount() != SkullManager.chickenSkull(64).getAmount()) {
				e.getInventory().setResult(null);
			} else {
				if (ChatColor.stripColor(e.getRecipe().getResult().getItemMeta().getDisplayName())
						.contains("Spawner")) {
					CraftingInventory bench = e.getInventory();
					bench.setMatrix(new ItemStack[] { new ItemStack(Material.AIR), new ItemStack(Material.AIR),
							new ItemStack(Material.AIR), new ItemStack(Material.AIR), new ItemStack(Material.AIR),
							new ItemStack(Material.AIR), new ItemStack(Material.AIR), new ItemStack(Material.AIR),
							new ItemStack(Material.AIR) });
					e.getWhoClicked().setItemOnCursor(e.getRecipe().getResult());
				}
			}
		}
	}

	@SuppressWarnings("deprecation")
	public void addChickenRecipe() {

		ItemStack chickenSpawnerItem = SpawnerManager.spawnerType("chicken");

		ShapedRecipe chickenSpawner = new ShapedRecipe(chickenSpawnerItem);

		chickenSpawner.shape("HHH", "HSH", "HHH");

		chickenSpawner.setIngredient('H', new RecipeChoice.ExactChoice(SkullManager.chickenSkull(64)));
		chickenSpawner.setIngredient('S', new RecipeChoice.ExactChoice(SpawnerManager.spawnerType("blank")));

		plugin.getServer().addRecipe(chickenSpawner);
	}

	public void addPigRecipe() {

		ItemStack chickenSpawnerItem = SpawnerManager.spawnerType("pig");

		ShapedRecipe chickenSpawner = new ShapedRecipe(chickenSpawnerItem);

		chickenSpawner.shape("HHH", "HSH", "HHH");

		chickenSpawner.setIngredient('H', new RecipeChoice.ExactChoice(SkullManager.pigSkull(64)));
		chickenSpawner.setIngredient('S', new RecipeChoice.ExactChoice(SpawnerManager.spawnerType("blank")));

		plugin.getServer().addRecipe(chickenSpawner);
	}

	public void addSheepRecipe() {

		ItemStack chickenSpawnerItem = SpawnerManager.spawnerType("sheep");

		ShapedRecipe chickenSpawner = new ShapedRecipe(chickenSpawnerItem);

		chickenSpawner.shape("HHH", "HSH", "HHH");

		chickenSpawner.setIngredient('H', new RecipeChoice.ExactChoice(SkullManager.sheepSkull(64)));
		chickenSpawner.setIngredient('S', new RecipeChoice.ExactChoice(SpawnerManager.spawnerType("blank")));

		plugin.getServer().addRecipe(chickenSpawner);
	}

	public void addCowRecipe() {

		ItemStack chickenSpawnerItem = SpawnerManager.spawnerType("cow");

		ShapedRecipe chickenSpawner = new ShapedRecipe(chickenSpawnerItem);

		chickenSpawner.shape("HHH", "HSH", "HHH");

		chickenSpawner.setIngredient('H', new RecipeChoice.ExactChoice(SkullManager.cowSkull(64)));
		chickenSpawner.setIngredient('S', new RecipeChoice.ExactChoice(SpawnerManager.spawnerType("blank")));

		plugin.getServer().addRecipe(chickenSpawner);
	}

	public void addZombieRecipe() {

		ItemStack chickenSpawnerItem = SpawnerManager.spawnerType("zombie");

		ShapedRecipe chickenSpawner = new ShapedRecipe(chickenSpawnerItem);

		chickenSpawner.shape("HHH", "HSH", "HHH");

		chickenSpawner.setIngredient('H', new RecipeChoice.ExactChoice(SkullManager.zombieSkull(64)));
		chickenSpawner.setIngredient('S', new RecipeChoice.ExactChoice(SpawnerManager.spawnerType("blank")));

		plugin.getServer().addRecipe(chickenSpawner);
	}

	public void addSkeletonRecipe() {

		ItemStack chickenSpawnerItem = SpawnerManager.spawnerType("skeleton");

		ShapedRecipe chickenSpawner = new ShapedRecipe(chickenSpawnerItem);

		chickenSpawner.shape("HHH", "HSH", "HHH");

		chickenSpawner.setIngredient('H', new RecipeChoice.ExactChoice(SkullManager.skeletonSkull(64)));
		chickenSpawner.setIngredient('S', new RecipeChoice.ExactChoice(SpawnerManager.spawnerType("blank")));

		plugin.getServer().addRecipe(chickenSpawner);
	}

	public void addPigZombieRecipe() {

		ItemStack chickenSpawnerItem = SpawnerManager.spawnerType("pigzombie");

		ShapedRecipe chickenSpawner = new ShapedRecipe(chickenSpawnerItem);

		chickenSpawner.shape("HHH", "HSH", "HHH");

		chickenSpawner.setIngredient('H', new RecipeChoice.ExactChoice(SkullManager.pigZombieSkull(64)));
		chickenSpawner.setIngredient('S', new RecipeChoice.ExactChoice(SpawnerManager.spawnerType("blank")));

		plugin.getServer().addRecipe(chickenSpawner);
	}

	public void addSlimeRecipe() {

		ItemStack chickenSpawnerItem = SpawnerManager.spawnerType("slime");

		ShapedRecipe chickenSpawner = new ShapedRecipe(chickenSpawnerItem);

		chickenSpawner.shape("HHH", "HSH", "HHH");

		chickenSpawner.setIngredient('H', new RecipeChoice.ExactChoice(SkullManager.slimeSkull(64)));
		chickenSpawner.setIngredient('S', new RecipeChoice.ExactChoice(SpawnerManager.spawnerType("blank")));

		plugin.getServer().addRecipe(chickenSpawner);
	}

	public void addCreeperRecipe() {

		ItemStack chickenSpawnerItem = SpawnerManager.spawnerType("creeper");

		ShapedRecipe chickenSpawner = new ShapedRecipe(chickenSpawnerItem);

		chickenSpawner.shape("HHH", "HSH", "HHH");

		chickenSpawner.setIngredient('H', new RecipeChoice.ExactChoice(SkullManager.creeperSkull(64)));
		chickenSpawner.setIngredient('S', new RecipeChoice.ExactChoice(SpawnerManager.spawnerType("blank")));

		plugin.getServer().addRecipe(chickenSpawner);
	}

	public void addPandaRecipe() {

		ItemStack chickenSpawnerItem = SpawnerManager.spawnerType("panda");

		ShapedRecipe chickenSpawner = new ShapedRecipe(chickenSpawnerItem);

		chickenSpawner.shape("HHH", "HSH", "HHH");

		chickenSpawner.setIngredient('H', new RecipeChoice.ExactChoice(SkullManager.pandaSkull(64)));
		chickenSpawner.setIngredient('S', new RecipeChoice.ExactChoice(SpawnerManager.spawnerType("blank")));

		plugin.getServer().addRecipe(chickenSpawner);
	}
}
