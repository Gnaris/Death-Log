package fr.gnaris.config;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.logging.Level;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import fr.gnaris.main.Main;

public class ViewNameModel {
	
	// Donner un nom du fichier dans la propriété name_of_file
	// Dans le main fichier utiliser la methode CustomConfig pour récuperer le main;
	// Creer le fichier sous le meme nom du propriété name_of_file comme le config.yml
	
	private static Main main;
	private static FileConfiguration config = null;
	private static File file = null;
	
	private static String name_of_file = "viewname";
	
	public ViewNameModel(Main plugin) 
	{
		main = plugin;
		saveDefaultConfig();
	}
	
	public static void reloadConfig() {
		if(file == null) 
		{
			file = new File(main.getDataFolder(), name_of_file + ".yml");
		}
		config = YamlConfiguration.loadConfiguration(file);
		InputStream defaultStream = main.getResource(name_of_file + ".yml");
		if(defaultStream != null)
		{
			YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
			config.setDefaults(defaultConfig);
		}
	}
	
	public static FileConfiguration getConfig()
	{
		if(config == null)
		{
			reloadConfig();
		}
		return config;
	}
	
	public static void saveConfig() 
	{
		if(config == null || file == null) {
			return;
		}
		try {
			getConfig().save(file);
		} catch (IOException e) {
			main.getLogger().log(Level.SEVERE, "Impossible de sauvegarder le fichier de configuration " + file + e);
		}
	}
	
	public static void saveDefaultConfig() 
	{
		if(file == null) {
			file = new File(main.getDataFolder(), name_of_file + ".yml");
		}
		
		if(!file.exists()) {
			main.saveResource(name_of_file + ".yml", false);
		}
	}
	
	//GETTER
	public static String GetString(String key) 
	{
		String value = getConfig().getString(key);
		return value;
	}
	
	public static List<String> GetStringList(String key) 
	{
		List<String> value = getConfig().getStringList(key);
		return value;
	}
	
	public static java.util.Set<String> GetKey(String key)
	{
		java.util.Set<String> value = getConfig().getConfigurationSection(key).getKeys(false);
		return value;
	}
	
	public static int GetInt(String key) 
	{
		int value = getConfig().getInt(key);
		return value;
	}
	
	public static void Set(String key, Object value)
	{
		getConfig().set(key, value);
		saveConfig();
	}
	
	public static boolean GetBoolean(String key) 
	{
		Boolean value = getConfig().getBoolean(key);
		return value;
	}
}
