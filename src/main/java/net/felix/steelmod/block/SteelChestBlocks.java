package net.felix.steelmod.block;

import net.felix.steelmod.SteelMod;
import net.felix.steelmod.block.chest.SteelChestBlock;
import net.felix.steelmod.block.chest.trapped.TrappedSteelChestBlock;
import net.felix.steelmod.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.concurrent.Callable;
import java.util.function.Function;
import java.util.function.Supplier;

public class SteelChestBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, SteelMod.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = ModItems.ITEMS;
    public static final RegistryObject<SteelChestBlock> STEEL_CHEST = register(
            "steel_chest", () -> new SteelChestBlock(Block.Properties.of().mapColor(MapColor.METAL).strength(10.0F,10.0F)),
            SteelChestType.STEEL, false);
    public static final RegistryObject<TrappedSteelChestBlock> TRAPPED_STEEL_CHEST = register(
            "trapped_steel_chest", () -> new TrappedSteelChestBlock(Block.Properties.of().mapColor(MapColor.METAL).strength(3.0F)),
            SteelChestType.STEEL, true);
    private static <T extends Block> RegistryObject<T> register(String name, Supplier<? extends T> sup, SteelChestType chestType, boolean trapped) {
        return register(name, sup, block -> item(block, () -> () -> chestType, () -> () -> trapped));
    }

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<? extends T> sup, Function<RegistryObject<T>, Supplier<? extends Item>> itemCreator) {
        RegistryObject<T> ret = registerNoItem(name, sup);
        ITEMS.register(name, itemCreator.apply(ret));
        return ret;
    }

    private static <T extends Block> RegistryObject<T> registerNoItem(String name, Supplier<? extends T> sup) {
        return BLOCKS.register(name, sup);
    }

    private static Supplier<BlockItem> item(final RegistryObject<? extends Block> block, Supplier<Callable<SteelChestType>> chestType, Supplier<Callable<Boolean>> trapped) {
        return () -> new SteelChestBlockItem(block.get(), new Item.Properties(), chestType, trapped);
    }
}
