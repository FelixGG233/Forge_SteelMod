package net.felix.steelmod.block.custom;

import net.felix.steelmod.blockentity.ModBlockEntityTypes;

import net.felix.steelmod.blockentity.custom.MetalPressureBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MetalPressureBlock extends HorizontalDirectionalBlock implements EntityBlock {
    public MetalPressureBlock(Properties pProperties) {
        super(pProperties);
        registerDefaultState(defaultBlockState().setValue(FACING, Direction.NORTH));
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return ModBlockEntityTypes.METAL_PRESS_BLOCK.get().create(pos,state);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING);
    }

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState state, @NotNull Level level,@NotNull BlockPos pos,@NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hitResult){
        if (!level.isClientSide() && hand == InteractionHand.MAIN_HAND){
            BlockEntity be = level.getBlockEntity(pos);
            if (be instanceof MetalPressureBlockEntity blockEntity){
                int counter = player.isCrouching() ? blockEntity.getCounter() : blockEntity.incrementCounter();
                player.sendSystemMessage(Component.literal("BlockEntity has been used %d time".formatted(counter)));
                return InteractionResult.sidedSuccess(level.isClientSide());
            }
        }
        return super.use(state,level,pos,player,hand,hitResult);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }
}
