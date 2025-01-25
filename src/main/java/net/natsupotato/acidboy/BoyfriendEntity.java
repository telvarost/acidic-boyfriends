package net.natsupotato.acidboy;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;

public class BoyfriendEntity extends AnimalEntity {

    private int skinID;

    public String ownerName;
    public boolean isSitting;

    public BoyfriendEntity(World world) {
        super(world);

        this.maxHealth = 20;
        this.health    = 20;

        this.ownerName = "";

        setSkinID(this.random.nextInt(4));
    }

    public void setSkinID(int value) {

        this.skinID = value;
        this.texture = "/assets/acidboy/stationapi/textures/entity/boyfriend" + this.skinID + ".png";
    }

    @Override
    protected void tickLiving() {
        super.tickLiving();

        if (!this.ownerName.isEmpty() && this.vehicle == null) {

            PlayerEntity owner = this.world.getPlayer(this.ownerName);

            if (owner != null) {

                this.setTarget(this.getDistance(owner) > 8 ? owner : null);

            } else if (!this.isSubmergedInWater()) {

                this.isSitting = true;
            }
        }
    }

    @Override
    protected boolean isMovementBlocked() {
        return this.isSitting;
    }

    @Override
    @Environment(EnvType.CLIENT)
    public boolean hasVehicle() {
        return this.isSitting;
    }

    @Override
    public boolean interact(PlayerEntity player) {

        //ItemStack itemStack = player.inventory.getSelectedItem();

        if (this.ownerName.isEmpty()) {

            String var2 = "heart"; // "smoke"

            for (int var3 = 0; var3 < 7; ++var3) {
                double var4 = this.random.nextGaussian() * 0.02;
                double var6 = this.random.nextGaussian() * 0.02;
                double var8 = this.random.nextGaussian() * 0.02;
                this.world.addParticle(var2, this.x + (double) (this.random.nextFloat() * this.width * 2.0F) - (double) this.width, this.y + 0.5 + (double) (this.random.nextFloat() * this.height), this.z + (double) (this.random.nextFloat() * this.width * 2.0F) - (double) this.width, var4, var6, var8);
            }

            this.ownerName = player.name;

            return true;

        } else if (this.ownerName.equals(player.name)) {

            this.isSitting = !this.isSitting;
            this.setPath(null);

            return true;
        }

        return false;
    }

    @Override
    protected boolean canDespawn() {
        return this.ownerName.isEmpty();
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);

        nbt.putBoolean("Sitting", this.isSitting);
        nbt.putString("Owner", this.ownerName);
        nbt.putInt("Skin", this.skinID);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);

        this.isSitting = nbt.getBoolean("Sitting");
        this.ownerName = nbt.getString("Owner");
        setSkinID(nbt.getInt("Skin"));
    }
}
