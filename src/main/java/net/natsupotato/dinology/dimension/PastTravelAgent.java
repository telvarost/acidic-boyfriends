package net.natsupotato.dinology.dimension;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraft.world.dimension.PortalForcer;

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

        entity.y = world.getTopSolidBlockY((int) entity.x, (int) entity.z);

        if (isUsingMultiBlock) {

            world.setBlockWithoutNotifyingNeighbors((int) entity.x - 1, (int) entity.y - 1, (int) entity.z - 1, Block.IRON_BLOCK.id);
        }

        return true;
    }
}
