package fr.gnaris.commands.instructions;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import fr.gnaris.item.PlayerDeathInformation;

public class Teleportation {
	
	public static void Tp(Player sender, Player skull_name) 
	{
		if(skull_name != null)
		{
			Location victim = PlayerDeathInformation.position.get(skull_name.getUniqueId());
			sender.teleport(victim);
		}
		else
		{
			sender.sendMessage("§cCe joueur n'est pas dans la liste des morts !");
		}
		
	}
	
	public static void Spectate(Player sender, Player skull_name) 
	{
		if(skull_name != null)
		{
			sender.setGameMode(GameMode.SPECTATOR);
			Location victim = PlayerDeathInformation.position.get(skull_name.getUniqueId());
			sender.teleport(victim);
			sender.sendMessage("§a/dl stop pour se remettre en normal");
		}
		else
		{
			sender.sendMessage("§cCe joueur n'est pas dans la liste des morts !");
		}
	}
	
	public static void Stop(Player sender)
	{
		if(sender.getGameMode() == GameMode.SPECTATOR)
		{
			if(sender.isOp())
			{
				sender.setGameMode(GameMode.CREATIVE);
			}
			else 
			{
				sender.setGameMode(GameMode.SURVIVAL);
			}
		}
	}
}
