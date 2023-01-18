package me.stitchie.legacypit.menu;

import me.stitchie.legacypit.perks.Perks;
import me.stitchie.legacypit.player.PlayerCache;
import me.stitchie.legacypit.register.UpgradeManager;
import me.stitchie.legacypit.upgrades.Upgrade;
import me.stitchie.legacypit.upgrades.Upgrades;
import me.stitchie.legacypit.util.ItemCreator;
import me.stitchie.legacypit.util.PitColor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Map;

public class UpgradesMenu implements PlayerMenu {

	private final Map<Player, PlayerCache> playerData;
	private final UpgradeManager upgradeManager;
	private final EnumMap<Upgrades, Upgrade> upgradeMap;
	private final Inventory inventory = Bukkit.createInventory(this, 45, "Permanent upgrades");

	public UpgradesMenu(final Map<Player, PlayerCache> playerData, final UpgradeManager upgradeManager) {
		this.playerData = playerData;
		this.upgradeManager = upgradeManager;
		this.upgradeMap = upgradeManager.getUpgradeMap();
	}

	@Override
	public Inventory getInventory() {
		return inventory;
	}

	@Override
	public boolean onClick(final Player player, final int slot, final ClickType type, final ItemStack currentItem) {
		final PlayerCache playerdata = playerData.get(player);
		final int level = playerdata.getLevel();

		switch (slot) {

			case 12:
				if(level >= 10) {
					player.openInventory(new PerksMenu(playerData, upgradeManager, 1).getInventory());
				}else {
					player.playSound(player.getLocation(), Sound.VILLAGER_NO, 10, 1);
				}
				break;
			case 13:
				if(level >= 35) {
					player.openInventory(new PerksMenu(playerData, upgradeManager, 2).getInventory());
				}else {
					player.playSound(player.getLocation(), Sound.VILLAGER_NO, 10, 1);
				}
				break;
			case 14:
				if(level >= 70) {
					player.openInventory(new PerksMenu(playerData, upgradeManager, 3).getInventory());
				}else {
					player.playSound(player.getLocation(), Sound.VILLAGER_NO, 10, 1);
				}
				break;
			case 28:
				upgradeCheck(playerdata, upgradeMap.get(Upgrades.XPBOOST));
				break;
			case 29:
				upgradeCheck(playerdata, upgradeMap.get(Upgrades.GOLDBOOST));
				break;
			case 30:
				upgradeCheck(playerdata, upgradeMap.get(Upgrades.MELEEDAMAGE));
				break;
			case 31:
				upgradeCheck(playerdata, upgradeMap.get(Upgrades.BOWDAMAGE));
				break;
			case 32:
				upgradeCheck(playerdata, upgradeMap.get(Upgrades.DAMAGEREDUCTION));
				break;
			case 33:
				upgradeCheck(playerdata, upgradeMap.get(Upgrades.BUILDBATTLER));
				break;
		}
		return true;
	}

	@Override
	public void onOpen(final Player player) {
		updateItems(player);
	}

	private void updateItems(final Player player) {

		final PlayerCache playerdata = playerData.get(player);
		final int level = playerdata.getLevel();
		final String bracketcolor = playerdata.getPrestigeManager();
		final ArrayList<Perks> currentperks = playerdata.getCurrentPerks();

		for (final Upgrade upgrade : upgradeManager.getUpgradeMap().values()) {
			addUpgrade(upgrade.getIndex(), playerdata, upgrade);
		}

		addSelectedPerks(12, currentperks.get(0), level, currentperks, 1, 10, bracketcolor, "&110");
		addSelectedPerks(13, currentperks.get(1), level, currentperks, 2, 35, bracketcolor, "&235");
		addSelectedPerks(14, currentperks.get(2), level, currentperks, 3, 70, bracketcolor, "&c&l70");

	}

	private void addSelectedPerks(final int index, final Perks perk, final int level,
			final ArrayList<Perks> currentperks, final int perkslot, final int slotminlevel, final String bracketcolor
			, final String minlevelcolor) {

		if(level < slotminlevel) {
			inventory.setItem(index, new ItemCreator(Material.BEDROCK, "&4Perk Slot #" + perkslot, "&7Required level: "
					+ bracketcolor + "[" + minlevelcolor + bracketcolor + "]").createItem(false, false, perkslot));
		}else
			if(currentperks.get(perkslot - 1) == Perks.NONE) {
				inventory.setItem(index, new ItemCreator(Material.DIAMOND_BLOCK, "&aPerk Slot #" + perkslot, "&7Select" +
						" a perk to", "&7fill this slot.", "", "&eClick to choose perk!").createItem(false, false,
						perkslot));
			}else {

				final ArrayList<String> description = new ArrayList<>();
				description.add("&7Selected: &a" + perk.getName());
				description.add("");
				description.addAll(perk.getDescription(perk));
				description.add("");
				description.add("&eClick to choose perk!");

				inventory.setItem(index, ItemCreator.createFromItem(perk.getIcon(), "&ePerk Slot #" + perkslot,
						description, false, false, perkslot));

			}
	}

	private void addUpgrade(final int index, final PlayerCache playerdata, final Upgrade upgrade) {

		final String bracketcolor = playerdata.getPrestigeBracketColor();
		final Map<Upgrades, Integer> playerupgrades = playerdata.getUpgrades();
		final int level = playerdata.getLevel();
		final double gold = playerdata.getGold();

		final int tier = playerupgrades.get(upgrade.getUpgrade());
		final int cost = upgrade.getCost(tier + 1);
		final int minlevel = upgrade.getMinimumLevel(tier + 1);
		final String name = upgrade.getName();
		final String coloredminlevel = PitColor.getLevelColor(minlevel, false) + minlevel;
		final ItemStack icon = upgrade.getIcon();
		final String minlevelmessage = bracketcolor + "[" + coloredminlevel + bracketcolor + "]";
		final ChatColor finalnamecolor;

		final ArrayList<String> description = new ArrayList<>();

		if(tier > 0) {
			description.add(upgrade.getCurrentText(tier));
			description.add("&7Tier: &a" + PitColor.getRoman(tier));
			description.add("");
		}

		description.addAll(upgrade.getDescription());
		description.add("");

		if(level < minlevel) {
			finalnamecolor = ChatColor.RED;
			description.add("&7Required level: " + minlevelmessage);
			description.add("&cLevel too low to upgrade!");
		}else
			if(gold < cost) {
				finalnamecolor = ChatColor.RED;
				description.add("&7Cost: &6" + PitColor.formatGold(cost) + "&6g");
				description.add("&cNot enough gold!");
			}else
				if(gold >= cost && tier == 0) {
					finalnamecolor = ChatColor.YELLOW;
					description.add("&7Cost: &6" + PitColor.formatGold(cost) + "&6g");
					description.add("&eClick to purchase!");
				}else
					if(tier == 5) {
						finalnamecolor = ChatColor.GREEN;
						description.add("&aMax tier unlocked!");
					}else {
						finalnamecolor = ChatColor.YELLOW;
						description.add("&7Cost: &6" + PitColor.formatGold(cost) + "&6g");
						description.add("&eClick to upgrade!");
					}

		inventory.setItem(index, ItemCreator.createFromItem(icon, finalnamecolor + name, description, false, false));
	}

	private void upgradeCheck(final PlayerCache playerdata, final Upgrade upgrade) {
		final Player player = playerdata.getPlayer();
		final double gold = playerdata.getGold();
		final int level = playerdata.getLevel();
		final int upgradelevel = playerdata.getUpgradeTier(upgrade.getUpgrade());
		System.out.println("upgradelevel: " + upgradelevel);
		final int upgradecost = upgrade.getCost(upgradelevel + 1);
		final int upgrademinlevel = upgrade.getMinimumLevel(upgradelevel + 1);
		if(gold >= upgradecost && level >= upgrademinlevel && upgradelevel < 5) {
			System.out.println("firstcheck");
			player.openInventory(new UpgradesConfirmationMenu(playerData, upgradeManager, playerdata, upgrade).getInventory());
		}else
			if(upgradelevel == 5) {
				System.out.println("secondcheck");
				player.playSound(player.getLocation(), Sound.VILLAGER_NO, 10, 1);
				player.sendMessage("§aYou already unlocked the last upgrade!");
			}else {
				System.out.println("thirdcheck");
				player.playSound(player.getLocation(), Sound.VILLAGER_NO, 10, 1);
			}
	}
}
