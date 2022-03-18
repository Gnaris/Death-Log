package fr.gnaris.gui;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

public class Gui {
	
	public static String name = "§cHistorique des morts";
	public static Inventory logs;
	
	public static void Init()
	{
		CreateGui();
	}
	
	public static void CreateGui() 
	{
		Inventory inventory = Bukkit.createInventory(null, 54, name);
		logs = inventory;
	}
}
