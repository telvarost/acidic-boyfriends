package net.natsupotato.acidboy;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.modificationstation.stationapi.api.client.event.render.entity.EntityRendererRegisterEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.util.Namespace;
import net.modificationstation.stationapi.api.util.Null;
import net.natsupotato.acidboy.entity.BoyfriendEntity;
import net.natsupotato.acidboy.entity.BoyfriendEntityModel;

public class AcidicBoyfriendsClient {

    @Entrypoint.Namespace
    public static final Namespace NAMESPACE = Null.get();

    @EventListener
    public void registerEntityRenderer(EntityRendererRegisterEvent event) {

        event.renderers.put(BoyfriendEntity.class, new LivingEntityRenderer(new BoyfriendEntityModel(), 0.5f));
    }
}
