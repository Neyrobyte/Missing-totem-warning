package nrb.mtw;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

import nrb.mtw.config.ConfigManager;
import nrb.mtw.config.ModConfig;

public class MissingTotemWarningClient implements ClientModInitializer {
    private static ModConfig cfg;
    private static final KeyBinding keyBind = KeyBindingHelper.registerKeyBinding(
            new KeyBinding(
                    "key.mtw.toggle_warning",
                    InputUtil.Type.KEYSYM,
                    GLFW.GLFW_KEY_B,
                    KeyBinding.UI_CATEGORY
            )
    );

    @Override
    public void onInitializeClient() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (keyBind.wasPressed()) {
                if (client.player != null) {
                    // cfg уже инициализирован в ClientLifecycleEvents
                    cfg.enableWarning = !cfg.enableWarning;
                    ConfigManager.save();
                    client.player.sendMessage(Text.of("mode: " + cfg.enableWarning), false);
                }
            }
        });

        ClientLifecycleEvents.CLIENT_STARTED.register(client -> {
            cfg = ConfigManager.load();
            HudRenderCallback.EVENT.register((matrixStack, tickDelta) -> {
                if (cfg != null) {
                    TotemWarningRenderer.render(matrixStack, cfg);
                }
            });
        });
    }
}
