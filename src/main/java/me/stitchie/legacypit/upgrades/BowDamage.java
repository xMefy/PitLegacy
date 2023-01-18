package me.stitchie.legacypit.upgrades;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class BowDamage implements Upgrade {

	private final ItemStack icon = new ItemStack(Material.INK_SACK, 1, (short) 11);
	private final ArrayList<String> description = new ArrayList<>();

	public BowDamage() {
		description.add("&7Each tier:");
		description.add("&7Deal &c+3% &7bow damage.");
	}

	@Override
	public String getName() {
		return "Bow Damage";
	}

	@Override
	public ItemStack getIcon() {
		return icon;
	}

	@Override
	public int getCost(final int tier) {
		switch (tier) {
			case 1:
				return 450;
			case 2:
				return 1050;
			case 3:
				return 1500;
			case 4:
				return 2250;
			case 5:
				return 3000;
			default:
				return 100000;
		}
	}

	@Override
	public int getMinimumLevel(final int tier) {
		switch (tier) {
			case 1:
				return 30;
			case 2:
				return 50;
			case 3:
				return 60;
			case 4:
				return 70;
			case 5:
				return 80;
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
		return "&7Current: &c+" + tier * 3 + "&c%";
	}

	@Override
	public double getFactor(final int tier) {
		return 1 + (tier * 0.01);
	}

	@Override
	public int getIndex() {
		return 31;
	}

	@Override
	public Upgrades getUpgrade() {
		return Upgrades.BOWDAMAGE;
	}
}
