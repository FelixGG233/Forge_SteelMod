package net.felix.steelmod.blockentity.chest;

import net.felix.steelmod.block.SteelChestBlocks;
import net.felix.steelmod.block.chest.SteelChestBlock;
import net.felix.steelmod.block.SteelChestType;
import net.felix.steelmod.blockentity.ModBlockEntityTypes;
import net.felix.steelmod.inventory.SteelChestMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;

public class SteelChestBlockEntity extends AbstractSteelChestBlockEntity{
    public SteelChestBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntityTypes.STEEL_CHEST.get(), blockPos, blockState, SteelChestType.STEEL, SteelChestBlocks.STEEL_CHEST::get);
    }

    @Override
    protected AbstractContainerMenu createMenu(int containerId, Inventory playerInventory) {
        return SteelChestMenu.createSteelContainer(containerId, playerInventory, this);
    }
}
