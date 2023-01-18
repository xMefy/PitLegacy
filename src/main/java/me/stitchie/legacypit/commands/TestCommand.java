package me.stitchie.legacypit.commands;

import me.stitchie.legacypit.util.PitEvent;
import me.stitchie.legacypit.util.PitUtility;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TestCommand implements CommandExecutor {
	@Override
	public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {

		if (sender instanceof Player) {

			final Player player = (Player) sender;

			if (args.length == 1) {

				final Player testPlayer = Bukkit.getPlayer(args[0]);

				PitUtility.callEvent(PitEvent.PLAYER_DEATH, testPlayer);

				return true;
			}
		}
		return false;
	}
}
