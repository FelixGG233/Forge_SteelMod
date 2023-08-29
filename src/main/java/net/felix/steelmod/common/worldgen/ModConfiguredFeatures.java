package net.felix.steelmod.common.worldgen;

import net.felix.steelmod.SteelMod;
import net.felix.steelmod.common.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_IRON_DISULFIDE_KEY = registerKey("iron_disulfide");
    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context){
        RuleTest stoneReplaceables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);

        List<OreConfiguration.TargetBlockState> overworldIronDisulfide = List.of(OreConfiguration.target(stoneReplaceables,
                ModBlocks.IRONDISULFIDE.get().defaultBlockState()));

        register(context, OVERWORLD_IRON_DISULFIDE_KEY, Feature.ORE, new OreConfiguration(overworldIronDisulfide, 10));
    }
    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name){
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation((SteelMod.MOD_ID), name));
    }
    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration){
        context.register(key, new ConfiguredFeature<>(feature,configuration));
    }
}
