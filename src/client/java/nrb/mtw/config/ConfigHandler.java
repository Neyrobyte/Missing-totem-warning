package nrb.mtw.config;

public class ConfigHandler {
    public static void toggleWarning() {
        ConfigManager.CONFIG.enableWarning = !ConfigManager.CONFIG.enableWarning;
        ConfigManager.save();
    }
}
