package fr.gnaris.commands.instructions;

import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import fr.gnaris.config.Config;

public class AdministratorConfig {
	
	public static List<String> adminLists;
	
	public static void AddAdmin(Player sender, Player target)
	{
		if(target != null)
		{
			adminLists = Config.GetStringList("administrator");
			if(!adminLists.contains(target.getUniqueId().toString()))
			{
				adminLists.add(target.getUniqueId().toString());
				Config.main.getConfig().set("administrator", adminLists);
				Config.main.saveConfig();
				sender.sendMessage("§aLe joueur " + target.getName() + " possède désormais les logs !");
			}
			else 
			{
				sender.sendMessage("§cCe joueur possède déjà les logs");
			}
		}
		else 
		{
			sender.sendMessage("§cVeuillez vérifier si le pseudo est exact");
		}
	}
	
	public static void RemoveAdmin(Player sender, Player target)
	{
		if(target != null)
		{
			adminLists = Config.GetStringList("administrator");
			if(adminLists.contains(target.getUniqueId().toString()))
			{
				adminLists.remove(target.getUniqueId().toString());
				Config.main.getConfig().set("administrator", adminLists);
				Config.main.saveConfig();
				sender.sendMessage("§cLe joueur " + target.getName() + " ne possède plus les logs !");
			}
			else 
			{
				sender.sendMessage("§cCe joueur ne possède déjà pas les logs");
			}
		}
		else 
		{
			sender.sendMessage("§cVeuillez vérifier si le pseudo est exact");
		}
	}
	
	public static void AdminList(Player sender)
	{
		sender.sendMessage("§aVoici la liste des admins :");
		StringBuilder builder = new StringBuilder();
		for(String name : Config.GetStringList("administrator"))
		{
			builder.append("§a"+Bukkit.getPlayer(UUID.fromString(name)).getName() + " §f| ");
		}
		sender.sendMessage(builder.toString());
	}
	
	public static boolean IsAdmin(Player sender)
	{	
		boolean response = false;
		adminLists = Config.GetStringList("administrator");
		if(adminLists.contains(sender.getUniqueId().toString()))
		{
			response = true;
		}
		return response;
	}
	
	public static void Help(Player sender)
	{
		sender.sendMessage("§aVoici la liste des commandes :");
		sender.sendMessage("§a/dl :§r Ouvre les logs");
		sender.sendMessage("§a/dl help :§r Affiche cette page");
		sender.sendMessage("§a/dl admin list :§r Affiche les admins qui ont accès aux logs");
		sender.sendMessage("§a/dl message on :§r Active l'affichage des logs dans le chat");
		sender.sendMessage("§a/dl message off :§r Arrête l'affichage des logs dans le chat");
		sender.sendMessage("§a/dl clear :§r Vide les logs");
		if(sender.isOp())
		{
			sender.sendMessage("§a/dl add admin <Joueur> :§r Ajoute un nouveau admin");
			sender.sendMessage("§a/dl remove admin <Joueur> :§r Supprime l'admin");
		}
	}
}
