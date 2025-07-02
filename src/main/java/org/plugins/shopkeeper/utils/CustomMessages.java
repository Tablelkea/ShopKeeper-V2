package org.plugins.shopkeeper.utils;

import net.kyori.adventure.text.Component;

public class CustomMessages {

    public static Component spawnVillager = Component.text("§8§l[§9ShopKeeper§8§l] §6Un shopKeeper vient d'apparaitre !");
    public static Component configMenuTitle = Component.text("§d§lMenu de configuration");
    public static Component configProfessionTitle = Component.text("§d§lSelection de la profession");
    public static Component successCaptureVillager = Component.text("§8§l[§9ShopKeeper§8§l] §6Le shopKeeper a bien été récupérer ! §a/shopkeeper move set §6pour déplacer le shopkeeper.");
    public static Component successMoveVillager = Component.text("§8§l[§9ShopKeeper§8§l] §6Le shopKeeper a bien été déplacer !");
    public static Component succesTradeLoad = Component.text("§8§l[§9ShopKeeper§8§l] §6Les Trades ont bien été chargés !");
    public static Component errorCaptureVillager = Component.text("§8§l[§9ShopKeeper§8§l] §cCette entité n'est pas un shopkeeper !");
    public static Component errorSubCommand = Component.text("§8§l[§9ShopKeeper§8§l] §cL'argument n'est pas correcte !");


}
