import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.world.dimension.Dimension;
import net.modificationstation.stationapi.api.client.world.dimension.TravelMessageProvider;
import net.modificationstation.stationapi.api.util.Identifier;

public class PastDimension extends Dimension implements TravelMessageProvider {

    public static final String
            ENTERING_MESSAGE = "gui." + Identifier.of(Dinology.NAMESPACE, "entering_past"),
            LEAVING_MESSAGE = "gui." + Identifier.of(Dinology.NAMESPACE, "exiting_past");

    public PastDimension(int serialId) {
        id = serialId;
    }

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
