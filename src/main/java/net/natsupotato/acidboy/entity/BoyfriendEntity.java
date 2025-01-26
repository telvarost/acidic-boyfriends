package net.natsupotato.acidboy.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FoodItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.server.entity.HasTrackingParameters;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.TriState;
import net.natsupotato.acidboy.AcidicBoyfriends;

@SuppressWarnings("UnnecessaryBoxing")
@HasTrackingParameters(updatePeriod = 1, sendVelocity = TriState.TRUE, trackingDistance = 30)
public class BoyfriendEntity extends AnimalEntity implements MobSpawnDataProvider {

    public BoyfriendEntity(World world) {
        super(world);

        this.maxHealth = 20;
        this.health    = 20;

        this.setSkinID(this.random.nextInt(4));
    }

    @Override
    protected void tickLiving() {
        super.tickLiving();

        if (!world.isRemote) {

            if (!this.getOwnerName().isEmpty() && this.vehicle == null) {

                PlayerEntity owner = this.world.getPlayer(this.getOwnerName());

                if (owner != null) {

                    this.setTarget(this.getDistance(owner) > 7 ? owner : null);

                } else if (!this.isSubmergedInWater()) {

                    setSitting(true);
                }
            }
        }
    }

    @Override
    protected boolean isMovementBlocked() {
        return isSitting();
    }

    @Override
    @Environment(EnvType.CLIENT)
    public boolean hasVehicle() {
        return isSitting();
    }

    @Override
    public String getTexture() {

        updateTexture();

        return texture;
    }

    public void updateTexture() {

        this.texture = "/assets/acidboy/stationapi/textures/entity/boyfriend" + getSkinID() + ".png";
    }

    @Override
    public boolean interact(PlayerEntity player) {

        if (!player.world.isRemote) {

            ItemStack itemStack = player.inventory.getSelectedItem();

            if (this.getOwnerName().isEmpty()) {

                // for now, boyfriends tame just by clicking them, very simple
                emitParticles("heart");
                this.setOwnerName(player.name);
                return true;

            } else if (itemStack != null && itemStack.getItem() instanceof FoodItem food) {

                this.heal(food.getHealthRestored());
                emitParticles(this.health == this.maxHealth ? "heart" : "smoke");
                player.inventory.removeStack(player.inventory.selectedSlot, 1);
                return true;

            } else if (this.getOwnerName().equals(player.name)) {

                setSitting(!isSitting());
                this.setPath(null);
                return true;
            }
        }

        return false;
    }

    private void emitParticles(String particleType) {

        for (int i = 0; i < 7; ++i) {

            this.world.addParticle(
                particleType,
                this.x + (double) (this.random.nextFloat() * this.width * 2.0F) - (double) this.width,
                this.y + 0.5 + (double) (this.random.nextFloat() * this.height),
                this.z + (double) (this.random.nextFloat() * this.width * 2.0F) - (double) this.width,
                this.random.nextGaussian() * 0.02,
                this.random.nextGaussian() * 0.02,
                this.random.nextGaussian() * 0.02
            );
        }
    }

    @Override
    protected boolean canDespawn() {
        return this.getOwnerName().isEmpty();
    }

    // Data Tracker
    @Override
    protected void initDataTracker() {
        super.initDataTracker();

        this.dataTracker.startTracking(16, Integer.valueOf(0)); // Sitting
        this.dataTracker.startTracking(17, ""); // Owner Name
        this.dataTracker.startTracking(18, Integer.valueOf(0)); // Skin ID
    }

    public boolean isSitting() {
        return this.dataTracker.getInt(16) == 1;
    }

    public void setSitting(boolean value) {
        this.dataTracker.set(16, value ? 1 : 0);
    }

    public String getOwnerName() {
        return this.dataTracker.getString(17);
    }

    public void setOwnerName(String value) {
        this.dataTracker.set(17, value);
    }

    public int getSkinID() {
        return this.dataTracker.getInt(18);
    }

    public void setSkinID(int value) {

        this.dataTracker.set(18, value);
        updateTexture();
    }

    // NBT
    @Override
    public void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);

        nbt.putBoolean("Sitting", isSitting());
        nbt.putString("Owner", getOwnerName());
        nbt.putInt("Skin", getSkinID());
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);

        setSitting(nbt.getBoolean("Sitting"));
        setOwnerName(nbt.getString("Owner"));
        if (nbt.contains("Skin")) {
            setSkinID(nbt.getInt("Skin"));
        } else {
            setSkinID(this.random.nextInt(4));
        }
    }

    // Spawn Data
    @Override
    public Identifier getHandlerIdentifier() {
        return AcidicBoyfriends.NAMESPACE.id("boyfriend");
    }
}
