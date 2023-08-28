package net.felix.steelmod.blockentity.custom;

import net.felix.steelmod.SteelMod;
import net.felix.steelmod.blockentity.ModBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class MetalPressureBlockEntity extends BlockEntity {

    private int counter;
    public MetalPressureBlockEntity( BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntityTypes.METAL_PRESS_BLOCK.get(), pPos, pBlockState);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);

        CompoundTag steelmodData = nbt.getCompound(SteelMod.MOD_ID);
        this.counter = steelmodData.getInt("Counter");
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag nbt) {
        super.saveAdditional(nbt);

        var steelmodData = new CompoundTag();
        steelmodData.putInt("Counter", this.counter);
        nbt.put(SteelMod.MOD_ID, steelmodData);
    }
    public int incrementCounter(){
        this.counter++;
        setChanged();
        return this.counter;
    }

    public int getCounter(){
        return this.counter;
    }
}
