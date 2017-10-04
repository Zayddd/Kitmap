package zaydd.pw.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class ScoreboardWrapper {

	static ScoreboardWrapper instance;
	
	final ScoreboardProvider provider;
	
	final JavaPlugin plugin;
	
	final ScoreboardManager scoreboardManager;
	
	public ScoreboardWrapper(JavaPlugin plugin, ScoreboardProvider provider) {
		ScoreboardWrapper.instance = this;
		this.provider = provider;
		this.plugin = plugin;
		this.scoreboardManager = new ScoreboardManager(this);
		Bukkit.getPluginManager().registerEvents(scoreboardManager, plugin);
	}
	
}