package zaydd.pw.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class StaffOnJoinListener implements Listener {
	
  @EventHandler(priority=EventPriority.HIGHEST)
  public void handleJoinEvent(PlayerJoinEvent e)
  {
    Player player = e.getPlayer();
    if (player.hasPermission("core.staff"))
    {
      Player[] arrayOfPlayer;
      int j = (arrayOfPlayer = Bukkit.getOnlinePlayers()).length;
      for (int i = 0; i < j; i++)
      {
        Player online = arrayOfPlayer[i];
        if ((online.isOp()) || (online.hasPermission("core.staff"))) {
          online.sendMessage(ChatColor.BLUE + "[Staff] " + ChatColor.AQUA + player.getName() + ChatColor.AQUA + " has joined " + ChatColor.AQUA + "the server.");
        }
      }
    }
    e.setJoinMessage(null);
  }
}
