package me.stitchie.legacypit.util;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import org.bukkit.*;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.PluginManager;

public final class PitUtility {

	private PitUtility() {
	}

	public static void playSoundToAll(final Sound sound) {
		for (final Player player : Bukkit.getOnlinePlayers()) {
			player.playSound(player.getLocation(), sound, 10, 1);
		}
	}

	public static void playSoundToAll(final Sound sound, final float pitch) {
		for (final Player player : Bukkit.getOnlinePlayers()) {
			player.playSound(player.getLocation(), sound, 10, pitch);
		}
	}

	public static void callEvent(final PitEvent event, final Player player) {

		final PluginManager pluginManager = Bukkit.getServer().getPluginManager();

		switch (event) {
			case PLAYER_DEATH:
				pluginManager.callEvent(new PlayerDeathEvent(player, null, 0, null));
				break;
		}
	}

	public static void playSoundToAll(final Sound sound, final Location location, final float pitch) {
		for (final Player player : Bukkit.getOnlinePlayers()) {
			player.playSound(location, sound, 10, pitch);
		}
	}

	public static void playNoteToAll(final Instrument instrument, final Note note) {
		for (final Player player : Bukkit.getOnlinePlayers()) {
			player.playNote(player.getLocation(), instrument, note);
		}
	}

	public static void sendActionbar(final Player player, final String text) {
		final PacketPlayOutChat packet = new PacketPlayOutChat(IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + text + "\"}"), (byte) 2);
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
	}

	public static void sendTitle(final Player player, final String title) {
		final IChatBaseComponent chatTitle = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + title + "\"}");
		final PacketPlayOutTitle titlepacket = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, chatTitle);
		final PacketPlayOutTitle length = new PacketPlayOutTitle(5, 60, 5);
		final PlayerConnection playerConnection = ((CraftPlayer) player).getHandle().playerConnection;
		playerConnection.sendPacket(titlepacket);
		playerConnection.sendPacket(length);
	}

	public static void sendTitleAndSubTitle(final Player player, final String title, final String subtitle) {
		final IChatBaseComponent chatTitle = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + title + "\"}");
		final IChatBaseComponent chatSubTitle = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + subtitle + "\"}");

		final PacketPlayOutTitle titlepacket = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, chatTitle);
		final PacketPlayOutTitle subtitlepacket = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, chatSubTitle);
		final PacketPlayOutTitle length = new PacketPlayOutTitle(5, 60, 5);

		final PlayerConnection playerConnection = ((CraftPlayer) player).getHandle().playerConnection;

		playerConnection.sendPacket(titlepacket);
		playerConnection.sendPacket(subtitlepacket);
		playerConnection.sendPacket(length);
	}
}
