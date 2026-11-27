package dev.alexandrs.acustom4vics;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public final class ACustom4VicsClient implements ClientModInitializer {
    public static KeyBinding OPEN_SETTINGS;

    @Override public void onInitializeClient() {
        Config.load();
        OPEN_SETTINGS = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.acustom4vics.settings", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_O,
            "category.acustom4vics"
        ));
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (OPEN_SETTINGS.wasPressed()) {
                if (client.currentScreen == null) client.setScreen(new SettingsScreen());
            }
        });
    }
}
