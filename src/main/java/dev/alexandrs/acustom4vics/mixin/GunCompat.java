package dev.alexandrs.acustom4vics.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;

public final class GunCompat {
    private static final String GUN_CLASS = "com.vicmatskiv.pointblank.item.GunItem";
    private GunCompat() {}
    public static boolean isPointBlankGun(ItemStack stack) {
        if (stack == null || stack.isEmpty()) return false;
        try {
            Class<?> type = Class.forName(GUN_CLASS, false, GunCompat.class.getClassLoader());
            return type.isInstance(stack.getItem());
        } catch (ClassNotFoundException ignored) { return false; }
    }
    public static boolean isHoldingPointBlankGun() {
        MinecraftClient client = MinecraftClient.getInstance();
        return client.player != null &&
            (isPointBlankGun(client.player.getMainHandStack()) || isPointBlankGun(client.player.getOffHandStack()));
    }
}
