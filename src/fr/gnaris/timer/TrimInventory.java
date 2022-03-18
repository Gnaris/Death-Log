package fr.gnaris.timer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import fr.gnaris.gui.Gui;
import fr.gnaris.item.PlayerDeathInformation;

public class TrimInventory extends BukkitRunnable {

	private String skull_name;
	private ItemStack[] logs_content;
	private List<ItemStack> leftover;
	
	@Override
	public void run() 
	{
		this.logs_content = Gui.logs.getContents();
		this.leftover = new ArrayList<>();
		
		for(UUID name : PlayerDeathInformation.time.keySet())
		{
			Player player  = Bukkit.getPlayer(name);
			for(ItemStack skull : this.logs_content)
			{
				if(skull != null)
				{
					this.skull_name = skull.getItemMeta().getDisplayName().replace("§c", "");
					if(skull_name.equalsIgnoreCase(player.getName())) 
					{
						if((PlayerDeathInformation.time.get(name) - System.currentTimeMillis() / 1000) >= 0)
						{
							this.leftover.add(skull);
						}
					}
				}
			}
		}
		
		Gui.logs.clear();
		for(ItemStack item : this.leftover)
		{
			Gui.logs.addItem(item);
		}
	}
}
