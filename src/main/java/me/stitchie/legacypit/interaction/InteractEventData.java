package me.stitchie.legacypit.interaction;

import lombok.Getter;
import me.stitchie.legacypit.player.PlayerCache;
import org.bukkit.event.Event;

@Getter
public class InteractEventData {

	private final EventType type;
	private final Event event;
	private final PlayerCache damagerCache;
	private final PlayerCache damagedCache;

	public InteractEventData(final EventType type, final Event event, final PlayerCache damagerCache, final PlayerCache damagedCache) {
		this.type = type;
		this.event = event;
		this.damagerCache = damagerCache;
		this.damagedCache = damagedCache;
	}
}
