package net.natsupotato.acidboy;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.model.BipedEntityModel;

@Environment(EnvType.CLIENT)
public class BoyfriendEntityModel extends BipedEntityModel {

    @Override
    public void setAngles(float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch, float scale) {
        super.setAngles(limbAngle, limbDistance, animationProgress, headYaw, headPitch, scale);

        if (this.riding) {

            this.head.pivotY     = 10.0f;
            this.hat.pivotY      = 10.0f;
            this.body.pivotY     = 10.0f;
            this.rightArm.pivotY = 12.0f;
            this.leftArm.pivotY  = 12.0f;
            this.rightLeg.pivotY = 22.0f;
            this.leftLeg.pivotY  = 22.0f;

            this.rightLeg.pitch  = -1.5F;
            this.leftLeg.pitch   = -1.5F;

        } else {

            this.head.pivotY     = 0.0f;
            this.hat.pivotY      = 0.0f;
            this.body.pivotY     = 0.0f;
            this.rightArm.pivotY = 2.0f;
            this.leftArm.pivotY  = 2.0f;
            this.rightLeg.pivotY = 12.0f;
            this.leftLeg.pivotY  = 12.0f;
        }
    }
}
