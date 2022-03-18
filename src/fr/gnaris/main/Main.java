package fr.gnaris.main;

import org.bukkit.plugin.java.JavaPlugin;

import fr.gnaris.commands.Commands;
import fr.gnaris.commands.ViewName;
import fr.gnaris.config.Config;
import fr.gnaris.config.ViewNameModel;
import fr.gnaris.event.Death;
import fr.gnaris.event.InventoryLogConfig;
import fr.gnaris.event.ViewNameEvent;
import fr.gnaris.gui.Gui;
import fr.gnaris.timer.ViewNameTick;
import fr.gnaris.timer.TrimInventory;

public class Main extends JavaPlugin {
	@Override
	public void onEnable() {
		saveDefaultConfig();
		reloadConfig();
		
		//INITIALIZATION
		Config.Init(this);
		new ViewNameModel(this);
		Gui.Init();
		new TrimInventory().runTaskTimer(this, 0, 20);
		ViewNameTick.init();
		
		//COMMANDS
		getCommand("dl").setExecutor(new Commands());
		getCommand("viewname").setExecutor(new ViewName());
		
		//EVENT
		getServer().getPluginManager().registerEvents(new Death(), this);
		getServer().getPluginManager().registerEvents(new InventoryLogConfig(), this);
		getServer().getPluginManager().registerEvents(new ViewNameEvent(), this);
		
		System.out.println("[Death Log] has been turn on");
	}
	
	@Override
	public void onDisable() {
		System.out.println("[Death Log] has been turn off");
	}
}
