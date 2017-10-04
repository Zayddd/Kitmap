package zaydd.pw.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class onPlayerJoinListener implements Listener {

  @EventHandler
  public void onJoin(PlayerJoinEvent e)
  {
    Player p = e.getPlayer();
    
    p.sendMessage("");
    p.sendMessage("");
    p.sendMessage("");
    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&m----------------------------------------------"));
    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&eWelcome to &6<servername>&e, &d" + e.getPlayer() + "&e!"));
    p.sendMessage(ChatColor.translateAlternateColorCodes('&', ""));
    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&5» &e&lGitHub: &ahttps://github.com/Zayddd"));
    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&5» &e&lNeed help: &aUse /help to display information!"));
    p.sendMessage(ChatColor.translateAlternateColorCodes('&', ""));
    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6» &eNeed help? Contact me at &6zaydd@skids.team if your having &4&lIssues&e."));
    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&m----------------------------------------------"));
    p.playSound(p.getLocation(), Sound.NOTE_PLING, 1.0F, 1.0F);
  }
}
