package org.plugins.shopkeeper.listeners;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;
import org.plugins.shopkeeper.commands.subCommands.ConfigSubCommand;
import org.plugins.shopkeeper.commands.subCommands.MoveSubCommand;
import org.plugins.shopkeeper.models.Trade;
import org.plugins.shopkeeper.utils.CustomMessages;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class InventoryEvent implements Listener {

    @EventHandler
    public void inventoryClick(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        Entity entity = player.getTargetEntity(10);
        Inventory inventory = event.getInventory();
        if(entity instanceof Villager villager){
            if(inventory.equals(ConfigSubCommand.configMenu)){
                switch (Objects.requireNonNull(event.getCurrentItem()).getType()){

                    case LIME_DYE -> {
                        event.setCancelled(true);
                        List<MerchantRecipe> recipes = new ArrayList<>();
                        for(int i = 0; i < 9; i++){
                            if(inventory.getItem(i) != null){

                                ItemStack input1 = inventory.getItem(i+27);
                                ItemStack output = inventory.getItem(i);

                                if(input1 != null && output != null){
                                    ItemStack input2 = inventory.getItem(i+18);
                                    if(input2 != null){
                                        Trade trade = new Trade(List.of(input1, input2), output, 1000000);
                                        recipes.add(Trade.convertUniqueVillagerTrades(trade));
                                    }
                                }
                            }
                        }
                        villager.setRecipes(recipes);
                        player.sendMessage(CustomMessages.succesTradeLoad);
                    }

                    case ENDER_PEARL -> {
                        event.setCancelled(true);
                        MoveSubCommand.execute(player, "get");
                    }
                }
            }
        }
    }

}
