package org.plugins.shopkeeper.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.plugins.shopkeeper.commands.subCommands.ConfigSubCommand;
import org.plugins.shopkeeper.commands.subCommands.MoveSubCommand;
import org.plugins.shopkeeper.commands.subCommands.SpawnSubCommand;

import java.util.ArrayList;
import java.util.List;

public class ShopKeeper implements CommandExecutor, TabCompleter {

    public static List<String> subCommand = new ArrayList<>();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {

        if(sender instanceof Player player){

            ConfigSubCommand.init();
            MoveSubCommand.init();
            SpawnSubCommand.init();

            switch (args[0]){
                case "spawn" -> SpawnSubCommand.execute(player);
                case "config" -> ConfigSubCommand.execute(player);
                case "move" -> MoveSubCommand.execute(player, args[1]);
            }

        }

        return false;
    }


    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {

        if(args.length == 1){
            return subCommand;
        }else if(args.length == 2 && args[0].equalsIgnoreCase("move")){
            return MoveSubCommand.subParametre();
        }

        return List.of();
    }
}
