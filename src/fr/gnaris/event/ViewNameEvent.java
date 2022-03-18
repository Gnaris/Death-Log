package fr.gnaris.event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import fr.gnaris.config.ViewNameModel;
import fr.gnaris.timer.ViewNameTick;

public class ViewNameEvent implements Listener {
	
	private Player sender;

	@EventHandler
	public void onClickInteract(PlayerInteractEntityEvent e)
	{
		this.sender = e.getPlayer();
		if(ViewNameModel.GetStringList("player_showname") == null) return;
		if(ViewNameTick.CheckCooldown(this.sender))
		{
			if(ViewNameModel.GetStringList("player_showname").contains(this.sender.getUniqueId().toString()))
			{
				this.sender.sendMessage("§3Pseudo : " + e.getRightClicked().getName());
				ViewNameTick.SetCooldown(this.sender);
			}
		}
	}
}
