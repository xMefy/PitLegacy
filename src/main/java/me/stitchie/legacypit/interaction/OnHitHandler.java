package me.stitchie.legacypit.interaction;

import me.stitchie.legacypit.interaction.managers.OnHitManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class OnHitHandler implements Listener {

	private final OnHitManager hitManager;

	public OnHitHandler(final OnHitManager hitManager) {
		this.hitManager = hitManager;
	}

	@EventHandler(ignoreCancelled = true, priority = EventPriority.NORMAL)
	private void onHit(final EntityDamageByEntityEvent event) {


	}

}
