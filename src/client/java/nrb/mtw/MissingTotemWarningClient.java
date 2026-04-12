package nrb.mtw;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import nrb.mtw.commands.ModCommands;
import org.lwjgl.glfw.GLFW;
import nrb.mtw.config.ConfigManager;

public class MissingTotemWarningClient implements ClientModInitializer {
    private static final KeyBinding keyBind = KeyBindingHelper.registerKeyBinding(
            new KeyBinding(
                    "key.mtw.toggle_warning",
                    InputUtil.Type.KEYSYM,
                    GLFW.GLFW_KEY_RIGHT_ALT,
                    KeyBinding.UI_CATEGORY
            )
    );

    @Override
    public void onInitializeClient() {
        ModCommands.register();
        ConfigManager.load();
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (keyBind.wasPressed()) {
                ConfigManager.toggleWarning();
                UIEffects.soundSwitch(ConfigManager.CONFIG.enableWarning);
                UIEffects.messageSwitch(ConfigManager.CONFIG.enableWarning);
            }
        });

        ClientLifecycleEvents.CLIENT_STARTED.register(client -> {
            HudRenderCallback.EVENT.register((matrixStack, tickDelta) -> {
                TotemWarningRenderer.render(matrixStack);
            });
        });
    }
}
