package zaydd.pw.blacklist;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import zaydd.pw.Run;

public class CheckBlacklist implements CommandExecutor {

  final Run plugin;
  
  public CheckBlacklist(Run instance)
  {
    plugin = instance;
  }
  
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
  {
    if ((sender.isOp()) || (sender.hasPermission("core.checkblacklist"))) {
      if (args.length < 1) {
        sender.sendMessage(ChatColor.RED + "Usage: /" + label + " <player>");
      } else if (args.length == 1) {
        if (plugin.banconfig.getConfig().contains(args[0].toLowerCase())) {
          sender.sendMessage(ChatColor.RED + args[0] + " has a recording of being blacklisted.");
        } else {
          sender.sendMessage(ChatColor.RED + args[0] + " has no recording of being blacklisted.");
        }
      }
    }
    return false;
  }
}
