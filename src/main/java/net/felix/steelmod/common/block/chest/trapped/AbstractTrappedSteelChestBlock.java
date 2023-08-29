package net.felix.steelmod.common.block.chest.trapped;

import net.felix.steelmod.common.block.SteelChestType;
import net.felix.steelmod.common.block.chest.regular.AbstractSteelChestBlock;
import net.felix.steelmod.common.block.chest.regular.entity.AbstractSteelChestBlockEntity;
import net.felix.steelmod.common.block.chest.trapped.entity.trapped.AbstractTrappedSteelChestBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.stats.Stat;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Supplier;

public class AbstractTrappedSteelChestBlock extends AbstractSteelChestBlock {
    protected AbstractTrappedSteelChestBlock(Properties properties, Supplier<BlockEntityType<? extends AbstractSteelChestBlockEntity>> blockEntityType, SteelChestType type) {
        super(properties, blockEntityType, type);
    }

    @Override
    protected Stat<ResourceLocation> getOpenChestStat() {
        return Stats.CUSTOM.get(Stats.TRIGGER_TRAPPED_CHEST);
    }

    @Override
    public boolean isSignalSource(BlockState blockState) {
        return true;
    }

    @Override
    public int getSignal(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, Direction direction) {
        return Mth.clamp(AbstractTrappedSteelChestBlockEntity.getOpenCount(blockGetter, blockPos),0,15);
    }

    @Override
    public int getDirectSignal(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, Direction direction) {
        return direction == Direction.UP ? blockState.getSignal(blockGetter, blockPos, direction) : 0;
    }
}
