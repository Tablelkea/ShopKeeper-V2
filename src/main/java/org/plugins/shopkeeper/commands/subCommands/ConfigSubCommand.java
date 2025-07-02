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

import java.util.Map;

public class ConfigSubCommand {

    public static Inventory configMenu = Bukkit.createInventory(null, 54, CustomMessages.configMenuTitle);
    public static Inventory professionSelect = Bukkit.createInventory(null, 36, CustomMessages.configProfessionTitle);

    public static final Map<Villager.Profession, Material> WORKSTATIONS = Map.ofEntries(
            Map.entry(Villager.Profession.ARMORER, Material.BLAST_FURNACE),
            Map.entry(Villager.Profession.BUTCHER, Material.SMOKER),
            Map.entry(Villager.Profession.CARTOGRAPHER, Material.CARTOGRAPHY_TABLE),
            Map.entry(Villager.Profession.CLERIC, Material.BREWING_STAND),
            Map.entry(Villager.Profession.FARMER, Material.COMPOSTER),
            Map.entry(Villager.Profession.FISHERMAN, Material.BARREL),
            Map.entry(Villager.Profession.FLETCHER, Material.FLETCHING_TABLE),
            Map.entry(Villager.Profession.LEATHERWORKER, Material.CAULDRON),
            Map.entry(Villager.Profession.LIBRARIAN, Material.LECTERN),
            Map.entry(Villager.Profession.MASON, Material.STONECUTTER),
            Map.entry(Villager.Profession.SHEPHERD, Material.LOOM),
            Map.entry(Villager.Profession.TOOLSMITH, Material.SMITHING_TABLE),
            Map.entry(Villager.Profession.WEAPONSMITH, Material.GRINDSTONE)
    );

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
            configMenu.setItem(configMenu.getSize()-4, new ItemStack(Material.IRON_PICKAXE));

            player.openInventory(configMenu);
        }
    }

    public static void init(){
        ShopKeeper.subCommand.add("config");
    }

}
