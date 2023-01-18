package me.stitchie.legacypit.player;

import me.stitchie.legacypit.player.data.PlayerData;
import me.stitchie.legacypit.util.Board;
import me.stitchie.legacypit.util.NameTag;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scoreboard.Scoreboard;

import java.util.Map;

public class PlayerCacheHandler implements Listener {

	private final Map<Player, PlayerCache> playerData = PlayerData.INSTANCE.getPlayerData();
	private final World world = Bukkit.getWorld("LegacyPit");
	private final Location spawnLocation = new Location(world, - 11.5, 125, 0.5, - 90, 0);

	public PlayerCacheHandler() {
	}

	@EventHandler
	private void onPlayerJoin(final PlayerJoinEvent event) {

		final Player player = event.getPlayer();
		final Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
		player.setScoreboard(board);

		final PlayerCache playerCache = new PlayerCache(new Board(board), player);

		playerData.put(player, playerCache);

		NameTag.setNameTag(player, ChatColor.GRAY + "[1] " + ChatColor.GREEN + "" + ChatColor.BOLD,
				ChatColor.RED + "" + ChatColor.BOLD + " IS TESTING!", playerCache);
		NameTag.displayNameTagsToYou(player, playerData);

		playerCache.updateLevelBar();

		player.teleport(spawnLocation);
	}

	@EventHandler
	private void onPlayerQuit(final PlayerQuitEvent event) {
		final Player player = event.getPlayer();
		playerData.remove(player);
	}
}
