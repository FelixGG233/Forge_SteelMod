package net.felix.steelmod.common.block;

import net.felix.steelmod.common.Util;
import net.felix.steelmod.common.block.entity.AbstractModChestBlockEntity;
import net.felix.steelmod.common.block.entity.SteelChestBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public enum ModChestTypes implements StringRepresentable {
    STEEL(81, 9, 184, 276, new ResourceLocation("steelchest", "textures/gui/steel_container.png"), 256, 276),
    WOOD(0, 0, 0, 0, null, 0, 0);

    private final String name;
    public final int size;
    public final int rowLength;
    public final int xSize;
    public final int ySize;
    public final ResourceLocation guiTexture;
    public final int textureXSize;
    public final int textureYSize;
    ModChestTypes(int size, int rowLength, int xSize, int ySize, ResourceLocation guiTexture, int textureXSize, int textureYSize) {
        this(null, size, rowLength, xSize, ySize, guiTexture, textureXSize, textureYSize);
    }
    ModChestTypes(@Nullable String name, int size, int rowLength, int xSize, int ySize, ResourceLocation guiTexture, int textureXSize, int textureYSize) {
        this.name = name == null ? Util.toEnglishName(this.name()) : name;
        this.size = size;
        this.rowLength = rowLength;
        this.xSize = xSize;
        this.ySize = ySize;
        this.guiTexture = guiTexture;
        this.textureXSize = textureXSize;
        this.textureYSize = textureYSize;
    }

    public String getId() {
        return this.name().toLowerCase(Locale.ROOT);
    }

    public String getEnglishName() {
        return this.name;
    }


    @Override
    public String getSerializedName() {
        return this.getEnglishName();
    }

    public static List<Block> get(ModChestTypes type){
        return switch (type){
            case STEEL -> Arrays.asList(ModBlocks.STEEL_CHEST.get());
            default -> List.of(Blocks.CHEST);
        };
    }

    @Nullable
    public AbstractModChestBlockEntity makeEntity(BlockPos blockPos, BlockState blockState){
        return switch (this){
            case STEEL -> new SteelChestBlockEntity(blockPos, blockState);
            default -> null;
        };
    }
}
