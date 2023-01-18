package me.stitchie.legacypit.perks;

import me.stitchie.legacypit.interaction.EventType;
import me.stitchie.legacypit.interaction.InteractEventData;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class Vampire implements Perk {

	private final ArrayList<EventType> eventTypes = new ArrayList<>();
	private final ArrayList<String> description = new ArrayList<>();

	public Vampire() {
		this.eventTypes.add(EventType.ONHIT);
		this.eventTypes.add(EventType.ONARROWHIT);
		this.eventTypes.add(EventType.ONARROWCRIT);
		this.eventTypes.add(EventType.ONKILL);
	}

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
		return "Vampire";
	}

	@Override
	public ArrayList<String> getDescription() {
		return description;
	}

	@Override
	public ItemStack getIcon() {
		return null;
	}

	@Override
	public boolean isHealPerk() {
		return true;
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
		return eventTypes;
	}

	@Override
	public void executePerk(final InteractEventData data) {

		final EventType eventType = data.getType();

		switch (eventType) {
			case ONHIT:


		}
	}
}
