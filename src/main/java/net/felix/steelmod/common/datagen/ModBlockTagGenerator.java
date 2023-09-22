package net.felix.steelmod.common.datagen;

import net.felix.steelmod.SteelMod;
import net.felix.steelmod.common.block.ModBlocks;
import net.felix.steelmod.common.utils.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {
    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, SteelMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(ModTags.Blocks.METAL_DETECTOR_VALUABLES)
                .add(ModBlocks.IRONDISULFIDE.get()).addTag(Tags.Blocks.ORES);

        this.tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.IRONDISULFIDE.get(),
                        ModBlocks.STEELBLOCK.get(),
                        ModBlocks.STEEL_BLAST_FURNACE_CORE.get(),
                        ModBlocks.STEELFENCE.get(),
                        ModBlocks.STEELPRESSUREPLATE.get(),
                        ModBlocks.STEELTRAPDOOR.get(),
                        ModBlocks.STEELWALL.get(),
                        ModBlocks.STEELSTAIRS.get(),
                        ModBlocks.STEELFENCEGATE.get(),
                        ModBlocks.STEELDOOR.get(),
                        ModBlocks.STEEL_COVERED_BRICK.get()

                );
        this.tag(ModTags.Blocks.NEEDS_STEEL_TOOL)
                .add(ModBlocks.IRONDISULFIDE.get(),
                        ModBlocks.STEELBLOCK.get(),
                        ModBlocks.STEEL_BLAST_FURNACE_CORE.get(),
                        ModBlocks.STEELFENCE.get(),
                        ModBlocks.STEELPRESSUREPLATE.get(),
                        ModBlocks.STEELTRAPDOOR.get(),
                        ModBlocks.STEELWALL.get(),
                        ModBlocks.STEELSTAIRS.get(),
                        ModBlocks.STEELFENCEGATE.get(),
                        ModBlocks.STEELDOOR.get(),
                        ModBlocks.STEEL_COVERED_BRICK.get()

                );

        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.IRONDISULFIDE.get(),
                        ModBlocks.STEELBLOCK.get(),

                        ModBlocks.STEELFENCE.get(),
                        ModBlocks.STEELPRESSUREPLATE.get(),
                        ModBlocks.STEELTRAPDOOR.get(),
                        ModBlocks.STEELWALL.get(),
                        ModBlocks.STEELSTAIRS.get(),
                        ModBlocks.STEELFENCEGATE.get(),
                        ModBlocks.STEELDOOR.get()
                );



        this.tag(BlockTags.FENCES)
                .add(ModBlocks.STEELFENCE.get());
        this.tag(BlockTags.FENCE_GATES)
                .add(ModBlocks.STEELFENCEGATE.get());
        this.tag(BlockTags.WALLS)
                .add(ModBlocks.STEELWALL.get());
    }
}
