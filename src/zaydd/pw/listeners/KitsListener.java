package zaydd.pw.listeners;

import org.bukkit.Bukkit;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import net.md_5.bungee.api.ChatColor;

public class KitsListener implements Listener {
	
	@EventHandler
	 public void onInteract(PlayerInteractEvent e)
	  {
	    Player p = e.getPlayer();
	    if ((e.getAction() == Action.RIGHT_CLICK_BLOCK) && ((e.getClickedBlock().getState() instanceof Sign)))
	    {
	      Sign s = (Sign)e.getClickedBlock().getState();
	      if (((s.getLine(0).equalsIgnoreCase(ChatColor.AQUA + "Diamond Kit"))) && 
	        (p.hasPermission("core.kits"))) {
	    	  Bukkit.dispatchCommand(p, "diamond");
	        }
	      }
	    }
	  }



