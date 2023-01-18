package me.stitchie.legacypit.player;

import lombok.Getter;
import lombok.Setter;
import me.stitchie.legacypit.perks.Perks;
import me.stitchie.legacypit.upgrades.Upgrades;
import me.stitchie.legacypit.util.Board;
import me.stitchie.legacypit.util.NameTag;
import me.stitchie.legacypit.util.PitUtility;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Locale;
import java.util.Map;

@Getter
@Setter
public class PlayerCache {

	private final Board scoreboard;

	private final Player player;

	private final PrestigeManager prestigeManager;

	private String teamName = "";
	private String teamPrefix = "";
	private String teamSuffix = "";

	private final boolean supporter = false;

	private int level = 1;
	private String levelColor = "§7";
	private String formattedLevel = "§7[1]";
	private int neededXP = 15;
	private String formattedNeededXP = "§b15";

	private double currentXPBoost = 1;

	private float gold = 5000;
	private String formattedGold = "§60.00g";
	private float grindedgold = 0;

	private double currentGoldBoost = 1;

	private int mushrooms = 0;

	private float totalgrindedgold = 0;

	private int killstreak = 0;
	private int multikillstreak = 0;
	private final long lastKill = System.currentTimeMillis();

	private int renown = 0;

	private int bounty = 0;
	private String formattedBounty = "";
	private String formattedScoreboardBounty = "";

	private final int combatTagTimeLeft = 0;

	private final int maxPerks = 3;

	private final Map<Upgrades, Integer> upgrades = new EnumMap<>(Upgrades.class);

	private final Map<Perks, Boolean> purchasedPerks = new EnumMap<>(Perks.class);
	private final ArrayList<Perks> currentPerks = new ArrayList<>();

	public PlayerCache(final int prestige, final int level, final int neededXP, final float gold,
			final float grindedgold, final float totalgrindedgold, final int renown, final Board scoreboard,
			final Player player) {
		this.level = level;
		this.neededXP = neededXP;
		this.gold = gold;
		this.grindedgold = grindedgold;
		this.totalgrindedgold = totalgrindedgold;
		this.renown = renown;
		this.scoreboard = scoreboard;
		this.player = player;
		this.prestigeManager = new PrestigeManager(prestige);
	}

	public PlayerCache(final Board scoreboard, final Player player) {
		this.scoreboard = scoreboard;
		this.player = player;
		this.prestigeManager = new PrestigeManager(0);
		initializeMaps();
	}

	private void initializeMaps() {
		initializeCurrentPerks();
		initializePurchasedPerks();
		initializeUpgrades();
	}

	private void initializeUpgrades() {
		for (final Upgrades upgrade : Upgrades.values()) {
			upgrades.put(upgrade, 0);
		}
	}

	private void initializePurchasedPerks() {
		for (final Perks perk : Perks.values()) {
			if(perk != Perks.NONE) {
				purchasedPerks.put(perk, false);
			}
		}
	}

	private void initializeCurrentPerks() {
		for (int i = 0; i < maxPerks; i++) {
			currentPerks.add(Perks.NONE);
		}
	}

	public void resetUpgrades() {
		this.upgrades.clear();
		initializeUpgrades();
	}

	public void increaseUpgrade(final Upgrades upgrade) {
		this.upgrades.merge(upgrade, 1, Integer::sum);
	}

	public int getUpgradeTier(final Upgrades upgrade) {
		return this.upgrades.get(upgrade);
	}

	public void addPurchasedPerk(final Perks perk) {
		this.purchasedPerks.put(perk, true);
	}

	public void changeCurrentPerk(final int slot, final Perks perk) {
		this.currentPerks.set(slot - 1, perk);
	}

	public void removeCurrentPerk(final int slot) {
		this.currentPerks.set(slot - 1, Perks.NONE);
	}

	public Perks getCurrentPerk(final int slot) {
		return this.currentPerks.get(slot - 1);
	}

	public void prestigePlayer() {
		this.prestigeManager.prestigePlayer();
	}

	public void levelUP(final int oldLevel) {

		final String oldLevelColor = getLevelColor(oldLevel);
		final String newLevelColor = getLevelColor(level);

		this.levelColor = newLevelColor;

		final String oldLevelMessage = prestigeBracketColor + "[" + oldLevelColor + oldLevel + prestigeBracketColor +
				"]";
		final String newLevelMessage = prestigeBracketColor + "[" + newLevelColor + level + prestigeBracketColor + "]";
		final String levelUpMessage = oldLevelMessage + "§7 ➟ " + newLevelMessage;

		PitUtility.sendTitleAndSubTitle(player, "§b§lLEVEL UP!", levelUpMessage);

		player.playSound(player.getLocation(), Sound.LEVEL_UP, 10, 1);

		if(level == 120) {
			formatXP();
		}
	}

	public void addXP(final double amount) {
		if(level < 120) {
			this.neededXP -= amount;
			if(neededXP <= 0) {
				updateLevel();
			}
			formatXP();
			updateLevelBar();
		}
	}

	public void updateLevelBar() {
		player.setExp(getXPforLevel());
		player.setLevel(level);
	}

	public void addGold(final double amount) {
		this.gold += amount;
		this.grindedgold += amount;
		this.totalgrindedgold += amount;
		formatGold();
	}

	public void removeGold(final double amount) {
		this.gold -= amount;
		formatGold();
	}

	public void addCurrentXPBoost(final double multiplier) {
		this.currentXPBoost *= multiplier;
	}

	public void addCurrentGoldBoost(final double multiplier) {
		this.currentGoldBoost *= multiplier;
	}

	public void addBounty(final int bounty) {
		this.bounty += bounty;
		formatBounty();
		formatScoreboardBounty();
		updateNameTag();
	}

	public void setBounty(final int bounty) {
		this.bounty = bounty;
		formatBounty();
		formatScoreboardBounty();
		updateNameTag();
	}

	public void clearBounty() {
		this.bounty = 0;
	}

	public void updateNameTag() {
		final String suffix = bounty > 0 ? formattedBounty : "";
		NameTag.setNameTag(player, formattedLevel, suffix, this);
	}

	private void formatBounty() {
		final DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
		final DecimalFormat df = new DecimalFormat("#,###,###", symbols);
		this.formattedBounty = " §6§l" + df.format(bounty) + "g";
	}

	private void formatScoreboardBounty() {
		final DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
		final DecimalFormat df = new DecimalFormat("#,###,###", symbols);
		this.formattedScoreboardBounty = "§6" + df.format(bounty) + "g";
	}

	private void formatGold() {
		final DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
		final DecimalFormat df = new DecimalFormat("#,###,##0.00", symbols);
		this.formattedGold = "§6" + df.format(gold) + "g";
	}

	private void formatXP() {
		if(level < 120) {
			final DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
			final DecimalFormat df = new DecimalFormat("#,###,###", symbols);
			this.formattedNeededXP = "§b" + df.format(neededXP);
		}else {
			this.formattedNeededXP = "§bMAXED!";
		}
	}

	private void formatLevel() {
		this.formattedLevel = prestigeBracketColor + "[" + getLevelColor() + level + prestigeBracketColor + "] ";
	}

	public void addMushrooms(final int amount) {
		this.mushrooms += amount;
	}

	public void removeMushrooms(final int amount) {
		this.mushrooms += amount;
	}

	public void increaseKillstreak(final int amount) {
		this.killstreak++;
	}

	public void increaseMultiKillstreak(final int amount) {
		this.multikillstreak++;
	}

	public void addRenown(final int amount) {
		this.renown += amount;
	}

	public void removeRenown(final int amount) {
		this.renown -= amount;
	}

	public void setScoreboardPrefix(final int index, final String text) {
		this.scoreboard.setPrefix(index, text);
	}

	public void setScoreboardSuffix(final int index, final String text) {
		this.scoreboard.setSuffix(index, text);
	}

	private void updateLevel() {
		final int oldLevel = level;
		while (neededXP <= 0 && level < 120) {
			level++;
			neededXP += getLevelreq() * getPrestigeXPMultiplier();
		}
		levelUP(oldLevel);
		formatLevel();
		updateNameTag();
	}

	public void setLevel(final int level) {
		this.level = level;
		updateNameTag();
		updateLevel();
	}

	private float getXPforLevel() {
		if(level == 120) {
			return 1.0f;
		}
		return 1 - (float) neededXP / (getLevelreq() * getPrestigeXPMultiplier());
	}

	private String getLevelColor(final int level) {
		if(level <= 9) return "§7";
		if(level <= 19) return "§9";
		if(level <= 29) return "§3";
		if(level <= 39) return "§2";
		if(level <= 49) return "§a";
		if(level <= 59) return "§e";
		if(level <= 69) return "§6§l";
		if(level <= 79) return "§c§l";
		if(level <= 89) return "§4§l";
		if(level <= 99) return "§5§l";
		if(level <= 109) return "§d§l";
		if(level <= 119) return "§f§l";
		if(level == 120) return "§b§l";
		return "§7";
	}

	private int getLevelreq() {
		if(level <= 9) return 15;
		if(level <= 19) return 30;
		if(level <= 29) return 50;
		if(level <= 39) return 75;
		if(level <= 49) return 125;
		if(level <= 59) return 300;
		if(level <= 69) return 600;
		if(level <= 79) return 800;
		if(level <= 89) return 900;
		if(level <= 99) return 1000;
		if(level <= 109) return 1200;
		if(level <= 119) return 1500;
		return 0;
	}
}
