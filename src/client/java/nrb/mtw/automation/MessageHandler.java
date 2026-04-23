package nrb.mtw.automation;

import nrb.mtw.config.ConfigManager;

import static nrb.mtw.config.ModConfig.*;

public class MessageHandler {

    public static void onTitle(String title) {
        String t = title.toLowerCase();

        boolean hasEnable = containsAny(t, EnableWords);
        boolean hasDisable = containsAny(t, DisableWords);

        // конфликт или ничего не найдено
        if (hasEnable == hasDisable) return;

        // только если состояние реально меняется
        if (hasEnable && !ConfigManager.CONFIG.enableWarning) {
            ConfigManager.toggleWarning();
        } else if (hasDisable && ConfigManager.CONFIG.enableWarning) {
            ConfigManager.toggleWarning();
        }
    }

    private static boolean containsAny(String text, String[] words) {
        for (String word : words) {
            if (text.contains(word.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
}