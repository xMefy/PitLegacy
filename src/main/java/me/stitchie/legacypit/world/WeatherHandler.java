package me.stitchie.legacypit.world;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

public class WeatherHandler implements Listener {

	@EventHandler
	private void onWeatherChange(final WeatherChangeEvent event) {
		event.setCancelled(true);
	}

}
