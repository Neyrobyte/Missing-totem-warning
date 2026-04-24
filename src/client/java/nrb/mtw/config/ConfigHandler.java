package nrb.mtw.config;


public class ConfigHandler {
    public static void toggleWarning() {
        ConfigManager.CONFIG.enableWarning = !ConfigManager.CONFIG.enableWarning;
        ConfigManager.save();
    }

    public static boolean setWarning(boolean state) {
        if (ConfigManager.CONFIG.enableWarning != state) {
            ConfigManager.CONFIG.enableWarning = state;
            ConfigManager.save();
            return true;
        }
        return false;
    }
}
