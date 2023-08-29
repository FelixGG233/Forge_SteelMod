package net.felix.steelmod.common.ai;

import net.felix.steelmod.common.block.chest.regular.AbstractSteelChestBlock;
import net.felix.steelmod.common.block.chest.regular.entity.AbstractSteelChestBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.CatSitOnBlockGoal;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class SteelChestCatSitOnBlockGoal extends CatSitOnBlockGoal {
    public SteelChestCatSitOnBlockGoal(Cat cat, double speedModifier) {
        super(cat, speedModifier);
    }

    @Override
    protected boolean isValidTarget(LevelReader worldIn, BlockPos pos) {
        if (!worldIn.isEmptyBlock(pos.above())){
            return false;
        }else {
            BlockState blockState = worldIn.getBlockState(pos);
            Block block = blockState.getBlock();

            if (block instanceof AbstractSteelChestBlock){
                return AbstractSteelChestBlockEntity.getOpenCount(worldIn, pos) < 1;
            }
        }

        return super.isValidTarget(worldIn, pos);
    }
}
