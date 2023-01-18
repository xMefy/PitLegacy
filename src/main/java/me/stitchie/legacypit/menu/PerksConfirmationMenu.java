package me.stitchie.legacypit.menu;

import me.stitchie.legacypit.perks.Perks;
import me.stitchie.legacypit.player.PlayerCache;
import me.stitchie.legacypit.register.UpgradeManager;
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

public class PerksConfirmationMenu implements PlayerMenu {

	private final Map<Player, PlayerCache> playerData;
	private final UpgradeManager upgradeManager;
	private final Perks perk;
	private final int perkslot;

	public PerksConfirmationMenu(final Map<Player, PlayerCache> playerData, final UpgradeManager upgradeManager, final Perks perk, final int perkslot) {
		this.playerData = playerData;
		this.upgradeManager = upgradeManager;
		this.perk = perk;
		this.perkslot = perkslot;
	}

	private final Inventory inventory = Bukkit.createInventory(this, 27, "Are you sure?");

	@Override
	public boolean onClick(final Player player, final int slot, final ClickType type, final ItemStack currentItem) {

		final PlayerCache playerdata = playerData.get(player);

		switch (slot) {

			case 11:
				playerdata.addPurchasedPerk(perk);
				playerdata.removeGold(perk.getCost());
				changePerk(playerdata, perk, player);
				break;
			case 15:
				player.playSound(player.getLocation(), Sound.VILLAGER_NO, 10, 1);
				player.openInventory(new PerksMenu(playerData, upgradeManager, perkslot).getInventory());
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
		inventory.setItem(11, new ItemCreator(Material.STAINED_CLAY, (byte) 13, "&aConfirm", "&7Purchasing: &6" + perk.getName(), "&7Cost: &6" + PitColor.formatGold(perk.getCost()) + "&6g").createItem(false, false));
		inventory.setItem(15, new ItemCreator(Material.STAINED_CLAY, (byte) 14, "&cCancel", "&7Return to previous menu.").createItem(false, false));
	}

	@Override
	public void onClose(final Player player) {

	}

	private void changePerk(final PlayerCache playerdata, final Perks perk, final Player player) {

		final Perks beforeperk = playerdata.getCurrentPerk(perkslot);

		switch (beforeperk) {

			// TODO: remove perk items

		}

		playerdata.changeCurrentPerk(perkslot, perk);
		player.playSound(player.getLocation(), Sound.ORB_PICKUP, 10, 1);
		player.openInventory(new UpgradesMenu(playerData, upgradeManager).getInventory());

		switch (perk) {

			// TODO: add perk items
		}
	}
}
