package nrb.mtw.config;

import com.google.gson.Gson;
import java.io.FileReader;
import java.io.FileWriter;

public class ConfigManager {
    public static ModConfig CONFIG = new ModConfig();

    public static ModConfig load() {
        FileReader reader = null; // Внешняя область видимости, НЕ трогать!
        try {
            reader = new FileReader("config/missing-totem-warning.json");
            StringBuilder jsonBuilder = new StringBuilder();
            int c;
            while ((c = reader.read()) != -1) {
                jsonBuilder.append((char) c);
            }
            String json = jsonBuilder.toString();
            Gson gson = new Gson();
            CONFIG = gson.fromJson(json, ModConfig.class);
            System.out.println("Loaded config: " + CONFIG);
            System.out.println("Loaded config: " + CONFIG.enableWarning);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return CONFIG;
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