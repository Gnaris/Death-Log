package fr.gnaris.event;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import fr.gnaris.commands.instructions.Teleportation;
import fr.gnaris.gui.Gui;

public class InventoryLogConfig implements Listener {
	
	private Player player;
	private ItemStack[] skulls_names;
	private ItemStack item;
	private InventoryAction action;
	
	@EventHandler
	public void onInteractItemInventory(InventoryClickEvent e)
	{	
		if(e.getView().getTitle().equalsIgnoreCase(Gui.name))
		{   
			this.item = e.getCurrentItem();
			if(this.item != null)
			{
				this.player = (Player) e.getWhoClicked();
				this.skulls_names = Gui.logs.getContents();
				e.setCancelled(true);
				this.action = e.getAction();
		        switch (action) 
		        {
		        	case MOVE_TO_OTHER_INVENTORY:
		        		this.player.sendMessage("§cFaut pas stresser... Enlèves ton doigts de la touche : SHIFT");
		        		e.setCancelled(true);
		        		return;
		        	case HOTBAR_MOVE_AND_READD:
		        		this.player.sendMessage("§cFaut pas stresser... Enlèves ton doigts de la touche : SHIFT");
		        		e.setCancelled(true);
		        		return;
		        	case HOTBAR_SWAP:
		        		this.player.sendMessage("§cFaut pas stresser... Enleve ton doigts de la touche : SHIFT");
		        		e.setCancelled(true);
		        		return;
		        	default: break;
		        }
				for(ItemStack skull_name : skulls_names)
				{
					if(skull_name != null)
					{
						if(this.item.getItemMeta().getDisplayName().equalsIgnoreCase(skull_name.getItemMeta().getDisplayName()))
						{
							if(e.isLeftClick())
							{
								Teleportation.Tp(this.player, Bukkit.getPlayer(skull_name.getItemMeta().getDisplayName().replace("§c", "")));
							}
							else if(e.isRightClick())
							{
								Teleportation.Spectate(this.player, Bukkit.getPlayer(skull_name.getItemMeta().getDisplayName().replace("§c", "")));
							}
						}
					}
				}
				
			}
		}
	}
}
