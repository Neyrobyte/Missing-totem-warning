package nrb.mtw.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;
import nrb.mtw.MissingTotemWarning;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

/**
 * ConfigManager class for serializing and deserializing the mod configuration to and from a JSON file.
 *
 * @see ModConfig
 * @see ConfigHandler
 * @see ConfigScreenFactoryImpl
 */

public class ConfigManager {
    private static final Path configPath = FabricLoader.getInstance().getConfigDir().resolve(
            MissingTotemWarning.MOD_ID + ".json");

    public static void load() {
        try (FileReader reader = new FileReader(configPath.toFile())) {
            ModConfig loaded = new Gson().fromJson(reader, ModConfig.class);
            ModConfig.setInstance(loaded);
            MissingTotemWarning.LOGGER.info("Config loaded");
        } catch (IOException e) {
            MissingTotemWarning.LOGGER.info(e.getMessage());
            save();
        }
    }

    public static void save() {
        try (FileWriter writer = new FileWriter(configPath.toFile())) {
            new GsonBuilder().setPrettyPrinting().create().toJson(ModConfig.getInstance(), writer);
            MissingTotemWarning.LOGGER.info("Config saved");
        } catch (IOException e) {
            MissingTotemWarning.LOGGER.error(e.getMessage());
        }
    }
}
