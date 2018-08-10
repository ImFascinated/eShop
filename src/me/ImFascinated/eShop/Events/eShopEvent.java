package me.ImFascinated.eShop.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import me.ImFascinated.eShop.Core;
import me.ImFascinated.eShop.Utils.CoreUtils;


public class eShopEvent implements Listener {
	
	@EventHandler
	public void onClickInv(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		
		if (e.getClickedInventory() == null) return;
		
		if (e.getCurrentItem() == null) return;
		
		if (!e.getCurrentItem().hasItemMeta()) return;
		
		if (e.getClickedInventory().getName().equalsIgnoreCase(CoreUtils.CCFormat(Core.config.getConfiguration().getString("eShop.eShopGUI.Name")))) {
		
		e.setCancelled(true);
		
		for (String keys : Core.config.getConfiguration().getConfigurationSection("eShop.items").getKeys(false)) {
			if(p.getInventory().getItemInHand() == null) {
				p.sendMessage("test");
			} else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(CoreUtils.CCFormat(Core.config.getConfiguration().getString("eShop.items." + keys + ".Name")))) {
				CoreUtils.shopEnchant(p, Core.config.getConfiguration().getString("eShop.items." + keys +  ".Enchant"), Core.config.getConfiguration().getInt("eShop.items." + keys + ".Level"), CoreUtils.CCFormat(Core.config.getConfiguration().getString("eShop.items." + keys + ".Name")), Core.config.getConfiguration().getInt("eShop.items." + keys + ".Price"));	
				}
			}
		}
	}
}