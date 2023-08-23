package net.felix.steelmod;

import com.mojang.logging.LogUtils;
import net.felix.steelmod.block.ModBlocks;
import net.felix.steelmod.item.ModCreativeTab;
import net.felix.steelmod.item.ModItems;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(SteelMod.MOD_ID)
public class SteelMod
{
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "steelmod";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();


    public SteelMod()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);

        ModBlocks.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        ModCreativeTab.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);


        modEventBus.addListener(this::addCreative);


        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {

    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS){
            event.accept(ModItems.STEELINGOT);
            event.accept(ModItems.STEELNUGGET);
            event.accept(ModItems.RAWIRONDISULFIDE);
        }
        if(event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS){
            event.accept(ModBlocks.STEELBLOCK);
            event.accept(ModBlocks.IRONDISULFIDE);
        }

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {

        }
    }
}
