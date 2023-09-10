package net.felix.steelmod.common.ai;

import net.felix.steelmod.SteelMod;
import net.minecraft.world.entity.ai.goal.CatSitOnBlockGoal;
import net.minecraft.world.entity.ai.goal.WrappedGoal;
import net.minecraft.world.entity.animal.Cat;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashSet;

@Mod.EventBusSubscriber(modid = SteelMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CatSitOnChestHandler {

    @SubscribeEvent
    static void changeSittingTaskForOcelots(final LivingEvent.LivingTickEvent evt){
        if (evt.getEntity().tickCount < 5 && evt.getEntity() instanceof Cat cat){
            HashSet<WrappedGoal> goals = new HashSet<>();

            for (WrappedGoal goal : cat.goalSelector.getAvailableGoals()){
                if (goal.getGoal().getClass() == CatSitOnBlockGoal.class){
                    goals.add(goal);
                }
            }

            for (WrappedGoal goal : goals){
                cat.goalSelector.removeGoal(goal.getGoal());
                cat.goalSelector.addGoal(goal.getPriority(), new SteelChestCatSitOnBlockGoal(cat, 0.4F));
            }
        }
    }
}
