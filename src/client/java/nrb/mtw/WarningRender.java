package nrb.mtw;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import nrb.mtw.config.ConfigManager;
import nrb.mtw.config.ModConfig;

import static nrb.mtw.config.ConfigHandler.isWarningEnabled;

/**
 * WarningRender class for rendering warning-image in player screen
 *
 * @see TextureManager
 * @see ModConfig
 */

public class WarningRender {
    private static final Identifier TEXTURE = TextureManager.getId();
    private static int windowWidth;
    private static int windowHeight;
    private static int posX;
    private static int posY;
    private static int frameWidth = 48;
    private static int frameHeight = 48;
    private static final int[] textureSize = TextureManager.getSize();
    private static int textureWidth = textureSize[0];
    private static int textureHeight = textureSize[1];
    private static final MinecraftClient client = MinecraftClient.getInstance();

    private static void calculateSizeFrameOnWindow() {
        // Calculate frame size based on window dimensions
        // requires some improvement!
        // probably won't be used
        var window = client.getWindow();
        windowWidth = window.getScaledWidth();
        windowHeight = window.getScaledHeight();
        frameWidth = windowWidth / 5;
        frameHeight = windowHeight / 5;
    }

    public static void calculateSizeOnWindow(float zoomLevel) {
        // Scaling by the bottom side while maintaining proportions
        // compression factors separately for width and height
        float widthRatio = (float) frameWidth / textureSize[0];
        float heightRatio = (float) frameHeight / textureSize[1];

        // Min factor
        float scaleFactor = Math.min(widthRatio, heightRatio);

        // Round product original dimensions and factor
        textureWidth = Math.round(textureSize[0] * scaleFactor * zoomLevel);
        textureHeight = Math.round(textureSize[1] * scaleFactor * zoomLevel);
        System.out.println(zoomLevel);
    }

    public static void updatePosition() {
        windowWidth = client.getWindow().getScaledWidth();
        windowHeight = client.getWindow().getScaledHeight();
        posX = (windowWidth - textureWidth) / 2; // Centered, horizontally
        posY = windowHeight / 22; // Just below the top edge
    }

    public static void render(DrawContext context) {
        // Check to config state
        if (!isWarningEnabled()) return;

        // Check to gamemod and config state
        if (client.player != null) {
            if (ModConfig.getInstance().onlySurvival) {
                if (client.player.isCreative() || client.player.isSpectator()) {
                    return;
                }
            }

            // Check to is player dead of F1 toggled
            if (client.player.isDead() || client.options.hudHidden) return;

            // Get hand items
            ItemStack mainHand = client.player.getMainHandStack();
            ItemStack offHand = client.player.getOffHandStack();
            boolean hasTotem = mainHand.isOf(Items.TOTEM_OF_UNDYING) || offHand.isOf(Items.TOTEM_OF_UNDYING);

            // HACK: Is it possible to use window resize event!?
            if (!hasTotem) {
                if (windowWidth != client.getWindow().getScaledWidth() || windowHeight != client.getWindow().getScaledHeight()) {
                    updatePosition();
                }

                context.drawTexture(
                        TEXTURE,
                        posX,
                        posY,
                        0, 0,
                        textureWidth,
                        textureHeight,
                        textureWidth,
                        textureHeight
                );
            }
        }
    }
}
