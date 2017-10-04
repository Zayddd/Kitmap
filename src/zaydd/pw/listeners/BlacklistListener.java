package zaydd.pw.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

import zaydd.pw.Run;

public class BlacklistListener implements Listener {

  final Run plugin;
  
  public BlacklistListener(Run instance)
  {
    this.plugin = instance;
  }
  
  //TODO: completely redo blacklists, LOL i'm 99% sure this is old skidded code 
  @EventHandler(priority=EventPriority.MONITOR)
  public void onJoin(AsyncPlayerPreLoginEvent event)
  {
    String player = event.getName().toLowerCase();
    if (this.plugin.banconfig.getConfig().getString(player) != null) {
      event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_BANNED, " §cYou account has been blacklisted from the \n<servername>\n §cThis punishment cannot be appealed." + this.plugin.banconfig.getConfig().getString(player));
    }
  }
}
