package net.felix.steelmod.common.block.chest.regular.entity;

import net.felix.steelmod.common.block.ModBlocks;

import net.felix.steelmod.common.block.SteelChestType;
import net.felix.steelmod.common.block.chest.entity.ModBlockEntityTypes;
import net.felix.steelmod.common.block.chest.regular.entity.AbstractSteelChestBlockEntity;
import net.felix.steelmod.common.inventory.SteelChestMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;

public class SteelChestBlockEntity extends AbstractSteelChestBlockEntity {
    public SteelChestBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntityTypes.STEEL_CHEST.get(), blockPos, blockState, SteelChestType.STEEL, ModBlocks.STEEL_CHEST::get);
    }

    @Override
    protected AbstractContainerMenu createMenu(int containerId, Inventory playerInventory) {
        return SteelChestMenu.createSteelContainer(containerId, playerInventory, this);
    }
}
