package zaydd.pw.commands;

import org.bukkit.command.*;
import org.bukkit.entity.*;

import zaydd.pw.Run;

import org.bukkit.*;

public class ModCommand implements CommandExecutor {

    private final Run plugin;
    
    public ModCommand(final Run pl) {
        this.plugin = pl;
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String commandLabel, final String[] args) {
        if (cmd.getName().equalsIgnoreCase("mod") || cmd.getName().equalsIgnoreCase("h") || cmd.getName().equalsIgnoreCase("staff")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED + "You must be a player to use this command.");
                return false;
            }
            final Player player = (Player)sender;
            if (player.hasPermission("core.mod")) {
                if (args.length == 0) {
                    this.plugin.toggleMode(player);
                }
                else {
                    player.sendMessage(ChatColor.RED + "Usage: /staff <mod|h>");
                }
            }
            else {
                player.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
            }
        }
        return false;
    }
}
