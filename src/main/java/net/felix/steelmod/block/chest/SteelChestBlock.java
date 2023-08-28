package net.felix.steelmod.block.chest;

import net.felix.steelmod.block.SteelChestType;
import net.felix.steelmod.blockentity.ModBlockEntityTypes;
import net.felix.steelmod.blockentity.chest.SteelChestBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class SteelChestBlock extends AbstractSteelChestBlock {
    public SteelChestBlock(Properties properties) {
        super(properties, ModBlockEntityTypes.STEEL_CHEST::get, SteelChestType.STEEL);
    }
    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new SteelChestBlockEntity(blockPos, blockState);
    }
}
