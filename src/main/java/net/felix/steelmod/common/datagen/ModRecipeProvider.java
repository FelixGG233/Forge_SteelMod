package net.felix.steelmod.common.datagen;

import net.felix.steelmod.SteelMod;
import net.felix.steelmod.common.block.ModBlocks;
import net.felix.steelmod.common.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    private static final List<ItemLike> STEEL_SMELTABLES = List.of(ModItems.STEELINGOT.get(), Items.IRON_INGOT);
    private static final List<ItemLike> COKE_SMELTABLES = List.of(ModItems.COKE.get(), Items.CHARCOAL);
    private static final List<ItemLike> IRONDISULFIDE_SMELTABLES = List.of(Items.IRON_INGOT, ModItems.RAWIRONDISULFIDE.get(),ModBlocks.IRONDISULFIDE.get());
    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }


    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        oreBlasting(consumer, STEEL_SMELTABLES, RecipeCategory.MISC, ModItems.STEELINGOT.get(), 1.2f, 450, "steel");
        oreBlasting(consumer,COKE_SMELTABLES,RecipeCategory.MISC,ModItems.COKE.get(),1.0f,300,"coke");
        oreBlasting(consumer,IRONDISULFIDE_SMELTABLES,RecipeCategory.MISC, Items.IRON_INGOT, 0.7f,100,"iron");
        oreSmelting(consumer,IRONDISULFIDE_SMELTABLES,RecipeCategory.MISC, Items.IRON_INGOT, 0.7f,200,"iron");

        this.addChestsRecipes(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,ModBlocks.STEELBLOCK.get())
                .pattern("SSS")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', ModItems.STEELINGOT.get())
                .unlockedBy(getHasName(ModItems.STEELINGOT.get()), has(ModItems.STEELINGOT.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,ModItems.STEEL_SWORD.get())
                .pattern(" & ")
                .pattern(" & ")
                .pattern(" * ")
                .define('*', Items.STICK)
                .define('&',ModItems.STEELINGOT.get())
                .unlockedBy(getHasName(ModItems.STEELINGOT.get()), has(ModItems.STEELINGOT.get()))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,ModItems.STEEL_PICKAXE.get())
                .pattern("%%%")
                .pattern(" @ ")
                .pattern(" @ ")
                .define('@', Items.STICK)
                .define('%',ModItems.STEELINGOT.get())
                .unlockedBy(getHasName(ModItems.STEELINGOT.get()), has(ModItems.STEELINGOT.get()))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,ModItems.STEEL_AXE.get())
                .pattern("!! ")
                .pattern("!+ ")
                .pattern(" + ")
                .define('+', Items.STICK)
                .define('!',ModItems.STEELINGOT.get())
                .unlockedBy(getHasName(ModItems.STEELINGOT.get()), has(ModItems.STEELINGOT.get()))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,ModItems.STEEL_SHOVEL.get())
                .pattern(" - ")
                .pattern(" = ")
                .pattern(" = ")
                .define('=', Items.STICK)
                .define('-',ModItems.STEELINGOT.get())
                .unlockedBy(getHasName(ModItems.STEELINGOT.get()), has(ModItems.STEELINGOT.get()))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,ModItems.STEEL_HOE.get())
                .pattern(".. ")
                .pattern(" , ")
                .pattern(" , ")
                .define(',', Items.STICK)
                .define('.',ModItems.STEELINGOT.get())
                .unlockedBy(getHasName(ModItems.STEELINGOT.get()), has(ModItems.STEELINGOT.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,ModItems.STEEL_HELMET.get())
                .pattern("```")
                .pattern("` `")
                .pattern("   ")
                .define('`',ModItems.STEELINGOT.get())
                .unlockedBy(getHasName(ModItems.STEELINGOT.get()), has(ModItems.STEELINGOT.get()))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,ModItems.STEEL_CHESTPLATE.get())
                .pattern("` `")
                .pattern("```")
                .pattern("```")
                .define('`',ModItems.STEELINGOT.get())
                .unlockedBy(getHasName(ModItems.STEELINGOT.get()), has(ModItems.STEELINGOT.get()))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,ModItems.STEEL_LEGGINGS.get())
                .pattern("```")
                .pattern("` `")
                .pattern("` `")
                .define('`',ModItems.STEELINGOT.get())
                .unlockedBy(getHasName(ModItems.STEELINGOT.get()), has(ModItems.STEELINGOT.get()))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,ModItems.STEEL_BOOTS.get())
                .pattern("` `")
                .pattern("` `")
                .pattern("   ")
                .define('`',ModItems.STEELINGOT.get())
                .unlockedBy(getHasName(ModItems.STEELINGOT.get()), has(ModItems.STEELINGOT.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,ModBlocks.STEELDOOR.get(), 3)
                .pattern("`` ")
                .pattern("`` ")
                .pattern("`` ")
                .define('`',ModItems.STEELINGOT.get())
                .unlockedBy(getHasName(ModItems.STEELINGOT.get()), has(ModItems.STEELINGOT.get()))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,ModBlocks.STEELFENCE.get(),3)
                .pattern("   ")
                .pattern("`#`")
                .pattern("`#`")
                .define('`',ModItems.STEELINGOT.get())
                .define('#',ModItems.STEELSTICK.get())
                .unlockedBy(getHasName(ModItems.STEELINGOT.get()), has(ModItems.STEELINGOT.get()))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,ModBlocks.STEELFENCEGATE.get())
                .pattern("   ")
                .pattern("#`#")
                .pattern("#`#")
                .define('`',ModItems.STEELINGOT.get())
                .define('#',ModItems.STEELSTICK.get())
                .unlockedBy(getHasName(ModItems.STEELINGOT.get()), has(ModItems.STEELINGOT.get()))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,ModBlocks.STEELWALL.get(),3)
                .pattern("   ")
                .pattern("```")
                .pattern("```")
                .define('`',ModItems.STEELINGOT.get())
                .unlockedBy(getHasName(ModItems.STEELINGOT.get()), has(ModItems.STEELINGOT.get()))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,ModBlocks.STEELSTAIRS.get(),6)
                .pattern("`  ")
                .pattern("`` ")
                .pattern("```")
                .define('`',ModItems.STEELINGOT.get())
                .unlockedBy(getHasName(ModItems.STEELINGOT.get()), has(ModItems.STEELINGOT.get()))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,ModBlocks.STEELTRAPDOOR.get())
                .pattern("   ")
                .pattern("`` ")
                .pattern("`` ")
                .define('`',ModItems.STEELINGOT.get())
                .unlockedBy(getHasName(ModItems.STEELINGOT.get()), has(ModItems.STEELINGOT.get()))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,ModBlocks.STEELSLAB.get(),3)
                .pattern("   ")
                .pattern("   ")
                .pattern("```")
                .define('`',ModItems.STEELINGOT.get())
                .unlockedBy(getHasName(ModItems.STEELINGOT.get()), has(ModItems.STEELINGOT.get()))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,ModBlocks.STEELPRESSUREPLATE.get())
                .pattern("   ")
                .pattern("   ")
                .pattern("`` ")
                .define('`',ModItems.STEELINGOT.get())
                .unlockedBy(getHasName(ModItems.STEELINGOT.get()), has(ModItems.STEELINGOT.get()))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,ModBlocks.STEELBUTTON.get())
                .pattern("   ")
                .pattern(" ` ")
                .pattern("   ")
                .define('`',ModItems.STEELINGOT.get())
                .unlockedBy(getHasName(ModItems.STEELINGOT.get()), has(ModItems.STEELINGOT.get()))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,ModItems.STEELSTICK.get(),2)
                .pattern("   ")
                .pattern(" ` ")
                .pattern(" ` ")
                .define('`',ModItems.STEELINGOT.get())
                .unlockedBy(getHasName(ModItems.STEELINGOT.get()), has(ModItems.STEELINGOT.get()))
                .save(consumer);


        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.STEELINGOT.get(), 9)
                .requires(ModBlocks.STEELBLOCK.get())
                .unlockedBy(getHasName(ModBlocks.STEELBLOCK.get()), has(ModBlocks.STEELBLOCK.get()))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.STEELNUGGET.get(), 9)
                .requires(ModItems.STEELINGOT.get())
                .unlockedBy(getHasName(ModItems.STEELINGOT.get()), has(ModItems.STEELINGOT.get()))
                .save(consumer);
    }

    private void addChestsRecipes(Consumer<FinishedRecipe> consumer) {
        String folder = "chests/";

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.STEEL_CHEST.get())
                .pattern("MMM")
                .pattern("MSM")
                .pattern("MMM")
                .define('M', ModItems.STEELINGOT.get())
                .define('S', Tags.Items.CHESTS_WOODEN)
                .unlockedBy(getHasName(ModItems.STEELINGOT.get()), has(ModItems.STEELINGOT.get()))
                .save(consumer, location(folder + "vanilla_steel_chest"));
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

    protected static ResourceLocation prefix(ItemLike item, String prefix) {
        ResourceLocation loc = Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(item.asItem()));
        return location(prefix + loc.getPath());
    }

    private static ResourceLocation location(String id) {
        return new ResourceLocation(SteelMod.MOD_ID, id);
    }

    private static TagKey<Item> tag(String name) {
        return ItemTags.create(new ResourceLocation("forge", name));
    }
}
