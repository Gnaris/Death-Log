package fr.gnaris.config;

import java.util.List;

import fr.gnaris.main.Main;

public class Config {
	
	public static Main main;
	
	public static void Init(Main plugin) 
	{
		main = plugin;
	}
	
	public static String GetString(String key) 
	{
		String value = main.getConfig().getString(key);
		return value;
	}
	
	public static List<String> GetStringList(String key) 
	{
		List<String> value = main.getConfig().getStringList(key);
		return value;
	}
	
	public static int GetInt(String key) 
	{
		int value = main.getConfig().getInt(key);
		return value;
	}
}
