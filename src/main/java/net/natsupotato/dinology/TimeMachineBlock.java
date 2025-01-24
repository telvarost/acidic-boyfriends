package net.natsupotato.dinology;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.template.block.TemplateBlock;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.world.dimension.DimensionHelper;
import net.natsupotato.dinology.dimension.PastTravelAgent;

public class TimeMachineBlock extends TemplateBlock {

    public TimeMachineBlock(Identifier id) {
        super(id, Material.METAL);
    }

    @Override
    public boolean onUse(World world, int x, int y, int z, PlayerEntity player) {

        DimensionHelper.switchDimension(player, Dinology.THE_PAST, 1.0, new PastTravelAgent(true));

        return true;
    }
}
