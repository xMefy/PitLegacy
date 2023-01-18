package me.stitchie.legacypit.menu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class TNTMenu implements PlayerMenu {

	private final Inventory inventory = Bukkit.createInventory(this, 27, "TNT Menu");

	@Override
	public Inventory getInventory() {

		return inventory;
	}

	@Override
	public boolean onClick(final Player player, final int slot, final ClickType type, final ItemStack currentItem) {

		switch (slot) {
			case 13:
				player.getWorld().createExplosion(player.getLocation(), 5f);
				player.closeInventory();
				break;
		}

		return true;
	}

	@Override
	public void onOpen(final Player player) {

		final ItemStack tnt = new ItemStack(Material.TNT, 1);
		final ItemMeta tntMeta = tnt.getItemMeta();
		tntMeta.setDisplayName("§cClick me to explode!");

		final ArrayList<String> tntLore = new ArrayList<>();
		tntLore.add("§6DONT DO IT!!!");

		tntMeta.setLore(tntLore);
		tnt.setItemMeta(tntMeta);

		inventory.setItem(13, tnt);

	}

	@Override
	public void onClose(final Player player) {

	}


}
