package net.felix.steelmod.common.item;

import net.felix.steelmod.client.model.inventory.ModChestItemStackRenderer;
import net.felix.steelmod.common.block.ModBlocks;
import net.felix.steelmod.common.block.ModChestTypes;
import net.felix.steelmod.common.block.entity.SteelChestBlockEntity;
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

public class ModChestBlockItem extends BlockItem {

    protected Supplier<ModChestTypes> type;
    public ModChestBlockItem(Block block, Properties properties, Supplier<Callable<ModChestTypes>> type) {
        super(block, properties);

        ModChestTypes tempType = DistExecutor.unsafeCallWhenOn(Dist.CLIENT, type);
        this.type = tempType == null ? null : () -> tempType;
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        super.initializeClient(consumer);

        consumer.accept(new IClientItemExtensions() {
            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                Supplier<BlockEntity> modelToUse = null;

                switch (type.get()){
                    case STEEL -> modelToUse = () -> new SteelChestBlockEntity(BlockPos.ZERO, ModBlocks.STEEL_CHEST.get().defaultBlockState());
                }
                return new ModChestItemStackRenderer<>(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels(), modelToUse);
            }
        });
    }
}
