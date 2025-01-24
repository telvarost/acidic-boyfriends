package net.natsupotato.dinology;

import net.minecraft.world.gen.feature.BirchTreeFeature;
import net.natsupotato.dinology.gen.PastDimension;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.item.Item;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.dimension.Dimension;
import net.modificationstation.stationapi.api.event.registry.DimensionRegistryEvent;
import net.modificationstation.stationapi.api.event.registry.ItemRegistryEvent;
import net.modificationstation.stationapi.api.event.world.biome.BiomeRegisterEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.registry.DimensionContainer;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.Namespace;
import net.modificationstation.stationapi.api.util.Null;
import net.modificationstation.stationapi.api.worldgen.biome.BiomeBuilder;
import net.natsupotato.dinology.gen.SequoiadendronFeature;

public class Dinology {

    @Entrypoint.Namespace
    public static final Namespace NAMESPACE = Null.get();

    public static Identifier THE_PAST;

    public static Biome PAST_BIOME;

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

    @EventListener
    public void registerBiomes(BiomeRegisterEvent event) {

        // TODO just make custom biome BiomeBuilder sucks ass
        PAST_BIOME = BiomeBuilder.start("past_biome")
                .grassAndLeavesColor(-14518477)
                .precipitation(false)
                .passiveEntity(ChickenEntity.class, 8)
                .snow(false)
                .feature(new SequoiadendronFeature())
                .noDimensionFeatures()
                .build();
    }
}
