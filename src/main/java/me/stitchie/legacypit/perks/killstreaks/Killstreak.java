package me.stitchie.legacypit.perks.killstreaks;

import me.stitchie.legacypit.player.PlayerCache;

public interface Killstreak {

	int getKillsRequired();

	int getCost();

	String getName();

	void onEquip(PlayerCache playerCache);

	void onTrigger(PlayerCache playerCache);

	void onStreak(PlayerCache playerCache);

	void onDownside(PlayerCache playerCache);

	void onDeath(PlayerCache playerCache);

}
