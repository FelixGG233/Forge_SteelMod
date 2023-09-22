package net.felix.steelmod.common.event;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.felix.steelmod.SteelMod;
import net.felix.steelmod.common.item.ModItems;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = SteelMod.MOD_ID)
public class ModEvents {

    @SubscribeEvent
    public static void addCustomTrade(VillagerTradesEvent event){
        if (event.getType() == VillagerProfession.ARMORER){
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();

            trades.get(2).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 4),
                    new ItemStack(ModItems.STEELINGOT.get(), 1),
                    10, 8, 0.02f
            ));


        }
    }

}
