package zaydd.pw;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import zaydd.pw.commands.ClearChatCommand;
import zaydd.pw.commands.DiamondCommand;
import zaydd.pw.commands.HelpCommand;
import zaydd.pw.commands.HelpopCommand;
import zaydd.pw.commands.ListCommand;
import zaydd.pw.commands.ModCommand;
import zaydd.pw.commands.ReportCommand;
import zaydd.pw.commands.StaffChatCommand;
import zaydd.pw.config.BlacklistConfiguration;
import zaydd.pw.cooldowns.Cooldowns;
import zaydd.pw.listeners.*;
import zaydd.pw.scoreboard.ScoreboardProvider;
import zaydd.pw.scoreboard.ScoreboardWrapper;
import zaydd.pw.staffutilites.StaffItemsListener;
import zaydd.pw.blacklist.*;

public class Run extends JavaPlugin {
	
	@SuppressWarnings("unused")
	private ScoreboardWrapper wrapper;
	
	public static Run plugin;
    public VanishListener vanish;
    public ModCommand mod;
    public Set<String> mode;
    private HashMap<String, ItemStack[]> inventoryContents;
    private HashMap<String, ItemStack[]> armorContents;
	public BlacklistConfiguration banconfig;
	  
    public Run()
    {
	    this.banconfig = new BlacklistConfiguration(this);
        this.vanish = new VanishListener(this);
        this.mod = new ModCommand(this);
        this.mode = new HashSet<String>();
        this.inventoryContents = new HashMap<String, ItemStack[]>();
        this.armorContents = new HashMap<String, ItemStack[]>();
    }
	  
	public void onEnable() 
	{
		Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "kCore has been sucessfully enabled! you can view this public plugin at http://github.com/Zayddd/kCore");
		registerCommands();
		setupConfig();
		loadCooldowns();
		//getConfig().options().copyDefaults(true);
		//saveConfig();
		registerListeners();
		wrapper = new ScoreboardWrapper(this, new ExampleProvider());
        Player[] arrayOfPlayer;
        for (int j = (arrayOfPlayer = Bukkit.getOnlinePlayers()).length, i = 0; i < j; ++i) {
            final Player online = arrayOfPlayer[i];
            if (this.isMod(online) && !this.isToggled(online)) {
                this.enableMode(online);
            }
        }
	}
	
	 public boolean isToggled(final Player player) {
	        return this.mode.contains(player.getName());
	    }
	    
	    public void toggleMode(final Player player) {
	        if (this.isToggled(player)) {
	            this.disableMode(player);
	        }
	        else {
	            this.enableMode(player);
	        }
	    }
	    public void enableMode(final Player player) {
	        this.mode.add(player.getName());
	        this.saveContents(player);
	        player.getInventory().clear();
	        player.getInventory().setHelmet((ItemStack)null);
	        player.getInventory().setChestplate((ItemStack)null);
	        player.getInventory().setLeggings((ItemStack)null);
	        player.getInventory().setBoots((ItemStack)null);
	        this.modItems(player);
	        this.vanish.enableVanish(player);
	        player.setGameMode(GameMode.CREATIVE);
	        player.sendMessage(this.translate("Enabled mode"));
	    }
	    public void disableMode(final Player player) {
	        this.mode.remove(player.getName());
	        this.vanish.disableVanish(player);
	        player.sendMessage(this.translate("Disabled mode"));
	        player.getInventory().clear();
	        this.setContents(player);
	        if (player.hasPermission("core.creative")) {
	            player.setGameMode(GameMode.CREATIVE);
	        }
	        else {
	            player.setGameMode(GameMode.SURVIVAL);
	        }
	    }
	    
	    public void disableModeQuit(final Player player) {
	        this.mode.remove(player.getName());
	        player.sendMessage(this.translate("Disabled mode"));
	        player.getInventory().clear();
	        this.setContents(player);
	        if (player.hasPermission("core.creative")) {
	            player.setGameMode(GameMode.CREATIVE);
	        }
	        else {
	            player.setGameMode(GameMode.SURVIVAL);
	        }
	    }
	    
	    public boolean isMod(final Player player) {
	        return player.hasPermission("core.mod");
	    }
	    
	    public void updateVanishItem(final Player player) {
	        if (this.vanish.isVanished(player)) {
	            final ItemStack vanish = new ItemStack(Material.INK_SACK, 1, (short)10);
	            final ItemMeta vanishMeta = vanish.getItemMeta();
	            vanishMeta.setDisplayName(this.translate("Vanish off name"));
	            final ArrayList<String> vanishLore = new ArrayList<String>();
	            for (final String voff : this.getConfig().getStringList("Vanish off lore")) {
	                vanishLore.add(this.color(voff));
	            }
	            vanishMeta.setLore(vanishLore);
	            vanish.setItemMeta(vanishMeta);
	            player.getInventory().setItem(7, vanish);
	        }
	        else {
	            final ItemStack vanish = new ItemStack(Material.INK_SACK, 1, (short)8);
	            final ItemMeta vanishMeta = vanish.getItemMeta();
	            vanishMeta.setDisplayName(this.translate("Vanish on name"));
	            final ArrayList<String> vanishLore = new ArrayList<String>();
	            for (final String von : this.getConfig().getStringList("Vanish on lore")) {
	                vanishLore.add(this.color(von));
	            }
	            vanishMeta.setLore(vanishLore);
	            vanish.setItemMeta(vanishMeta);
	            player.getInventory().setItem(7, vanish);
	        }
	    }
	    
	    public void saveContents(final Player player) {
	        this.inventoryContents.put(player.getName(), player.getInventory().getContents());
	        this.armorContents.put(player.getName(), player.getInventory().getArmorContents());
	    }
	    
	    public void setContents(final Player player) {
	        player.getInventory().setContents((ItemStack[])this.inventoryContents.get(player.getName()));
	        player.getInventory().setArmorContents((ItemStack[])this.armorContents.get(player.getName()));
	    }
	    
	    public void modItems(final Player player) {
	        final ItemStack compass = new ItemStack(Material.COMPASS, 1);
	        final ItemStack book = new ItemStack(Material.BOOK, 1);
	        final ItemStack axe = new ItemStack(Material.WOOD_AXE, 1);
	        final ItemStack random = new ItemStack(Material.RECORD_3, 1);
	        final ItemStack staff = new ItemStack(Material.CARPET, 1, (short)9);
	        final ItemMeta compassMeta = compass.getItemMeta();
	        final ItemMeta bookMeta = book.getItemMeta();
	        final ItemMeta axeMeta = axe.getItemMeta();
	        final ItemMeta randomMeta = random.getItemMeta();
	        compassMeta.setDisplayName(this.translate("Compass name"));
	        bookMeta.setDisplayName(this.translate("Book name"));
	        axeMeta.setDisplayName(this.translate("Axe name"));
	        randomMeta.setDisplayName(this.translate("Random TP name"));
	        final ArrayList<String> compassLore = new ArrayList<String>();
	        final ArrayList<String> bookLore = new ArrayList<String>();
	        final ArrayList<String> axeLore = new ArrayList<String>();
	        final ArrayList<String> randomLore = new ArrayList<String>();
	        final ArrayList<String> vanishOffLore = new ArrayList<String>();
	        final ArrayList<String> vanishOnLore = new ArrayList<String>();
	        for (final String scompass : this.getConfig().getStringList("Compass lore")) {
	            compassLore.add(this.color(scompass));
	        }
	        for (final String sbook : this.getConfig().getStringList("Book lore")) {
	            bookLore.add(this.color(sbook));
	        }
	        for (final String saxe : this.getConfig().getStringList("Axe lore")) {
	            axeLore.add(this.color(saxe));
	        }
	        for (final String srandom : this.getConfig().getStringList("Random TP lore")) {
	            randomLore.add(this.color(srandom));
	            compassMeta.setLore((List)compassLore);
	            bookMeta.setLore((List)bookLore);
	            axeMeta.setLore((List)axeLore);
	            randomMeta.setLore((List)randomLore);
	            compass.setItemMeta(compassMeta);
	            book.setItemMeta(bookMeta);
	            axe.setItemMeta(axeMeta);
	            random.setItemMeta(randomMeta);
	            player.getInventory().setItem(0, compass);
	            player.getInventory().setItem(1, book);
	            player.getInventory().setItem(2, axe);
	            player.getInventory().setItem(3, staff);
	            player.getInventory().setItem(8, random);
	            final ItemStack ll = new ItemStack(Material.WOOD_AXE);
	            final ItemMeta llmeta = ll.getItemMeta();
	            ll.addUnsafeEnchantment(Enchantment.LUCK, 1);
	            ll.setItemMeta(llmeta);
	        }
	        final ArrayList<String> c = new ArrayList<String>();
	        final int times = 0;
	        final int players = Bukkit.getOnlinePlayers().length;
	    }
	    
	    public void setupConfig() {
	        this.getConfig().options().copyDefaults(true);
	        this.saveDefaultConfig();
	        this.reloadConfig();
	    }
	    
	    public String translate(final String text) {
	        return ChatColor.translateAlternateColorCodes('&', this.getConfig().getString(text));
	    }
	    
	    public String color(final String text) {
	        return ChatColor.translateAlternateColorCodes('&', text);
	    }
	    
	    public void debug(final String text) {
	        Bukkit.broadcastMessage(text);
	    }
	    
	  public static class ExampleProvider implements ScoreboardProvider {
		
		public static DecimalFormat TPS_FORMAT = new DecimalFormat("0.0");
		//TODO: should probably fix this kek
	
		public String getTitle(Player player) 
		{
			return ChatColor.GOLD + "   §6§lCore §c[Kit Map]  ";
		}

		@SuppressWarnings("deprecation")
		public List<String> getLines(Player player) {
			List<String> lines = new ArrayList<>();
			lines.add(ChatColor.YELLOW.toString() + ChatColor.BOLD.toString() + "Online: " + ChatColor.WHITE + Bukkit.getOnlinePlayers().length + "/100");


			//lines.add(ChatColor.GRAY + "§m--------------------------");
			//lines.add(ChatColor.GREEN + "Online§7: " + ChatColor.WHITE + Bukkit.getOnlinePlayers().length + "/100");
			//lines.add(ChatColor.GRAY + "§m--------------------------");
			if (!lines.isEmpty()) 
			{
			if (player.hasPermission("core.staff")) {
				lines.add("§7§m----------------------------");
		          lines.add("§e§lStaff Mode§7:");
		           lines.add(ChatColor.GOLD.toString() + "» " + ChatColor.YELLOW.toString() + "TPS" + ChatColor.GRAY + ": " + ChatColor.RED + ExampleProvider.TPS_FORMAT.format(Bukkit.spigot().getTPS()[0]));
		           lines.add(ChatColor.GOLD.toString() + "» " + ChatColor.YELLOW.toString() + "Lag" + ChatColor.GRAY + ": " + ChatColor.RED + ExampleProvider.TPS_FORMAT.format(150.0 - Bukkit.spigot().getTPS()[0] * 5.0) + "%");
		           lines.add("§7§m----------------------------");
					
				}
			}
			return lines;
		}

	}
	
    public void loadCooldowns() 
    {
        Cooldowns.createCooldown("report_cooldown");
    }
	
	public void registerListeners() 
	{
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new EnderpearlCooldownListener(), this);
		pm.registerEvents(new BlacklistListener(this), this);
		pm.registerEvents(new KitsListener(), this);
		pm.registerEvents(new StaffItemsListener(this), this);
		pm.registerEvents(new VanishListener(this), this);
		pm.registerEvents(new onPlayerJoinListener(), this);
		pm.registerEvents(new WeatherCancelListener(), this);
		pm.registerEvents(new DefaultOnJoinMessageListener(), this);
		pm.registerEvents(new StaffOnQuitListener(), this);
		pm.registerEvents(new StaffOnJoinListener(), this);
		
	}
	
	public void registerCommands() 
	{
		getCommand("diamond").setExecutor(new DiamondCommand());
		getCommand("help").setExecutor(new HelpCommand());
		getCommand("list").setExecutor(new ListCommand());
	    getCommand("blacklist").setExecutor(new Blacklist(this));
	    getCommand("unblacklist").setExecutor(new Unblacklist(this));
	    getCommand("blacklisthelp").setExecutor(new BlacklistHelpCommand(this));
	    getCommand("checkblacklist").setExecutor(new CheckBlacklist(this));
	    getCommand("mod").setExecutor(new ModCommand(this));
	    getCommand("report").setExecutor(new ReportCommand());
	    getCommand("helpop").setExecutor(new HelpopCommand());
	    getCommand("cc").setExecutor(new ClearChatCommand());
	    getCommand("sc").setExecutor(new StaffChatCommand());
    }

	
	public void onDisable() 
	{
		Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "kCore has been disabled. you can view this public plugin at http://github.com/Zayddd/kCore");
		wrapper = null;
        saveDefaultConfig();
        Player[] arrayOfPlayer;
        for (int j = (arrayOfPlayer = Bukkit.getOnlinePlayers()).length, i = 0; i < j; ++i) {
            final Player online = arrayOfPlayer[i];
            if (this.isMod(online) && this.isToggled(online)) {
                this.disableMode(online);
                online.sendMessage(this.translate("Reload disable"));
            }
        }
	}
  }

