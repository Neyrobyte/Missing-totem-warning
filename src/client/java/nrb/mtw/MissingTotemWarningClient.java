package nrb.mtw;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import nrb.mtw.config.ConfigManager;
import nrb.mtw.config.ModConfig;

public class MissingTotemWarningClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ClientLifecycleEvents.CLIENT_STARTED.register(client -> {
            ModConfig cfg = ConfigManager.load();
            HudRenderCallback.EVENT.register((matrixStack, tickDelta) -> {
                TotemWarningRenderer.render(matrixStack, cfg);
            });
        });
    }
}
