package net.felix.steelmod.inventory;

import net.felix.steelmod.SteelMod;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SteelChestContainerTypes {
    public static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, SteelMod.MOD_ID);
    public static final RegistryObject<MenuType<SteelChestMenu>> STEEL_CHEST = CONTAINERS.register("iron_chest", () -> new MenuType<>(SteelChestMenu::createIronContainer, FeatureFlags.REGISTRY.allFlags()));
}
