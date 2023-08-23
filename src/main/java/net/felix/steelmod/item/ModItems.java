package net.felix.steelmod.item;

import net.felix.steelmod.SteelMod;
import net.felix.steelmod.item.custom.FuelItem;
import net.felix.steelmod.item.custom.MetalDetectorItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, SteelMod.MOD_ID);

    public static final RegistryObject<Item> STEELINGOT = ITEMS.register("steel_ingot",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> STEELNUGGET = ITEMS.register("steel_nugget",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAWIRONDISULFIDE = ITEMS.register("raw_iron_disulfide",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> METAL_DETECTOR = ITEMS.register("metal_detector",
            () -> new MetalDetectorItem(new Item.Properties().durability(100)));
    public static final RegistryObject<Item> COKE = ITEMS.register("coke",
            () -> new FuelItem(new Item.Properties(), 400));


    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
