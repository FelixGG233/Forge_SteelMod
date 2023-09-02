package net.felix.steelmod.common.block.entity;

import net.felix.steelmod.SteelMod;
import net.felix.steelmod.common.block.ModChestTypes;
import net.felix.steelmod.common.block.custom.AbstractModChestBlock;
import net.felix.steelmod.common.block.inventory.ModChestMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.CompoundContainer;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public abstract class AbstractModChestBlockEntity extends RandomizableContainerBlockEntity implements LidBlockEntity {
    private static final int EVENT_SET_OPEN_COUNT = 1;
    private NonNullList<ItemStack> items;

    private final ContainerOpenersCounter openersCounter = new ContainerOpenersCounter() {
        @Override
        protected void onOpen(Level level, BlockPos pos, BlockState blockState) {
            AbstractModChestBlockEntity.playSound(level, pos, blockState, SoundEvents.CHEST_OPEN);
        }

        @Override
        protected void onClose(Level level, BlockPos pos, BlockState blockState) {
            AbstractModChestBlockEntity.playSound(level, pos, blockState, SoundEvents.CHEST_CLOSE);
        }

        @Override
        protected void openerCountChanged(Level level, BlockPos pos, BlockState blockState, int previousCount, int newCount) {
            AbstractModChestBlockEntity.this.signalOpenCount(level, pos, blockState, previousCount, newCount);
        }

        @Override
        protected boolean isOwnContainer(Player player) {
            if (!(player.containerMenu instanceof ModChestMenu)){
                return false;
            }else {
                Container container = ((ModChestMenu) player.containerMenu).getContainer();
                return container instanceof AbstractModChestBlockEntity || container instanceof CompoundContainer && ((CompoundContainer) container).contains(AbstractModChestBlockEntity.this);
            }
        }
    };

    private final ChestLidController chestLidController = new ChestLidController();
    private final ModChestTypes chestType;
    private final Supplier<Block> blockToUse;

    protected AbstractModChestBlockEntity(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState, ModChestTypes chestTypeIn, Supplier<Block> blockToUseIn) {
        super(blockEntityType, blockPos, blockState);

        this.items = NonNullList.<ItemStack>withSize(chestTypeIn.size, ItemStack.EMPTY);
        this.chestType = chestTypeIn;
        this.blockToUse = blockToUseIn;
    }

    @Override
    public int getContainerSize() {
        return this.getItems().size();
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable(SteelMod.MOD_ID + ".container" + this.chestType.getId() + "_chest");
    }

    @Override
    public void load(CompoundTag compoundTag) {
        super.load(compoundTag);

        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);

        if (!this.tryLoadLootTable(compoundTag)) {
            ContainerHelper.loadAllItems(compoundTag, this.items);
        }
    }

    @Override
    protected void saveAdditional(CompoundTag compoundTag) {
        super.saveAdditional(compoundTag);

        if (!this.trySaveLootTable(compoundTag)) {
            ContainerHelper.saveAllItems(compoundTag, this.items);
        }
    }

    public static void lidAnimataTick(Level level, BlockPos blockPos, BlockState blockState, AbstractModChestBlockEntity chestBlockEntity) {
        chestBlockEntity.chestLidController.tickLid();
    }

    static void playSound(Level level, BlockPos blockPos, BlockState blockState, SoundEvent soundEvent) {
        double d0 = (double) blockPos.getX() + 0.5D;
        double d1 = (double) blockPos.getY() + 0.5D;
        double d2 = (double) blockPos.getZ() + 0.5D;

        level.playSound((Player) null, d0, d1, d2, soundEvent, SoundSource.BLOCKS, 0.5F, level.random.nextFloat() * 0.1F + 0.9F);
    }

    @Override
    public boolean triggerEvent(int id, int type) {
        if (id == 1) {
            this.chestLidController.shouldBeOpen(type > 0);
            return true;
        } else {
            return super.triggerEvent(id, type);
        }
    }

    @Override
    public void startOpen(Player player) {
        if (!this.remove && !player.isSpectator()){
            this.openersCounter.decrementOpeners(player, this.getLevel(), this.getBlockPos(), this.getBlockState());
        }
    }

    @Override
    public void stopOpen(Player player) {
        if (!this.remove && !player.isSpectator()) {
            this.openersCounter.decrementOpeners(player, this.getLevel(), this.getBlockPos(), this.getBlockState());
        }
    }

    @Override
    public NonNullList<ItemStack> getItems() {
        return items;
    }

    @Override
    public void setItems(NonNullList<ItemStack> itemsIn) {
        this.items = NonNullList.<ItemStack>withSize(this.getChestType().size, ItemStack.EMPTY);

        for (int i = 0; i < itemsIn.size(); i++) {
            if (i < this.items.size()) {
                this.getItems().set(i, itemsIn.get(i));
            }
        }
    }

    @Override
    public float getOpenNess(float partialTicks) {
        return this.chestLidController.getOpenness(partialTicks);
    }

    public static int getOpenCount(BlockGetter blockGetter, BlockPos blockPos){
        BlockState blockstate = blockGetter.getBlockState(blockPos);

        if (blockstate.hasBlockEntity()){
            BlockEntity blockentity = blockGetter.getBlockEntity(blockPos);

            if (blockentity instanceof AbstractModChestBlockEntity){
                return ((AbstractModChestBlockEntity) blockentity).openersCounter.getOpenerCount();
            }
        }
        return 0;
    }

    public void recheckOpen(){
        if (!this.remove){
            this.openersCounter.recheckOpeners(this.getLevel(), this.getBlockPos(), this.getBlockState());
        }
    }

    protected void signalOpenCount(Level level, BlockPos blockPos, BlockState blockState, int previousCount, int newCount){
        Block block = blockState.getBlock();
        level.blockEvent(blockPos, block, 1, newCount);
    }

    public void wasPlaced(@Nullable LivingEntity livingEntity, ItemStack stack){

    }

    public void removeAdornments(){

    }

    public ModChestTypes getChestType(){
        ModChestTypes type = ModChestTypes.STEEL;

        if (this.hasLevel()){
            ModChestTypes typeFromBlock = AbstractModChestBlock.getTypeFromBlock(this.getBlockState().getBlock());

            if (typeFromBlock != null) {
                type = typeFromBlock;
            }
        }
        return type;
    }
    public Block getBlockToUse(){
        return this.blockToUse.get();
    }
}
