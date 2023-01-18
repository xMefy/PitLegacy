package me.stitchie.legacypit.commands;

import me.stitchie.legacypit.player.PlayerCache;
import me.stitchie.legacypit.util.NameTag;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Map;

public class SetPrefixTest implements CommandExecutor {

	private final Map<Player, PlayerCache> playerData;

	public SetPrefixTest(final Map<Player, PlayerCache> playerData) {
		this.playerData = playerData;
	}

	@Override
	public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {

		if (sender instanceof Player) {

			final Player player = Bukkit.getPlayer(args[0]);

			final PlayerCache playerCache = playerData.get(player);

			final int level = playerCache.getLevel();

			final String prefix = ChatColor.AQUA + "[" + ChatColor.RED + level + ChatColor.AQUA + "] " + ChatColor.RESET;

			final int bounty = playerCache.getBounty();
			final String bountyString = " " + ChatColor.GOLD + "" + ChatColor.BOLD + bounty + "g";

			NameTag.setNameTag(player, prefix, bountyString, playerCache);

		}
		return true;
	}
}
