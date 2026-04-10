package nrb.mtw;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import nrb.mtw.config.ConfigManager;
import nrb.mtw.config.ModConfig;

import java.util.Optional;

public class MissingTotemWarningClient implements ClientModInitializer {
    private static KeyBindingHelper keyBindingHelper;

    @Override
    public void onInitializeClient() {
        Optional.ofNullable(keyBindingHelper).ifPresentOrElse(helper -> KeyBindingHelper.registerKeyBinding(ConfigManager.CONFIG.keyBind), () -> {
            KeyBindingHelper.registerKeyBinding(ConfigManager.CONFIG.keyBind);
        });

        ClientLifecycleEvents.CLIENT_STARTED.register(client -> {
            ModConfig cfg = ConfigManager.load(); // Локальная переменная для оптимизации
            HudRenderCallback.EVENT.register((matrixStack, tickDelta) -> {
                TotemWarningRenderer.render(matrixStack, cfg);
            });
        });
    }
}
