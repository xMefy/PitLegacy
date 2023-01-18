package me.stitchie.legacypit.chat;

import me.stitchie.legacypit.player.PlayerCache;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.Map;

public class PlayerChat implements Listener {

	private final Map<Player, PlayerCache> playerData;

	public PlayerChat(final Map<Player, PlayerCache> playerData) {
		this.playerData = playerData;
	}

	@EventHandler(ignoreCancelled = true)
	private void onMessageSent(final AsyncPlayerChatEvent event) {

		final Player player = event.getPlayer();
		final PlayerCache playerCache = playerData.get(event.getPlayer());
		final String prestigeBracketColor = playerCache.getPrestigeBracketColor();
		final String level = prestigeBracketColor + "[" + playerCache.getLevelColor() + playerCache.getLevel() + prestigeBracketColor + "]";

		Bukkit.broadcastMessage(level + " " + player.getName() + ": " + ChatColor.WHITE + event.getMessage());

		event.setCancelled(true);
	}
}
