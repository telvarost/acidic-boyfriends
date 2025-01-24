import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.item.Item;
import net.minecraft.world.dimension.Dimension;
import net.modificationstation.stationapi.api.event.registry.DimensionRegistryEvent;
import net.modificationstation.stationapi.api.event.registry.ItemRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.registry.DimensionContainer;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.Namespace;
import net.modificationstation.stationapi.api.util.Null;

public class Dinology {

    @Entrypoint.Namespace
    public static final Namespace NAMESPACE = Null.get();
    public static Identifier THE_PAST;

    public static Item TIME_MACHINE_ITEM;
    // TIME_MACHINE_BLOCK

    @EventListener
    private static void registerDimensions(DimensionRegistryEvent event) {

        event.registry.register(
                THE_PAST = Identifier.of(NAMESPACE, "the_past"),
                new DimensionContainer<Dimension>(PastDimension::new)
        );
    }

    @EventListener
    public void registerItems(ItemRegistryEvent event) {

        TIME_MACHINE_ITEM = new TimeMachineItem(NAMESPACE.id("time_machine"))
                .setMaxCount(1)
                .setTranslationKey(NAMESPACE, "time_machine");
    }
}
