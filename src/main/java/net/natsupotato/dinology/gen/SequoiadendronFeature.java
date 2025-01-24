package net.natsupotato.dinology.gen;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;

public class SequoiadendronFeature extends Feature {

    @Override
    public boolean generate(World world, Random random, int x, int y, int z) {

        x += random.nextInt(0, 16);
        z += random.nextInt(0, 16);

        y = world.getTopSolidBlockY(x, z);

        if (world.getBlockId(x, y - 1, z) != Block.GRASS_BLOCK.id)
            return false;

        for (int dx = -1; dx <= 1; dx++) {
            for (int dz = -1; dz <= 1; dz++) {

                for (int dy = 0; dy <= 30; dy++) {

                    world.setBlockWithoutNotifyingNeighbors(x + dx, y + dy, z + dz, Block.LOG.id, 1);
                }

                if (world.getBlockId(x + dx, y - 1, z + dz) == 0) {

                    world.setBlockWithoutNotifyingNeighbors(x + dx, y - 1, z + dz, Block.LOG.id, 1);
                }
            }
        }

        return true;
    }
}
