package nrb.mtw.config;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class ConfigScreenFactoryImpl {

    public static Screen create(Screen parent) {
        ConfigBuilder builder = ConfigBuilder.create();
        builder.setParentScreen(parent);
        builder.setTitle(Text.literal("Missing Totem Warning"));

        ConfigCategory category = builder.getOrCreateCategory(Text.literal("General"));

        // пример настройки
        category.addEntry(
                builder.entryBuilder()
                        .startBooleanToggle(Text.literal("Enable Warning"), ConfigManager.CONFIG.enableWarning)
                        .setSaveConsumer(newValue -> ConfigManager.CONFIG.enableWarning = newValue)
                        .build()
        );

        builder.setSavingRunnable(ConfigManager::save);

        return builder.build();
    }
}