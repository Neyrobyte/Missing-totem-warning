package nrb.mtw.automation;

import nrb.mtw.config.ConfigHandler;
import nrb.mtw.config.ModConfig;

public class MessageHandler {

    public static void onTitle(String title) {
        String t = title.toLowerCase();

        boolean hasEnable = containsAny(t, ModConfig.getInstance().enableWords);
        boolean hasDisable = containsAny(t, ModConfig.getInstance().disableWords);

        // конфликт или ничего не найдено
        if (hasEnable == hasDisable) return;

        // только если состояние реально меняется
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