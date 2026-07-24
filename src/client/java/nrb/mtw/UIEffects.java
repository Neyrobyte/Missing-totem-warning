package nrb.mtw;

import net.minecraft.client.MinecraftClient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;

/**
 * UIEffects class provides simple sound and message visual effects
 */

public class UIEffects {
    private static final SoundEvent soundEvent = SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP;
    private static final String text = "Totem warning: ";
    private static final Text enabledLiteral = Text.literal("enabled!").withColor(0x00FF00);
    private static final Text disabledLiteral = Text.literal("disabled!").withColor(0xFF0000);
    private static final MinecraftClient client = MinecraftClient.getInstance();

    public static void soundSwitch(boolean state) {
        float pitch = state ? 0.8f : 1.5f;
        if (client.player != null) {
            client.player.playSound(soundEvent, 1f, pitch);
        }
    }

    public static void messageSwitch(boolean state) {
        if (client.player == null) return;
        if (state) {
            client.player.sendMessage(Text.literal(text).append(enabledLiteral), true);
        } else {
            client.player.sendMessage(Text.literal(text).append(disabledLiteral), true);
        }
    }

    public static void message(String message) {
        if (client.player == null) return;
        client.player.sendMessage(Text.literal(message), true);
    }
}
