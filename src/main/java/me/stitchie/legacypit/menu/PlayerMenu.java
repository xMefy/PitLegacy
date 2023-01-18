package me.stitchie.legacypit.menu;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public interface PlayerMenu extends InventoryHolder {

	default boolean onClick(final Player player, final int slot, final ClickType type, final ItemStack currentItem) {
		return true;
	}

	default void onOpen(final Player player) {
	}

	default void onClose(final Player player) {
	}
}