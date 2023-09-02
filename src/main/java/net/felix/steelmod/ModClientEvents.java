package net.felix.steelmod;

import net.felix.steelmod.client.render.SteelChestRenderer;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SteelMod.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModClientEvents {

    public static final ModelLayerLocation STEEL_CHEST = new ModelLayerLocation(new ResourceLocation(SteelMod.MOD_ID, "steel_chest"), "main");

    @SubscribeEvent
    public static void layerDefinition(EntityRenderersEvent.RegisterLayerDefinitions event){
        event.registerLayerDefinition(STEEL_CHEST, SteelChestRenderer::createBodyLayer);
    }
}
