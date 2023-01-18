package me.stitchie.legacypit.interaction.managers;

import me.stitchie.legacypit.player.PlayerCache;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.Map;

public class OnKillManager implements Listener {

	private final Map<Player, PlayerCache> playerData;

	public OnKillManager(final Map<Player, PlayerCache> playerData) {
		this.playerData = playerData;
	}

	public void onPlayerKill(final PlayerDeathEvent event) {

	}


}
