package mmr.littledelicacies.api.trackmaid;

import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TrackMaidProvider implements ICapabilitySerializable {
    private final ITrackMaidCapability instance;
    @CapabilityInject(ITrackMaidCapability.class)
    public static final Capability<ITrackMaidCapability> TRACKMAID_CAPABILITY = null;

    public TrackMaidProvider() {
        this.instance = new TrackMaidCapability();
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == TRACKMAID_CAPABILITY) {
            return LazyOptional.of(() -> instance).cast();
        } else {
            return LazyOptional.empty();
        }
    }

    @Override
    public INBT serializeNBT() {
        return instance.serializeNBT();
    }

    @Override
    public void deserializeNBT(INBT nbt) {
        instance.deserializeNBT(nbt);
    }
}