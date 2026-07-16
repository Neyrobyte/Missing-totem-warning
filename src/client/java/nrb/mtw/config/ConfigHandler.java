package nrb.mtw.config;


public class ConfigHandler {
    public static boolean isWarningEnabled() {
        return ConfigManager.CONFIG.enableWarning;
    }

    public static boolean isSurvivalMode() {
        return ConfigManager.CONFIG.onlySurvival;
    }

    public static int getTotemSlot() {
        return ConfigManager.CONFIG.totemSlot;
    }

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

    public static void setSurvivalMode(boolean state) {
        if (ConfigManager.CONFIG.onlySurvival != state) {
            ConfigManager.CONFIG.onlySurvival = state;
            ConfigManager.save();
        }
    }

    public static void setTotemSlot(int slot) {
        if (slot < 1 || slot > 9) {
            throw new IllegalArgumentException("Slot must be between 1 and 9");
        }
        if (slot == ConfigManager.CONFIG.totemSlot) {
            return;
        }
        ConfigManager.CONFIG.totemSlot = slot;
        ConfigManager.save();
    }
}
