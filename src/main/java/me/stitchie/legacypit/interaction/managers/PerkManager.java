package me.stitchie.legacypit.interaction.managers;

import me.stitchie.legacypit.managers.Manager;
import me.stitchie.legacypit.perks.Barbarian;
import me.stitchie.legacypit.perks.Perk;
import me.stitchie.legacypit.perks.Perks;

import java.util.EnumMap;

public enum PerkManager implements Manager {

	INSTANCE;

	private final EnumMap<Perks, Perk> pitPerks = new EnumMap<>(Perks.class);

	PerkManager() {
		pitPerks.put(Perks.BARBARIAN, new Barbarian());

	}

	public final EnumMap<Perks, Perk> getPerkList() {
		return this.pitPerks;
	}
}

