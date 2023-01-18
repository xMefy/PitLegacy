package me.stitchie.legacypit.player;

import me.stitchie.legacypit.LegacyPit;
import me.stitchie.legacypit.util.PitUtility;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Map;

public class KillHandler implements Listener {

	private final LegacyPit plugin;
	private final Map<Player, PlayerCache> playerData;

	public KillHandler(final LegacyPit plugin, final Map<Player, PlayerCache> playerData) {
		this.plugin = plugin;
		this.playerData = playerData;
	}

	@EventHandler
	private void onKill(final PlayerDeathEvent event) {

		final Player killed = event.getEntity();
		final Player killer = killed.getKiller();

		if (killer == null || killer == killed) {
			return;
		}

		final PlayerCache killerData = playerData.get(killer);

		final int killstreak = killerData.getKillstreak();

		/*

		// Get XPBOOST and GOLDBOOST from player
		final int xpboosttier = killerData.getPlayerupgrades().get(Upgrades.XPBOOST);
		final int goldboosttier = killerData.getPlayerupgrades().get(Upgrades.GOLDBOOST);

		final float xpboost = xpboosttier != 0 ? (xpboosttier / 10f) + 1 : 1.0F;
		final float goldboost = goldboosttier != 0 ? (goldboosttier / 10f) + 1 : 1.0F;

		 */

		killerData.increaseKillstreak(1);

		if (killstreak == 5 || killstreak % 10 == 0) {
			Bukkit.broadcastMessage(ChatColor.RED + "" + ChatColor.BOLD + "STREAK!" + ChatColor.GRAY + " of " + ChatColor.RED + killstreak + ChatColor.GRAY + " kills by " + killer.getDisplayName());
		}

		sendActionBarKillMessage(killer);


		// Calculate killstreakbonusXP | 1.1x bonus every 10 kills
		final double killstreakbonusXP = killstreak <= 150 ? ((Math.floor(killstreak / 10.0)) / 10) + 1 : 3.0;

		final int XP = (int) (5 * killstreakbonusXP);
		final double gold = 10;

		killerData.addGold(gold);
		killerData.addXP(XP);

		// Send kill message to player
		sendKillMessage(killer, killed, killerData, gold, XP);

		// killerData.setLastKill(System.currentTimeMillis());

	}

	private void sendKillMessage(final Player player, final Player killed, final PlayerCache playerCache, final double gold, final int XP) {

		int multiKillStreak = playerCache.getMultikillstreak();

		if (lastKillCheck(playerCache.getLastKill())) {
			multiKillStreak += 1;
		} else {
			multiKillStreak = 1;
		}

		playerCache.setMultikillstreak(multiKillStreak);

		final String multikillname = getMultiKillstreak(multiKillStreak, player, killed);

		player.sendMessage(multikillname + killed.getDisplayName() + " §b+" + XP + "XP §6+" + formatGold(gold));

	}

	private boolean lastKillCheck(final long lastkill) {
		return lastkill + 3000 > System.currentTimeMillis();
	}

	private void sendActionBarKillMessage(final Player player) {
		new BukkitRunnable() {
			@Override
			public void run() {
				PitUtility.sendActionbar(player, "§7" + player.getDisplayName() + " §a§lKILL!");
			}
		}.runTaskLater(plugin, 0);
	}

	private void killStreakSound(final Player player, final Player killed, final int multikillstreak) {

		player.playSound(killed.getLocation(), Sound.ORB_PICKUP, 1, 1.6F);

		final int amount = Math.min(multikillstreak, 4);

		if (multikillstreak > 1) {
			for (int i = 1; i < amount + 1; i++) {
				final float pitch = 1.6F + (i * 0.1f);
				new BukkitRunnable() {
					@Override
					public void run() {
						player.playSound(killed.getLocation(), Sound.ORB_PICKUP, 1, pitch);
					}
				}.runTaskLater(plugin, i * 2);
			}
		}
	}

	private String getMultiKillstreak(final int multikillstreak, final Player player, final Player killed) {
		killStreakSound(player, killed, multikillstreak);
		switch (multikillstreak) {
			case 1:
				return "§a§lKILL! §7on ";
			case 2:
				return "§a§lDOUBLE KILL! §7on ";
			case 3:
				return "§a§lTRIPLE KILL! §7on ";
			case 4:
				return "§a§lQUADRA KILL! §7on ";
			case 5:
				return "§a§lPENTA KILL! §7on ";
			default:
				return "§a§lMULTI KILL! §7(" + (multikillstreak + 1) + ") on ";
		}
	}

	private String formatGold(final double gold) {
		final DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
		final DecimalFormat df = new DecimalFormat("#,###,##0.00", symbols);
		return "§6" + df.format(gold) + "g";
	}
}
