package zaydd.pw.listeners;

import java.util.*;
import org.bukkit.entity.*;
import org.bukkit.*;
import org.bukkit.event.block.*;

import zaydd.pw.Run;

import org.bukkit.event.*;

public class VanishListener implements Listener {
	
    private final Run plugin;
    public Set<String> vanish;
    
    public VanishListener(final Run pl) {
        this.vanish = new HashSet<String>();
        this.plugin = pl;
    }
    
    public boolean isVanished(final Player player) {
        return this.vanish.contains(player.getName());
    }
    
    public void toggleVanish(final Player player) {
        if (this.isVanished(player)) {
            this.disableVanish(player);
        }
        else {
            this.enableVanish(player);
        }
    }
    
    

    
    public void enableVanish(final Player player) {
        Player[] arrayOfPlayer;
        for (int j = (arrayOfPlayer = Bukkit.getOnlinePlayers()).length, i = 0; i < j; ++i) {
            final Player online = arrayOfPlayer[i];
            if (!online.hasPermission("pure.mod")) {
                online.hidePlayer(player);
            }
        }
        this.vanish.add(player.getName());
        this.plugin.updateVanishItem(player);
    }
    
    public void disableVanish(final Player player) {
        Player[] arrayOfPlayer;
        for (int j = (arrayOfPlayer = Bukkit.getOnlinePlayers()).length, i = 0; i < j; ++i) {
            final Player online = arrayOfPlayer[i];
            online.showPlayer(player);
        }
        this.vanish.remove(player.getName());
        this.plugin.updateVanishItem(player);
    }
    
    @EventHandler
    public void onBuild(final BlockPlaceEvent event) {
        event.setBuild(true);
    }
}