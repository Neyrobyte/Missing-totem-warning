package nrb.mtw.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jetbrains.annotations.Nullable;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ConfigManager {
    public static ModConfig CONFIG = new ModConfig();

    public static @Nullable ModConfig load() {
        try (FileReader reader = new FileReader("config/missing-totem-warning.json")) {
            CONFIG = new Gson().fromJson(reader, ModConfig.class);
            System.out.println("Loading config: " + CONFIG.enableWarning);
            return CONFIG;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void save() {
        try (FileWriter writer = new FileWriter("config/missing-totem-warning.json")) {
            new GsonBuilder().setPrettyPrinting().create().toJson(CONFIG, writer);
            System.out.println("Saving config: " + CONFIG.enableWarning);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}