package nrb.mtw.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;
import nrb.mtw.MissingTotemWarning;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

public class ConfigManager {
    public static ModConfig CONFIG = new ModConfig();
    private static final Path configPath = FabricLoader.getInstance().getConfigDir().resolve(
            MissingTotemWarning.MOD_ID +
                    ".json"
    );

    public static void load() {
        try (FileReader reader = new FileReader(configPath.toFile())) {
            CONFIG = new Gson().fromJson(reader, ModConfig.class);
            if (CONFIG == null) {
                CONFIG = new ModConfig();
            }
            MissingTotemWarning.LOGGER.info("Loading config: {}", CONFIG.enableWarning);
        } catch (IOException e) {
            MissingTotemWarning.LOGGER.error(e.getMessage());
            CONFIG = new ModConfig();
        }
    }

    public static void save() {
        try (FileWriter writer = new FileWriter(configPath.toFile())) {
            new GsonBuilder().setPrettyPrinting().create().toJson(CONFIG, writer);
            MissingTotemWarning.LOGGER.info("Config saved: {}", CONFIG.enableWarning);
        } catch (IOException e) {
            MissingTotemWarning.LOGGER.error(e.getMessage());
        }
    }

    public static void toggleWarning() {
        CONFIG.enableWarning = !CONFIG.enableWarning;
        save();
    }
}