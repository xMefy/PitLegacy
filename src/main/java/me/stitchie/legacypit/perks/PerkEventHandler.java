package me.stitchie.legacypit.perks;

import me.stitchie.legacypit.player.PlayerCache;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.Map;

public class PerkEventHandler implements Listener {

	private final Map<Player, PlayerCache> playerData;

	public PerkEventHandler(final Map<Player, PlayerCache> playerData) {
		this.playerData = playerData;
	}

	@EventHandler(ignoreCancelled = true, priority = EventPriority.LOW)
	private void onHit(final EntityDamageByEntityEvent event) {

		if(event.getEntity() instanceof Player && event.getDamager() instanceof Player) {

			final Player damager = (Player) event.getDamager();
			final Player damaged = (Player) event.getEntity();


			/*

			if (damager == damaged) {
				return;
			}

			final PlayerCache damagerData = playerData.get(damager);
			final PlayerCache damagedData = playerData.get(damaged);

			for (final Perks enumPerk : damagerData.getCurrentPerks()) {
				final Perk perk = perkMap.get(enumPerk);

				if (perk.getEventTypes().contains(EventType.ONHIT)) {

				}

			}

			 */

		}

	}


}
