package me.stitchie.legacypit.commands;

import me.stitchie.legacypit.events.EventGroup;
import me.stitchie.legacypit.menu.EventsListMenu;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class EventsCommand implements CommandExecutor {

	private final ArrayList<EventGroup> events;

	public EventsCommand(final ArrayList<EventGroup> events) {
		this.events = events;
	}


	@Override
	public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {

		if (sender instanceof Player) {

			final Player player = (Player) sender;

			player.openInventory(new EventsListMenu(events).getInventory());
			player.playSound(player.getLocation(), Sound.LAVA_POP, 10, 1);

			return true;
		}

		return false;
	}
}
