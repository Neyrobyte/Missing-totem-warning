package nrb.mtw.mixin.client;

import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.text.Text;
import nrb.mtw.automation.MessageHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class MessageMixin {

    @Inject(method = "setTitle", at = @At("HEAD"))
    private void onTitle(Text title, CallbackInfo ci) {
        MessageHandler.onTitle(title.getString());
    }

    @Inject(method = "setSubtitle", at = @At("HEAD"))
    private void onSubtitle(Text subtitle, CallbackInfo ci) {
        MessageHandler.onTitle(subtitle.getString());
    }

    @Inject(method = "setOverlayMessage", at = @At("HEAD"))
    private void onActionBar(Text message, boolean tinted, CallbackInfo ci) {
        MessageHandler.onTitle(message.getString());
    }

    // TODO: добавить обработку чата
}