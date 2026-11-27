package dev.alexandrs.acustom4vics.mixin;

import dev.alexandrs.acustom4vics.Config;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HeldItemRenderer.class)
public abstract class HeldItemRendererMixin {
    @Inject(method = "renderFirstPersonItem", at = @At("HEAD"))
    private void acustom4vics$beforeRender(AbstractClientPlayerEntity player, float tickProgress,
        float pitch, Hand hand, float swingProgress, ItemStack item, float equipProgress,
        MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, CallbackInfo ci) {
        if (GunCompat.isPointBlankGun(item)) {
            matrices.push();
            matrices.translate(Config.x, Config.y, Config.z);
            matrices.scale(Config.scale, Config.scale, Config.scale);
        }
    }

    @Inject(method = "renderFirstPersonItem", at = @At("RETURN"))
    private void acustom4vics$afterRender(AbstractClientPlayerEntity player, float tickProgress,
        float pitch, Hand hand, float swingProgress, ItemStack item, float equipProgress,
        MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, CallbackInfo ci) {
        if (GunCompat.isPointBlankGun(item)) matrices.pop();
    }
}
