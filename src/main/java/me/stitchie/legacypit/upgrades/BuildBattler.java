package me.stitchie.legacypit.upgrades;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class BuildBattler implements Upgrade {

	private final ItemStack icon = new ItemStack(Material.INK_SACK, 1, (short) 15);
	private final ArrayList<String> description = new ArrayList<>();

	public BuildBattler() {
		description.add("&7Each tier:");
		description.add("&7Your blocks stay");
		description.add("&a+60% longer.");
	}

	@Override
	public String getName() {
		return "Build Battler";
	}

	@Override
	public ItemStack getIcon() {
		return icon;
	}

	@Override
	public int getCost(final int tier) {
		switch (tier) {
			case 1:
				return 750;
			case 2:
				return 1750;
			case 3:
				return 2750;
			case 4:
				return 3750;
			case 5:
				return 5000;
			default:
				return 100000;
		}
	}

	@Override
	public int getMinimumLevel(final int tier) {
		switch (tier) {
			case 1:
				return 40;
			case 2:
				return 50;
			case 3:
				return 55;
			case 4:
				return 60;
			case 5:
				return 65;
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
		return "&7Current: &a+" + tier * 60 + "&a%";
	}

	@Override
	public double getFactor(final int tier) {
		return 1 + (tier * 0.6);
	}

	@Override
	public int getIndex() {
		return 33;
	}

	@Override
	public Upgrades getUpgrade() {
		return Upgrades.BUILDBATTLER;
	}
}
