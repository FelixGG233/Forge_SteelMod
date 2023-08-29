package net.felix.steelmod.common.block.chest.trapped.entity.trapped;

import net.felix.steelmod.common.block.SteelChestType;
import net.felix.steelmod.common.block.chest.regular.entity.AbstractSteelChestBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Supplier;

public class AbstractTrappedSteelChestBlockEntity extends AbstractSteelChestBlockEntity {
    protected AbstractTrappedSteelChestBlockEntity(BlockEntityType<?> blockEntityType, BlockPos pos, BlockState blockState, SteelChestType chestTypeIn, Supplier<Block> blockToUseIn) {
        super(blockEntityType, pos, blockState,chestTypeIn,blockToUseIn);
    }

    @Override
    protected void signalOpenCount(Level level, BlockPos blockPos, BlockState blockState, int previousCount, int newCount) {
        super.signalOpenCount(level, blockPos, blockState, previousCount, newCount);
        if (previousCount != newCount) {
            Block block = blockState.getBlock();

            level.updateNeighborsAt(blockPos, block);
            level.updateNeighborsAt(blockPos.below(), block);
        }
    }
}
