package me.stitchie.legacypit.menu;

import me.stitchie.legacypit.player.PlayerCache;
import me.stitchie.legacypit.register.UpgradeManager;
import me.stitchie.legacypit.upgrades.Upgrade;
import me.stitchie.legacypit.util.ItemCreator;
import me.stitchie.legacypit.util.PitColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public class UpgradesConfirmationMenu implements PlayerMenu {

	private final Map<Player, PlayerCache> playerData;
	private final UpgradeManager upgradeManager;
	private final PlayerCache playerdata;
	private final Upgrade upgrade;
	private final int upgradetier;

	public UpgradesConfirmationMenu(final Map<Player, PlayerCache> playerData, final UpgradeManager upgradeManager, final PlayerCache playerdata, final Upgrade upgrade) {
		this.playerData = playerData;
		this.upgradeManager = upgradeManager;
		this.playerdata = playerdata;
		this.upgrade = upgrade;
		this.upgradetier = playerdata.getUpgradeTier(upgrade.getUpgrade());
	}

	private final Inventory inventory = Bukkit.createInventory(this, 27, "Are you sure?");

	@Override
	public boolean onClick(final Player player, final int slot, final ClickType type, final ItemStack currentItem) {

		switch (slot) {
			case 11:
				playerdata.increaseUpgrade(upgrade.getUpgrade());
				playerdata.removeGold(upgrade.getCost(upgradetier + 1));
				player.playSound(player.getLocation(), Sound.ORB_PICKUP, 10, 1);
				player.openInventory(new UpgradesMenu(playerData, upgradeManager).getInventory());
				break;
			case 15:
				player.playSound(player.getLocation(), Sound.VILLAGER_NO, 10, 1);
				player.openInventory(new UpgradesMenu(playerData, upgradeManager).getInventory());
				break;
			default: {
			}
		}
		return true;
	}

	@Override
	public Inventory getInventory() {
		return inventory;
	}

	@Override
	public void onOpen(final Player player) {
		inventory.setItem(11, new ItemCreator(Material.STAINED_CLAY, (byte) 13, "&aConfirm", "&7Purchasing: &6" + upgrade.getName() + " " +
				PitColor.getRoman(upgradetier + 1), "&7Cost: &6" + PitColor.formatGold(upgrade.getCost(upgradetier + 1)) + "&6g").createItem(false, false));
		inventory.setItem(15, new ItemCreator(Material.STAINED_CLAY, (byte) 14, "&cCancel", "&7Return to previous menu.").createItem(false, false));
	}
}
