package me.stitchie.legacypit.interaction.managers;

import me.stitchie.legacypit.perks.Perk;
import me.stitchie.legacypit.perks.Perks;
import me.stitchie.legacypit.player.PlayerCache;
import org.bukkit.entity.Player;

import java.util.EnumMap;
import java.util.Map;

public class OnHitManager {

	private final Map<Player, PlayerCache> playerData;
	private final EnumMap<Perks, Perk> perkMap;

	public OnHitManager(final Map<Player, PlayerCache> playerData, final EnumMap<Perks, Perk> perkMap) {
		this.playerData = playerData;
		this.perkMap = perkMap;
	}
	
}
