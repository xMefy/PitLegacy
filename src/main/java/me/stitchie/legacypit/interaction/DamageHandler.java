package me.stitchie.legacypit.interaction;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class DamageHandler implements Listener {

	public DamageHandler() {

	}

	@EventHandler
	private void onHit(final EntityDamageByEntityEvent event) {

	}

	public static void safeAddPlayerHealth(final Player player, final double healthIncrease) {
		player.setHealth(Math.min(player.getMaxHealth(), player.getHealth() + healthIncrease));
	}

	public static void safeDecreasePlayerHealth(final Player player, final double healthDecrease) {
		player.setHealth(Math.min(0, player.getHealth() - healthDecrease));
	}

}
