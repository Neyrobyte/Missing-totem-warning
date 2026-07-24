package nrb.mtw.automation;

import nrb.mtw.config.ConfigHandler;
import nrb.mtw.config.ModConfig;

/**
 * MessageHandler class for handling in-game messages.
 *
 * @see ConfigHandler
 * @see ModConfig
 */

public class MessageHandler {

    public static void onTitle(String title) {
        String t = title.toLowerCase();

        boolean hasEnable = containsAny(t, ModConfig.getInstance().enableWords);
        boolean hasDisable = containsAny(t, ModConfig.getInstance().disableWords);

        // conflict or nothing found
        if (hasEnable == hasDisable) return;

        // only if state real changed
        ConfigHandler.setWarning(hasEnable);
    }

    // TODO: добавить обработку чата

    private static boolean containsAny(String text, String[] words) {
        for (String word : words) {
            if (text.contains(word.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
}
