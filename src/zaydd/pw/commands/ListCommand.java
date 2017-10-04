package zaydd.pw.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ListCommand
  implements CommandExecutor
{
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
    if ((cmd.getName().equalsIgnoreCase("list")) && ((sender instanceof Player)))
    {
      Player p = (Player)sender;
      p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&eThere are &6" + Bukkit.getOnlinePlayers().length + " &eplayers online."));
      return true;
    }
    return false;
  }
}
