package me.stitchie.legacypit.world;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.*;

public class WorldHandler implements Listener {

	@EventHandler
	private void onHungerLoss(final FoodLevelChangeEvent event) {
		event.setCancelled(true);
	}

	@EventHandler
	private void onFallDamage(final EntityDamageEvent event) {
		if ((event.getCause() == EntityDamageEvent.DamageCause.FALL)) {
			event.setCancelled(true);
		}
	}

	@EventHandler
	private void onFireDamage(final EntityDamageEvent event) {
		if ((event.getCause() == EntityDamageEvent.DamageCause.FIRE_TICK)) {
			event.getEntity().setFireTicks(0);
			event.setCancelled(true);
		}
	}

	@EventHandler
	private void onSlimeSplit(final SlimeSplitEvent event) {
		event.setCancelled(true);
	}

	@EventHandler
	private void onBlockGrow(final BlockGrowEvent event) {
		event.setCancelled(true);
	}

	@EventHandler
	private void onBlockSpread(final BlockSpreadEvent event) {
		event.setCancelled(true);
	}

	@EventHandler
	private void onMobSpawn(final EntitySpawnEvent event) {
		if (!(event.getEntityType() == EntityType.VILLAGER || event.getEntityType() == EntityType.DROPPED_ITEM)) {
			event.setCancelled(true);
		}
	}

	@EventHandler
	private void onBlockFade(final BlockFadeEvent event) {
		event.setCancelled(true);
	}

	@EventHandler
	private void onBlockForm(final BlockFormEvent event) {
		event.setCancelled(true);
	}

	@EventHandler
	private void onBlockBurn(final BlockBurnEvent event) {
		event.setCancelled(true);
	}

	@EventHandler
	private void onExplode(final EntityExplodeEvent event) {
		event.setCancelled(true);
		if (event.getEntityType() == EntityType.PRIMED_TNT) {
			final Location location = event.getEntity().getLocation();
			location.getWorld().createExplosion(location.getBlockX(), location.getY(), location.getBlockZ(), 4F, false, false);
		}
	}

	@EventHandler
	private void onExpDrop(final BlockExpEvent event) {
		event.setExpToDrop(0);
	}

	@EventHandler
	private void onBlockExplode(final BlockExplodeEvent event) {
		event.setCancelled(true);
	}

	@EventHandler
	private void onBlockPlace(final BlockPlaceEvent event) {
		if (event.getBlock().getType() == Material.TNT) {
			event.setCancelled(true);
			final TNTPrimed tnt = (TNTPrimed) event.getPlayer().getWorld().spawnEntity(event.getBlock().getLocation(), EntityType.PRIMED_TNT);
			tnt.setFuseTicks(20);
		}
	}
}
