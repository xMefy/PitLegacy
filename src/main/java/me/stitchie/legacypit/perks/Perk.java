package me.stitchie.legacypit.perks;

import me.stitchie.legacypit.interaction.EventType;
import me.stitchie.legacypit.interaction.InteractEventData;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public interface Perk {

	int getCost();

	int getMinLevel();

	int getIndex();

	String getName();

	ArrayList<String> getDescription();

	ItemStack getIcon();

	boolean isHealPerk();

	boolean addItems(Player player);

	boolean removeItems(Player player);

	ArrayList<EventType> getEventTypes();

	void executePerk(InteractEventData data);

}