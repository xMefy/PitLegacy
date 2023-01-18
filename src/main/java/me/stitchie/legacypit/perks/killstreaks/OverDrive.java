package me.stitchie.legacypit.perks.killstreaks;

import me.stitchie.legacypit.player.PlayerCache;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class OverDrive implements Killstreak {

	@Override
	public int getKillsRequired() {
		return 50;
	}

	@Override
	public int getCost() {
		return 0;
	}

	@Override
	public String getName() {
		return "Overdrive";
	}

	@Override
	public void onEquip(final PlayerCache playerCache) {

	}

	@Override
	public void onTrigger(final PlayerCache playerCache) {
		final Player player = playerCache.getPlayer();
		playerCache.addCurrentXPBoost(2.0);
		playerCache.addCurrentGoldBoost(1.5);
		player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1728000, 0), true);
	}

	@Override
	public void onStreak(final PlayerCache playerCache) {

	}

	@Override
	public void onDownside(final PlayerCache playerCache) {
		final Player player = playerCache.getPlayer();
	}

	@Override
	public void onDeath(final PlayerCache playerCache) {
		playerCache.addXP(4000);
	}
}
