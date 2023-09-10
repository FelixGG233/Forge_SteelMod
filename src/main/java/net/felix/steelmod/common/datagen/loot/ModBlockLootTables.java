package net.felix.steelmod.common.datagen.loot;

import net.felix.steelmod.common.block.ModBlocks;
import net.felix.steelmod.common.item.ModItems;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.dropSelf(ModBlocks.STEELBLOCK.get());

        this.add(ModBlocks.IRONDISULFIDE.get(),
                block -> createCopperLikeOreDrops(ModBlocks.IRONDISULFIDE.get(), ModItems.RAWIRONDISULFIDE.get()));

        this.dropSelf(ModBlocks.STEELSTAIRS.get());
        this.dropSelf(ModBlocks.STEELWALL.get());
        this.dropSelf(ModBlocks.STEELFENCE.get());
        this.dropSelf(ModBlocks.STEELTRAPDOOR.get());
        this.dropSelf(ModBlocks.STEELFENCEGATE.get());
        this.dropSelf(ModBlocks.STEELBUTTON.get());
        this.dropSelf(ModBlocks.STEELPRESSUREPLATE.get());

        this.dropSelf(ModBlocks.STEEL_CHEST.get());
        this.dropSelf(ModBlocks.STEEL_COVERED_BRICK.get());

        this.add(ModBlocks.STEELSLAB.get(),
                block -> createSlabItemTable(ModBlocks.STEELSLAB.get()));

        this.add(ModBlocks.STEELDOOR.get(),
                block -> createDoorTable(ModBlocks.STEELDOOR.get()));
    }
    protected LootTable.Builder createCopperLikeOreDrops(Block pBlock, Item item) {
        return createSilkTouchDispatchTable(pBlock,
                this.applyExplosionDecay(pBlock,
                        LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 5.0F)))
                                .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
