package dev.alexandrs.acustom4vics;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.SliderWidget;
import net.minecraft.text.Text;
import java.util.ArrayList;
import java.util.List;

public final class SettingsScreen extends Screen {
    private final List<ConfigSlider> sliders = new ArrayList<>();
    public SettingsScreen() { super(Text.literal("ACustom 4 Vic's")); }

    @Override protected void init() {
        sliders.clear();
        int w = 220, x = this.width / 2 - w / 2, y = this.height / 2 - 78;
        addSlider("Weapon FOV (zoom)", Config.fov, 30, 120, x, y, v -> Config.fov = v); y += 27;
        addSlider("X position", Config.x, -1.5f, 1.5f, x, y, v -> Config.x = v); y += 27;
        addSlider("Y position", Config.y, -1.5f, 1.5f, x, y, v -> Config.y = v); y += 27;
        addSlider("Z position", Config.z, -2f, 2f, x, y, v -> Config.z = v); y += 27;
        addSlider("Weapon scale", Config.scale, 0.25f, 2.5f, x, y, v -> Config.scale = v); y += 34;
        addDrawableChild(ButtonWidget.builder(Text.literal("Reset"), b -> {
            Config.x=0; Config.y=0; Config.z=0; Config.scale=1; Config.fov=90;
            Config.save();
            clearAndInit();
        }).dimensions(x, y, 105, 20).build());
        addDrawableChild(ButtonWidget.builder(Text.literal("Save & close"), b -> {
            Config.clamp(); Config.save(); close();
        }).dimensions(x + 115, y, 105, 20).build());
    }
    private void addSlider(String label, float value, float min, float max, int x, int y, java.util.function.Consumer<Float> setter) {
        ConfigSlider s = new ConfigSlider(x,y,220,20,label,value,min,max,setter);
        sliders.add(s); addDrawableChild(s);
    }
    @Override public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context, mouseX, mouseY, delta);
        context.drawCenteredTextWithShadow(textRenderer, title, width/2, height/2-105, 0xFFFFFF);
        super.render(context, mouseX, mouseY, delta);
    }
    @Override public void close() {
        Config.clamp();
        Config.save();
        if (client != null) client.setScreen(null);
    }
    private static final class ConfigSlider extends SliderWidget {
        private final String label; private final float min,max; private final java.util.function.Consumer<Float> setter;
        ConfigSlider(int x,int y,int w,int h,String label,float value,float min,float max,java.util.function.Consumer<Float> setter) {
            super(x,y,w,h,Text.empty(),(value-min)/(max-min)); this.label=label; this.min=min; this.max=max; this.setter=setter; updateMessage();
        }
        private float val() { return min + (float)value*(max-min); }
        @Override protected void updateMessage() { setMessage(Text.literal(label + ": " + String.format(java.util.Locale.ROOT,"%.2f",val()))); }
        @Override protected void applyValue() { setter.accept(val()); }
    }
}
