package nrb.mtw.config;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import nrb.mtw.TotemWarningRender;

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
                        .setSaveConsumer(ConfigHandler::setWarning)
                        .build()
        );

        category.addEntry(
                builder.entryBuilder()
                        .startBooleanToggle(Text.literal("Only survival"), isSurvivalMode())
                        .setTooltip(Text.literal("Show warning only in survival and adventure modes"))
                        .setSaveConsumer(ConfigHandler::setSurvivalMode)
                        .build()
        );

        category.addEntry(
                builder.entryBuilder()
                        .startIntSlider(Text.literal("Hotbar totem"), isTotemSlot(), 1, 9)
                        .setTooltip(Text.literal("Set the hotbar slot where the totem is located (1-9)"))
                        .setSaveConsumer(ConfigHandler::setTotemSlot)
                        .build()
        );

        category.addEntry(
                builder.entryBuilder()
                        .startFloatField(Text.literal("Zoom multipler"), isZoomLevel())
                        .setMin(0)
                        .setMax(10)
                        .setTooltip(Text.literal("Set the zoom level for the warning texture (0-10)"))
                        .setSaveConsumer((value) -> {
                            setZoomLevel(value);
                            TotemWarningRender.calculateSizeOnWindow(value);
                            TotemWarningRender.updatePosition();
                        })
                        .build()
        );

        builder.setSavingRunnable(ConfigManager::save);

        return builder.build();
    }
}