package zaydd.pw.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

public class WeatherCancelListener implements Listener {
	
  @EventHandler
  public void fuckoffWeather(WeatherChangeEvent e)
  {
    e.setCancelled(true);
  }
}
