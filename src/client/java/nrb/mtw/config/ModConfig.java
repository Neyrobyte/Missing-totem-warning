package nrb.mtw.config;

import java.util.ArrayList;
import java.util.List;

public class ModConfig {
    public boolean enableWarning = true;

    // Список предметов, которые должны быть в инвентаре, что бы мод работал (например, кристалл энда)
    public List<String> requiredItems = new ArrayList<>();

    // Список триггеров в чате, при которых мод будет автоматически включаться\выключаться (например, "You duel"\"You win the duel")
    public List<String> chatTriggers = new ArrayList<>();

    // Переключать мод клавишей
    public boolean useKeybind = true;
}
