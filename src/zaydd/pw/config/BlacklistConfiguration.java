package zaydd.pw.config;

import java.io.File;
import java.io.IOException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import zaydd.pw.Run;

public class BlacklistConfiguration {

  final Run plugin;
  public FileConfiguration config = null;
  public File configFile = null;
  
  public BlacklistConfiguration(Run instance)
  {
    plugin = instance;
  }

  public FileConfiguration getConfig()
  {
    if (config == null) {
      reloadConfig();
    }
    return config;
  }
  
  public void reloadConfig()
  {
    if (configFile == null) {
      configFile = new File(this.plugin.getDataFolder(), "blacklist.yml");
    }
    config = YamlConfiguration.loadConfiguration(this.configFile);
  }
  
  public void saveConfig()
  {
    if ((this.config == null) || (this.configFile == null)) {
      return;
    }
    try
    {
      this.config.save(this.configFile);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
}
