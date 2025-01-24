package net.natsupotato.dinology.dimension;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.world.dimension.Dimension;
import net.modificationstation.stationapi.api.client.world.dimension.TravelMessageProvider;
import net.modificationstation.stationapi.api.util.Identifier;
import net.natsupotato.dinology.Dinology;

public class PastDimension extends Dimension implements TravelMessageProvider {

    // https://github.com/matthewperiut/aether-fabric-b1.7.3/blob/master/src/main/java/com/matthewperiut/aether/gen/dim/AetherDimension.java

    public static final String
            ENTERING_MESSAGE = "gui." + Identifier.of(Dinology.NAMESPACE, "entering_past"),
            LEAVING_MESSAGE = "gui." + Identifier.of(Dinology.NAMESPACE, "exiting_past");

    public PastDimension(int serialId) {
        id = serialId;
    }

    @Override
    protected void initBiomeSource() {
        this.biomeSource = new PastBiomeSource();
    }

//    @Override
//    public ChunkSource createChunkGenerator() {
//        return new ChunkProviderAether(world, world.getSeed());
//    }

    @Environment(EnvType.CLIENT)
    @Override
    public String getEnteringTranslationKey() {
        return ENTERING_MESSAGE;
    }

    @Environment(EnvType.CLIENT)
    @Override
    public String getLeavingTranslationKey() {
        return LEAVING_MESSAGE;
    }
}
