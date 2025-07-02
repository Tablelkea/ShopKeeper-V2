package org.plugins.shopkeeper.commands.subCommands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.plugins.shopkeeper.commands.ShopKeeper;
import org.plugins.shopkeeper.models.Trade;
import org.plugins.shopkeeper.utils.CustomMessages;

public class ConfigSubCommand {

    public static Inventory configMenu = Bukkit.createInventory(null, 54, CustomMessages.configMenuTitle);

    public static void execute(Player player){

        if(player.getTargetEntity(10) instanceof Villager villager){
            configMenu.clear();
            Trade.loadIntoGUI(configMenu, Trade.getRecipeFromVillager(villager));
            configMenu.setItem(configMenu.getSize()-1, new ItemStack(Material.LIME_DYE));
            if(villager.hasAI()){
                configMenu.setItem(configMenu.getSize()-2, new ItemStack(Material.REDSTONE));
            }else{
                configMenu.setItem(configMenu.getSize()-2, new ItemStack(Material.BARRIER));
            }
            configMenu.setItem(configMenu.getSize()-3, new ItemStack(Material.ENDER_PEARL));

            player.openInventory(configMenu);
        }
    }

    public static void init(){
        ShopKeeper.subCommand.add("config");
    }

}
