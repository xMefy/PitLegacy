package me.stitchie.legacypit.menu;

import me.stitchie.legacypit.util.ItemCreator;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class PrestigeMenu implements PlayerMenu {

	private final Inventory inventory = Bukkit.createInventory(this, 27, "Prestige & Renown");

	@Override
	public Inventory getInventory() {
		return inventory;
	}

	@Override
	public boolean onClick(final Player player, final int slot, final ClickType type, final ItemStack currentItem) {
		return true;
	}

	@Override
	public void onOpen(final Player player) {
		final ItemStack soon = new ItemCreator(Material.SAPLING, "&c&lSOON!").createItem(false, false);
		inventory.setItem(13, soon);
	}

	@Override
	public void onClose(final Player player) {

	}

}
