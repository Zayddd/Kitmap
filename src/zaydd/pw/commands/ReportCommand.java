package zaydd.pw.commands;

import org.bukkit.command.*;
import org.bukkit.entity.*;

import zaydd.pw.cooldowns.Cooldowns;

import org.bukkit.*;

public class ReportCommand implements CommandExecutor {

    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args)
    {
        if (!cmd.getName().equalsIgnoreCase("report")) {
            return false;
        }
        if (args.length < 2) {
            sender.sendMessage("§cCorrect Usage: /report <player> <reason>");
            return true;
        }
        if (sender instanceof Player && Cooldowns.isOnCooldown("report_cooldown", (Player)sender)) {
            sender.sendMessage("§cYou cannot do this for another " + Cooldowns.getCooldownForPlayerInt("report_cooldown", (Player)sender) + " §cseconds.");
            return true;
            
            //TODO: change to Translate Color codes, this is currently really bad ;c
        }
        Player[] arrayOfPlayer;
        for (int j = (arrayOfPlayer = Bukkit.getOnlinePlayers()).length, i = 0; i < j; ++i) {
            final Player online = arrayOfPlayer[i];
            final Player t = Bukkit.getServer().getPlayer(args[0]);
            final StringBuilder message = new StringBuilder();
            for (int i2 = 1; i2 < args.length; ++i2) {
                message.append(String.valueOf(args[i2]) + " ");
            }
            if (!(sender instanceof Player)) {
                if (online.hasPermission("core.report")) {
                    if (t == null) {
                        online.sendMessage("§9" + sender.getName() + " §bhas reported §4(offline)§c" + args[0].toString() + " §bfor: §e" + (message));
                    }
                    else {
                        online.sendMessage("§9" + sender.getName() + " §bhas reported §c" + t.getName() + " §bfor: §e" + (message));
                    }
                }
                sender.sendMessage("§aThanks for your report, all staff on the network have been notified and will investigate soon.");
            }
            else {
                final Player p = (Player)sender;
                if (online.hasPermission("core.viewreport")) {
                    if (t == null) {
                        online.sendMessage("§9" + p.getName() + " §bhas reported §4(offline)§c" + args[0].toString() + " §bfor: §e" + (message));
                        Cooldowns.addCooldown("report_cooldown", p, 60);
                    }
                    else {
                        online.sendMessage("§9" + p.getName() + " §bhas reported §c" + t.getName() + " §bfor: §e" + (message));
                        Cooldowns.addCooldown("report_cooldown", p, 60);
                    }
                }
            }
        }
        sender.sendMessage("§aThanks for your report, all staff on the network have been notified and will investigate soon.");
        return true;
    }
}
