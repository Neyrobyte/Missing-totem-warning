package nrb.mtw.config;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import nrb.mtw.WarningRender;

import static nrb.mtw.config.ConfigHandler.*;

public class ConfigScreenFactoryImpl {

    public static Screen create(Screen parent) {
        ConfigBuilder builder = ConfigBuilder.create();
        builder.setParentScreen(parent);
        builder.setTitle(Text.literal("Missing Totem Warning"));

        ConfigCategory category = builder.getOrCreateCategory(Text.literal("General"));

        category.addEntry(
                builder.entryBuilder()
                        .startBooleanToggle(Text.literal("Enable warning"), isWarningEnabled())
                        .setTooltip(Text.literal("Show warning when the player has no totem in the hotbar"))
                        .setDefaultValue(ModConfig.DEFAULT_ENABLE_WARNING)
                        .setSaveConsumer(ConfigHandler::setWarning)
                        .build()
        );

        category.addEntry(
                builder.entryBuilder()
                        .startBooleanToggle(Text.literal("Only survival"), isSurvivalMode())
                        .setTooltip(Text.literal("Show warning only in survival and adventure modes"))
                        .setDefaultValue(ModConfig.DEFAULT_ONLY_SURVIVAL)
                        .setSaveConsumer(ConfigHandler::setSurvivalMode)
                        .build()
        );

        category.addEntry(
                builder.entryBuilder()
                        .startIntSlider(Text.literal("Hotbar totem"), getTotemSlot(), 1, 9)
                        .setTooltip(Text.literal("Set the hotbar slot where the totem is located (1-9)"))
                        .setDefaultValue(ModConfig.DEFAULT_SECOND_TOTEM_SLOT)
                        .setSaveConsumer(ConfigHandler::setTotemSlot)
                        .build()
        );

        category.addEntry(
                builder.entryBuilder()
                        .startFloatField(Text.literal("Zoom multipler"), getZoomLevel())
                        .setMin(0.1F)
                        .setMax(10)
                        .setTooltip(Text.literal("Set the zoom level for the warning texture (0-10)"))
                        .setDefaultValue(ModConfig.DEFAULT_ZOOM_LEVEL)
                        .setSaveConsumer((value) -> {
                            setZoomLevel(value);
                            WarningRender.calculateSizeOnWindow(value);
                            WarningRender.updatePosition();
                        })
                        .build()
        );

        builder.setSavingRunnable(ConfigManager::save);

        return builder.build();
    }
}