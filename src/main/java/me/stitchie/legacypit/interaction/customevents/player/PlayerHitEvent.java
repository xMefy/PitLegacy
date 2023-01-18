package me.stitchie.legacypit.interaction.customevents.player;

import lombok.Getter;
import me.stitchie.legacypit.interaction.DamageType;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

@Getter
public class PlayerHitEvent extends EntityDamageByEntityEvent implements Cancellable {

	private static final HandlerList handlers = new HandlerList();
	private boolean cancelled;
	private final Player damager;
	private final Player damaged;
	private double damage;
	private final EntityDamageEvent event;

	public PlayerHitEvent(final EntityDamageEvent event, final Player damager, final Player damaged, final double damage, final DamageCause cause) {
		super(damager, damaged, cause, damage);
		this.damager = damager;
		this.damaged = damaged;
		this.damage = damage;
		this.event = event;
	}

	public void addDamage(final double amount, final DamageType type) {
		if (type == DamageType.MULTIPLICATIVE) {
			event.setDamage(this.damage *= amount);
		} else {
			event.setDamage(this.damage += amount);
		}
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
}
