package me.stitchie.legacypit.enchants.sword;

import me.stitchie.legacypit.enchants.EnchantGroup;
import me.stitchie.legacypit.enchants.EnchantType;
import me.stitchie.legacypit.enchants.MysticEnchant;
import me.stitchie.legacypit.interaction.DamagePriority;
import me.stitchie.legacypit.interaction.EventType;
import me.stitchie.legacypit.interaction.InteractEventData;

import java.util.ArrayList;

public class Shark implements MysticEnchant {

	private final ArrayList<EventType> eventTypes = new ArrayList<>();
	private final ArrayList<String> description = new ArrayList<>();

	public Shark() {
		this.eventTypes.add(EventType.ONHIT);
	}

	@Override
	public ArrayList<String> getDescription(final int level) {
		return null;
	}

	@Override
	public EnchantGroup getEnchantGroup() {
		return null;
	}

	@Override
	public EnchantType getEnchantType() {
		return EnchantType.NORMAL_SWORD;
	}

	@Override
	public DamagePriority getDamagePriority() {
		return DamagePriority.NORMAL_DAMAGE;
	}

	@Override
	public boolean isRareEnchant() {
		return false;
	}

	@Override
	public String getName() {
		return "Shark";
	}

	@Override
	public ArrayList<EventType> getEventTypes() {
		return eventTypes;
	}

	@Override
	public void executeEnchant(int level, InteractEventData data, ArrayList<MysticEnchant> enchants) {
		
	}
}
