package nrb.mtw.mixin.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * ScreenResizeMixin unused class
 */

@Mixin(MinecraftClient.class)
public class ScreenResizeMixin {
    @Inject(method = "setScreen", at = @At("TAIL"))
    private void onSetScreen(Screen screen, CallbackInfo ci) {
//        ClientLifecycleEvents.CLIENT_STARTED.register(client -> {
//            HudRenderCallback.EVENT.register((matrixStack, tickDelta) -> {
//                if (client != null) {
//                    TotemWarningRender.updatePosition();
////                    TotemWarningRender.calculateSizeOnWindow(ModConfig.getInstance().zoomLevel);
////                    Window.toggleFullscreen();
//                    System.out.println("resize!!");
//                }
//            });
//        });
    }
}
