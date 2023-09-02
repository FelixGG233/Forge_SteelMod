package net.felix.steelmod.common.block.custom;


import net.felix.steelmod.common.block.ModChestBlockEntityTypes;
import net.felix.steelmod.common.block.ModChestTypes;
import net.felix.steelmod.common.block.entity.SteelChestBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.checkerframework.checker.nullness.qual.Nullable;

public class SteelChestBlock extends AbstractModChestBlock{
    public SteelChestBlock(Properties properties){
        super(properties, ModChestBlockEntityTypes.STEEL_CHEST::get, ModChestTypes.STEEL);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new SteelChestBlockEntity(blockPos, blockState);
    }
}
