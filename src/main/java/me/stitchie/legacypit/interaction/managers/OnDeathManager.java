package me.stitchie.legacypit.interaction.managers;

import me.stitchie.legacypit.player.PlayerCache;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.Map;

public class OnDeathManager {

	private final Map<Player, PlayerCache> playerData;

	public OnDeathManager(final Map<Player, PlayerCache> playerData) {
		this.playerData = playerData;
	}

	public void onPlayerDeath(PlayerDeathEvent event) {
		
	}

}
