package me.stitchie.legacypit.upgrades;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class DamageReduction implements Upgrade {

	private final ItemStack icon = new ItemStack(Material.INK_SACK, 1, (short) 6);
	private final ArrayList<String> description = new ArrayList<>();

	public DamageReduction() {
		description.add("&7Each tier:");
		description.add("&7Receive &9-1% &7damage.");
	}

	@Override
	public String getName() {
		return "Damage Reduction";
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
		return "&7Current: &9-" + tier + "&9%";
	}

	@Override
	public double getFactor(final int tier) {
		return 1 + (tier * 0.01);
	}

	@Override
	public int getIndex() {
		return 32;
	}

	@Override
	public Upgrades getUpgrade() {
		return Upgrades.DAMAGEREDUCTION;
	}
}
