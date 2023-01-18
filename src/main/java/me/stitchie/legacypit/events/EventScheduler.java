package me.stitchie.legacypit.events;

import lombok.Getter;
import me.stitchie.legacypit.LegacyPit;
import me.stitchie.legacypit.events.majorevents.MajorEvents;
import me.stitchie.legacypit.events.minorevents.MinorEvents;
import me.stitchie.legacypit.util.PitUtility;
import org.bukkit.*;
import org.bukkit.scheduler.BukkitRunnable;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Getter
public class EventScheduler {

	private final LegacyPit plugin = LegacyPit.getPlugin();
	private final ArrayList<EventGroup> scheduledEvents = new ArrayList<>();

	public EventScheduler() {
		generateEvents();
		eventChecker();
	}

	private void eventChecker() {
		new BukkitRunnable() {
			@Override
			public void run() {

				if(! scheduledEvents.isEmpty()) {

					final EventGroup eventGroup = scheduledEvents.get(0);
					final LocalDateTime majorTime = eventGroup.getMajorEventTime();
					// final LocalDateTime previousTime = eventGroup.getPreviousMajorEventTime();
					final ArrayList<MinorEventTime> minorEvents = eventGroup.getMinorEvents();

					if(LocalDateTime.now().isAfter(majorTime)) {

						final MajorEvents event = eventGroup.getMajorEvent();

						Bukkit.broadcastMessage(ChatColor.RED + "MajorEvent Started: " + event.name());

						PitUtility.playSoundToAll(Sound.ENDERDRAGON_GROWL);

						scheduledEvents.remove(0);

					}else
						if(! minorEvents.isEmpty()) {

							final MinorEventTime minorEventTime = minorEvents.get(0); // TODO: FIX EMPTY MINOREVENTS
							// GET
							final LocalDateTime nextMinorEvent = minorEventTime.getTime();

							if(LocalDateTime.now().isAfter(nextMinorEvent)) {

								final MinorEvents event = minorEventTime.getMinorEvent();
								Bukkit.broadcastMessage(ChatColor.AQUA + "MinorEvent Started: " + event.name());
								PitUtility.playSoundToAll(Sound.NOTE_PLING, 1.2f);
								eventGroup.getMinorEvents().remove(0);
							}
						}
				}


			}
		}.runTaskTimer(plugin, 0, 20 * 60);
	}

	private void generateEvents() {
		while (scheduledEvents.size() < 54) {
			scheduledEvents.add(new EventGroup(scheduledEvents));
		}
	}

	private void playMinorEventSound() {

		PitUtility.playNoteToAll(Instrument.PIANO, Note.natural(1, Note.Tone.A));

		new BukkitRunnable() {
			@Override
			public void run() {
				PitUtility.playNoteToAll(Instrument.PIANO, Note.natural(1, Note.Tone.B));
			}
		}.runTaskLater(plugin, 2);

		new BukkitRunnable() {
			@Override
			public void run() {
				PitUtility.playNoteToAll(Instrument.PIANO, Note.natural(1, Note.Tone.C));
			}
		}.runTaskLater(plugin, 4);
	}
}
