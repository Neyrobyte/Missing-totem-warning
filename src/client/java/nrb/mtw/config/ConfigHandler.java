package nrb.mtw.config;

/**
 * ConfigHandler class for managing configuration settings.
 *
 * @see ModConfig
 * @see ConfigManager
 * @see ConfigScreenFactoryImpl
 */

public class ConfigHandler {
    public static boolean isWarningEnabled() {
        return ModConfig.getInstance().enableWarning;
    }

    public static boolean isSurvivalMode() {
        return ModConfig.getInstance().onlySurvival;
    }

    public static int getTotemSlot() {
        return ModConfig.getInstance().secondTotemSlot;
    }

    public static float getZoomLevel() {
        return ModConfig.getInstance().zoomLevel;
    }

    public static void toggleWarning() {
        ModConfig.getInstance().enableWarning = !ModConfig.getInstance().enableWarning;
    }

    public static boolean setWarning(boolean state) {
        if (ModConfig.getInstance().enableWarning != state) {
            ModConfig.getInstance().enableWarning = state;
            return true;
        }
        return false;
    }

    public static void setSurvivalMode(boolean state) {
        if (ModConfig.getInstance().onlySurvival != state) {
            ModConfig.getInstance().onlySurvival = state;
        }
    }

    public static void setTotemSlot(int slot) {
        if (slot < 1 || slot > 9) {
            throw new IllegalArgumentException("Slot must be between 1 and 9");
        }
        if (slot == ModConfig.getInstance().secondTotemSlot) {
            return;
        }
        ModConfig.getInstance().secondTotemSlot = slot;
    }

    public static void setZoomLevel(float zoomLevel) {
        if (zoomLevel < 0.1 || zoomLevel > 10) {
            throw new IllegalArgumentException("Zoom level must be between 0 and 10");
        }
        if (zoomLevel == ModConfig.getInstance().zoomLevel) {
            return;
        }
        ModConfig.getInstance().zoomLevel = zoomLevel;
    }
}
