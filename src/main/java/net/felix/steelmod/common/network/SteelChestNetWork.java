package net.felix.steelmod.common.network;

import net.felix.steelmod.SteelMod;
import net.felix.steelmod.common.network.helper.ISimplePacket;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class SteelChestNetWork {

    private static SteelChestNetWork instance = null;

    public final SimpleChannel network;
    private int id = 0;
    private static final String PROTOCOL_VERSION = Integer.toString(1);

    public SteelChestNetWork(){
        this.network = NetworkRegistry.ChannelBuilder.named(new ResourceLocation(SteelMod.MOD_ID, "network"))
                .clientAcceptedVersions(PROTOCOL_VERSION::equals)
                .serverAcceptedVersions(PROTOCOL_VERSION::equals)
                .networkProtocolVersion(() -> PROTOCOL_VERSION)
                .simpleChannel();
    }

    public static SteelChestNetWork getInstance(){
        if (instance == null){
            throw new IllegalStateException("Attempt to call network getInstance before network is setup");
        }
        return instance;
    }

    public static void setup(){
        if (instance != null){
            return;
        }

        instance = new SteelChestNetWork();
        instance.registerPacket(InventoryTopStacksSyncPacket.class, InventoryTopStacksSyncPacket::new, NetworkDirection.PLAY_TO_CLIENT);
    }

    public <MSG extends ISimplePacket> void registerPacket(Class<MSG> clazz, Function<FriendlyByteBuf, MSG> decoder, @Nullable NetworkDirection direction) {
        registerPacket(clazz, ISimplePacket::encode, decoder, ISimplePacket::handle, direction);
    }

    public <MSG> void registerPacket(Class<MSG> clazz, BiConsumer<MSG, FriendlyByteBuf> encoder, Function<FriendlyByteBuf, MSG> decoder, BiConsumer<MSG, Supplier<NetworkEvent.Context>> consumer, @Nullable NetworkDirection direction) {
        this.network.registerMessage(this.id++, clazz, encoder, decoder, consumer, Optional.ofNullable(direction));
    }

    public void sendToServer(Object msg) {
        this.network.sendToServer(msg);
    }

    public void send(PacketDistributor.PacketTarget target, Object message) {
        network.send(target, message);
    }

    public void sendVanillaPacket(Packet<?> packet, Entity player) {
        if (player instanceof ServerPlayer && ((ServerPlayer) player).connection != null) {
            ((ServerPlayer) player).connection.send(packet);
        }
    }

    public void sendTo(Object msg, Player player) {
        if (player instanceof ServerPlayer) {
            sendTo(msg, (ServerPlayer) player);
        }
    }

    public void sendTo(Object msg, ServerPlayer player) {
        if (!(player instanceof FakePlayer)) {
            network.sendTo(msg, player.connection.connection, NetworkDirection.PLAY_TO_CLIENT);
        }
    }

    public void sendToClientsAround(Object msg, ServerLevel serverWorld, BlockPos position) {
        LevelChunk chunk = serverWorld.getChunkAt(position);
        network.send(PacketDistributor.TRACKING_CHUNK.with(() -> chunk), msg);
    }

    public void sendToTrackingAndSelf(Object msg, net.minecraft.world.entity.Entity entity) {
        this.network.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> entity), msg);
    }

    public void sendToTracking(Object msg, net.minecraft.world.entity.Entity entity) {
        this.network.send(PacketDistributor.TRACKING_ENTITY.with(() -> entity), msg);
    }

    public void sendToClientsAround(Object msg, @Nullable LevelAccessor world, BlockPos position) {
        if (world instanceof ServerLevel) {
            sendToClientsAround(msg, (ServerLevel) world, position);
        }
    }
}
