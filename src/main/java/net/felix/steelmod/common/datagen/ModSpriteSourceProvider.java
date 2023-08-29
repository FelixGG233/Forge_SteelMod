package net.felix.steelmod.common.datagen;


import net.felix.steelmod.SteelMod;
import net.minecraft.client.renderer.texture.atlas.sources.DirectoryLister;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.SpriteSourceProvider;

public class ModSpriteSourceProvider extends SpriteSourceProvider {
    public ModSpriteSourceProvider(PackOutput output, ExistingFileHelper fileHelper) {
        super(output, fileHelper, SteelMod.MOD_ID);
    }

    @Override
    protected void addSources() {
        atlas(CHESTS_ATLAS).addSource(new DirectoryLister("model", "model/"));
    }
}
