package zaydd.pw.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class StaffOnQuitListener implements Listener {

    @EventHandler(priority=EventPriority.HIGHEST)
    public void handleQuitEvent(PlayerQuitEvent e)
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
                    online.sendMessage(ChatColor.BLUE + "[Staff] " + ChatColor.AQUA + player.getName() + ChatColor.AQUA + " has left " + ChatColor.AQUA + "the server.");
                }
            }
        }
        e.setQuitMessage(null);
    }
}
