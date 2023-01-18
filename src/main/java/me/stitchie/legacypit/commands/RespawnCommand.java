package me.stitchie.legacypit.commands;

import me.stitchie.legacypit.LegacyPit;
import me.stitchie.legacypit.player.PlayerCache;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Map;

public class RespawnCommand implements CommandExecutor {

	private final LegacyPit plugin;
	private final Map<Player, Integer> combatTagData;
	private final World world = Bukkit.getWorld("LegacyPit");
	private final Location spawnLocation = new Location(world, -11.5, 125, 0.5, -90, 0);

	public RespawnCommand(final LegacyPit plugin, final Map<Player, Integer> combatTagData) {
		this.plugin = plugin;
		this.combatTagData = combatTagData;
	}

	@Override
	public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {

		if (sender instanceof Player) {

			if (args.length == 1) {

				final Player player = (Player) sender;

				final PlayerCache playerCache = plugin.getPlayerData().get(player);

				playerCache.addXP(Integer.parseInt(args[0]));

				if (combatTagData.containsKey(player)) {
					player.sendMessage(ChatColor.RED + "You can't spawn while in combat!");
					return false;
				}

				player.teleport(spawnLocation);
				return true;
			}
		}
		return false;
	}
}
