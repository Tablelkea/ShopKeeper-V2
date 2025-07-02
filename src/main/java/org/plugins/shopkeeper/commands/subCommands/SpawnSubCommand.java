package org.plugins.shopkeeper.commands.subCommands;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.plugins.shopkeeper.commands.ShopKeeper;
import org.plugins.shopkeeper.utils.CustomMessages;

public class SpawnSubCommand {

    public static void execute(Player player){
        World world = player.getWorld();
        world.spawn(player.getLocation(), Villager.class, villager -> villager.setAI(false));

        player.sendMessage(CustomMessages.spawnVillager);
    }

    public static void init(){
        ShopKeeper.subCommand.add("spawn");
    }

}
