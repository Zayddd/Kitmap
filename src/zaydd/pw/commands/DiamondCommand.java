package zaydd.pw.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

public class DiamondCommand
  implements CommandExecutor
{
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
    Player player = (Player)sender;
    if (cmd.getName().equalsIgnoreCase("diamond"))
    {
      player.getInventory().clear();
      
      player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 1, 0), true);
      
      player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1, 0), true);
      
      ItemStack diamond_sword = new ItemStack(Material.DIAMOND_SWORD, 1);
      diamond_sword.addEnchantment(Enchantment.DAMAGE_ALL, 2);
      player.getInventory().addItem(new ItemStack[] { diamond_sword });
      
      ItemStack diamond_helmet = new ItemStack(Material.DIAMOND_HELMET);
      diamond_helmet.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
      diamond_helmet.addEnchantment(Enchantment.DURABILITY, 3);
      
      player.getInventory().setHelmet(diamond_helmet);
      
      ItemStack diamond_chestplate = new ItemStack(Material.DIAMOND_CHESTPLATE);
      
      diamond_chestplate.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
      diamond_chestplate.addEnchantment(Enchantment.DURABILITY, 3);
      
      player.getInventory().setChestplate(diamond_chestplate);
      
      ItemStack diamond_leggings = new ItemStack(Material.DIAMOND_LEGGINGS);
      
      diamond_leggings.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
      diamond_leggings.addEnchantment(Enchantment.DURABILITY, 3);
      player.getInventory().setLeggings(diamond_leggings);
      
      ItemStack diamond_boots = new ItemStack(Material.DIAMOND_BOOTS);
      diamond_boots.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
      diamond_boots.addEnchantment(Enchantment.DURABILITY, 3);
      
      diamond_boots.addEnchantment(Enchantment.PROTECTION_FALL, 4);
      
      player.getInventory().setBoots(diamond_boots);
      
      ItemStack enderpearls = new ItemStack(Material.ENDER_PEARL, 16);
      player.getInventory().addItem(new ItemStack[] { enderpearls });
      
      Potion splash = new Potion(PotionType.INSTANT_HEAL, 2);
      
      Potion speed = new Potion(PotionType.SPEED, 2);
      
      splash.setSplash(true);
      
      player.getInventory().addItem(new ItemStack[] { splash.toItemStack(1) });
      player.getInventory().addItem(new ItemStack[] { splash.toItemStack(1) });
      player.getInventory().addItem(new ItemStack[] { splash.toItemStack(1) });
      player.getInventory().addItem(new ItemStack[] { splash.toItemStack(1) });
      player.getInventory().addItem(new ItemStack[] { splash.toItemStack(1) });
      player.getInventory().addItem(new ItemStack[] { speed.toItemStack(1) });
      ItemStack food = new ItemStack(Material.GOLDEN_CARROT, 64);
      player.getInventory().addItem(new ItemStack[] { food });
      player.getInventory().addItem(new ItemStack[] { speed.toItemStack(1) });
      player.getInventory().addItem(new ItemStack[] { speed.toItemStack(1) });
      player.getInventory().addItem(new ItemStack[] { splash.toItemStack(1) });
      player.getInventory().addItem(new ItemStack[] { splash.toItemStack(1) });
      player.getInventory().addItem(new ItemStack[] { splash.toItemStack(1) });
      player.getInventory().addItem(new ItemStack[] { splash.toItemStack(1) });
      player.getInventory().addItem(new ItemStack[] { splash.toItemStack(1) });
      player.getInventory().addItem(new ItemStack[] { splash.toItemStack(1) });
      player.getInventory().addItem(new ItemStack[] { splash.toItemStack(1) });
      player.getInventory().addItem(new ItemStack[] { speed.toItemStack(1) });
      player.getInventory().addItem(new ItemStack[] { speed.toItemStack(1) });
      player.getInventory().addItem(new ItemStack[] { splash.toItemStack(1) });
      player.getInventory().addItem(new ItemStack[] { splash.toItemStack(1) });
      player.getInventory().addItem(new ItemStack[] { splash.toItemStack(1) });
      player.getInventory().addItem(new ItemStack[] { splash.toItemStack(1) });
      player.getInventory().addItem(new ItemStack[] { splash.toItemStack(1) });
      player.getInventory().addItem(new ItemStack[] { splash.toItemStack(1) });
      player.getInventory().addItem(new ItemStack[] { splash.toItemStack(1) });
      player.getInventory().addItem(new ItemStack[] { speed.toItemStack(1) });
      player.getInventory().addItem(new ItemStack[] { splash.toItemStack(1) });
      player.getInventory().addItem(new ItemStack[] { splash.toItemStack(1) });
      player.getInventory().addItem(new ItemStack[] { splash.toItemStack(1) });
      player.getInventory().addItem(new ItemStack[] { splash.toItemStack(1) });
      player.getInventory().addItem(new ItemStack[] { splash.toItemStack(1) });
      player.getInventory().addItem(new ItemStack[] { splash.toItemStack(1) });
      player.getInventory().addItem(new ItemStack[] { splash.toItemStack(1) });
      player.getInventory().addItem(new ItemStack[] { splash.toItemStack(1) });
      
      //TODO: literally spamming an ItemStack to add potions, need to recode this.
      player.sendMessage(ChatColor.GREEN + "Diamond kit equipped.");
    }
    return false;
  }
}
