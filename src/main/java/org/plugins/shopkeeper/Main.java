package org.plugins.shopkeeper;

import org.bukkit.plugin.java.JavaPlugin;
import org.plugins.shopkeeper.commands.ShopKeeper;
import org.plugins.shopkeeper.listeners.InventoryEvent;

import java.util.Objects;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        Objects.requireNonNull(getCommand("shopkeeper")).setExecutor(new ShopKeeper());
        getServer().getPluginManager().registerEvents(new InventoryEvent(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
