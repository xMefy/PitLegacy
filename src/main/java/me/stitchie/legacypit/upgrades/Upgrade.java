package me.stitchie.legacypit.upgrades;

import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public interface Upgrade {

	String getName();

	ItemStack getIcon();

	int getCost(final int tier);

	int getMinimumLevel(int tier);

	ArrayList<String> getDescription();

	String getCurrentText(int tier);

	double getFactor(int tier);

	int getIndex();

	Upgrades getUpgrade();

}
