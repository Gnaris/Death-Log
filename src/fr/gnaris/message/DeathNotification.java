package fr.gnaris.message;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import fr.gnaris.config.Config;

public class DeathNotification {
	
	public static String title;
	
	public static void Message(Player victim)
	{
		String message = "§8[Death Log] Le joueur "+victim.getName()+" est mort !";
		for(String name : Config.GetStringList("administrator"))
		{
			Bukkit.getPlayer(UUID.fromString(name)).sendMessage(message);
		}
	}
}
