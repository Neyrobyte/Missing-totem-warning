package nrb.mtw.config;

import com.google.gson.Gson;
import org.jetbrains.annotations.Nullable;

import java.io.FileReader;
import java.io.FileWriter;

public class ConfigManager {
    public static ModConfig CONFIG = new ModConfig();

    public static @Nullable ModConfig load() {
        FileReader reader = null; // Внешняя область видимости, НЕ трогать!
        try {
            reader = new FileReader("config/missing-totem-warning.json");
            Gson gson = new Gson();
            CONFIG = gson.fromJson(reader, ModConfig.class);
            return CONFIG;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void save() {
        FileWriter writer = null; // Внешняя область видимости, НЕ трогать!
        try {
            writer = new FileWriter("config/missing-totem-warning.json");
            writer.write("{\n");
            writer.write("  \"enableWarning\": " + CONFIG.enableWarning + "\n");
//            writer.write("  \"requiredItems\": " + CONFIG.requiredItems.toString() + ",\n");
//            writer.write("  \"chatTriggers\": " + CONFIG.chatTriggers.toString() + ",\n");
//            writer.write("  \"useKeybind\": " + CONFIG.useKeybind + "\n");
            writer.write("}");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("Saving config: " + CONFIG.enableWarning);
    }
}