package zaydd.pw.blacklist;

import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import net.md_5.bungee.api.ChatColor;
import zaydd.pw.Run;

public class Unblacklist implements CommandExecutor {
	
  final Run plugin;
  
  public Unblacklist(Run instance)
  {
    this.plugin = instance;
  }
  
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
  {
    if ((sender.isOp()) || (sender.hasPermission("core.unblacklist"))) {
      if (args.length < 1)
      {
        sender.sendMessage(ChatColor.RED + "Usage: /" + label + " <player>");
      }
      else if (args.length > 0)
      {
        OfflinePlayer target = this.plugin.getServer().getOfflinePlayer(args[0]);
        if (target != null)
        {
          if (target.isBanned())
          {
            this.plugin.banconfig.getConfig().set(target.getName().toLowerCase(), null);
            this.plugin.banconfig.saveConfig();
            
            target.setBanned(false);
            
            sender.sendMessage(ChatColor.RED + target.getName() + " has been unblacklisted.");
          }
          else
          {
            sender.sendMessage(ChatColor.RED + "That player isn't blacklisted!");
          }
        }
        else {
          sender.sendMessage(ChatColor.RED + "That player has no records of joining our server before.");
        }
      }
    }
    return true;
  }
}
