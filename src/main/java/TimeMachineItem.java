import com.matthewperiut.retrocommands.dimension.BareTravelAgent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.template.item.TemplateItem;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.world.dimension.DimensionHelper;

public class TimeMachineItem extends TemplateItem {

    public TimeMachineItem(Identifier id) {
        super(id);
    }

    public ItemStack use(ItemStack stack, World world, PlayerEntity user) {

        DimensionHelper.switchDimension(user, Dinology.THE_PAST, 1.0, new BareTravelAgent());

        return stack;
    }
}
