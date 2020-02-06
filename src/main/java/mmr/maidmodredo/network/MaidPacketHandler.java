package mmr.maidmodredo.network;

import mmr.maidmodredo.MaidModRedo;
import mmr.maidmodredo.api.MaidAnimation;
import mmr.maidmodredo.entity.LittleMaidBaseEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import org.apache.commons.lang3.ArrayUtils;

public class MaidPacketHandler {
    public static final String NETWORK_PROTOCOL = "2";

    public static final SimpleChannel CHANNEL = NetworkRegistry.ChannelBuilder.named(new ResourceLocation(MaidModRedo.MODID, "net"))
            .networkProtocolVersion(() -> NETWORK_PROTOCOL)
            .clientAcceptedVersions(NETWORK_PROTOCOL::equals)
            .serverAcceptedVersions(NETWORK_PROTOCOL::equals)
            .simpleChannel();

    public static void register() {
        CHANNEL.messageBuilder(MessageChangeModelStat.class, 0)
                .encoder(MessageChangeModelStat::writePacketData).decoder(MessageChangeModelStat::readPacketData)
                .consumer(MessageChangeModelStat.Handler::handle)
                .add();
        CHANNEL.messageBuilder(MessageAnimation.class, 1)
                .encoder(MessageAnimation::writePacketData).decoder(MessageAnimation::readPacketData)
                .consumer(MessageAnimation.Handler::handle)
                .add();
    }

    public static void syncModel(LittleMaidBaseEntity entity, CompoundNBT compoundNBT) {
        if (!entity.getEntityWorld().isRemote()) {
            MaidPacketHandler.CHANNEL.sendToServer(new MessageChangeModelStat(entity, compoundNBT));

        } else {
            MaidPacketHandler.CHANNEL.send(PacketDistributor.TRACKING_ENTITY.with(() -> entity), new MessageChangeModelStat(entity.getEntityId(), compoundNBT));
        }
    }

    public static void animationModel(LittleMaidBaseEntity entity, MaidAnimation animation) {
        if (!entity.getEntityWorld().isRemote()) {
            entity.setAnimation(animation);
            MaidPacketHandler.CHANNEL.send(PacketDistributor.TRACKING_ENTITY.with(() -> entity), new MessageAnimation(entity.getEntityId(), ArrayUtils.indexOf(entity.getAnimations(), animation)));
        }
    }
}