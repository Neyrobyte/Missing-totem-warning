package nrb.mtw.config;

import java.util.ArrayList;
import java.util.List;

public class ModConfig {
    private static final ModConfig INSTANCE = new ModConfig();

    private ModConfig() {
    }

    public static ModConfig getInstance() {
        return INSTANCE;
    }

    public boolean enableWarning = true;
    public boolean useKeybind = true;
    // Список предметов, которые должны быть в инвентаре, что бы мод работал (например, кристалл энда)
    public List<String> requiredItems = new ArrayList<>();
    // Список триггеров в чате, при которых мод будет автоматически включаться\выключаться.
    public String[] EnableWords = {
            "в бой",
            "duel",
            "fight",
            "battle"
    };
    public String[] DisableWords = {
            "win",
            "victory",
            "loss",
            "defeat"
    };
    public boolean onlySurvival = false;
    public int totemSlot = 8;
    public float zoomLevel = 1;
}
