package net.felix.steelmod.datagen;

import net.felix.steelmod.SteelMod;
import net.felix.steelmod.item.ModItems;
import net.felix.steelmod.item.custom.FuelItem;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, SteelMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItems.METAL_DETECTOR);
        simpleItem(ModItems.STEELINGOT);
        simpleItem(ModItems.STEELNUGGET);
        simpleItem(ModItems.RAWIRONDISULFIDE);
        simpleItem(ModItems.COKE);
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item){
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(SteelMod.MOD_ID,"item/" + item.getId().getPath()));
    }
}
