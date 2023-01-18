package me.stitchie.legacypit.util;

import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import static org.bukkit.Bukkit.getScoreboardManager;
import static org.bukkit.ChatColor.COLOR_CHAR;
import static org.bukkit.scoreboard.DisplaySlot.SIDEBAR;

public class Board {
	private static final int MAX_LINES = 15;
	private static final String[] BLANKS = new String[MAX_LINES];
	private final Team[] teams = new Team[MAX_LINES];
	private final Objective objective;

	static {
		for (int i = 0; i < MAX_LINES; ++i)
			BLANKS[i] = new String(new char[]{COLOR_CHAR, (char) ('s' + i)});
	}

	public Board(final Scoreboard board) {
		board.clearSlot(SIDEBAR);
		objective = board.registerNewObjective("sidebar", "dummy");
		objective.setDisplaySlot(SIDEBAR);
		for (int i = 0; i < MAX_LINES; i++)
			(teams[i] = board.registerNewTeam(BLANKS[i])).addEntry(BLANKS[i]);
	}

	public Board() {
		this(getScoreboardManager().getMainScoreboard());
	}

	public void title(final Object title) {
		objective.setDisplayName(title.toString());
	}

	public void line(final int index, final String prefix, final String suffix, final int score) {
		teams[index].setPrefix(prefix);
		teams[index].setSuffix(suffix);
		objective.getScore(BLANKS[index]).setScore(score);
	}

	public void lineSuffix(final int index, final String suffix, final int score) {
		teams[index].setSuffix(suffix);
		objective.getScore(BLANKS[index]).setScore(score);
	}

	public void linePrefix(final int index, final String prefix, final int score) {
		teams[index].setPrefix(prefix);
		objective.getScore(BLANKS[index]).setScore(score);
	}

	public void line(final int index, final String prefix, final String suffix) {
		line(index, prefix, suffix, MAX_LINES - index);
	}

	public void setPrefix(final int index, final String prefix) {
		linePrefix(index, prefix, MAX_LINES - index);
	}

	public void setSuffix(final int index, final String suffix) {
		lineSuffix(index, suffix, MAX_LINES - index);
	}

	public boolean remove(final int index) {
		if (index >= MAX_LINES) return false;
		objective.getScoreboard().resetScores(BLANKS[index]);
		return true;
	}

	public void removeAll() {
		for (int i = 0; i < MAX_LINES; i++) {
			objective.getScoreboard().resetScores(BLANKS[i]);
		}
	}
}