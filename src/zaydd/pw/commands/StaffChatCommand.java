package zaydd.pw.commands;

import java.util.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.player.*;
import org.bukkit.*;

public class StaffChatCommand implements CommandExecutor {

    private List<String> chatters;
    
    public StaffChatCommand() {
        chatters = new ArrayList<String>();
    }
    
    private boolean canUseAdminChat(final CommandSender sender) {
        return sender.hasPermission("core.staff") || sender.isOp() || sender.isOp();
    }
    
    private String buildMessage(final String[] args, final int start) {
        final StringBuilder msg = new StringBuilder();
        for (int i = start; i < args.length; ++i) {
            if (i != start) {
                msg.append(" ");
            }
            msg.append(args[i]);
        }
        return msg.toString();
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String commandLabel, final String[] args) {
        final String msg = this.buildMessage(args, 0);
        if (commandLabel.equalsIgnoreCase("sc") || commandLabel.equalsIgnoreCase("staffchat")) {
            if (canUseAdminChat(sender)) {
                if (args.length > 0) {
                }
                else {
                    sender.sendMessage("§cUsage: /" + commandLabel + " <message>");
                }
            }
            else {
                sender.sendMessage(ChatColor.RED + "You don't have permission to execute this command!");
            }
        }
        return false;
    }
    
    @EventHandler
    public void onPlayerChat(final AsyncPlayerChatEvent event) {
        if (chatters.contains(event.getPlayer().getName())) {
            sendToChat(event.getMessage(), (CommandSender)event.getPlayer());
            event.setCancelled(true);
        }
    }
    
    @EventHandler
    public void onPlayerQuit(final PlayerQuitEvent event) {
        final String pname = event.getPlayer().getName();
        if (chatters.contains(pname)) {
            chatters.remove(pname);
        }
    }
    
    private void sendToChat(final String msg, final CommandSender sender) {
        final boolean b = sender instanceof Player;
        Player[] arrayOfPlayer;
        for (int j = (arrayOfPlayer = Bukkit.getServer().getOnlinePlayers()).length, i = 0; i < j; ++i) {
            final Player p = arrayOfPlayer[i];
            if (this.canUseAdminChat((CommandSender)p)) {
                p.sendMessage("§b" + sender.getName() + "§b: §b" + ChatColor.translateAlternateColorCodes('&', msg));
            }
        }
    }
}