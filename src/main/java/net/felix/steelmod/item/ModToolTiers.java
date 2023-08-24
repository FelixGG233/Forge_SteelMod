package net.felix.steelmod.item;

import net.felix.steelmod.SteelMod;
import net.felix.steelmod.util.ModTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;

import java.util.List;

public class ModToolTiers {
    public static final Tier STEEL = TierSortingRegistry.registerTier(
            new ForgeTier(2, 1700, 6.5f,2.5f,22,
                    ModTags.Blocks.NEEDS_STEEL_TOOL, () -> Ingredient.of(ModItems.STEELINGOT.get())),
            new ResourceLocation(SteelMod.MOD_ID, "steel"), List.of(Tiers.IRON), List.of());
}
