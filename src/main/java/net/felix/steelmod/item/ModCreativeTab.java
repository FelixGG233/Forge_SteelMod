package net.felix.steelmod.item;

import net.felix.steelmod.SteelMod;
import net.felix.steelmod.block.ModBlocks;
import net.felix.steelmod.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, SteelMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> ITEM_TAB = CREATIVE_MODE_TABS.register("item_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.STEELINGOT.get()))
                    .title(Component.translatable("creativetab.item_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.STEELINGOT.get());
                        pOutput.accept(ModItems.STEELNUGGET.get());

                    })
                    .build());
    public static final RegistryObject<CreativeModeTab> INGREDIENT_TAB = CREATIVE_MODE_TABS.register("ingredient_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.RAWIRONDISULFIDE.get()))
                    .title(Component.translatable("creativetab.ingredient_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.COKE.get());
                        pOutput.accept(ModItems.RAWIRONDISULFIDE.get());
                    })
                    .build());
    public static final RegistryObject<CreativeModeTab> BLOCKS_TAB = CREATIVE_MODE_TABS.register("block_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.STEELBLOCK.get()))
                    .title(Component.translatable("creativetab.block_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModBlocks.STEELBLOCK.get());
                    })
                    .build());
    public static final RegistryObject<CreativeModeTab> TOOLS_TAB = CREATIVE_MODE_TABS.register("tool_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.METAL_DETECTOR.get()))
                    .title(Component.translatable("creativetab.tool_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.METAL_DETECTOR.get());
                    })
                    .build());
    public static final RegistryObject<CreativeModeTab> ORES_TAB = CREATIVE_MODE_TABS.register("ore_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.IRONDISULFIDE.get()))
                    .title(Component.translatable("creativetab.ore_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModBlocks.IRONDISULFIDE.get());
                    })
                    .build());
    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
