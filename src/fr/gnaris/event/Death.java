package fr.gnaris.event;

import java.time.LocalTime;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import fr.gnaris.config.Config;
import fr.gnaris.item.PlayerDeathInformation;
import fr.gnaris.message.DeathNotification;

public class Death implements Listener {
	
	@EventHandler
	public void PlayerDeath(EntityDeathEvent e)
	{
		if(e.getEntity() instanceof Player)
		{
			LocalTime time = LocalTime.now();
			Player victim = Bukkit.getPlayer(e.getEntity().getName());
			DeathNotification.Message(victim);
			PlayerDeathInformation.position.put(victim.getUniqueId(), e.getEntity().getLocation());
			PlayerDeathInformation.time.put(victim.getUniqueId(), (System.currentTimeMillis() / 1000 + Config.GetInt("cooldown_delete_head_on_log")));
			if(e.getEntity().getKiller() != null) 
			{
				PlayerDeathInformation.KillByPlayer(victim, 
													e.getEntity().getKiller().getName(), 
													e.getEntity().getKiller().getInventory().getItemInMainHand().getType().toString().toLowerCase(), 
													time, 
													e.getDrops(),
													e.getDroppedExp());
			}
			else
			{
				PlayerDeathInformation.KillByOtherEntity(victim, 
														e.getEntity().getLastDamageCause().getCause(), 
														time,
														e.getDrops(),
														e.getDroppedExp());
			}	
		}
	}
}
