package me.ImFascinated.eShop.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.ImFascinated.eShop.GUIS.eShopGUI;


public class eshop implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		eShopGUI.categories(p);
		return true;
	}	
	
}
