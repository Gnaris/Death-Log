package fr.gnaris.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.gnaris.commands.instructions.AdministratorConfig;
import fr.gnaris.commands.instructions.Teleportation;
import fr.gnaris.gui.Gui;

public class Commands implements CommandExecutor {
		
		private Player sender;
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) 
	{
			if(sender instanceof Player)
			{
				this.sender = (Player) sender;
				if(label.equals("dl"))
				{
					if(AdministratorConfig.IsAdmin(this.sender) || this.sender.isOp())
					{
						if(args.length == 0) 
						{
							this.sender.openInventory(Gui.logs);
						}
						else if(args.length == 3)
						{
							if(args[1].equalsIgnoreCase("admin")) 
							{
								if(this.sender.isOp())
								{
									switch(args[0]) 
									{
									case "add" : AdministratorConfig.AddAdmin(this.sender, Bukkit.getPlayer(args[2])); break;
									case "remove" : AdministratorConfig.RemoveAdmin(this.sender, Bukkit.getPlayer(args[2])); break;
									default : break;
									}
								}
								else 
								{
									this.sender.sendMessage("§cSeuls les opérateurs ont accès à ces commandes.");
								}
							}
						}	
						else if(args.length == 2)
						{
							if(args[0].equalsIgnoreCase("admin") && args[1].equals("list"))
							{
								AdministratorConfig.AdminList(this.sender);
							}
							else if(args[0].equalsIgnoreCase("tp")) 
							{
								Teleportation.Tp(this.sender, Bukkit.getPlayer(args[1]));
							}
							else if(args[0].equalsIgnoreCase("spectate")) 
							{
								Teleportation.Spectate(this.sender, Bukkit.getPlayer(args[1]));
							}
						}
						else if(args.length == 1)
						{
							if(args[0].equalsIgnoreCase("help"))
							{
								AdministratorConfig.Help(this.sender);
							}
							else if(args[0].equalsIgnoreCase("stop"))
							{
								Teleportation.Stop(this.sender);
							}
							else if(args[0].equalsIgnoreCase("clear"))
							{
								Gui.logs.clear();
								this.sender.sendMessage("§aLes logs ont été nettoyés !");
							}
						}
						
					}
					else 
					{
						this.sender.sendMessage("§cTu n'as pas les permissions pour faire ceci !");
					}
				}
			}
		return false;
	}
}
