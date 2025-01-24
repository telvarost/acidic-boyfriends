package net.natsupotato.acidboy;

import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.world.World;

public class BoyfriendEntity extends AnimalEntity {

    public BoyfriendEntity(World world) {
        super(world);

        this.maxHealth = 20;
        this.health    = 20;

        this.texture = "/assets/acidboy/stationapi/textures/entity/test.png";
    }

    @Override
    protected boolean canDespawn() {
        return false;
    }

    // todo ask that guy about what kinda interactions/skins boyfriends should have
}
