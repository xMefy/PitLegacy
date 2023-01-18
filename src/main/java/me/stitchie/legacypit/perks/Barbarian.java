package me.stitchie.legacypit.perks;

import me.stitchie.legacypit.interaction.EventType;
import me.stitchie.legacypit.interaction.InteractEventData;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class Barbarian implements Perk {
	
	@Override
	public int getCost() {
		return 0;
	}

	@Override
	public int getMinLevel() {
		return 0;
	}

	@Override
	public int getIndex() {
		return 0;
	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public ArrayList<String> getDescription() {
		return null;
	}

	@Override
	public ItemStack getIcon() {
		return null;
	}

	@Override
	public boolean isHealPerk() {
		return false;
	}

	@Override
	public boolean addItems(final Player player) {
		return false;
	}

	@Override
	public boolean removeItems(final Player player) {
		return false;
	}

	@Override
	public ArrayList<EventType> getEventTypes() {
		return null;
	}

	@Override
	public void executePerk(final InteractEventData data) {

	}
}
