package me.stitchie.legacypit.world;

import me.stitchie.legacypit.LegacyPit;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.util.Vector;

public class RegionHandler implements Listener {

	private final LegacyPit plugin = LegacyPit.getPlugin();
	private final World world = Bukkit.getWorld("LegacyPit");

	private final Location spawn1 = new Location(world, 24, 91, - 23);
	private final Location spawn2 = new Location(world, - 22, 120, 23);

	private final Location spawnarea1 = new Location(world, - 7, 69, - 7);
	private final Location spawnarea2 = new Location(world, 7, 92, 7);

	public RegionHandler() {
		// playableAreaCheck();
	}

	@EventHandler(priority = EventPriority.LOWEST)
	private void onHit(final EntityDamageByEntityEvent event) {

		if(event.getEntityType() == EntityType.VILLAGER || event.getEntityType() == EntityType.ARMOR_STAND) {
			event.setCancelled(true);
			return;
		}

		if(isInRegion(event.getEntity().getLocation(), spawn1, spawn2)) {
			event.setCancelled(true);
		}
	}

	@EventHandler(priority = EventPriority.LOWEST)
	private void onArrowShoot(final EntityShootBowEvent event) {
		/*
		if (event.getProjectile() instanceof Arrow) {
			if (((Arrow) event.getProjectile()).getShooter() instanceof Player) {
				final Player player = (Player) ((Arrow) event.getProjectile()).getShooter();
				if (player != null && isInRegion((player.getLocation()), spawn1, spawn2)) {
					event.setCancelled(true);
					player.playSound(player.getLocation(), Sound.VILLAGER_NO, 10, 1);
				}
			}
		}

		 */
	}

	@EventHandler(priority = EventPriority.LOWEST)
	private void onBlockBreak(final BlockBreakEvent event) {

		final Block block = event.getBlock();

		if(block.getType() != Material.OBSIDIAN && ! event.getPlayer().isOp()) {
			event.setCancelled(true);
		}

		/*
		if ((!block.getType().equals(Material.OBSIDIAN)) || isInRegion(block.getLocation(), spawn1, spawn2) && !event
		.getPlayer().isOp()) {
			event.setCancelled(true);
		}

		 */

	}

	@EventHandler(priority = EventPriority.LOWEST)
	private void onBlockPlace(final BlockPlaceEvent event) {

		final Block block = event.getBlock();

		if(block.getType() != Material.OBSIDIAN && ! event.getPlayer().isOp()) {
			event.setCancelled(true);
		}

		/*
		if ((isInRegion(block.getLocation(), spawn1, spawn2) || isInRegion(block.getLocation(), spawnarea1,
		spawnarea2) || !block.getType().equals(Material.OBSIDIAN)) && !event.getPlayer().isOp()) {
			event.setCancelled(true);
		}

		 */
	}

	public static boolean isInRegion(final Location playerLocation, final Location lowestPos,
			final Location highestPos) {

		// TODO: cache values

		final double x1 = lowestPos.getX();
		final double y1 = lowestPos.getY();
		final double z1 = lowestPos.getZ();

		final double x2 = highestPos.getX();
		final double y2 = highestPos.getY();
		final double z2 = highestPos.getZ();

		final Vector curr = new Vector(playerLocation.getX(), playerLocation.getY(), playerLocation.getZ());
		final Vector min = new Vector(Math.min(x1, x2), Math.min(y1, y2), Math.min(z1, z2));
		final Vector max = new Vector(Math.max(x1, x2), Math.max(y1, y2), Math.max(z1, z2));

		return curr.isInAABB(min, max);
	}

	private void playableAreaCheck() {

		final Location map1 = new Location(world, - 130, 30, 144);
		final Location map2 = new Location(world, 140, 250, - 160);

		// TODO: change to bukkitrunnable

		Bukkit.getScheduler().runTaskTimer(plugin, () -> {

			for (final Player player : Bukkit.getOnlinePlayers()) {

				if(! isInRegion(player.getLocation(), map1, map2)) {

					player.sendMessage(ChatColor.RED + "Don't escape the map!");

				}
			}
		}, 0, 60);
	}
}

