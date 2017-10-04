package zaydd.pw.commands;

import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.*;

public class ClearChatCommand implements CommandExecutor
{
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args)
    {
        if (sender instanceof Player && !sender.hasPermission("core.cc"))
        {
            sender.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
            return true;
        }
        for (int i = 0; i < 100; ++i) {
            Bukkit.broadcastMessage("");
        }
        Bukkit.broadcastMessage(ChatColor.RED + "Chat was cleared by staff.");
        return true;
    }
}
