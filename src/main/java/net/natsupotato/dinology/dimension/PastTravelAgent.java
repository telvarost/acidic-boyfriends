package net.natsupotato.dinology.dimension;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraft.world.dimension.PortalForcer;
import net.natsupotato.dinology.Dinology;

import java.util.Random;

public class PastTravelAgent extends PortalForcer {

    private final Random random = new Random();
    private final boolean isUsingMultiBlock;

    public PastTravelAgent(boolean isUsingMultiBlock) {

        this.isUsingMultiBlock = isUsingMultiBlock;
    }

    public boolean teleportToValidPortal(World world, Entity entity) {

        return false;
    }

    public boolean createPortal(World world, Entity entity) {

        int x = (int) entity.x;
        int y = world.getTopSolidBlockY((int) entity.x, (int) entity.z);
        int z = (int) entity.z;

        if (isUsingMultiBlock) {

            world.setBlockWithoutNotifyingNeighbors(x, y, z, Dinology.TIME_MACHINE.id);
        }

        entity.x = x;
        entity.y = y + 1;
        entity.z = z;

        return true;
    }
}
