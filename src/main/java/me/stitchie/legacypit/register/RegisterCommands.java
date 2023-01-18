package me.stitchie.legacypit.register;

import me.stitchie.legacypit.LegacyPit;
import me.stitchie.legacypit.commands.*;
import me.stitchie.legacypit.events.EventGroup;
import me.stitchie.legacypit.player.PlayerCache;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Map;

public class RegisterCommands {

	private final LegacyPit plugin;
	private final Map<Player, Integer> combatTagData;
	private final Map<Player, PlayerCache> playerData;
	private final ArrayList<EventGroup> events;

	public RegisterCommands(final LegacyPit plugin) {
		this.plugin = plugin;
		this.combatTagData = plugin.getCombatTagData();
		this.playerData = plugin.getPlayerData();
		this.events = plugin.getEvents();
		registerCommands();
	}

	private void registerCommands() {
		registerCommand("setplayertag", new SetPrefixTest(playerData));
		registerCommand("spawn", new RespawnCommand(plugin, combatTagData));
		registerCommand("setbounty", new setBountyTest(playerData));
		registerCommand("events", new EventsCommand(events));
		registerCommand("testmethod", new TestCommand());
		registerCommand("tntgui", new GuiCommand());
	}

	private void registerCommand(final String commandName, final CommandExecutor executor) {
		plugin.getCommand(commandName).setExecutor(executor);
	}
}
