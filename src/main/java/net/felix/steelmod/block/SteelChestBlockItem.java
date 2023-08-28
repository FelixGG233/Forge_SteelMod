package net.felix.steelmod.block;

import net.felix.steelmod.block.chest.render.SteelChestItemStackRanderer;
import net.felix.steelmod.blockentity.chest.SteelChestBlockEntity;
import net.felix.steelmod.blockentity.chest.trapped.TrappedSteelChestBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.TrappedChestBlockEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.fml.DistExecutor;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class SteelChestBlockItem extends BlockItem {
    protected Supplier<SteelChestType> type;
    protected Supplier<Boolean> trapped;
    public SteelChestBlockItem(Block block, Properties properties, Supplier<Callable<SteelChestType>> type, Supplier<Callable<Boolean>> trapped) {
        super(block, properties);

        SteelChestType tempType = DistExecutor.unsafeCallWhenOn(Dist.CLIENT, type);
        Boolean tempTrapped = DistExecutor.unsafeCallWhenOn(Dist.CLIENT, trapped);

        this.type = tempType == null ? null : () -> tempType;
        this.trapped = tempTrapped == null ? null : () -> tempTrapped;
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        super.initializeClient(consumer);

        consumer.accept(new IClientItemExtensions() {
            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                Supplier<BlockEntity> modelToUse;

                if (trapped.get()) {
                    switch (type.get()) {
                        default -> modelToUse = () -> new TrappedSteelChestBlockEntity(BlockPos.ZERO, SteelChestBlocks.TRAPPED_STEEL_CHEST.get().defaultBlockState());
                    }
                }else {
                    switch (type.get()){
                        default -> modelToUse = () -> new SteelChestBlockEntity(BlockPos.ZERO, SteelChestBlocks.STEEL_CHEST.get().defaultBlockState());
                    }
                }
                return new SteelChestItemStackRanderer<>(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels(), modelToUse);
            }
        });
    }

}
