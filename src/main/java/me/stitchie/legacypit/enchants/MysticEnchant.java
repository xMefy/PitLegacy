package me.stitchie.legacypit.enchants;

import me.stitchie.legacypit.interaction.DamagePriority;
import me.stitchie.legacypit.interaction.EventType;
import me.stitchie.legacypit.interaction.InteractEventData;

import java.util.ArrayList;

public interface MysticEnchant {

	ArrayList<String> getDescription(int level);

	EnchantGroup getEnchantGroup();

	EnchantType getEnchantType();

	DamagePriority getDamagePriority();

	boolean isRareEnchant();

	String getName();

	ArrayList<EventType> getEventTypes();

	void executeEnchant(int level, InteractEventData data, ArrayList<MysticEnchant> enchants);

}
