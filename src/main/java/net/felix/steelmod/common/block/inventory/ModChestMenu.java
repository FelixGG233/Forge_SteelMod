package net.felix.steelmod.common.block.inventory;

import net.felix.steelmod.common.block.ModChestTypes;
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

public class ModChestMenu extends AbstractContainerMenu {

    private final Container container;
    private final ModChestTypes chestType;
    private ModChestMenu(@Nullable MenuType<?> menuType, int containerId, Inventory playerInventory) {
        this(menuType, containerId, playerInventory, new SimpleContainer(ModChestTypes.WOOD.size), ModChestTypes.WOOD);
    }

    public static ModChestMenu createSteelContainer(int containerId, Inventory playerInventory){
        return new ModChestMenu(ModChestsContainerTypes.STEEL_CHEST.get(), containerId, playerInventory, new SimpleContainer(ModChestTypes.STEEL.size), ModChestTypes.STEEL);
    }
    public static ModChestMenu createSteelContainer(int containerId, Inventory playerInventory, Container inventory){
        return new ModChestMenu(ModChestsContainerTypes.STEEL_CHEST.get(), containerId, playerInventory, inventory,ModChestTypes.STEEL);
    }

    protected ModChestMenu(@Nullable MenuType<?> menuType, int containerId, Inventory playerInventory, Container inventory, ModChestTypes chestType){
        super(menuType, containerId);

        checkContainerSize(inventory, chestType.size);

        this.container = inventory;
        this.chestType = chestType;

        inventory.startOpen(playerInventory.player);

        int leftCol = (chestType.xSize - 162) / 2 + 1;

        for (int playerInvRow = 0; playerInvRow < 3; playerInvRow++) {
            for (int playerInvCol = 0; playerInvCol < 9; playerInvCol++) {
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

        if (slot != null && slot.hasItem()){
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();

            if (index < this.chestType.size){
                if (!this.moveItemStackTo(itemstack1, this.chestType.size, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            }else if (!this.moveItemStackTo(itemstack1, 0, this.chestType.size, false)){
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

    public Container getContainer(){
        return this.container;
    }
    @OnlyIn(Dist.CLIENT)
    public ModChestTypes getChestType() {
        return this.chestType;
    }
}
