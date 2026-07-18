package nrb.mtw;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import nrb.mtw.commands.ModCommands;
import nrb.mtw.keybinding.KeyBinds;
import nrb.mtw.config.ConfigManager;

public class MissingTotemWarningClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ConfigManager.load();
        ModCommands.register();
        KeyBinds.register();

        ClientLifecycleEvents.CLIENT_STARTED.register(client -> {
            TextureManager.loadImage();
            HudRenderCallback.EVENT.register((matrixStack, tickDelta) -> {
                WarningRender.render(matrixStack);
//                TotemWarningRenderSecondSlot.render(matrixStack);
            });
        });
    }
}
