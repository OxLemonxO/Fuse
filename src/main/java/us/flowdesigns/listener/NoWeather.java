package us.flowdesigns.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

import static us.flowdesigns.fuse.Fuse.plugin;

public class NoWeather implements Listener
{
    String weatherenabled = plugin.getConfig().getString("server.weather_enabled");

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent event) {
        if (!weatherenabled.equalsIgnoreCase("true")) {
            event.setCancelled(true);
        }
    }
}