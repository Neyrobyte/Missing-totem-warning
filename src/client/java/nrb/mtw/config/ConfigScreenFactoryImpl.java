package nrb.mtw.config;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import static nrb.mtw.config.ConfigHandler.isSurvivalMode;
import static nrb.mtw.config.ConfigHandler.isWarningEnabled;

public class ConfigScreenFactoryImpl {

    public static Screen create(Screen parent) {
        ConfigBuilder builder = ConfigBuilder.create();
        builder.setParentScreen(parent);
        builder.setTitle(Text.literal("Missing Totem Warning"));

        ConfigCategory category = builder.getOrCreateCategory(Text.literal("General"));

        // пример настройки
        category.addEntry(
                builder.entryBuilder()
                        .startBooleanToggle(Text.literal("Enable warning"), isWarningEnabled())
                        .setSaveConsumer(ConfigHandler::setWarning)
                        .build()
        );

        category.addEntry(
                builder.entryBuilder()
                        .startBooleanToggle(Text.literal("Only survival"), isSurvivalMode())
                        .setSaveConsumer(ConfigHandler::setSurvivalMode)
                        .build()
        );

        builder.setSavingRunnable(ConfigManager::save);

        return builder.build();
    }
}