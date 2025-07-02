package org.plugins.shopkeeper.commands.subCommands;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.MerchantRecipe;
import org.plugins.shopkeeper.commands.ShopKeeper;
import org.plugins.shopkeeper.utils.CustomMessages;

import java.util.ArrayList;
import java.util.List;

public class MoveSubCommand {

    private static Villager globalVillager;
    public static List<MerchantRecipe> globalVillagerRecipes = new ArrayList<>();

    public static void execute(Player player, String parametre){
        if(parametre.equalsIgnoreCase("get")){
            if(player.getTargetEntity(10) instanceof Villager targetVillager){
                globalVillager = targetVillager;
                targetVillager.damage(10000000);
                player.sendMessage(CustomMessages.successCaptureVillager);
            }else{
                player.sendMessage(CustomMessages.errorCaptureVillager);
            }
        }else if(parametre.equalsIgnoreCase("set")){
            World world = player.getWorld();
            world.spawn(player.getLocation(), Villager.class, villager ->{
                villager.setRecipes(globalVillagerRecipes);
                villager.setAI(globalVillager.hasAI());
                villager.setProfession(globalVillager.getProfession());
            });
            player.sendMessage(CustomMessages.successMoveVillager);
        }else{
            player.sendMessage(CustomMessages.errorSubCommand);
        }
    }

    public static void init(){
        ShopKeeper.subCommand.add("move");
    }

    public static List<String> subParametre(){
        return List.of("get", "set");
    }

}
