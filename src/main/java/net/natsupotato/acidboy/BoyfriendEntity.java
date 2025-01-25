package net.natsupotato.acidboy;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FoodItem;
import net.minecraft.item.ItemStack;
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

                this.setTarget(this.getDistance(owner) > 7 ? owner : null);

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

        ItemStack itemStack = player.inventory.getSelectedItem();

        if (this.ownerName.isEmpty()) {

            // for now, boyfriends tame just by clicking them, very simple
            emitParticles("heart");

            this.ownerName = player.name;

            return true;

        } else if (itemStack != null && itemStack.getItem() instanceof FoodItem food) {

            this.heal(food.getHealthRestored());

            emitParticles(this.health == this.maxHealth ? "heart" : "smoke");

            player.inventory.removeStack(player.inventory.selectedSlot, 1);

            return true;

        } else if (this.ownerName.equals(player.name)) {

            this.isSitting = !this.isSitting;
            this.setPath(null);

            return true;
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
