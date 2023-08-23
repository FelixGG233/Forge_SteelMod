package net.felix.steelmod.datagen;

import net.felix.steelmod.SteelMod;
import net.felix.steelmod.block.ModBlocks;
import net.felix.steelmod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    private static final List<ItemLike> STEEL_SMELTABLES = List.of(ModItems.RAWIRONDISULFIDE.get(),
            ModBlocks.IRONDISULFIDE.get());
    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        oreBlasting(pWriter, STEEL_SMELTABLES, RecipeCategory.MISC, ModItems.STEELINGOT.get(), 1.2f, 450, "steel");
        oreBlasting(pWriter, STEEL_SMELTABLES, RecipeCategory.MISC, ModItems.STEELNUGGET.get(), 0.2f, 100, "steel");
        oreBlasting(pWriter,STEEL_SMELTABLES,RecipeCategory.MISC,ModItems.COKE.get(),1.0f,300,"coke");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,ModBlocks.STEELBLOCK.get())
                .pattern("SSS")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', ModItems.STEELINGOT.get())
                .unlockedBy(getHasName(ModItems.STEELINGOT.get()), has(ModItems.STEELINGOT.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STEELINGOT.get())
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', ModItems.STEELINGOT.get())
                .unlockedBy(getHasName(ModItems.STEELNUGGET.get()), has(ModItems.STEELNUGGET.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.STEELINGOT.get(), 9)
                .requires(ModBlocks.STEELBLOCK.get())
                .unlockedBy(getHasName(ModBlocks.STEELBLOCK.get()), has(ModBlocks.STEELBLOCK.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.STEELNUGGET.get(), 9)
                .requires(ModItems.STEELINGOT.get())
                .unlockedBy(getHasName(ModItems.STEELINGOT.get()), has(ModItems.STEELINGOT.get()))
                .save(pWriter);
    }
    protected static void oreSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }
    protected static void oreCooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeSerializer<? extends AbstractCookingRecipe> pCookingSerializer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult,
                    pExperience, pCookingTime, pCookingSerializer)
                    .group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pFinishedRecipeConsumer, SteelMod.MOD_ID + ":"+(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }

    }
}
