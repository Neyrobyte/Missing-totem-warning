package nrb.mtw.mixin.client;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.Window;
import nrb.mtw.TotemWarningRender;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class ScreenResizeMixin {
    @Inject(method = "setScreen", at = @At("TAIL"))
    private void onSetScreen(Screen screen, CallbackInfo ci) {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (screen != null) {
//                TotemWarningRender.updatePosition();
//                Window.toggleFullscreen();
                System.out.println("resize!!");
            }
        });
    }
}
