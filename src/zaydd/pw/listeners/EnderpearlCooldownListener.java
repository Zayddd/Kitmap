package zaydd.pw.listeners;

import java.util.HashMap;
import java.util.UUID;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class EnderpearlCooldownListener implements Listener {
 
  private HashMap<UUID, Long> cooldown = new HashMap<UUID, Long>();
  
  @EventHandler
  public void onPlayerInteract(PlayerInteractEvent event)
  {
    Player player = event.getPlayer();
    if (((event.getAction() == Action.RIGHT_CLICK_AIR) || (event.getAction() == Action.RIGHT_CLICK_BLOCK)) && 
      (player.getItemInHand().getType() == Material.ENDER_PEARL)) {
      if ((this.cooldown.containsKey(player.getUniqueId())) && (((Long)this.cooldown.get(player.getUniqueId())).longValue() > System.currentTimeMillis()))
      {
        event.setCancelled(true);
        player.updateInventory();
        long remainingTime = ((Long)this.cooldown.get(player.getUniqueId())).longValue() - System.currentTimeMillis();
        player.sendMessage(ChatColor.RED + "You cannot enderpearl for another " + remainingTime / 1000L + " seconds");
      }
      else
      {
        this.cooldown.put(player.getUniqueId(), Long.valueOf(System.currentTimeMillis() + 16000L));
      }
    }
  }
}
