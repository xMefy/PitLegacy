package me.stitchie.legacypit.player;

import me.stitchie.legacypit.LegacyPit;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Iterator;
import java.util.Map;

public class CombatTag implements Listener {

	private final LegacyPit plugin;
	private final Map<Player, Integer> combatTagData;

	public CombatTag(final LegacyPit plugin, final Map<Player, Integer> combatTagData) {
		this.plugin = plugin;
		this.combatTagData = combatTagData;
		startCombatCheck();
	}

	@EventHandler(ignoreCancelled = true)
	private void onCombatHit(final EntityDamageByEntityEvent event) {
		if (event.getDamager() instanceof Player && event.getEntity() instanceof Player) {
			final Player player = (Player) event.getEntity();
			final Player target = (Player) event.getDamager();

			combatTagData.put(player, 15);
			combatTagData.put(target, 15);
		}
	}

	@EventHandler(priority = EventPriority.HIGH)
	private void onCombatLog(final PlayerQuitEvent event) {
		final Player player = event.getPlayer();
		if (combatTagData.containsKey(player)) {
			Bukkit.broadcastMessage(player.getDisplayName() + ChatColor.GRAY + " has " + ChatColor.RED + "combat logged!");
		}
	}

	private void startCombatCheck() {

		Bukkit.getScheduler().runTaskTimer(plugin, () -> {

			final Iterator<Player> iterator = combatTagData.keySet().iterator();

			while (iterator.hasNext()) {
				final Player player = iterator.next();
				final int seconds = combatTagData.get(player);
				if (seconds <= 1) {
					iterator.remove();
				} else {
					combatTagData.replace(player, seconds - 1);
				}
			}
		}, 20L, 20L);
	}
}

