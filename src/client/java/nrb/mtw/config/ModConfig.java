package nrb.mtw.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * ModConfig class for storing configuration settings.
 * Singleton immutable class. Has an instance auto-update system.
 *
 * @see ConfigHandler
 * @see ConfigManager
 * @see ConfigScreenFactoryImpl
 */

public class ModConfig {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static final boolean DEFAULT_ENABLE_WARNING = true;
    public static final boolean DEFAULT_ONLY_SURVIVAL = false;
    public static final int DEFAULT_SECOND_TOTEM_SLOT = 8;
    public static final float DEFAULT_ZOOM_LEVEL = 1.6F;

    public boolean enableWarning = DEFAULT_ENABLE_WARNING;
    public boolean onlySurvival = DEFAULT_ONLY_SURVIVAL;
    public int secondTotemSlot = DEFAULT_SECOND_TOTEM_SLOT;
    public float zoomLevel = DEFAULT_ZOOM_LEVEL;

    // List of items that, when in the inventory, enable Warning can be toggled
    public List<String> requiredItems = new ArrayList<>();

    //List of words-triggers that, when in the inventory, enableWarning can be toggled
    public String[] enableWords = {"в бой", "duel", "fight", "battle"};
    public String[] disableWords = {"win", "victory", "loss", "defeat"};

    // Immutable singleton instance
    private static ModConfig INSTANCE = new ModConfig();

    public static ModConfig getInstance() {
        return INSTANCE;
    }

    public static void setInstance(ModConfig newConfig) {
        INSTANCE = copy(newConfig); // защита от shared ссылок
    }

    // Autocopy
    public static ModConfig copy(ModConfig other) {
        String json = GSON.toJson(other);
        return GSON.fromJson(json, ModConfig.class);
    }
}
