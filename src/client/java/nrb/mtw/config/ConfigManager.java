package nrb.mtw.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import nrb.mtw.MissingTotemWarning;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ConfigManager {
    public static ModConfig CONFIG = new ModConfig();

    public static ModConfig load() {
        try (FileReader reader = new FileReader("config/" + MissingTotemWarning.MOD_ID + ".json")) {
            CONFIG = new Gson().fromJson(reader, ModConfig.class);
            MissingTotemWarning.LOGGER.info("Loading config: {}", CONFIG.enableWarning);
            if (CONFIG == null) {
                return new ModConfig();
            }
            return CONFIG;
        } catch (IOException e) {
            e.printStackTrace();
            return new ModConfig();
        }
    }

    public static void save() {
        try (FileWriter writer = new FileWriter("config/" + MissingTotemWarning.MOD_ID + ".json")) {
            new GsonBuilder().setPrettyPrinting().create().toJson(CONFIG, writer);
            MissingTotemWarning.LOGGER.info("Config saved: {}", CONFIG.enableWarning);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}