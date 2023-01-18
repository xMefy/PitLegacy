package me.stitchie.legacypit.menu;

import me.stitchie.legacypit.events.EventGroup;
import me.stitchie.legacypit.events.MinorEventTime;
import me.stitchie.legacypit.events.majorevents.MajorEvents;
import me.stitchie.legacypit.events.minorevents.MinorEvents;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class EventsListMenu implements PlayerMenu {

	private final Inventory inventory = Bukkit.createInventory(this, 54, "Upcoming Events");
	private final ArrayList<EventGroup> events;

	public EventsListMenu(final ArrayList<EventGroup> events) {
		this.events = events;
	}

	@Override
	public Inventory getInventory() {
		return inventory;
	}

	@Override
	public boolean onClick(final Player player, final int slot, final ClickType type, final ItemStack currentItem) {
		return true;
	}

	@Override
	public void onOpen(final Player player) {

		int slot = 0;

		final LocalDateTime currentTime = LocalDateTime.now();

		for (final EventGroup eventGroup : events) {

			final ArrayList<MinorEventTime> minorEvents = eventGroup.getMinorEvents();

			final MajorEvents majorEvent = eventGroup.getMajorEvent();

			final ItemStack eventIcon = getMajorEventIcon(majorEvent);
			final ItemMeta eventMeta = eventIcon.getItemMeta();
			eventMeta.setDisplayName(ChatColor.YELLOW + "Major #" + (slot + 1));
			final ArrayList<String> minorEventInfo = new ArrayList<>();

			for (final MinorEventTime minorEvent : minorEvents) {
				final MinorEvents event = minorEvent.getMinorEvent();
				final LocalDateTime time = minorEvent.getTime();
				final long timeBetween = Math.abs(ChronoUnit.MINUTES.between(time, currentTime));

				if (timeBetween < 1) {

					minorEventInfo.add(ChatColor.GRAY + "+" + ChatColor.YELLOW + "Imminently" + ChatColor.GRAY + ": " + getMinorEventName(event));

				} else if (timeBetween < 1440) {

					final int hours = (int) timeBetween / 60;
					final int minutes = (int) (timeBetween % 60);

					final String timeFormat = String.format("%02dh%02dm", hours, minutes);

					minorEventInfo.add(ChatColor.GRAY + "+" + ChatColor.YELLOW + timeFormat + ChatColor.GRAY + ": " + getMinorEventName(event));

				} else {

					final int days = (int) (timeBetween / 60 / 24);
					final int hours = (int) (timeBetween / 60) - (days * 24);

					final String timeFormat = String.format("%02dh", hours);

					minorEventInfo.add(ChatColor.GRAY + "+" + ChatColor.YELLOW + days + "d" + timeFormat + ChatColor.GRAY + ": " + getMinorEventName(event));

				}
			}

			final LocalDateTime time = eventGroup.getMajorEventTime();

			final long timeBetween = Math.abs(ChronoUnit.MINUTES.between(time, currentTime));

			if (timeBetween < 1) {

				minorEventInfo.add(ChatColor.GRAY + "+" + ChatColor.YELLOW + "Imminently" + ChatColor.GRAY + ": " + getMajorEventName(majorEvent));

			} else if (timeBetween < 1440) {

				final int hours = (int) timeBetween / 60;
				final int minutes = (int) (timeBetween % 60);

				final String timeFormat = String.format("%02dh%02dm", hours, minutes);

				minorEventInfo.add(ChatColor.GRAY + "+" + ChatColor.YELLOW + timeFormat + ChatColor.GRAY + ": " + getMajorEventName(majorEvent));

			} else {

				final int days = (int) (timeBetween / 60 / 24);
				final int hours = (int) (timeBetween / 60) - (days * 24);

				final String timeFormat = String.format("%02dh", hours);

				minorEventInfo.add(ChatColor.GRAY + "+" + ChatColor.YELLOW + days + "d" + timeFormat + ChatColor.GRAY + ": " + getMajorEventName(majorEvent));

			}

			eventMeta.setLore(minorEventInfo);
			eventIcon.setItemMeta(eventMeta);

			inventory.setItem(slot, eventIcon);

			slot++;

		}
	}

	private ItemStack getMajorEventIcon(final MajorEvents majorEvents) {
		switch (majorEvents) {
			case RAGE_PIT:
				return new ItemStack(Material.BAKED_POTATO, 1);
			case TEAM_DEATHMATCH:
				return new ItemStack(Material.WOOL, 1, (short) 10);
			case RAFFLE:
				return new ItemStack(Material.NAME_TAG, 1);
			case BEAST:
				return new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
			case SQUADS:
				return new ItemStack(Material.BANNER, 1, (short) 12);
			case BLOCKHEAD:
				return new ItemStack(Material.BRICK, 1);
			case ROBBERY:
				return new ItemStack(Material.GOLD_NUGGET, 1);
			case SPIRE:
				return new ItemStack(Material.MAGMA_CREAM, 1);
			case PIZZA:
				return new ItemStack(Material.COOKIE, 1);
			default:
				return new ItemStack(Material.AIR, 1);
		}
	}

	private String getMinorEventName(final MinorEvents minorEvent) {
		switch (minorEvent) {
			case AUCTION:
				return ChatColor.YELLOW + "Auction";
			case CARE_PACKAGE:
				return ChatColor.GOLD + "Care Package";
			case BOUNTY:
				return ChatColor.GOLD + "All bounty";
			case GIANT_CAKE:
				return ChatColor.LIGHT_PURPLE + "Giant Cake";
			case QUICK_MATHS:
				return ChatColor.LIGHT_PURPLE + "Quick Maths";
			case DRAGON_EGG:
				return ChatColor.LIGHT_PURPLE + "Dragon Egg";
			case DOUBLE_REWARDS:
				return ChatColor.GREEN + "2x Rewards";
			case KOTH:
				return ChatColor.AQUA + "KOTH";
			case KOTL:
				return ChatColor.GREEN + "KOTL";
			default:
				return "";
		}
	}

	private String getMajorEventName(final MajorEvents majorEvent) {
		switch (majorEvent) {
			case RAGE_PIT:
				return ChatColor.RED + "Rage Pit";
			case TEAM_DEATHMATCH:
				return ChatColor.LIGHT_PURPLE + "Team Deathmatch";
			case RAFFLE:
				return ChatColor.GOLD + "Raffle";
			case BEAST:
				return ChatColor.GREEN + "Beast";
			case SQUADS:
				return ChatColor.AQUA + "Squads";
			case BLOCKHEAD:
				return ChatColor.BLUE + "Blockhead";
			case ROBBERY:
				return ChatColor.GOLD + "Robbery";
			case SPIRE:
				return ChatColor.LIGHT_PURPLE + "Spire";
			case PIZZA:
				return ChatColor.RED + "Pizza";
			default:
				return "";
		}
	}
}
