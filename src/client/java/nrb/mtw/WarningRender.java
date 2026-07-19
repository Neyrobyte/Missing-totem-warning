package nrb.mtw;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import nrb.mtw.config.ConfigManager;
import nrb.mtw.config.ModConfig;

import static nrb.mtw.config.ConfigHandler.isWarningEnabled;

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
        // Вычисление размеров фрейма на основе размеров окна
        // требует доработки!
        // Вероятно, не будет использован.
        var window = client.getWindow();
        windowWidth = window.getScaledWidth();
        windowHeight = window.getScaledHeight();
        frameWidth = windowWidth / 5;
        frameHeight = windowHeight / 5;
    }

    public static void calculateSizeOnWindow(float zoomLevel) {
        // Масштабирование по меньшей стороне с сохранением пропорций
        // коэффициенты сжатия отдельно для ширины и высоты
        float widthRatio = (float) frameWidth / textureSize[0];
        float heightRatio = (float) frameHeight / textureSize[1];

        // меньший коэффициент, чтобы текстура вписалась в 48x48
        float scaleFactor = Math.min(widthRatio, heightRatio);

        // округленное произведение исходных размеров и коэффициента
        textureWidth = Math.round(textureSize[0] * scaleFactor * zoomLevel);
        textureHeight = Math.round(textureSize[1] * scaleFactor * zoomLevel);
        System.out.println(zoomLevel);
    }

    public static void updatePosition() {
        windowWidth = client.getWindow().getScaledWidth();
        windowHeight = client.getWindow().getScaledHeight();
        posX = (windowWidth - textureWidth) / 2; // По центру по горизонтали
        posY = windowHeight / 22; // Немного ниже верхнего края
    }

    public static void render(DrawContext context) {
        // Проверка на состояние конфига
        if (!isWarningEnabled()) return;

        if (client.player != null) {
            if (ModConfig.getInstance().onlySurvival) {
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