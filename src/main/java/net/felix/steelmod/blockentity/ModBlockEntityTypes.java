package net.felix.steelmod.blockentity;

import net.felix.steelmod.SteelMod;
import net.felix.steelmod.block.ModBlocks;
import net.felix.steelmod.block.SteelChestBlocks;
import net.felix.steelmod.block.chest.trapped.TrappedSteelChestBlock;
import net.felix.steelmod.blockentity.chest.SteelChestBlockEntity;
import net.felix.steelmod.blockentity.chest.trapped.TrappedSteelChestBlockEntity;
import net.felix.steelmod.blockentity.custom.MetalPressureBlockEntity;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntityTypes {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, SteelMod.MOD_ID);
    public static final RegistryObject<BlockEntityType<MetalPressureBlockEntity>> METAL_PRESS_BLOCK =
            BLOCK_ENTITIES.register("metal_pressure_block",
                    () -> BlockEntityType.Builder.of(MetalPressureBlockEntity::new, ModBlocks.METAL_PRESS_BLOCK.get())
                            .build(null));
    public static final RegistryObject<BlockEntityType<SteelChestBlockEntity>> STEEL_CHEST = BLOCK_ENTITIES.register(
            "steel_chest", () -> typeOf(SteelChestBlockEntity::new, SteelChestBlocks.STEEL_CHEST.get()));
    public static final RegistryObject<BlockEntityType<TrappedSteelChestBlockEntity>> TRAPPED_STEEL_CHEST = BLOCK_ENTITIES.register(
            "trapped_dirt_chest", () -> typeOf(TrappedSteelChestBlockEntity::new, SteelChestBlocks.TRAPPED_STEEL_CHEST.get()));

    private static <T extends BlockEntity> BlockEntityType<T> typeOf(BlockEntityType.BlockEntitySupplier<T> entity, Block... blocks) {
        return BlockEntityType.Builder.of(entity, blocks).build(null);
    }
}
