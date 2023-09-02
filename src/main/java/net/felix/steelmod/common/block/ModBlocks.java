package net.felix.steelmod.common.block;

import net.felix.steelmod.SteelMod;


import net.felix.steelmod.common.block.custom.SteelChestBlock;
import net.felix.steelmod.common.item.ModChestBlockItem;
import net.felix.steelmod.common.item.ModItems;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.concurrent.Callable;
import java.util.function.Function;
import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, SteelMod.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = ModItems.ITEMS;



    public static final RegistryObject<Block> STEELBLOCK = registerBlock("steel_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(10.0f,10.0f)));
    public static final RegistryObject<Block> IRONDISULFIDE = registerBlock("iron_disulfide",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)
                    .requiresCorrectToolForDrops(), UniformInt.of(1,5)));
    public static final RegistryObject<Block> STEELSTAIRS = registerBlock("steel_stairs",
            () -> new StairBlock(() -> ModBlocks.STEELBLOCK.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(6.0f,7.0f)));
    public static final RegistryObject<Block> STEELSLAB = registerBlock("steel_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(6.0f,7.0f)));
    public static final RegistryObject<Block> STEELBUTTON = registerBlock("steel_button",
            () -> new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BUTTON).strength(6.0f,7.0f),
                    BlockSetType.IRON, 10,true));
    public static final RegistryObject<Block> STEELPRESSUREPLATE = registerBlock("steel_pressure_plate",
            () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(6.0f,7.0f),
                    BlockSetType.IRON));
    public static final RegistryObject<Block> STEELFENCE = registerBlock("steel_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(6.0f,7.0f)));
    public static final RegistryObject<Block> STEELFENCEGATE = registerBlock("steel_fence_gate",
            () -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(6.0f,7.0f), SoundEvents.CHAIN_PLACE, SoundEvents.ANVIL_BREAK));
    public static final RegistryObject<Block> STEELWALL = registerBlock("steel_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(6.0f,7.0f)));
    public static final RegistryObject<Block> STEELDOOR = registerBlock("steel_door",
            () -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(6.0f,7.0f).noOcclusion(), BlockSetType.IRON));
    public static final RegistryObject<Block> STEELTRAPDOOR = registerBlock("steel_trap_door",
            () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(6.0f,7.0f).noOcclusion(), BlockSetType.IRON));

    public static final RegistryObject<Block> STEEL_COVERED_BRICK = registerBlock("steel_covered_brick",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.BRICKS).strength(6.0f, 8.0f)));

    public static final RegistryObject<Block> STEEL_CHEST = register(
            "steel_chest", () -> new SteelChestBlock(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).strength(6.0f,7.0f)),
            ModChestTypes.STEEL);

    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block){
        RegistryObject<T> toReturn = BLOCKS.register(name,block);
        registryBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block>RegistryObject<Item> registryBlockItem(String name, RegistryObject<T> block){
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<? extends T> sup, ModChestTypes chestType) {
        return register(name, sup, block -> item(block, () -> () -> chestType));
    }

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<? extends T> sup, Function<RegistryObject<T>, Supplier<? extends Item>> itemCreator) {
        RegistryObject<T> ret = registerNoItem(name, sup);
        ITEMS.register(name, itemCreator.apply(ret));
        return ret;
    }
    private static Supplier<BlockItem> item(final RegistryObject<? extends Block> block, Supplier<Callable<ModChestTypes>> chestType) {
        return () -> new ModChestBlockItem(block.get(), new Item.Properties(), chestType);
    }

    private static <T extends Block> RegistryObject<T> registerNoItem(String name, Supplier<? extends T> sup) {
        return BLOCKS.register(name, sup);
    }


}
