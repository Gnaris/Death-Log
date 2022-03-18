package fr.gnaris.commands;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.gnaris.config.ViewNameModel;

public class ViewName implements CommandExecutor {
	
	private Player sender;
	private List<String> playerLists = ViewNameModel.GetStringList("player_showname_on");
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
			if(sender instanceof Player && args.length == 1 && label.equalsIgnoreCase("viewname"))
			{
				this.sender = (Player) sender;
				switch(args[0])
				{
				case "on" : 
					if(!ViewNameModel.GetStringList("player_showname").contains(this.sender.getUniqueId().toString())) 
					{
						this.playerLists.add(this.sender.getUniqueId().toString());
						ViewNameModel.Set("player_showname", this.playerLists);
						this.sender.sendMessage("§aVous pouvez dès à présent voir les pseudos !");
					}
					else
					{
						this.sender.sendMessage("§cVous voyez déjà les pseudos des gens !");
					}
					break;
				case "off" :
					if(ViewNameModel.GetStringList("player_showname").contains(this.sender.getUniqueId().toString())) 
					{
						this.playerLists.remove(this.sender.getUniqueId().toString());
						ViewNameModel.Set("player_showname", this.playerLists);
						this.sender.sendMessage("§aVous ne voyez plus les pseudos dès à présent !");
					}
					else
					{
						this.sender.sendMessage("§cVous ne voyez déjà pas les pseudos !");
					}
					break;
				}
			}
		return false;
	}

}
