package me.stitchie.legacypit.player;

import me.stitchie.legacypit.LegacyPit;
import me.stitchie.legacypit.util.PitUtility;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.text.DecimalFormat;

public class ActionBarHP implements Listener {

	private final LegacyPit plugin;

	public ActionBarHP(final LegacyPit plugin) {
		this.plugin = plugin;
	}

	@EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
	private void onHit(final EntityDamageByEntityEvent event) {

		if (event.getDamager() instanceof Player) {

			final LivingEntity whoWasHit = (LivingEntity) event.getEntity();
			final Player whoHit = (Player) event.getDamager();

			final double healthbeforedamage = whoWasHit.getHealth();

			Bukkit.getScheduler().runTaskLater(plugin, () -> {

				int hearts = (int) Math.round((whoWasHit.getHealth()) / 2);
				final int heartsbeforedamage = (int) (Math.round(healthbeforedamage) / 2) - hearts;
				final double truedamage = healthbeforedamage - whoWasHit.getHealth();
				final DecimalFormat display3digits = new DecimalFormat("#.###");
				final String truedamageoutput = display3digits.format(truedamage);
				final double health = whoWasHit.getHealth();
				if (health < 1) {
					hearts = 1;
				}
				final int maxhealth = (int) (whoWasHit.getMaxHealth() / 2) - hearts - heartsbeforedamage;

				if (health > 0) {

					final StringBuilder healthbar = new StringBuilder();

					healthbar.append(ChatColor.GRAY).append(whoWasHit.getName()).append(" ");

					for (int i = 0; i < hearts; i++) {
						healthbar.append(ChatColor.DARK_RED).append("❤");
					}

					for (int i = 0; i < heartsbeforedamage; i++) {
						healthbar.append(ChatColor.RED).append("❤");
					}

					for (int i = 0; i < maxhealth; i++) {
						healthbar.append(ChatColor.BLACK).append("❤");
					}

					if (whoWasHit instanceof Player) {

						final EntityPlayer craftPlayer = ((CraftPlayer) whoWasHit).getHandle();

						final int absHearts = (int) (craftPlayer.getAbsorptionHearts() / 2);

						for (int i = 0; i < absHearts; i++) {
							healthbar.append(ChatColor.YELLOW).append("❤");
						}
					}

					PitUtility.sendActionbar(whoHit, healthbar + " " + ChatColor.RED + truedamageoutput + "HP");

				}
			}, 0);
		}

		if (event.getDamager() instanceof Arrow) {

			final Arrow arrow = (Arrow) event.getDamager();

			if (arrow.getShooter() instanceof Player) {

				final Player whoHit = (Player) arrow.getShooter();
				final LivingEntity whoWasHit = (LivingEntity) event.getEntity();

				final double healthbeforedamage = whoWasHit.getHealth();

				Bukkit.getScheduler().runTaskLater(plugin, () -> {
					int hearts = (int) Math.round((whoWasHit.getHealth()) / 2);
					final int heartsbeforedamage = (int) (Math.round(healthbeforedamage) / 2) - hearts;
					final double truedamage = healthbeforedamage - whoWasHit.getHealth();
					final DecimalFormat display3digits = new DecimalFormat("#.###");
					final String truedamageoutput = display3digits.format(truedamage);
					final double health = whoWasHit.getHealth();
					if (health < 1) {
						hearts = 1;
					}
					final int maxhealth = (int) (whoWasHit.getMaxHealth() / 2) - hearts - heartsbeforedamage;

					if (health > 0) {

						final StringBuilder healthbar = new StringBuilder();

						healthbar.append(ChatColor.GRAY).append(whoWasHit.getName()).append(" ");

						for (int i = 0; i < hearts; i++) {
							healthbar.append(ChatColor.DARK_RED).append("❤");
						}

						for (int i = 0; i < heartsbeforedamage; i++) {
							healthbar.append(ChatColor.RED).append("❤");
						}

						for (int i = 0; i < maxhealth; i++) {
							healthbar.append(ChatColor.BLACK).append("❤");
						}

						if (whoWasHit instanceof Player) {

							final EntityPlayer craftPlayer = ((CraftPlayer) whoWasHit).getHandle();

							final int absHearts = (int) (craftPlayer.getAbsorptionHearts() / 2);

							for (int i = 0; i < absHearts; i++) {
								healthbar.append(ChatColor.YELLOW).append("❤");
							}
						}

						PitUtility.sendActionbar(whoHit, healthbar + " " + ChatColor.RED + truedamageoutput + "HP");

					}
				}, 0);
			}
		}
	}
}

