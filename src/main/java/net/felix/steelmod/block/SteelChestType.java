package net.felix.steelmod.block;

import net.felix.steelmod.Util;
import net.felix.steelmod.block.chest.SteelChestBlock;
import net.felix.steelmod.blockentity.chest.AbstractSteelChestBlockEntity;
import net.felix.steelmod.blockentity.chest.SteelChestBlockEntity;
import net.felix.steelmod.blockentity.chest.trapped.TrappedSteelChestBlockEntity;
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

public enum SteelChestType implements StringRepresentable {
        STEEL(81, 9, 184, 276, new ResourceLocation("steelmod", "textures/gui/steel_container.png"), 256, 276);
    private final String name;
    public final int size;
    public final int rowLength;
    public final int xSize;
    public final int ySize;
    public final ResourceLocation guiTexture;
    public final int textureXSize;
    public final int textureYSize;

    SteelChestType(int size, int rowLength, int xSize, int ySize, ResourceLocation guiTexture, int textureXSize, int textureYSize){
        this(null, size, rowLength, xSize, ySize, guiTexture, textureXSize, textureYSize);
    }
    SteelChestType(@Nullable String name, int size, int rowLength, int xSize, int ySize, ResourceLocation guiTexture, int textureXSize, int textureYSize) {
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

    public int getRowCount() {
        return this.size / this.rowLength;
    }

    public static List<Block> get(SteelChestType type){
        return switch (type){
            case STEEL -> Arrays.asList(SteelChestBlocks.STEEL_CHEST.get(), SteelChestBlocks.TRAPPED_STEEL_CHEST.get());
            default -> List.of(Blocks.CHEST);
        };
    }
    @Nullable
    public AbstractSteelChestBlockEntity makeEntity(BlockPos blockPos, BlockState blockState, boolean trapped){
        if (trapped){
            return switch (this){
                case STEEL -> new TrappedSteelChestBlockEntity(blockPos, blockState);
                default -> null;
            };
        }else {
            return switch (this){
                case STEEL -> new SteelChestBlockEntity(blockPos, blockState);
                default -> null;
            };
        }
    }


    
}
