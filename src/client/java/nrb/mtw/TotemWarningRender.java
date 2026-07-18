package nrb.mtw;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import nrb.mtw.config.ConfigManager;
import nrb.mtw.config.ModConfig;

import static nrb.mtw.config.ConfigHandler.isWarningEnabled;

public class TotemWarningRender implements WarningRender {
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

    static {
        calculateSizeOnWindow(ModConfig.getInstance().zoomLevel);
    }

    public static void calculateSizeOnWindow(float zoomLevel) {
        // Масштабирование по меньшей стороне с сохранением пропорций
        if (textureWidth > frameWidth || textureHeight > frameHeight) {
            // коэффициенты сжатия отдельно для ширины и высоты
            double widthRatio = (double) frameWidth / textureWidth;
            double heightRatio = (double) frameHeight / textureHeight;

            // меньший коэффициент, чтобы текстура вписалась в 48x48
            double scaleFactor =
                    Math.min(widthRatio, heightRatio) +
                            ConfigManager.CONFIG.zoomLevel *
                                    zoomLevel;

            // округленное произведение исходных размеров и коэффициента
            textureWidth = (int) Math.round(textureSize[0] * scaleFactor);
            textureHeight = (int) Math.round(textureSize[1] * scaleFactor);
        }
    }

    private static void calculateSizeFrameOnWindow() {
        var window = client.getWindow();
        windowWidth = window.getScaledWidth();
        windowHeight = window.getScaledHeight();
        frameWidth = windowWidth / 5;
        frameHeight = windowHeight / 5;
    }

    public static void updatePosition() {
        var window = client.getWindow();
        if (windowWidth != window.getScaledWidth() || windowHeight != window.getScaledHeight()) {
            windowWidth = window.getScaledWidth();
            windowHeight = window.getScaledHeight();
            posX = (windowWidth - textureWidth) / 2; // По центру по горизонтали
            posY = windowHeight / 32; // Немного ниже верхнего края
        }
    }

    public static void render(DrawContext context) {
        // Проверка на состояние конфига
        if (!isWarningEnabled()) return;

        if (client.player != null) {
            if (ConfigManager.CONFIG.onlySurvival) {
                if (client.player.isCreative() || client.player.isSpectator()) {
                    return;
                }
            }

            if (client.player.isDead() || client.options.hudHidden) {
                return;
            }
            ItemStack mainHand = client.player.getMainHandStack();
            ItemStack offHand = client.player.getOffHandStack();
            boolean hasTotem = mainHand.isOf(Items.TOTEM_OF_UNDYING) || offHand.isOf(Items.TOTEM_OF_UNDYING);

            if (!hasTotem) {
                // Обновлять поля только если они изменились
                // updatePosition();

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