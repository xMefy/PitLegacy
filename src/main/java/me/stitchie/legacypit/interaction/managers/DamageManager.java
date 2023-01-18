package me.stitchie.legacypit.interaction.managers;

import me.stitchie.legacypit.interaction.EventType;
import me.stitchie.legacypit.interaction.InteractEventData;
import me.stitchie.legacypit.perks.Perk;
import me.stitchie.legacypit.perks.Perks;
import me.stitchie.legacypit.player.PlayerCache;
import me.stitchie.legacypit.player.data.PlayerData;
import me.stitchie.legacypit.register.UpgradeManager;
import me.stitchie.legacypit.upgrades.Upgrade;
import me.stitchie.legacypit.upgrades.Upgrades;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.Map;

public enum DamageManager implements Listener {

	INSTANCE;

	private final Map<Player, PlayerCache> playerData = PlayerData.INSTANCE.getPlayerData();
	private final Map<Upgrades, Upgrade> upgradeData = UpgradeManager.INSTANCE.getUpgradeList();
	private final Map<Perks, Perk> perkList = PerkManager.INSTANCE.getPerkList();

	@EventHandler
	private void onHit(final EntityDamageByEntityEvent event) {

		if(event.getEntity() instanceof Player && event.getDamager() instanceof Player) {

			final Player damager = (Player) event.getDamager();
			final Player damaged = (Player) event.getEntity();

			final PlayerCache damagerCache = playerData.get(damager);
			final PlayerCache damagedCache = playerData.get(damaged);

			for (final Perks enumPerk : damagerCache.getCurrentPerks()) {
				final Perk perk = perkList.get(enumPerk);
				perk.executePerk(new InteractEventData(EventType.ONHIT, event, damagerCache, damagedCache));
			}
		}
	}
}
