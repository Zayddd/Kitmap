package zaydd.pw.staffutilites;

import org.bukkit.event.block.*;
import org.bukkit.event.*;
import java.util.*;
import org.bukkit.event.entity.*;
import org.bukkit.entity.*;
import org.bukkit.event.player.*;
import org.bukkit.inventory.*;

import zaydd.pw.Run;

import org.bukkit.*;
import org.bukkit.block.Chest;

public class StaffItemsListener implements Listener
{
    private final Run plugin;
    
    public StaffItemsListener(final Run pl) {
        plugin = pl;
    }
    
    @EventHandler
    public void onClick(final PlayerInteractEvent event)
    {
        final Player player = event.getPlayer();
        if ((event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) && plugin.isToggled(player)) {
            final ItemStack hand = player.getItemInHand();
            if (hand.getItemMeta().getDisplayName().equalsIgnoreCase(plugin.translate("Vanish off name")) || hand.getItemMeta().getDisplayName().equalsIgnoreCase(plugin.translate("Vanish on name"))) {
                plugin.vanish.toggleVanish(player);
            }
        }
    }
    
	//@EventHandler
	//public void onInteract(PlayerInteractEvent e) {
		//Player p = e.getPlayer();
		//ItemStack itemInHand = p.getItemInHand();
		//Inventory rm = Bukkit.createInventory(null, 54, ChatColor.GREEN + "Silent Chest");		
			//if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
				//if(e.getClickedBlock().getType().equals(Material.CHEST) || e.getClickedBlock().getType().equals(Material.TRAPPED_CHEST)) {
					//Chest chest = (Chest) e.getClickedBlock().getState();
					//p.sendMessage(ChatColor.YELLOW + "Opened chest silently.");
					//rm.setContents(chest.getInventory().getContents());
					//p.openInventory(rm);
					//e.setCancelled(true);
				//}
			//}
			//e.setCancelled(true);
		//}
	
    
    @EventHandler
    public void onRecord(final PlayerInteractEvent event)
    {
        final Player player = event.getPlayer();
        final Random random = new Random();
        final int online = Bukkit.getOnlinePlayers().length;
        final int randomP = random.nextInt(online);
        final Player Random = Bukkit.getOnlinePlayers()[randomP];
        if ((event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) && plugin.mode.contains(player.getName()) && player.getItemInHand().getType() == Material.RECORD_3) {
            if (online > 1) {
                if (Random == player) {
                    random.nextInt(online);
                    onRecord(event);
                }
                else {
                    player.sendMessage(plugin.translate("Random teleport").replace("{name}", Random.getDisplayName()));
                    player.teleport(Random);
                }
            }
            if (online < 2) {
                player.sendMessage(plugin.translate("No users online"));
            }
        }
    }
    
    @EventHandler
    public void onQuit(final PlayerQuitEvent event)
    {
        final Player player = event.getPlayer();
        if (plugin.isMod(player)) {
            plugin.disableModeQuit(player);
        }
    }
    
    @EventHandler
    public void onDrop(final PlayerDropItemEvent event)
    {
        final Player player = event.getPlayer();
        if (plugin.isMod(player) && this.plugin.isToggled(player)) {
            event.setCancelled(true);
        }
    }
    
    @EventHandler
    public void onJoinUser(final PlayerJoinEvent event)
    {
        final Player player = event.getPlayer();
        if (!plugin.isMod(player) && plugin.vanish.vanish.size() > 0) {
            for (final String v : plugin.vanish.vanish) {
                final Player vanished = plugin.getServer().getPlayer(v);
                if (vanished != null) {
                    player.hidePlayer(vanished);
                }
            }
        }
    }
    
    @EventHandler
    public void cancelPvP(final EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player && event.getDamager() instanceof Player)
        {
            final Player attacker = (Player)event.getDamager();
            if (plugin.isMod(attacker) && plugin.isToggled(attacker)) {
                event.setCancelled(true);
            }
        }
        if (event.getEntity() instanceof LivingEntity && event.getDamager() instanceof Player)
        {
            final Player attacker = (Player)event.getDamager();
            if (plugin.isMod(attacker) && plugin.isToggled(attacker)) {
                event.setCancelled(true);
            }
        }
    }
    
    @EventHandler
    public void rightClick(final PlayerInteractEntityEvent event)
    {
        if (!(event.getRightClicked() instanceof Player)) {
            return;
        }
        final Player player = event.getPlayer();
        final Player p = (Player)event.getRightClicked();
        if (plugin.mode.contains(player.getName()) && p instanceof Player && player instanceof Player && player.getItemInHand().getType() == Material.BOOK) {
            player.openInventory(p.getInventory());
            player.sendMessage(ChatColor.YELLOW + "Opening inventory of: " + p.getDisplayName());
        }
    }
}