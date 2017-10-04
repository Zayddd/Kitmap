package zaydd.pw.blacklist;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import zaydd.pw.Run;

public class BlacklistHelpCommand implements CommandExecutor { 
	
  final Run plugin;
  
  public BlacklistHelpCommand(Run instance)
  {
    this.plugin = instance;
  }
  
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
  {
    if ((sender.isOp()) || (sender.hasPermission("core.blacklisthelp")))
    {
      sender.sendMessage("§6§m----------------------------------------------");
      sender.sendMessage("§9§lBlacklist §7- §eInformation on Blacklist Help.");
      sender.sendMessage("§6§m----------------------------------------------");
      sender.sendMessage("§9Blacklist Commands:");
      sender.sendMessage("§e/blacklist <player>  §7- Blacklists a player from the Network");
      sender.sendMessage("§e/unblacklist <player> §7- Removes blacklist data from your selected user.");
      sender.sendMessage("§e/checkblacklist <player> §7- Checks whether a player is blacklisted or not.");
      sender.sendMessage("§6§m----------------------------------------------");
      
      return false;
    }
    return false;
  }
}