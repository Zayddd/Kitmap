package zaydd.pw.commands;

import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.*;

public class HelpopCommand implements CommandExecutor
{
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final boolean b = sender instanceof Player;
        final Player p = (Player)sender;
        if (!cmd.getName().equalsIgnoreCase("helpop")) {
            return false;
        }
        if (args.length < 1) {
            p.sendMessage("§cCorrect Usage: /helpop <message>");
            return true;
        }
        final StringBuilder message = new StringBuilder();
        for (int i = 0; i < args.length; ++i) {
            message.append(String.valueOf(args[i]) + " ");
        }
        Player[] arrayOfPlayer;
        for (int j = (arrayOfPlayer = Bukkit.getOnlinePlayers()).length, k = 0; k < j; ++k) {
            final Player online = arrayOfPlayer[k];
            if (online.hasPermission("core.viewhelpop")) {
                online.sendMessage("§9" + p.getName() + " §bhas requested staff assistance: §e" + (Object)message);
                p.playSound(p.getLocation(), Sound.NOTE_PLING, 3.0f, 3.0f);
            }
        }
        p.sendMessage("§aThank you for your message, all staff on the server have been notified and will try to help as soon as possible.");
        return true;
    }
}
