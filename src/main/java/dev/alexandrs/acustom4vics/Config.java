package dev.alexandrs.acustom4vics;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;
import java.nio.file.Files;
import java.nio.file.Path;

public final class Config {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path FILE = FabricLoader.getInstance().getConfigDir().resolve("acustom4vics.json");
    public static float x = 0.0f, y = 0.0f, z = 0.0f, scale = 1.0f, fov = 90.0f;
    private Config() {}
    public static void load() {
        try {
            if (Files.exists(FILE)) {
                ConfigData d = GSON.fromJson(Files.readString(FILE), ConfigData.class);
                if (d != null) { x=d.x; y=d.y; z=d.z; scale=d.scale; fov=d.fov; }
            }
        } catch (Exception ignored) {}
        clamp();
    }
    public static void save() {
        try {
            Files.createDirectories(FILE.getParent());
            Files.writeString(FILE, GSON.toJson(new ConfigData(x,y,z,scale,fov)));
        } catch (Exception e) { e.printStackTrace(); }
    }
    public static void clamp() {
        x=clamp(x,-1.5f,1.5f); y=clamp(y,-1.5f,1.5f); z=clamp(z,-2.0f,2.0f);
        scale=clamp(scale,0.25f,2.5f); fov=clamp(fov,30f,120f);
    }
    private static float clamp(float v,float a,float b) { return Math.max(a,Math.min(b,v)); }
    private record ConfigData(float x,float y,float z,float scale,float fov) {}
}
