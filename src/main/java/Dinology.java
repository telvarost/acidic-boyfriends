import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.world.dimension.Dimension;
import net.modificationstation.stationapi.api.event.registry.DimensionRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.registry.DimensionContainer;
import net.modificationstation.stationapi.api.registry.DimensionRegistry;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.Namespace;
import net.modificationstation.stationapi.api.util.Null;

public class Dinology {

    @Entrypoint.Namespace
    public static final Namespace NAMESPACE = Null.get();

    @EventListener
    private static void registerDimensions(DimensionRegistryEvent event) {

        for (int i=0; i<100; i++)
            System.out.println("init dimensioN!!!");

        DimensionRegistry r = event.registry;
        r.register(Identifier.of(NAMESPACE, "the_past"), new DimensionContainer<Dimension>(PastDimension::new));
    }

//    public static Item IRON_BAUBLE;
//
//    @EventListener
//    public void registerItems(ItemRegistryEvent event) {
//
//        IRON_BAUBLE = new TemplateItem(NAMESPACE.id("iron_bauble"))
//                .setMaxCount(1)
//                .setTranslationKey(NAMESPACE, "iron_bauble");
//    }
}
