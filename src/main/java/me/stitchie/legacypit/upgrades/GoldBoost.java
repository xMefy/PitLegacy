package me.stitchie.legacypit.upgrades;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class GoldBoost implements Upgrade {

	private final ItemStack icon = new ItemStack(Material.INK_SACK, 1, (short) 14);
	private final ArrayList<String> description = new ArrayList<>();

	public GoldBoost() {
		description.add("&7Each tier:");
		description.add("&7Earn &6+10% gold (g) &7from");
		description.add("&7kills and coin pickups.");
	}

	@Override
	public String getName() {
		return "Gold Boost";
	}

	@Override
	public ItemStack getIcon() {
		return icon;
	}

	@Override
	public int getCost(final int tier) {
		switch (tier) {
			case 1:
				return 1000;
			case 2:
				return 2500;
			case 3:
				return 10000;
			case 4:
				return 25000;
			case 5:
				return 40000;
			default:
				return 100000;
		}
	}

	@Override
	public int getMinimumLevel(final int tier) {
		switch (tier) {
			case 1:
				return 20;
			case 2:
				return 40;
			case 3:
				return 50;
			case 4:
				return 70;
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
		return "&7Current: &6+" + tier * 10 + "&6% gold (g)";
	}

	@Override
	public double getFactor(final int tier) {
		return 1 + (tier * 0.1);
	}

	@Override
	public int getIndex() {
		return 29;
	}

	@Override
	public Upgrades getUpgrade() {
		return Upgrades.GOLDBOOST;
	}
}
