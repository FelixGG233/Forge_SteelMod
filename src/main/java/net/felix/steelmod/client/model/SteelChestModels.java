package net.felix.steelmod.client.model;

import net.felix.steelmod.SteelMod;
import net.felix.steelmod.common.block.SteelChestType;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class SteelChestModels {
    public static final ResourceLocation STEEL_CHEST_LOCATION = new ResourceLocation(SteelMod.MOD_ID, "models/steel_chest");
    public static final ResourceLocation VANILLA_CHEST_LOCATION = new ResourceLocation("entity/chest/normal");

    public static final ResourceLocation TRAPPED_STEEL_CHEST_LOCATION = new ResourceLocation(SteelMod.MOD_ID, "models/trapped_steel_chest");
    public static final ResourceLocation TRAPPED_VANILLA_CHEST_LOCATION = new ResourceLocation("entity/chest/trapped");

    public static ResourceLocation chooseChestTexture(SteelChestType type, boolean trapped){
        if (trapped){
            return getResourceLocation(type, TRAPPED_STEEL_CHEST_LOCATION, TRAPPED_VANILLA_CHEST_LOCATION);
        }else {
            return getResourceLocation(type, STEEL_CHEST_LOCATION, VANILLA_CHEST_LOCATION);
        }
    }

    @NotNull
    private static ResourceLocation getResourceLocation(SteelChestType type, ResourceLocation steelChestLocation, ResourceLocation vanillaChestLocation){
        return switch (type){
            case STEEL -> steelChestLocation;
            default -> vanillaChestLocation;
        };
    }
}
