package net.felix.steelmod.common.datagen;

import net.felix.steelmod.SteelMod;
import net.felix.steelmod.common.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.*;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, SteelMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.STEELBLOCK);
        blockWithItem(ModBlocks.IRONDISULFIDE);

        stairsBlock(((StairBlock) ModBlocks.STEELSTAIRS.get()), blockTexture(ModBlocks.STEELBLOCK.get()));
        slabBlock(((SlabBlock) ModBlocks.STEELSLAB.get()), blockTexture(ModBlocks.STEELBLOCK.get()), blockTexture(ModBlocks.STEELBLOCK.get()));

        buttonBlock(((ButtonBlock) ModBlocks.STEELBUTTON.get()), blockTexture(ModBlocks.STEELBLOCK.get()));
        pressurePlateBlock(((PressurePlateBlock) ModBlocks.STEELPRESSUREPLATE.get()), blockTexture(ModBlocks.STEELBLOCK.get()));

        fenceBlock(((FenceBlock) ModBlocks.STEELFENCE.get()), blockTexture(ModBlocks.STEELBLOCK.get()));
        fenceGateBlock(((FenceGateBlock) ModBlocks.STEELFENCEGATE.get()), blockTexture(ModBlocks.STEELBLOCK.get()));
        wallBlock(((WallBlock) ModBlocks.STEELWALL.get()), blockTexture(ModBlocks.STEELBLOCK.get()));

        doorBlockWithRenderType(((DoorBlock) ModBlocks.STEELDOOR.get()), modLoc("block/steel_door_bottom"), modLoc("block/steel_door_top"), "cutout");
        trapdoorBlockWithRenderType(((TrapDoorBlock) ModBlocks.STEELTRAPDOOR.get()), modLoc("block/steel_trap_door"), true, "cutout");

        blockWithItem(ModBlocks.STEEL_COVERED_BRICK);
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject){
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
