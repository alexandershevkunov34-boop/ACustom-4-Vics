package dev.alexandrs.acustom4vics.mixin;

import dev.alexandrs.acustom4vics.Config;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin {
    @Inject(method = "getFov", at = @At("RETURN"), cancellable = true)
    private void acustom4vics$weaponFov(Camera camera, float tickDelta, boolean changingFov,
        CallbackInfoReturnable<Float> cir) {
        if (GunCompat.isHoldingPointBlankGun()) cir.setReturnValue(Config.fov);
    }
}
