package me.stitchie.legacypit.interaction;

import me.stitchie.legacypit.interaction.managers.OnDeathManager;
import me.stitchie.legacypit.interaction.managers.OnKillManager;
import me.stitchie.legacypit.player.PlayerCache;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.Map;

public class OnDeathHandler implements Listener {

	private final Map<Player, PlayerCache> playerData;
	private final OnDeathManager deathManager;
	private final OnKillManager killManager;

	public OnDeathHandler(final Map<Player, PlayerCache> playerData, final OnDeathManager deathManager, final OnKillManager killManager) {
		this.playerData = playerData;
		this.deathManager = deathManager;
		this.killManager = killManager;
	}

	@EventHandler
	public void onPlayerDeath(final PlayerDeathEvent event) {

		final Player killer = event.getEntity().getKiller();
		final Player killed = event.getEntity();

		final PlayerCache killerCache = playerData.get(killer);
		final PlayerCache killedCache = playerData.get(killed);

		Bukkit.broadcastMessage("DEATH EVENT CALLED!");

		// killManager.onPlayerKill(event);

	}
}
