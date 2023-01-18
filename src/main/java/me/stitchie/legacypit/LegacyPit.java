package me.stitchie.legacypit;

import lombok.Getter;
import me.stitchie.legacypit.chat.PlayerChat;
import me.stitchie.legacypit.interaction.customevents.CustomEventHandler;
import me.stitchie.legacypit.perks.PerkEventHandler;
import me.stitchie.legacypit.player.*;
import me.stitchie.legacypit.world.*;
import me.stitchie.legacypit.world.spawn.MenuHandler;
import me.stitchie.legacypit.world.spawn.NPCSpawner;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

@Getter
public class LegacyPit extends JavaPlugin {

	private final PluginManager manager = Bukkit.getPluginManager();
	private final ArrayList<Listener> listeners = new ArrayList<>();
	private static LegacyPit plugin;

	@Override
	public void onEnable() {

		plugin = this;

		NPCSpawner.INSTANCE.spawnNPCs();

		listeners.add(new RegionHandler());
		listeners.add(new WorldHandler());
		listeners.add(new WeatherHandler());
		listeners.add(new PlayerCacheHandler());
		listeners.add(new MenuHandler());
		listeners.add(new CoinPickup());
		listeners.add(new ActionBarHP());
		listeners.add(new KillHandler());
		listeners.add(new BlockRemovalHandler());
		listeners.add(new MushroomCollecter());
		listeners.add(new PlayerRespawnHandler());
		listeners.add(new BountyAnimation());
		listeners.add(new CombatTag());
		listeners.add(new PlayerChat());
		listeners.add(new PerkEventHandler());
		listeners.add(new CustomEventHandler());

		registerListeners();

	}

	private void registerListeners() {
		for (final Listener listener : listeners) {
			manager.registerEvents(listener, LegacyPit.getPlugin());
		}
	}

	@Override
	public void onDisable() {

		// MySQL.disconnect();

		// private HashMap<UUID, NPCType> npcUUIDs = new HashMap<>();

	}

	public static LegacyPit getPlugin() {
		return plugin;
	}

}
