package me.stitchie.legacypit.commands;

import me.stitchie.legacypit.player.PlayerCache;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Map;

public class setBountyTest implements CommandExecutor {

	private final Map<Player, PlayerCache> playerData;

	public setBountyTest(final Map<Player, PlayerCache> playerData) {
		this.playerData = playerData;
	}

	@Override
	public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {

		if (sender instanceof Player) {

			final Player player = Bukkit.getPlayer(args[0]);

			final int bounty = Integer.parseInt(args[1]);

			final int level = Integer.parseInt(args[2]);

			final PlayerCache playerCache = playerData.get(player);

			playerCache.setBounty(bounty);
			playerCache.setLevel(level);

		}

		return true;
	}
}
