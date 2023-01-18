package me.stitchie.legacypit.menu;

import me.stitchie.legacypit.player.PlayerCache;
import me.stitchie.legacypit.util.ItemCreator;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class ItemShopMenu implements PlayerMenu {

	private final Map<Player, PlayerCache> playerData;
	private final Inventory inventory = Bukkit.createInventory(this, 27, "Non-permanent items");

	public ItemShopMenu(final Map<Player, PlayerCache> playerData) {
		this.playerData = playerData;
	}

	@Override
	public Inventory getInventory() {
		return inventory;
	}

	@Override
	public boolean onClick(final Player player, final int slot, final ClickType type, final ItemStack currentItem) {

		// TODO: add price reduction

		switch (slot) {
			case 11:
				final ItemStack diamondSword = new ItemCreator(Material.DIAMOND_SWORD).createItem(true, false);
				attemptItemPurchase(player, diamondSword, 150);
				break;
			case 12:
				final ItemStack obsidian = new ItemCreator(Material.OBSIDIAN).createItem(false, false, 8);
				attemptItemPurchase(player, obsidian, 50);
				break;
			case 14:
				attemptArmorPurchase(player, 500, ArmorType.DIAMOND_CHESTPLATE);
				break;
			case 15:
				attemptArmorPurchase(player, 300, ArmorType.DIAMOND_BOOTS);
				break;
		}

		return true;
	}


	@Override
	public void onOpen(final Player player) {
		updateItems(player);
	}

	@Override
	public void onClose(final Player player) {

	}

	private void updateItems(final Player player) {

		final PlayerCache playerCache = playerData.get(player);
		final double gold = playerCache.getGold();
		final double sA = 1.0; // TODO: Add renown gold price reduction upgrade

		inventory.setItem(11, addGUIItem(Material.DIAMOND_SWORD, "Diamond Sword", 1, 150, sA, gold, "&9+20% damage vs bountied"));
		inventory.setItem(12, addGUIItem(Material.OBSIDIAN, "Obsidian", 8, 50, sA, gold, "&7Remains for 120 seconds"));
		inventory.setItem(14, addGUIItem(Material.DIAMOND_CHESTPLATE, "Diamond Chestplate", 1, 500, sA, gold, "&7Auto-equips on buy!"));
		inventory.setItem(15, addGUIItem(Material.DIAMOND_BOOTS, "Diamond Boots", 1, 300, sA, gold, "&7Auto-equips on buy!"));
	}

	private ItemStack addGUIItem(final Material material, final String name, final int amount, int price, final double priceReduction, final double gold, final String... description) {

		price *= priceReduction;
		final boolean canBuy = gold >= price;
		final String displayName = (canBuy ? "&e" : "&c") + name;

		final ArrayList<String> lore = new ArrayList<>(Arrays.asList(description));
		lore.add("");
		lore.add("&7Cost: &6" + price + "g");
		lore.add(canBuy ? "&eClick to purchase!" : "&cNot enough gold!");

		return new ItemCreator(material, displayName, lore).createItem(false, false, amount);
	}

	private void attemptItemPurchase(final Player player, final ItemStack item, final int price) {

		final PlayerCache playerCache = playerData.get(player);
		final double gold = playerCache.getGold();

		if (gold >= price) {
			playerCache.removeGold(price);
			player.playSound(player.getLocation(), Sound.ORB_PICKUP, 10, 1);
			player.getInventory().addItem(item);
			updateItems(player);
		} else {
			player.playSound(player.getLocation(), Sound.VILLAGER_NO, 10, 1);
		}
	}

	private void attemptArmorPurchase(final Player player, final int price, final ArmorType type) {
		final PlayerCache playerCache = playerData.get(player);
		final double gold = playerCache.getGold();
		if (gold >= price) {
			playerCache.removeGold(price);
			final PlayerInventory inventory = player.getInventory();

			switch (type) {
				case DIAMOND_CHESTPLATE:
					final ItemStack diamondChestPlate = new ItemCreator(Material.DIAMOND_CHESTPLATE).createItem(true, false);
					if (inventory.getChestplate() == null || inventory.getChestplate().getType() == Material.IRON_CHESTPLATE || inventory.getChestplate().getType() == Material.CHAINMAIL_CHESTPLATE) {
						player.playSound(player.getLocation(), Sound.HORSE_ARMOR, 10, 1);
						player.getInventory().setChestplate(diamondChestPlate);
					} else {
						player.playSound(player.getLocation(), Sound.ORB_PICKUP, 10, 1);
						player.getInventory().addItem(diamondChestPlate);
					}
					break;
				case DIAMOND_BOOTS:
					final ItemStack diamondBoots = new ItemCreator(Material.DIAMOND_BOOTS).createItem(true, false);
					if (inventory.getBoots() == null || inventory.getBoots().getType() == Material.IRON_BOOTS || inventory.getBoots().getType() == Material.CHAINMAIL_BOOTS) {
						player.playSound(player.getLocation(), Sound.HORSE_ARMOR, 10, 1);
						player.getInventory().setBoots(diamondBoots);
					} else {
						player.playSound(player.getLocation(), Sound.ORB_PICKUP, 10, 1);
						player.getInventory().addItem(diamondBoots);
					}
					break;
			}
			updateItems(player);
		} else {
			player.playSound(player.getLocation(), Sound.VILLAGER_NO, 10, 1);
		}
	}
}
	
