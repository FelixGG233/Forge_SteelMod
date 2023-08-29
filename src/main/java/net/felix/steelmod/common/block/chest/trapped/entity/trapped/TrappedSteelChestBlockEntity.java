package net.felix.steelmod.common.block.chest.trapped.entity.trapped;


import net.felix.steelmod.common.block.ModBlocks;
import net.felix.steelmod.common.block.SteelChestType;
import net.felix.steelmod.common.block.chest.entity.ModBlockEntityTypes;
import net.felix.steelmod.common.inventory.SteelChestMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;

public class TrappedSteelChestBlockEntity extends AbstractTrappedSteelChestBlockEntity {
    public TrappedSteelChestBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntityTypes.TRAPPED_STEEL_CHEST.get(), blockPos,blockState, SteelChestType.STEEL, ModBlocks.TRAPPED_STEEL_CHEST::get);
    }

    @Override
    protected AbstractContainerMenu createMenu(int containId, Inventory playerInventory){
        return SteelChestMenu.createSteelContainer(containId,playerInventory,this);
    }
}
