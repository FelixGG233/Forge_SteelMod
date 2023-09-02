package net.felix.steelmod.common.block.entity;

import net.felix.steelmod.common.block.ModBlocks;
import net.felix.steelmod.common.block.ModChestBlockEntityTypes;
import net.felix.steelmod.common.block.ModChestTypes;
import net.felix.steelmod.common.block.inventory.ModChestMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Supplier;

public class SteelChestBlockEntity extends AbstractModChestBlockEntity{
    public SteelChestBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModChestBlockEntityTypes.STEEL_CHEST.get(), blockPos, blockState, ModChestTypes.STEEL, ModBlocks.STEEL_CHEST::get);
    }

    @Override
    protected AbstractContainerMenu createMenu(int containerId, Inventory playerInventory) {
        return ModChestMenu.createSteelContainer(containerId, playerInventory, this);
    }
}
