package me.stitchie.legacypit.events;

import lombok.Getter;
import lombok.Setter;
import me.stitchie.legacypit.events.majorevents.MajorEvents;
import me.stitchie.legacypit.events.minorevents.MinorEvents;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

@Getter
@Setter
public class EventGroup {

	private final static int MINOR_MIN_INTERVAL = 4;
	private final static int MINOR_MAX_INTERVAL = 12;
	private final static int MAJOR_MIN_INTERVAL = 50;
	private final static int MAJOR_MAX_INTERVAL = 70;

	private final static MajorEvents[] majorEventsList = MajorEvents.values();
	private final static MinorEvents[] minorEventsList = MinorEvents.values();

	private final MajorEvents majorEvent;
	private final LocalDateTime majorEventTime;
	private final LocalDateTime previousMajorEventTime;
	private final ArrayList<MinorEventTime> minorEvents;

	public EventGroup(final ArrayList<EventGroup> currentEvents) {

		this.previousMajorEventTime = currentEvents.size() == 0 ? LocalDateTime.now() : currentEvents.get(currentEvents.size() - 1).getMajorEventTime();

		MajorEvents tempMajorEvent;

		final int majorInterval = randomMajorInterval();

		if (currentEvents.size() == 0) {
			tempMajorEvent = randomMajorEvent();
			this.majorEventTime = LocalDateTime.now().plusMinutes(majorInterval);
		} else {
			do {
				tempMajorEvent = randomMajorEvent();
			} while (currentEvents.get(currentEvents.size() - 1).getMajorEvent() == tempMajorEvent);
			this.majorEventTime = currentEvents.get(currentEvents.size() - 1).getMajorEventTime().plusMinutes(majorInterval);
		}

		// +00h10m
		// &7+&e &7:

		this.majorEvent = tempMajorEvent;
		this.minorEvents = generateMinorEvents(majorInterval);
	}

	private int randomMinorInterval() {
		return ThreadLocalRandom.current().nextInt(MINOR_MIN_INTERVAL, MINOR_MAX_INTERVAL);
	}

	private int randomMajorInterval() {
		return ThreadLocalRandom.current().nextInt(MAJOR_MIN_INTERVAL, MAJOR_MAX_INTERVAL);
	}

	private MinorEvents randomMinorEvent() {
		return minorEventsList[ThreadLocalRandom.current().nextInt(minorEventsList.length)];
	}

	private MajorEvents randomMajorEvent() {
		return majorEventsList[ThreadLocalRandom.current().nextInt(majorEventsList.length)];
	}

	private ArrayList<MinorEventTime> generateMinorEvents(final int majorInterval) {
		final ArrayList<MinorEventTime> minorEvents = new ArrayList<>();

		int minutes = 0;
		final int firstInterval = randomMinorInterval();
		minutes += firstInterval;

		minorEvents.add(new MinorEventTime(previousMajorEventTime.plusMinutes(firstInterval), randomMinorEvent()));

		MinorEvents tempEvent;

		while ((minutes + 3) < majorInterval) {

			do {
				tempEvent = randomMinorEvent();
			} while (minorEvents.get(minorEvents.size() - 1).getMinorEvent() == tempEvent);

			final int interval = randomMinorInterval();
			minutes += interval;

			if ((minutes + 3) < majorInterval) {

				minorEvents.add(new MinorEventTime(previousMajorEventTime.plusMinutes(minutes), tempEvent));

			}
		}

		return minorEvents;
	}
}
