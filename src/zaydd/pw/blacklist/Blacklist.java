package zaydd.pw.blacklist;

import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;
import zaydd.pw.Run;

public class Blacklist implements CommandExecutor {
  
  final Run plugin;
  
  public Blacklist(Run instance)
  {
    plugin = instance;
  }
  
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
  {
    if ((sender.isOp()) || (sender.hasPermission("pure.blacklist"))) {
      if (args.length < 1)
      {
        sender.sendMessage(ChatColor.RED + "Usage: /" + label + " <player> <reason>");
      }
      else if (args.length == 1)
      {
        Player target = plugin.getServer().getPlayer(args[0]);
        OfflinePlayer offlineTarget = plugin.getServer().getOfflinePlayer(args[0]);
        if (target != null)
        {
          String reason = "\n";
          
          target.kickPlayer("§cYou account has been blacklisted from the \n<servername>\n§cThis punishment cannot be appealed.");
          target.setBanned(true);
          
          plugin.getServer().broadcastMessage("§c" + target.getName() + " was blacklisted by " + sender.getName() + ".");
          
          plugin.banconfig.getConfig().set(target.getName().toLowerCase(), reason);
          plugin.banconfig.saveConfig();
        }
        else
        {
          String reason = "\n";
          
          offlineTarget.setBanned(true);
          
          plugin.getServer().broadcastMessage("§c" + target.getName() + " was blacklisted by " + sender.getName() + ".");
          
          plugin.banconfig.getConfig().set(offlineTarget.getName().toLowerCase(), reason);
          plugin.banconfig.saveConfig();
        }
      }
      else if (args.length > 1)
      {
        Player target = plugin.getServer().getPlayer(args[0]);
        OfflinePlayer offlineTarget = plugin.getServer().getOfflinePlayer(args[0]);
        if (target != null)
        {
          String reason = "";
          for (int i = 1; i < args.length; i++) {
            reason = reason + args[i] + " ";
          }
          target.kickPlayer("§cYou account has been blacklisted from the \n<servername>\n§cThis punishment cannot be appealed.");
          target.setBanned(true);
          
          plugin.getServer().broadcastMessage("§c" + target.getName() + " was blacklisted by " + sender.getName() + ".");
          plugin.banconfig.getConfig().set(target.getName().toLowerCase(), reason);
          plugin.banconfig.saveConfig();
        }
        else
        {
          String reason = "";
          for (int i = 1; i < args.length; i++) {
            reason = reason + args[i] + " ";
          }
          offlineTarget.setBanned(true);
          
          plugin.getServer().broadcastMessage("§c" + target.getName() + " was blacklisted by " + sender.getName() + ".");
          
          plugin.banconfig.getConfig().set(offlineTarget.getName().toLowerCase(), reason);
          plugin.banconfig.saveConfig();
        }
      }
    }
    return false;
  }
}