package net.felix.steelmod.common.block.inventory;

import net.felix.steelmod.SteelMod;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModChestsContainerTypes {
    public static final DeferredRegister<MenuType<?>> CONTAINERS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, SteelMod.MOD_ID);

    public static final RegistryObject<MenuType<ModChestMenu>> STEEL_CHEST =
            CONTAINERS.register("steel_chest", () -> new MenuType<>(ModChestMenu::createSteelContainer, FeatureFlags.REGISTRY.allFlags()));
}
