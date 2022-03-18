package fr.gnaris.item;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import fr.gnaris.gui.Gui;

public class PlayerDeathInformation {
	
	public static Map<UUID, Long> time = new HashMap<>();
	public static Map<UUID, Location> position = new HashMap<>();
	
	@SuppressWarnings("deprecation")
	public static void KillByPlayer(Player victim, String killer ,String weapon, LocalTime time, List<ItemStack> itemDropped, int xpDropped)
	{
		String[] splitweapon = null;
		StringBuilder weapon_name = new StringBuilder();
		
		if(weapon.contains("_"))
		{
			splitweapon = weapon.split("_");
		}
		ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		SkullMeta custom = (SkullMeta) skull.getItemMeta();
		custom.setDisplayName("§c"+victim.getName());
		List<String> information = new ArrayList<>();
		information.add("§8Meurtrier : " + killer);
		if(splitweapon != null)
		{
			for(String name : splitweapon)
			{
				weapon_name.append(name + " ");
			}
			information.add("§8Arme : " + weapon_name.toString());
		}
		else if(weapon.equalsIgnoreCase("air"))
		{
			information.add("§8Arme : Par un objet tranchant (air)");
		}
		else 
		{
			information.add("§8Arme : " + weapon);
		}
		information.add("");
		information.add("§7Mort à : " + time.getHour() + "h " + time.getMinute() + "m " + time.getSecond() + "s");
		information.add("");
		information.add("Expérience perdu : " + xpDropped);
		information.add("§9Liste des items dropper : ");
		for(ItemStack item : itemDropped)
		{
			if(!item.getType().toString().toLowerCase().equalsIgnoreCase("air"))
			{
				information.add("-"+item.getType().toString().toLowerCase());
			}
		}
		information.add("§aPour se tp à l'endroit où le joueur est mort :");
		information.add("§aClique gauche pour se tp");
		information.add("§aClique droit pour être en spectateur");
		custom.setLore(information);
		custom.setOwner(victim.getName());
		skull.setItemMeta(custom);
		Gui.logs.addItem(skull);
	}
	
	@SuppressWarnings("deprecation")
	public static void KillByOtherEntity(Player victim, DamageCause damageCause, LocalTime time, List<ItemStack> itemDropped, int xpDropped)
	{
		ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		SkullMeta custom = (SkullMeta) skull.getItemMeta();
		custom.setDisplayName("§c"+victim.getName());
		List<String> information = new ArrayList<>();
		information.add("§8Cause de la mort : " + damageCause.toString().toLowerCase());
		information.add("");
		information.add("§7Mort à : " + time.getHour() + "h " + time.getMinute() + "m " + time.getSecond() + "s");
		information.add("");
		information.add("Expérience perdu : " + xpDropped);
		information.add("");
		information.add("§9Liste des items dropper : ");
		for(ItemStack item : itemDropped)
		{
			if(!item.getType().toString().toLowerCase().equalsIgnoreCase("air"))
			{
				information.add("-"+item.getType().toString().toLowerCase());
			}
		}
		information.add("§aPour se tp à l'endroit où le joueur est mort :");
		information.add("§aClique gauche pour se tp");
		information.add("§aClique droit pour être en spectateur");
		custom.setLore(information);
		custom.setOwner(victim.getName());
		skull.setItemMeta(custom);
		Gui.logs.addItem(skull);
	}
}
