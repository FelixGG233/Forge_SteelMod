package net.felix.steelmod.block.chest.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.function.Supplier;

@OnlyIn(Dist.CLIENT)
public class SteelChestItemStackRanderer<T extends BlockEntity> extends BlockEntityWithoutLevelRenderer {

    private final Supplier<T> te;
    private final BlockEntityRenderDispatcher blockEntityRenderDispatcher;
    public SteelChestItemStackRanderer(BlockEntityRenderDispatcher renderDispatcher, EntityModelSet modelSet, Supplier<T> te) {
        super(renderDispatcher, modelSet);

        this.te = te;
        this.blockEntityRenderDispatcher = renderDispatcher;
    }

    @Override
    public void renderByItem(ItemStack itemStack, ItemDisplayContext tranfromTypr, PoseStack matrixStackIn, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
        super.renderByItem(itemStack, tranfromTypr, matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn);
    }
}
