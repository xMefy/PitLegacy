package me.stitchie.legacypit.commands;

import me.stitchie.legacypit.menu.TNTMenu;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GuiCommand implements CommandExecutor {

	@Override
	public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {

		if (sender instanceof Player) {

			final Player player = (Player) sender;

			player.openInventory(new TNTMenu().getInventory());

			return true;
		}

		return false;
	}
}
