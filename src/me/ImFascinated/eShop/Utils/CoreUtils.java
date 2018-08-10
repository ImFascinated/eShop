package me.ImFascinated.eShop.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import me.ImFascinated.eShop.Core;
import net.md_5.bungee.api.ChatColor;
import net.milkbowl.vault.economy.Economy;

public class CoreUtils {
	
	private static HashMap<String, List<String>> lores = new HashMap<>();

	
	public static String CCFormat(String entry) {
		return ChatColor.translateAlternateColorCodes('&', entry);
	}
	
	public static void reloadConfigurationFiles() {
		Core.config.reloadConfig();
	}
	
	public static void shopEnchant(Player p, String e, Integer eLVL, String boughtItemName, Integer price) {
		Economy eco = Core.getEconomy();
		if (eco.getBalance(p) < price) {
			p.sendMessage("§cInsufficient balance. You have $" + eco.getBalance(p));
		} else {
			eco.withdrawPlayer(p, price);
			p.sendMessage("§aPurchased " + boughtItemName + " for $" + price);
			p.getOpenInventory().close();
			ItemStack item = p.getItemInHand();
			item.addUnsafeEnchantment(Enchantment.getByName(e), eLVL);
		}
	}
	
    public static ItemStack createItem(Material m, String itemName, List<String> loreDir) {
        ItemStack item = new ItemStack(m, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(itemName);
        List<String> lore = new ArrayList<String>();
        for (String loreFromConfig : loreDir) {
            lore.add(loreFromConfig);
        }
        meta.setLore(lore);
        item.setItemMeta(meta);
        

        return item;
	}
    
    public static List<String> getLores(String key) {
    	return lores.get(key);
    }
    
    public static void loadLores(String keys, List<String> lore) {
    	
    	List<String> list = new ArrayList<>();
    	
    	for (String ls : lore) {
    		list.add(CCFormat(ls));
    	
    	}
    	lores.put(keys, list);
    	
    	
    }
    
    
}