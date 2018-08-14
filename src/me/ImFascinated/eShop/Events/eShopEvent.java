package me.ImFascinated.eShop.Events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import me.ImFascinated.eShop.Core;
import me.ImFascinated.eShop.GUIS.eShopGUI;
import me.ImFascinated.eShop.Utils.CoreUtils;
import net.milkbowl.vault.economy.Economy;


public class eShopEvent implements Listener {
	
	@EventHandler
	public void onClickInv(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		
		if (e.getClickedInventory() == null) return;
		
		if (e.getCurrentItem() == null) return;
		
		if (!e.getCurrentItem().hasItemMeta()) return;
		
		if (e.getClickedInventory().getName().equalsIgnoreCase(CoreUtils.CCFormat(Core.config.getConfiguration().getString("eShop.eShopGUI.Name")))) {
		
		for (String keys : Core.config.getConfiguration().getConfigurationSection("eShop.categories").getKeys(false)) {

			if (e.getCurrentItem().getItemMeta().getDisplayName().equals(CoreUtils.CCFormat(Core.config.getConfiguration().getString("eShop.categories." + keys + ".Name")))) {
											
						eShopGUI.betterMenu(p, keys);						
						e.setCancelled(true);

			}
		}
		
		if (p.getItemInHand() != null && p.getItemInHand().getType() != Material.AIR) {
			
			e.setCancelled(true);
		for (String keys : Core.config.getConfiguration().getConfigurationSection("eShop.items").getKeys(false)) {
			
				if (e.getCurrentItem().getItemMeta().getDisplayName().equals(CoreUtils.CCFormat(Core.config.getConfiguration().getString("eShop.items." + keys + ".Name")))) {
					int price = Core.config.getConfiguration().getInt("eShop.items." + keys + ".Price");
					String boughtItemName = CoreUtils.CCFormat(Core.config.getConfiguration().getString("eShop.items." + keys + ".Name"));
					
					Economy eco = Core.getEconomy();
					if (eco.getBalance(p) < price) {
						p.sendMessage("§cInsufficient balance. You have $" + eco.getBalance(p));
					} else {
						eco.withdrawPlayer(p, price);
						p.sendMessage("§aPurchased " + boughtItemName + " for $" + price);
						p.getOpenInventory().close();
						CoreUtils.shopEnchant(p, Core.config.getConfiguration().getString("eShop.items." + keys +  ".Enchant"), Core.config.getConfiguration().getInt("eShop.items." + keys + ".Level"));
					}		
					}
				}
			}
		}
	}
}