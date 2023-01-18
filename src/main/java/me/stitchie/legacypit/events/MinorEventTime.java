package me.stitchie.legacypit.events;

import lombok.Getter;
import me.stitchie.legacypit.events.minorevents.MinorEvents;

import java.time.LocalDateTime;

@Getter
public class MinorEventTime {

	private final LocalDateTime time;
	private final MinorEvents minorEvent;

	public MinorEventTime(final LocalDateTime time, final MinorEvents minorEvent) {
		this.time = time;
		this.minorEvent = minorEvent;
	}
}
