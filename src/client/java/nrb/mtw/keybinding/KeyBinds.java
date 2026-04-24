package nrb.mtw.keybinding;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import nrb.mtw.UIEffects;
import nrb.mtw.config.ConfigHandler;
import org.lwjgl.glfw.GLFW;

import static nrb.mtw.config.ConfigHandler.isWarningEnabled;

public class KeyBinds {

    private static final KeyBinding keyBind = KeyBindingHelper.registerKeyBinding(
            new KeyBinding(
                    "key.mtw.toggle_warning",
                    InputUtil.Type.KEYSYM,
                    GLFW.GLFW_KEY_RIGHT_ALT,
                    KeyBinding.UI_CATEGORY
            )
    );

    public static void register() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            // цикл для корректной обработки нажатия
            while (keyBind.wasPressed()) {
                if (client.player == null) continue;
                if (client.player.isCreative() || client.player.isSpectator()) continue;
                ConfigHandler.toggleWarning();
                UIEffects.soundSwitch(isWarningEnabled());
                UIEffects.messageSwitch(isWarningEnabled());
            }
        });
    }
}
