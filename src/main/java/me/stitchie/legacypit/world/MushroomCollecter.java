package me.stitchie.legacypit.world;

import me.stitchie.legacypit.player.PlayerCache;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.Map;

public class MushroomCollecter implements Listener {

	private final Map<Player, PlayerCache> playerData;

	public MushroomCollecter(final Map<Player, PlayerCache> playerData) {
		this.playerData = playerData;
	}

	@EventHandler(ignoreCancelled = true)
	private void onMushroomBreak(final BlockBreakEvent event) {

		final Player player = event.getPlayer();

		if (event.getBlock().getType() == Material.BROWN_MUSHROOM) {
			playerData.get(player).addMushrooms(1);
			player.playEffect(event.getBlock().getLocation(), Effect.HAPPY_VILLAGER, null);
		} else if (event.getBlock().getType() == Material.RED_MUSHROOM) {
			playerData.get(player).addMushrooms(3);
			player.playEffect(event.getBlock().getLocation(), Effect.HAPPY_VILLAGER, null);
		}
	}
}
