package net.felix.steelmod.inventory;

import net.felix.steelmod.block.SteelChestType;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;

public class SteelChestMenu extends AbstractContainerMenu {
    private final Container container;

    private final SteelChestType chestType;

    public static SteelChestMenu createSteelContainer(int containerId, Inventory playerInventory) {
        return new SteelChestMenu(SteelChestContainerTypes.STEEL_CHEST.get(), containerId, playerInventory, new SimpleContainer(SteelChestType.STEEL.size), SteelChestType.STEEL);
    }

    public static SteelChestMenu createSteelContainer(int containerId, Inventory playerInventory, Container inventory) {
        return new SteelChestMenu(SteelChestContainerTypes.STEEL_CHEST.get(), containerId, playerInventory, inventory, SteelChestType.STEEL);
    }
    protected SteelChestMenu(@Nullable MenuType<?> menuType, int containerId, Inventory playerInventory, Container inventory, SteelChestType chestType) {
        super(menuType, containerId);
        checkContainerSize(inventory, chestType.size);
        this.container = inventory;
        this.chestType = chestType;
        inventory.startOpen(playerInventory.player);
        for (int chestRow = 0; chestRow < chestType.getRowCount(); chestRow++) {
            for (int chestCol = 0; chestCol < chestType.rowLength; chestCol++) {
                this.addSlot(new Slot(inventory, chestCol + chestRow * chestType.rowLength, 12 + chestCol * 18, 18 + chestRow * 18));
            }
        }
        int leftCol = (chestType.xSize - 162)/2+1;
        for (int playerInvRow = 0; playerInvRow < 3; playerInvRow++){
            for (int playerInvCol = 0; playerInvCol<9;playerInvCol++){
                this.addSlot(new Slot(playerInventory, playerInvCol + playerInvRow * 9 + 9, leftCol + playerInvCol * 18, chestType.ySize - (4 - playerInvRow) * 18 - 10));
            }
        }
        for (int hotHarSlot = 0; hotHarSlot < 9; hotHarSlot++) {
            this.addSlot(new Slot(playerInventory, hotHarSlot, leftCol + hotHarSlot * 18, chestType.ySize - 24));
        }
    }
    @Override
    public boolean stillValid(Player player){
        return this.container.stillValid(player);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);

        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();

            if (index < this.chestType.size) {
                if (!this.moveItemStackTo(itemstack1, this.chestType.size, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemstack1, 0, this.chestType.size, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }

        return itemstack;
    }
    @Override
    public void removed(Player playerIn) {
        super.removed(playerIn);
        this.container.stopOpen(playerIn);
    }

    public Container getContainer() {
        return this.container;
    }

    @OnlyIn(Dist.CLIENT)
    public SteelChestType getChestType() {
        return this.chestType;
    }


}
