package me.stitchie.legacypit.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class PitColor {

	private static final DecimalFormatSymbols USFormat = new DecimalFormatSymbols(Locale.US);
	private static final DecimalFormat goldFormat = new DecimalFormat("#,###,###", USFormat);

	private PitColor() {

	}

	public static String getPrestigeBracketColor(final int prestige, final boolean legacy) {
		if (prestige < 1) return legacy ? "§7" : "&7";
		if (prestige < 5) return legacy ? "§9" : "&9";
		if (prestige < 10) return legacy ? "§e" : "&e";
		if (prestige < 15) return legacy ? "§6" : "&6";
		if (prestige < 20) return legacy ? "§c" : "&c";
		if (prestige < 25) return legacy ? "§5" : "&5";
		if (prestige < 30) return legacy ? "§d" : "&d";
		if (prestige == 30) return legacy ? "§f" : "&f";
		return legacy ? "§7" : "&7";
	}

	public static String getLevelColor(final int level, final boolean legacy) {
		if (level <= 9) return legacy ? "§7" : "&7";
		if (level <= 19) return legacy ? "§9" : "&9";
		if (level <= 29) return legacy ? "§3" : "&3";
		if (level <= 39) return legacy ? "§2" : "&2";
		if (level <= 49) return legacy ? "§a" : "&a";
		if (level <= 59) return legacy ? "§e" : "&e";
		if (level <= 69) return legacy ? "§6§l" : "&6§l";
		if (level <= 79) return legacy ? "§c§l" : "&c§l";
		if (level <= 89) return legacy ? "§4§l" : "&4§l";
		if (level <= 99) return legacy ? "§5§l" : "&5§l";
		if (level <= 109) return legacy ? "§d§l" : "&d§l";
		if (level <= 119) return legacy ? "§f§l" : "&f§l";
		if (level == 120) return legacy ? "§b§l" : "&b§l";
		return legacy ? "§7" : "&7";
	}

	public static String formatGold(final int cost) {
		return goldFormat.format(cost);
	}

	public static String getRoman(final int number) {
		switch (number) {
			case 1:
				return "I";
			case 2:
				return "II";
			case 3:
				return "III";
			case 4:
				return "IV";
			case 5:
				return "V";
			case 6:
				return "VI";
			case 7:
				return "VII";
			case 8:
				return "VIII";
			case 9:
				return "IX";
			case 10:
				return "X";
			case 11:
				return "XI";
			case 12:
				return "XII";
			case 13:
				return "XIII";
			case 14:
				return "XIV";
			case 15:
				return "XV";
			case 16:
				return "XVI";
			case 17:
				return "XVII";
			case 18:
				return "XVIII";
			case 19:
				return "XIX";
			case 20:
				return "XX";
			case 21:
				return "XXI";
			case 22:
				return "XXII";
			case 23:
				return "XXIII";
			case 24:
				return "XXIV";
			case 25:
				return "XXV";
			case 26:
				return "XXVI";
			case 27:
				return "XXVII";
			case 28:
				return "XXVIII";
			case 29:
				return "XXIX";
			case 30:
				return "XXX";
			default:
				return "0 Bug";
		}
	}
}
