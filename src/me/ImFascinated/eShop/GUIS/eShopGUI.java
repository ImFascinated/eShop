package me.ImFascinated.eShop.GUIS;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.ImFascinated.eShop.Core;
import me.ImFascinated.eShop.Utils.CoreUtils;

public class eShopGUI {
	

   
    
    public static void betterMenu(Player p) {
    	Inventory maingui = Bukkit.getServer().createInventory(p, Core.config.getConfiguration().getInt("eShop.eShopGUI.Size"), CoreUtils.CCFormat(Core.config.getConfiguration().getString("eShop.eShopGUI.Name")));
        
    	for (String keys : Core.config.getConfiguration().getConfigurationSection("eShop.items").getKeys(false)) {
    		try {
    			
   
    			
    			ItemStack item = CoreUtils.createItem(Material.getMaterial(Core.config.getConfiguration().getString("eShop.items." + keys + ".ItemMaterial")), CoreUtils.CCFormat(Core.config.getConfiguration().getString("eShop.items."  + keys +  ".Name")), CoreUtils.getLores(keys));
    			
    			maingui.setItem(Core.config.getConfiguration().getInt("eShop.items." + keys + ".GUISlot"), item);
    			
    		} catch(Exception e) {
    			System.out.println("Something went wrong while setting up gui! (Items most likely");
    		}
    	}
    	
    	p.openInventory(maingui);
    }
}