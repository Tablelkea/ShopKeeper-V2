package org.plugins.shopkeeper.models;

import org.bukkit.entity.Villager;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Trade {

    private @NotNull List<@NotNull ItemStack> inputs;
    private @NotNull ItemStack output;
    private int maxUse;

    public Trade(@NotNull List<@NotNull ItemStack> inputs, @NotNull ItemStack output, int maxUse) {
        this.inputs = inputs;
        this.output = output;
        this.maxUse = maxUse;
    }

    public @NotNull List<ItemStack> getInputs() {
        return inputs;
    }

    public void setInputs(@NotNull List<@NotNull ItemStack> inputs) {
        this.inputs = inputs;
    }

    public @NotNull ItemStack getOutput() {
        return output;
    }

    public void setOutput(@NotNull ItemStack output) {
        this.output = output;
    }

    public int getMaxUse() {
        return maxUse;
    }

    public void setMaxUse(int maxUse) {
        this.maxUse = maxUse;
    }

    public static List<MerchantRecipe> convertToVillagerTrades(@NotNull List<@NotNull Trade> trades){
        List<MerchantRecipe> merchantRecipes = new ArrayList<>();

        for(Trade trade : trades){
            MerchantRecipe recipe = new MerchantRecipe(trade.getOutput(), trade.getMaxUse());
            recipe.addIngredient(trade.getInputs().getFirst());
            recipe.addIngredient(Objects.requireNonNull(trade.getInputs().get(1)));

            merchantRecipes.add(recipe);
        }

        return merchantRecipes;
    }

    public static MerchantRecipe convertUniqueVillagerTrades(@NotNull Trade trade){

            MerchantRecipe recipe = new MerchantRecipe(trade.getOutput(), trade.getMaxUse());
            recipe.addIngredient(trade.getInputs().getFirst());
            if(trade.getInputs().size() == 2){
                recipe.addIngredient(Objects.requireNonNull(trade.getInputs().get(1)));
            }


        return recipe;
    }

    public static void loadIntoGUI(@NotNull Inventory inventory, @NotNull List<@NotNull MerchantRecipe> recipes){
        int x = 0;
        for(MerchantRecipe recipe : recipes){
            if(x < 9){
                inventory.setItem(x, recipe.getResult());
                inventory.setItem(x+27, recipe.getIngredients().getFirst());
                if(recipe.getIngredients().size() == 2){
                    inventory.setItem(x+18, recipe.getIngredients().get(1));

                }
                x++;
            }
        }
    }

    public static List< MerchantRecipe> getRecipeFromVillager(Villager villager){
        return villager.getRecipes();
    }
}
