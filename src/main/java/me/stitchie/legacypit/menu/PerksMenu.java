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

import java.util.ArrayList;
import java.util.Map;

public class PerksMenu implements PlayerMenu {

	private final Map<Player, PlayerCache> playerData;
	private final UpgradeManager upgradeManager;
	private final int perkslot;

	public PerksMenu(final Map<Player, PlayerCache> playerData, final UpgradeManager upgradeManager, final int perkslot) {
		this.playerData = playerData;
		this.upgradeManager = upgradeManager;
		this.perkslot = perkslot;
	}

	private final Inventory inventory = Bukkit.createInventory(this, 54, "Choose a perk");

	@Override
	public Inventory getInventory() {
		return inventory;
	}

	@Override
	public boolean onClick(final Player player, final int slot, final ClickType type, final ItemStack currentItem) {

		final PlayerCache playerdata = playerData.get(player);
		final ArrayList<Perks> currentperks = playerdata.getCurrentPerks();

		switch (slot) {

			case 10:
			case 11:
			case 12:
			case 13:
			case 14:
			case 15:
			case 16:
			case 19:
			case 20:
			case 21:
			case 22:
			case 23:
			case 24:
			case 25:
			case 28:
			case 29:
			case 30:
				checkPerk(playerdata, player, getPerkfromIndex(slot));
				break;
			case 49:
				player.openInventory(new UpgradesMenu(playerData, upgradeManager).getInventory());
				break;
			case 50:
				if (currentperks.get(perkslot - 1) != Perks.NONE) {
					changePerk(playerdata, Perks.NONE, player);
					player.playSound(player.getLocation(), Sound.ORB_PICKUP, 10, 1);
					player.openInventory(new UpgradesMenu(playerData, upgradeManager).getInventory());
				}
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

		final PlayerCache playerdata = playerData.get(player);
		final ArrayList<Perks> currentperks = playerdata.getCurrentPerks();

		for (final Perks perk : Perks.values()) {
			if (perk != Perks.NONE)
				addPerk(perk, perk.getIndex(), playerdata);
		}

		inventory.setItem(49, new ItemCreator(Material.ARROW, "&aGo Back", "&7To Permanent upgrades").createItem(false, false));

		if (currentperks.get(perkslot - 1) != Perks.NONE) {
			inventory.setItem(50, new ItemCreator(Material.DIAMOND_BLOCK, "&cNo perk", "&7Are you hardcore enough",
					"&7that you don't need any", "&7perk for this slot?", "", "&eClick to remove perk!").createItem(false, false));
		}
	}

	private void addPerk(final Perks perk, final int index, final PlayerCache playerdata) {

		final double gold = playerdata.getGold();
		final int level = playerdata.getLevel();
		final String bracketcolor = PitColor.getPrestigeBracketColor(playerdata.getPrestige(), false);

		final Map<Perks, Boolean> purchasedperks = playerdata.getPurchasedPerks();
		final ArrayList<Perks> currentperks = playerdata.getCurrentPerks();

		final String finalname;

		if (purchasedperks.get(perk)) {
			finalname = "&a" + perk.getName();
		} else if (gold >= perk.getCost()) {
			finalname = "&e" + perk.getName();
		} else {
			finalname = "&c" + perk.getName();
		}

		final ArrayList<String> description = perk.getDescription(perk);

		if (currentperks.contains(perk)) {
			description.add("");
			description.add("&aAlready selected!");
		} else if (purchasedperks.get(perk)) {
			description.add("");
			description.add("&eClick to select!");
		} else {
			description.add("");
			description.add("&7Cost: &6" + perk.getCost() + "g");
			if (level < perk.getMinlevel()) {
				description.add("&7Required level: " + bracketcolor + "[" + PitColor.getLevelColor(perk.getMinlevel(), false) + perk.getMinlevel() + bracketcolor + "]");
				description.add("&cToo low level!");
			} else {
				description.add(gold >= perk.getCost() ? "&eClick to purchase!" : "&cNot enough gold!");
			}
		}
		inventory.setItem(index, ItemCreator.createFromItem(perk.getIcon(), finalname, description, false, currentperks.contains(perk)));
	}

	private void changePerk(final PlayerCache playerdata, final Perks perk, final Player player) {

		final Perks beforeperk = playerdata.getCurrentPerk(perkslot);

		switch (beforeperk) {

			// TODO: remove perk items

		}

		playerdata.changeCurrentPerk(perkslot, perk);
		player.playSound(player.getLocation(), Sound.ORB_PICKUP, 10, 1);

		switch (perk) {

			// TODO: add perk items
		}
	}

	private void checkPerk(final PlayerCache playerdata, final Player player, final Perks perk) {

		final double gold = playerdata.getGold();
		final int level = playerdata.getLevel();

		boolean hashealperk = false;

		final Map<Perks, Boolean> purchasedperks = playerdata.getPurchasedPerks();
		final ArrayList<Perks> currentperks = playerdata.getCurrentPerks();

		for (final Perks healperk : currentperks) {
			if (healperk.isHealperk() && perk.isHealperk()) {
				hashealperk = true;
				break;
			}
		}

		if (hashealperk) {
			player.sendMessage("§cYou can't equip multiple healing perks!");
			player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 10, 1);
		} else if (purchasedperks.get(perk) && !currentperks.contains(perk)) {
			changePerk(playerdata, perk, player);
			player.openInventory(new UpgradesMenu(playerData, upgradeManager).getInventory());
		} else if (purchasedperks.get(perk) && currentperks.contains(perk)) {
			player.sendMessage("§cThis perk is already selected!");
			player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 10, 1);
		} else if (level >= perk.getMinlevel() && gold >= perk.getCost()) {
			player.openInventory(new PerksConfirmationMenu(playerData, upgradeManager, perk, perkslot).getInventory());
		} else {
			player.playSound(player.getLocation(), Sound.VILLAGER_NO, 10, 1);
		}

	}

	private Perks getPerkfromIndex(final int slot) {
		for (final Perks perk : Perks.values()) {
			if (perk.getIndex() == slot) {
				return perk;
			}
		}
		return null;
	}
}
