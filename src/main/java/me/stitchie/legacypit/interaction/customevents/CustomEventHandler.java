package me.stitchie.legacypit.interaction.customevents;

import me.stitchie.legacypit.interaction.DamageType;
import me.stitchie.legacypit.interaction.customevents.player.PlayerHitEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class CustomEventHandler implements Listener {

	public CustomEventHandler() {

	}

	@EventHandler(priority = EventPriority.LOW)
	private void onEntityHit(final EntityDamageByEntityEvent event) {
		final Entity damager = event.getDamager();
		final Entity damaged = event.getEntity();
		final double damage = event.getFinalDamage();

		if(damager instanceof Player && damaged instanceof Player) {
			final PlayerHitEvent playerHitEvent = new PlayerHitEvent(event, (Player) damager, (Player) damaged, damage
					, event.getCause());
			Bukkit.getServer().getPluginManager().callEvent(playerHitEvent);
		}
	}

	@EventHandler
	private void onPlayerHit(final PlayerHitEvent event) {
		event.addDamage(10, DamageType.ADDITIVE);
		Bukkit.broadcastMessage("PlayerHitEvent registered.");
	}
}
