package me.stitchie.legacypit.player;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import me.stitchie.legacypit.LegacyPit;
import me.stitchie.legacypit.util.TaskDelay;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.*;

public class BountyAnimation implements Listener {

	private final LegacyPit plugin;
	private final Random random = new Random();
	private final Map<Player, PlayerCache> playerData;
	private final HashSet<Player> bountyList = new HashSet<>();
	private final ArrayList<BountyHologram> hologramList = new ArrayList<>();
	private int hologramTimer = 0;

	public BountyAnimation(final LegacyPit plugin, final Map<Player, PlayerCache> playerData) {
		this.plugin = plugin;
		this.playerData = playerData;
		checkBounties();
		startAnimation();
	}

	@EventHandler
	private void onPlayerQuit(final PlayerQuitEvent event) {
		bountyList.remove(event.getPlayer());
	}

	private void checkBounties() {
		new BukkitRunnable() {
			@Override
			public void run() {
				for (final Map.Entry<Player, PlayerCache> entry : playerData.entrySet()) {
					final Player player = entry.getKey();
					final PlayerCache playerCache = entry.getValue();
					if (playerCache.getBounty() > 0) {
						bountyList.add(player);
					}
				}
			}
		}.runTaskTimer(plugin, TaskDelay.getNextTaskDelay(), 20L);
	}

	private void startAnimation() {
		new BukkitRunnable() {
			@Override
			public void run() {

				final Iterator<BountyHologram> iterator = hologramList.iterator();

				while (iterator.hasNext()) {

					final BountyHologram bountyHologram = iterator.next();

					final Hologram hologram = bountyHologram.getHologram();

					for (final Player player : Bukkit.getOnlinePlayers()) {
						if (player == bountyHologram.getPlayer()) {
							hologram.getVisibilityManager().hideTo(player);
							continue;
						}
						if (player.getLocation().distance(bountyHologram.getPlayer().getLocation()) < 6) {
							hologram.getVisibilityManager().hideTo(player);
						} else {
							hologram.getVisibilityManager().showTo(player);
						}
					}

					bountyHologram.increaseTimer();

					hologram.teleport(bountyHologram.getPlayer().getLocation()
							.add(0, bountyHologram.getCurrentTime() / 5.0, 0)
							.add(bountyHologram.getLocationOffset()));

					if (bountyHologram.getCurrentTime() > 10) {
						bountyHologram.delete();
						iterator.remove();
					}
				}

				if (hologramTimer % 4 == 0) {

					for (final Player player : bountyList) {

						final float randomX = (random.nextFloat() * 1.2f) - 0.6f;
						final float randomZ = (random.nextFloat() * 1.2f) - 0.6f;

						final Vector offset = new Vector(randomX, -0.5, randomZ);

						final Location location = player.getLocation().add(offset);

						final Hologram newHologram = HologramsAPI.createHologram(plugin, location);

						newHologram.getVisibilityManager().setVisibleByDefault(false);

						newHologram.teleport(location.add(0, 0.2, 0));
						hologramList.add(new BountyHologram(newHologram, new Vector(randomX, 0, randomZ),
								playerData.get(player).getBounty(), player));
					}
					hologramTimer = 0;
				}
				hologramTimer++;

			}
		}.runTaskTimer(plugin, 0L, 1L);
	}
}
