package zaydd.pw.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HelpCommand
  implements CommandExecutor
{
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
    if ((cmd.getName().equalsIgnoreCase("help")) && ((sender instanceof Player)))
    {
      Player p = (Player)sender;
      
      p.sendMessage("§6§m----------------------------------------------");
      p.sendMessage("§9§lKit Map Help §7- §eInformation on <servername>");
      p.sendMessage("§6§m----------------------------------------------");
      p.sendMessage("§9Map Information:");
      p.sendMessage("§eCurrent Map: §7Kit-Map - No Date. ");
      p.sendMessage("§eMap Border: §7300");
      p.sendMessage("§eWarzone Until: §70");
      p.sendMessage("§eEnd Portals: §7-50 88 180");
      p.sendMessage("§eEnchant Limits: §7Protection 2, Sharpness 2");
      p.sendMessage("§eDeathban: §7No Deathbans.");
      p.sendMessage("");
      p.sendMessage("§9Helpful Commands:");
      p.sendMessage("§e/report <player> <reason> §7- Report a player if you think they are hacking!");
      p.sendMessage("§e/helpop §7- Request staff assistance!");
      p.sendMessage("");
      p.sendMessage("§9Other Information:");
      p.sendMessage("§eOffical Teamspeak: §7- §d<teamspeak>");
      p.sendMessage("§ePure Network Rules: §7- §d<website>/rules");
      p.sendMessage("§eStore: §7- §dhttp://buycraftlinkhere.buycraft.net/");
      p.sendMessage("§eWebsite: §7- §d<website>");
      p.sendMessage("§6§m----------------------------------------------");
      
      return true;
    }
    return false;
  }
}
