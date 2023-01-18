package me.stitchie.legacypit.upgrades;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class XPBoost implements Upgrade {

	private final ItemStack icon = new ItemStack(Material.INK_SACK, 1, (short) 12);
	private final ArrayList<String> description = new ArrayList<>();

	public XPBoost() {
		description.add("&7Each tier:");
		description.add("&7Earn &b+10% XP &7from");
		description.add("&7all sources.");
	}

	@Override
	public String getName() {
		return "XP Boost";
	}

	@Override
	public ItemStack getIcon() {
		return icon;
	}

	@Override
	public int getCost(final int tier) {
		switch (tier) {
			case 1:
				return 500;
			case 2:
				return 2500;
			case 3:
				return 5000;
			case 4:
				return 10000;
			case 5:
				return 25000;
			default:
				return 100000;
		}
	}

	@Override
	public int getMinimumLevel(final int tier) {
		switch (tier) {
			case 1:
				return 10;
			case 2:
				return 30;
			case 3:
				return 50;
			case 4:
				return 80;
			case 5:
				return 100;
			default:
				return 120;
		}
	}

	@Override
	public ArrayList<String> getDescription() {
		return description;
	}

	@Override
	public String getCurrentText(final int tier) {
		return "&7Current: &b+" + tier * 10 + "&b% XP";
	}

	@Override
	public double getFactor(final int tier) {
		return 1 + (tier * 0.1);
	}

	@Override
	public int getIndex() {
		return 28;
	}

	@Override
	public Upgrades getUpgrade() {
		return Upgrades.XPBOOST;
	}
}
