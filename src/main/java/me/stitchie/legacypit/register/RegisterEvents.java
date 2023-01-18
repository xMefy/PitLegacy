package me.stitchie.legacypit.register;

import me.stitchie.legacypit.LegacyPit;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;

import java.util.ArrayList;

public class RegisterEvents {

	private final PluginManager manager = Bukkit.getPluginManager();
	private final ArrayList<Listener> listeners = new ArrayList<>();

	public RegisterEvents(final Listener... listenerz) {

	
	}

	private void registerListeners() {
		for (final Listener listener : listeners) {
			manager.registerEvents(listener, LegacyPit.getPlugin());
		}
	}
}
