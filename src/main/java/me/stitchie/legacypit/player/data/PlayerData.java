package me.stitchie.legacypit.player.data;

import me.stitchie.legacypit.player.PlayerCache;
import org.bukkit.entity.Player;

import java.util.IdentityHashMap;
import java.util.Map;

public enum PlayerData {

	INSTANCE;

	PlayerData() {
	}

	private final Map<Player, PlayerCache> playerData = new IdentityHashMap<>();

	public Map<Player, PlayerCache> getPlayerData() {
		return playerData;
	}

}
