package net.natsupotato.acidboy;

import net.glasslauncher.mods.gcapi3.api.ConfigRoot;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.event.entity.EntityRegister;
import net.modificationstation.stationapi.api.event.registry.MobHandlerRegistryEvent;
import net.modificationstation.stationapi.api.event.worldgen.biome.BiomeModificationEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.registry.Registry;
import net.modificationstation.stationapi.api.util.Namespace;
import net.modificationstation.stationapi.api.util.Null;
import net.natsupotato.acidboy.entity.BoyfriendEntity;

public class AcidicBoyfriends {

    @Entrypoint.Namespace
    public static final Namespace NAMESPACE = Null.get();

    @ConfigRoot(value = "spawning", visibleName = "Spawning")
    public static final Config.SpawnConfig SPAWN_CONFIG = new Config.SpawnConfig();
    
    @EventListener
    public void registerEntities(EntityRegister event) {
        event.register(BoyfriendEntity.class, NAMESPACE.id("boyfriend").toString());
    }

    @EventListener
    public void registerMobHandlers(MobHandlerRegistryEvent event) {
        Registry.register(event.registry, NAMESPACE.id("boyfriend"), BoyfriendEntity::new);
    }
    
    @EventListener
    public void registerEntitySpawn(BiomeModificationEvent event) {
        if(SPAWN_CONFIG.spawningEnabled){
            event.biome.addPassiveEntity(BoyfriendEntity.class, SPAWN_CONFIG.spawnRarity);
        }
    }
}
