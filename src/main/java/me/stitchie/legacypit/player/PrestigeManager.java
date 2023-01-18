package me.stitchie.legacypit.player;

import lombok.Getter;

@Getter
public class PrestigeManager {

	private int prestige;
	private String prestigeBracketColor = "§7";
	private String formattedPrestige = "";

	PrestigeManager(final int prestige) {
		this.prestige = prestige;
	}

	public void prestigePlayer() {
		this.prestige++;
		this.prestigeBracketColor = getPrestigeColor(prestige);
		this.formattedPrestige = getRomanPrestige(prestige);
	}

	private static float getPrestigeXPMultiplier(final int prestige) {

		switch (prestige) {

			case 1:
				return 1.1f;
			case 2:
				return 1.2f;
			case 3:
				return 1.3f;
			case 4:
				return 1.4f;
			case 5:
				return 1.5f;
			case 6:
				return 1.75f;
			case 7:
				return 2;
			case 8:
				return 2.5f;
			case 9:
				return 3;
			case 10:
				return 4;
			case 11:
				return 5;
			case 12:
				return 6;
			case 13:
				return 7;
			case 14:
				return 8;
			case 15:
				return 9;
			case 16:
				return 10;
			case 17:
				return 12;
			case 18:
				return 14;
			case 19:
				return 16;
			case 20:
				return 18;
			case 21:
				return 20;
			case 22:
				return 24;
			case 23:
				return 28;
			case 24:
				return 32;
			case 25:
				return 36;
			case 26:
				return 40;
			case 27:
				return 45;
			case 28:
				return 50;
			case 29:
				return 75;
			case 30:
				return 100;
			default:
				return 1.0f;
		}
	}

	private static int getGoldreq(final int prestige) {
		switch (prestige) {
			case 0:
				return 10000;
			case 1:
				return 22000;
			case 2:
				return 24000;
			case 3:
				return 26000;
			case 4:
				return 28000;
			case 5:
				return 30000;
			case 6:
				return 70000;
			case 7:
				return 80000;
			case 8:
				return 100000;
			case 9:
				return 120000;
			case 10:
				return 160000;
			case 11:
				return 200000;
			case 12:
				return 240000;
			case 13:
				return 280000;
			case 14:
				return 320000;
			case 15:
				return 360000;
			case 16:
				return 400000;
			case 17:
				return 480000;
			case 18:
				return 560000;
			case 19:
				return 800000;
			case 20:
				return 900000;
			case 21:
				return 1000000;
			case 22:
				return 1200000;
			case 23:
				return 1400000;
			case 24:
				return 1600000;
			case 25:
				return 1800000;
			case 26:
				return 2400000;
			case 27:
				return 2700000;
			case 28:
				return 3000000;
			case 29:
				return 4500000;
			case 30:
				return 10000000;
			default:
				return 15000000;
		}
	}

	private static int getPrestigeRenown(final int prestige) {
		switch (prestige) {
			case 0:
			case 1:
			case 2:
			case 3:
				return 10;
			case 4:
			case 5:
			case 6:
			case 7:
			case 8:
			case 9:
				return 20;
			case 10:
			case 11:
			case 12:
			case 13:
			case 14:
				return 30;
			case 15:
			case 16:
			case 17:
			case 18:
			case 19:
				return 40;
			case 20:
			case 21:
			case 22:
			case 23:
			case 24:
				return 50;
			case 25:
			case 26:
			case 27:
			case 28:
				return 75;
			case 29:
				return 250;
			default:
				return 0;
		}
	}

	private static String getFormattedPrestigeXPMultiplier(final int prestige) {
		switch (prestige) {
			case 0:
				return "+10%";
			case 1:
				return "+20%";
			case 2:
				return "+30%";
			case 3:
				return "+40%";
			case 4:
				return "+50%";
			case 5:
				return "+75%";
			case 6:
				return "+100%";
			case 7:
				return "+150%";
			case 8:
				return "+200%";
			case 9:
				return "+300%";
			case 10:
				return "+400%";
			case 11:
				return "+500%";
			case 12:
				return "+600%";
			case 13:
				return "+700%";
			case 14:
				return "+800%";
			case 15:
				return "+900%";
			case 16:
				return "+1100%";
			case 17:
				return "+1300%";
			case 18:
				return "+1500%";
			case 19:
				return "+1700%";
			case 20:
				return "+1900%";
			case 21:
				return "+2300%";
			case 22:
				return "+2700%";
			case 23:
				return "+3100%";
			case 24:
				return "+3500%";
			case 25:
				return "+3900%";
			case 26:
				return "+4400%";
			case 27:
				return "+4900%";
			case 28:
				return "+7400%";
			case 29:
				return "+9900%";
			default:
				return "+1%";
		}
	}

	private static String getPrestigeColor(final int prestige) {
		if(prestige < 1) {
			return "$7";
		}
		if(prestige < 5) {
			return "§9";
		}
		if(prestige < 10) {
			return "$e";
		}
		if(prestige < 15) {
			return "§6";
		}
		if(prestige < 20) {
			return "§c";
		}
		if(prestige < 25) {
			return "§5";
		}
		if(prestige < 30) {
			return "§d";
		}
		if(prestige == 30) {
			return "§f";
		}
		return "§7";
	}

	private static String getRomanPrestige(final int prestige) {

		switch (prestige) {
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
				return "";
		}
	}
}
