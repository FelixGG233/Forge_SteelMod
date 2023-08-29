package net.felix.steelmod.common.item;

import net.felix.steelmod.client.model.inventory.SteelChestItemStackRenderer;
import net.felix.steelmod.common.block.ModBlocks;
import net.felix.steelmod.common.block.SteelChestType;
import net.felix.steelmod.common.block.chest.regular.entity.SteelChestBlockEntity;
import net.felix.steelmod.common.block.chest.trapped.entity.trapped.TrappedSteelChestBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.fml.DistExecutor;

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
                        default -> modelToUse = () -> new TrappedSteelChestBlockEntity(BlockPos.ZERO, ModBlocks.TRAPPED_STEEL_CHEST.get().defaultBlockState());
                    }
                }else {
                    switch (type.get()){
                        default -> modelToUse = () -> new SteelChestBlockEntity(BlockPos.ZERO, ModBlocks.STEEL_CHEST.get().defaultBlockState());
                    }
                }
                return new SteelChestItemStackRenderer<>(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels(), modelToUse);
            }
        });
    }

}
