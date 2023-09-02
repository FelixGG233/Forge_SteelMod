package net.felix.steelmod.client.model;

import net.felix.steelmod.SteelMod;
import net.felix.steelmod.common.block.ModChestTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;

@Mod.EventBusSubscriber(modid = SteelMod.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SteelChestModel {

    public static final ResourceLocation STEEL_CHEST_LOCATION = new ResourceLocation(SteelMod.MOD_ID, "model/steel_chest");
    public static final ResourceLocation VANILLA_CHEST_LOCATION = new ResourceLocation("entity/chest/normal");

    public static ResourceLocation chooseChestTexture(ModChestTypes type){
        return getResourceLocation(type, STEEL_CHEST_LOCATION, VANILLA_CHEST_LOCATION);
    }

    @NotNull
    private static ResourceLocation getResourceLocation(ModChestTypes type, ResourceLocation steelChestLocation, ResourceLocation vanillaChestLocation){
        return switch (type){
            case STEEL -> steelChestLocation;
            default -> vanillaChestLocation;
        };
    }
}
