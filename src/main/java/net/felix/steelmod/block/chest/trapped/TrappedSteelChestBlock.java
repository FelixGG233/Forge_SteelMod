package net.felix.steelmod.block.chest.trapped;

import net.felix.steelmod.block.SteelChestType;
import net.felix.steelmod.blockentity.ModBlockEntityTypes;
import net.felix.steelmod.blockentity.chest.AbstractSteelChestBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.TrappedChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

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
