package net.felix.steelmod.common.block.chest.trapped;

import net.felix.steelmod.common.block.SteelChestType;
import net.felix.steelmod.common.block.chest.entity.ModBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.TrappedChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class TrappedSteelChestBlock extends AbstractTrappedSteelChestBlock{
    public TrappedSteelChestBlock(Properties properties) {
        super(properties, ModBlockEntityTypes.TRAPPED_STEEL_CHEST::get, SteelChestType.STEEL);
    }
    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new TrappedChestBlockEntity(blockPos, blockState);
    }
}
