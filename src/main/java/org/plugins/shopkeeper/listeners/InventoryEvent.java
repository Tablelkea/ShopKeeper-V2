package org.plugins.shopkeeper.listeners;

import org.bukkit.Material;
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
import java.util.Map;
import java.util.Objects;

public class InventoryEvent implements Listener {

    @EventHandler
    public void inventoryClick(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        Entity entity = player.getTargetEntity(10);
        Inventory inventory = event.getInventory();
        if(entity instanceof Villager villager){
            if(inventory.equals(ConfigSubCommand.configMenu)){

                if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR) return;

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

                    case REDSTONE -> {
                        villager.setAI(false);
                        inventory.setItem(inventory.getSize()-2, new ItemStack(Material.BARRIER));
                    }
                    case BARRIER -> {
                        inventory.setItem(inventory.getSize()-2, new ItemStack(Material.REDSTONE));
                        villager.setAI(true);
                    }
                    case IRON_PICKAXE -> {
                        ConfigSubCommand.professionSelect.clear();
                        for (Map.Entry<Villager.Profession, Material> entry : ConfigSubCommand.WORKSTATIONS.entrySet()) {
                            ItemStack item = new ItemStack(entry.getValue());

                            ConfigSubCommand.professionSelect.addItem(item);
                        }

                        player.openInventory(ConfigSubCommand.professionSelect);
                    }
                }
            }else if(inventory.equals(ConfigSubCommand.professionSelect)){
                if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR) return;

                event.setCancelled(true);

                Material type = event.getCurrentItem().getType();
                switch (type) {
                    case BLAST_FURNACE ->{
                        villager.setProfession(Villager.Profession.ARMORER);
                        villager.setRecipes(List.of());
                    }
                    case SMOKER ->{
                        villager.setProfession(Villager.Profession.BUTCHER);
                        villager.setRecipes(List.of());
                    }
                    case CARTOGRAPHY_TABLE -> {
                        villager.setProfession(Villager.Profession.CARTOGRAPHER);
                        villager.setRecipes(List.of());
                    }
                    case BREWING_STAND -> {
                        villager.setProfession(Villager.Profession.CLERIC);
                        villager.setRecipes(List.of());
                    }
                    case COMPOSTER -> {
                        villager.setProfession(Villager.Profession.FARMER);
                        villager.setRecipes(List.of());
                    }
                    case BARREL -> {
                        villager.setProfession(Villager.Profession.FISHERMAN);
                        villager.setRecipes(List.of());
                    }
                    case FLETCHING_TABLE -> {
                        villager.setProfession(Villager.Profession.FLETCHER);
                        villager.setRecipes(List.of());
                    }
                    case CAULDRON -> {
                        villager.setProfession(Villager.Profession.LEATHERWORKER);
                        villager.setRecipes(List.of());
                    }
                    case LECTERN -> {
                        villager.setProfession(Villager.Profession.LIBRARIAN);
                        villager.setRecipes(List.of());
                    }
                    case STONECUTTER -> {
                        villager.setProfession(Villager.Profession.MASON);
                        villager.setRecipes(List.of());
                    }
                    case LOOM -> {
                        villager.setProfession(Villager.Profession.SHEPHERD);
                        villager.setRecipes(List.of());
                    }
                    case SMITHING_TABLE -> {
                        villager.setProfession(Villager.Profession.TOOLSMITH);
                        villager.setRecipes(List.of());
                    }
                    case GRINDSTONE -> {
                        villager.setProfession(Villager.Profession.WEAPONSMITH);
                        villager.setRecipes(List.of());
                    }
                    default -> player.sendMessage("§cBloc non reconnu.");
                }
            }
        }
    }

}
