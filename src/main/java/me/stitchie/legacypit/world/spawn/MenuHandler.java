package me.stitchie.legacypit.world.spawn;

import me.stitchie.legacypit.menu.*;
import me.stitchie.legacypit.player.PlayerCache;
import me.stitchie.legacypit.player.data.PlayerData;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.InventoryHolder;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MenuHandler implements Listener {

	private final Map<Player, PlayerCache> playerData = PlayerData.INSTANCE.getPlayerData();
	private final HashMap<UUID, NPCType> npcUUIDs = NPCSpawner.INSTANCE.getNPCUUIDs();

	public MenuHandler() {
	}

	// TODO: Make villager tnt proof

	@EventHandler(ignoreCancelled = true)
	private void onVillagerClick(final PlayerInteractEntityEvent event) {
		if(event.getRightClicked().getType() == EntityType.VILLAGER) {
			event.setCancelled(true);
			openMenu(npcUUIDs.get(event.getRightClicked().getUniqueId()), event.getPlayer());
		}
	}

	@EventHandler(ignoreCancelled = true)
	private void onEnchantTableClick(final PlayerInteractEvent event) {
		if(event.getClickedBlock().getType() == Material.ENCHANTMENT_TABLE) {
			event.setCancelled(true);
			final Player player = event.getPlayer();
			player.openInventory(new MysticWellMenu().getInventory());
			player.playSound(player.getLocation(), Sound.ZOMBIE_INFECT, 10, 0.6f);
		}
	}

	@EventHandler(ignoreCancelled = true)
	private void onClick(final InventoryClickEvent event) {
		final InventoryHolder holder = event.getInventory().getHolder();
		if(! (holder instanceof PlayerMenu)) return;
		event.setCancelled(((PlayerMenu) holder).onClick((Player) event.getWhoClicked(), event.getRawSlot(),
				event.getClick(), event.getCurrentItem()));
	}

	@EventHandler
	private void onOpen(final InventoryOpenEvent event) {
		final InventoryHolder holder = event.getInventory().getHolder();
		if(holder instanceof PlayerMenu) ((PlayerMenu) holder).onOpen((Player) event.getPlayer());
	}

	@EventHandler
	private void onClose(final InventoryCloseEvent event) {
		final InventoryHolder holder = event.getInventory().getHolder();
		if(holder instanceof PlayerMenu) ((PlayerMenu) holder).onClose((Player) event.getPlayer());
	}

	private void openMenu(final NPCType npcType, final Player player) {
		switch (npcType) {
			case ITEMSHOP:
				player.openInventory(new ItemShopMenu(playerData).getInventory());
				break;
			case PRESTIGE:
				player.openInventory(new PrestigeMenu().getInventory());
				break;
			case CONTRACTS:
				player.sendMessage("Contracts NPC");
				break;
			case UPGRADESHOP:
				player.openInventory(new UpgradesMenu(playerData, upgradeManager).getInventory());
				break;
			default:
				player.sendMessage(ChatColor.RED + "Illegal NPC. Please report to staff.");
		}
	}
}
