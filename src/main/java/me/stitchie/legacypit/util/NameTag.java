package me.stitchie.legacypit.util;

import me.stitchie.legacypit.player.PlayerCache;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.Map;

public final class NameTag {

	private NameTag() {

	}

	public static void setNametagPrefix(final Player player, final String prefix) {

		if (prefix.length() <= 16) {

			for (final Player p : Bukkit.getOnlinePlayers()) {

				final Scoreboard board = p.getScoreboard();
				final Team team = board.getTeam(player.getName()) == null ? board.registerNewTeam(player.getName()) : board.getTeam(player.getName());

				team.unregister();

				team.setPrefix(prefix);
				team.addEntry(player.getName());
			}
		}
	}

	public static String getPlayerTeamName(final int level, final int prestige, final int bounty, final boolean supporter) {

		final String formattedLevel = String.format("%04d", reverseLevel(level));

		if (prestige != 0) {
			// bla bla bla prestige bracket color ID
		}

		if (supporter) {
			// add some special symbol idk
		}

		if (bounty != 0) {
			return formattedLevel.concat("b" + bounty);
		}

		return formattedLevel;
	}


	public static void setNametagSuffix(final Player player, final String suffix) {

		if (suffix.length() <= 16) {

			for (final Player p : Bukkit.getOnlinePlayers()) {

				final Scoreboard board = p.getScoreboard();
				final Team team = board.getTeam(player.getName()) == null ? board.registerNewTeam(player.getName()) : board.getTeam(player.getName());

				team.setSuffix(suffix);
				team.addEntry(player.getName());
			}
		}
	}

	public static void setNameTag(final Player player, final String prefix, final String suffix, final PlayerCache playerCache) {

		if (suffix.length() <= 16 && prefix.length() <= 16) {

			final int level = playerCache.getLevel();
			final int prestige = playerCache.getPrestige();
			final int bounty = playerCache.getBounty();
			final boolean supporter = playerCache.isSupporter();

			final String newTeamName = NameTag.getPlayerTeamName(level, prestige, bounty, supporter);
			final String oldTeamName = playerCache.getTeamName();

			playerCache.setTeamName(newTeamName);

			for (final Player p : Bukkit.getOnlinePlayers()) {
				final Scoreboard board = p.getScoreboard();

				if (board.getTeam(oldTeamName) != null) {
					final Team oldTeam = board.getTeam(oldTeamName);
					oldTeam.unregister();
				}

				if (board.getTeam(newTeamName) != null) {
					final Team newTeam = board.getTeam(newTeamName);
					playerCache.setTeamPrefix(prefix);
					playerCache.setTeamSuffix(suffix);
					newTeam.setPrefix(prefix);
					newTeam.setSuffix(suffix);
					newTeam.addEntry(player.getName());
				} else {
					final Team newTeam = board.registerNewTeam(newTeamName);
					playerCache.setTeamPrefix(prefix);
					playerCache.setTeamSuffix(suffix);
					newTeam.setPrefix(prefix);
					newTeam.setSuffix(suffix);
					newTeam.addEntry(player.getName());
				}
			}
		}
	}

	public static void displayNameTagsToYou(final Player player, final Map<Player, PlayerCache> playerData) {

		final Scoreboard board = player.getScoreboard();

		for (final Map.Entry<Player, PlayerCache> entry : playerData.entrySet()) {

			final Player p = entry.getKey();

			if (player == p) {
				continue;
			}

			final PlayerCache playerCache = entry.getValue();

			final String teamName = playerCache.getTeamName();

			if (board.getTeam(teamName) != null) {
				final Team team = board.getTeam(teamName);
				team.setPrefix(playerCache.getTeamPrefix());
				team.setSuffix(playerCache.getTeamSuffix());
				team.addEntry(p.getName());
			} else {
				final Team team = board.registerNewTeam(playerCache.getTeamName());
				team.setPrefix(playerCache.getTeamPrefix());
				team.setSuffix(playerCache.getTeamSuffix());
				team.addEntry(p.getName());
			}
		}
	}

	public static int reverseLevel(final int level) {
		return 121 - level;
	}

}
