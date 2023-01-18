package me.stitchie.legacypit.perks;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public enum Perks {

	GOLDEN_HEADS(500, 10, "Golden Heads", 10, true),
	FISHING_ROD(1000, 10, "Fishing Rod", 11, false),
	LAVA_BUCKET(1000, 10, "Lava Bucket", 12, false),
	STRENGTH_CHAINING(2000, 20, "Strength Chaining", 13, false),
	MINEMAN(3000, 30, "Mineman", 14, false),
	SAFETY_FIRST(3000, 30, "Safety First", 15, false),
	BARBARIAN(3000, 30, "Barbarian", 16, false),
	TRICKLE_DOWN(1000, 40, "Trickle Down", 19, false),
	LUCKY_DIAMOND(4000, 40, "Lucky Diamond", 20, false),
	BOUNTY_HUNTER(2000, 50, "Bounty Hunter", 21, false),
	STREAKER(8000, 50, "Streaker", 22, false),
	VAMPIRE(4000, 60, "Vampire", 23, true),
	GLADIATOR(4000, 60, "Gladiator", 24, false),
	OLYMPUS(6000, 70, "Olympus", 25, true),
	OVERHEAL(6000, 70, "Overheal", 28, false),
	FIRST_STRIKE(8000, 80, "First Strike", 29, false),
	DIRTY(8000, 80, "Dirty", 30, false),
	NONE(0, 0, "", 0, false);

	private final int cost;
	private final int minlevel;
	private final String name;
	private final int index;
	private final boolean healperk;

	Perks(final int cost, final int minlevel, final String name, final int index, final boolean healperk) {
		this.cost = cost;
		this.minlevel = minlevel;
		this.name = name;
		this.index = index;
		this.healperk = healperk;
	}

	public int getCost() {
		return cost;
	}

	public int getIndex() {
		return index;
	}

	public int getMinlevel() {
		return minlevel;
	}

	public String getName() {
		return name;
	}

	public boolean isHealperk() {
		return healperk;
	}

	public ItemStack getIcon() {
		switch (this) {
			default:
				return new ItemStack(Material.DIRT);
		}
	}

	public ArrayList<String> getDescription(final Perks perk) {

		final ArrayList<String> description = new ArrayList<>();

		switch (perk) {
			case GOLDEN_HEADS:
				description.add("&7Golden apples you earn");
				description.add("&7turn into &6Golden Heads&7.");
				break;
			case FISHING_ROD:
				description.add("&7Spawn with a fishing rod.");
				break;
			case LAVA_BUCKET:
				description.add("&7Spawn with a lava bucket.");
				break;
			case STRENGTH_CHAINING:
				description.add("&c+8% damage &7for 7s");
				description.add("&7stacking on kill.");
				break;
			case MINEMAN:
				description.add("&7Spawn with &f24 cobblestone");
				description.add("&7and a diamond pickaxe.");
				description.add("");
				description.add("&7+&f3 blocks &7on kill.");
				break;
			case SAFETY_FIRST:
				description.add("&7Spawn with a helmet.");
				break;
			case BARBARIAN:
				description.add("&7Replaces your sword");
				description.add("&7with a stronger axe.");
				break;
			case TRICKLE_DOWN:
				description.add("&7Gold ingots reward");
				description.add("&6+10g &7and heal &c2❤");
				break;
			case LUCKY_DIAMOND:
				description.add("&730% Chance to upgrade");
				description.add("&7dropped armor pieces");
				description.add("&7from kills to &bdiamond&7.");
				description.add("");
				description.add("&7Upgraded pieces warp");
				description.add("&7to your inventory.");
				break;
			case BOUNTY_HUNTER:
				description.add("&6+4g &7on all kills.");
				description.add("&7Earn bounty assist shares.");
				description.add("");
				description.add("&c+1% damage&7/100g bounty");
				description.add("&7on target.");
				break;
			case STREAKER:
				description.add("&7Triple streak kill");
				description.add("&bXP &7bonus.");
				break;
			case VAMPIRE:
				description.add("&7Don't earn golden apples.");
				description.add("&7Heal &c0.5❤ &7on hit.");
				description.add("&7Tripled on arrow crit.");
				description.add("&cRegen I &7(8s) on kill.");
				break;
			case GLADIATOR:
				description.add("&7Receive &9-3% &7damage");
				description.add("&7per nearby player.");
				description.add("");
				description.add("&712 blocks range.");
				description.add("&7Minimum 3, max 10 players.");
				break;
			case OLYMPUS:
				description.add("&7Golden apples you earn");
				description.add("&7turn into &bOlympus Potions&7.");
				description.add("");
				description.add("&bOlympus Potion");
				description.add("&9Speed I (0:24)");
				description.add("&9Regeneration III (0:10)");
				description.add("&9Resistance II (0:04)");
				description.add("&bGain +27 XP!");
				description.add("&7Can only hold 1");
				break;
			case OVERHEAL:
				description.add("&7Double healing item limits.");
				break;
			case FIRST_STRIKE:
				description.add("&7First hit on a player deals");
				description.add("&c+35% damage &7and grants");
				description.add("&eSpeed I &7(5s).");
				break;
			case DIRTY:
				description.add("&7Gain Resistance II (4s)");
				description.add("&7on kill.");
				break;
			case NONE:
				description.add("&7Select a perk to");
				description.add("&7fill this slot.");
		}

		return description;
	}

}
