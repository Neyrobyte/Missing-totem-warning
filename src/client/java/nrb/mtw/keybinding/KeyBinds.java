package nrb.mtw.keybinding;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import nrb.mtw.UIEffects;
import nrb.mtw.config.ConfigHandler;
import nrb.mtw.config.ModConfig;
import org.lwjgl.glfw.GLFW;

import static nrb.mtw.config.ConfigHandler.isWarningEnabled;

/**
 * KeyBinds class for registering mod key binds
 * Has debounce for key bind
 *
 * @see ConfigHandler
 * @see ModConfig
 * @see UIEffects
 */

public class KeyBinds {
    private static boolean wasHeld = false;

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

            boolean isHeld = keyBind.isPressed();

            // debounce: срабатывает только на переходе false -> true
            if (isHeld && !wasHeld) {

                if (client.player == null) return;
                if (ModConfig.getInstance().onlySurvival) {
                    if (client.player.isCreative() || client.player.isSpectator()) return;
                }

                ConfigHandler.toggleWarning();
                UIEffects.soundSwitch(isWarningEnabled());
                UIEffects.messageSwitch(isWarningEnabled());
            }

            wasHeld = isHeld;
        });
    }
}
