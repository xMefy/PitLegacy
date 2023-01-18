package me.stitchie.legacypit.player.scoreboard;

import me.stitchie.legacypit.LegacyPit;
import me.stitchie.legacypit.player.PlayerCache;
import me.stitchie.legacypit.util.Board;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Map;

public class ScoreboardHandler {

	private final Map<Player, PlayerCache> playerData;
	private final Map<Player, Integer> combatTagData;
	private final LegacyPit plugin;

	public ScoreboardHandler(final LegacyPit plugin) {
		this.playerData = plugin.getPlayerData();
		this.combatTagData = plugin.getCombatTagData();
		this.plugin = plugin;
	}

	public void createScoreboardScheduler() {

		Bukkit.getScheduler().runTaskTimer(plugin, this::setNormalScoreboard, 10L, 20L);

	}

	private void setNormalScoreboard() {

		for (final Player player : Bukkit.getOnlinePlayers()) {
			final PlayerCache playerCache = playerData.get(player);
			final Board board = playerCache.getScoreboard();
			final String level = playerCache.getFormattedLevel();

			String status = ChatColor.GREEN + "Idling";

			if (combatTagData.containsKey(player)) {
				final int seconds = combatTagData.get(player);
				status = ChatColor.RED + "Combat " + ChatColor.GRAY + "(" + seconds + ")";
			}

			board.title(ChatColor.GOLD + "" + ChatColor.BOLD + "PitLegacy");
			board.line(1, "", "");
			if (playerCache.getPrestige() != 0) {
				board.line(2, "Prestige: ", playerCache.getFormattedPrestige());
			}
			board.line(3, "Level: ", level);
			board.line(4, "Needed XP: ", "§b" + playerCache.getFormattedNeededXP());
			board.line(5, "", "");
			board.line(6, "Gold: ", playerCache.getFormattedGold());
			board.line(7, "", "");
			board.line(8, "Status: ", status);
			if (playerCache.getBounty() > 0) {
				board.line(9, "Bounty: ", playerCache.getFormattedScoreboardBounty());
			}
			board.line(10, "", "");
			board.line(11, "§ediscord.gg/", "§eH2Q9qkY");
		}
	}
}
