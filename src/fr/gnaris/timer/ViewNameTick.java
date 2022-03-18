package fr.gnaris.timer;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.entity.Player;

public class ViewNameTick {
	
	
	public static Map<UUID, Long> cooldown;
	public static double cooldownTime;
	
	public static void init()
	{
		cooldown = new HashMap<>();
		cooldownTime = 0.1;
	}
	
	public static void SetCooldown(Player sender)
	{
		cooldown.put(sender.getUniqueId(), System.currentTimeMillis() / 1000);
	}
	
	public static boolean CheckCooldown(Player sender)
	{
		boolean response = false;
		
		if(!cooldown.containsKey(sender.getUniqueId()) || ((cooldown.get(sender.getUniqueId()) + cooldownTime) <= (System.currentTimeMillis() / 1000))) 
		{
			response = true;
		}
		return response;
	}
}
