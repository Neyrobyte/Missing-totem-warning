package nrb.mtw.config;

import java.util.ArrayList;
import java.util.List;

public class ModConfig {
    public boolean enableWarning = true;

    public boolean useKeybind = true;

    // Список предметов, которые должны быть в инвентаре, что бы мод работал (например, кристалл энда)
    public List<String> requiredItems = new ArrayList<>();

    // Список триггеров в чате, при которых мод будет автоматически включаться\выключаться.
    public static String[] EnableWords = {
            "в бой",
            "duel",
            "fight",
            "battle"
    };

    public static String[] DisableWords = {
            "win",
            "victory",
            "loss",
            "defeat"
    };
    public boolean onlySurvival = false;

    public int totemSlot = 8;
}
