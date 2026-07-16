package nrb.mtw;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import nrb.mtw.config.ConfigManager;

import static nrb.mtw.config.ConfigHandler.isWarningEnabled;

public class TotemWarningRender implements WarningRender {
    private static final Identifier TEXTURE = Identifier.of(MissingTotemWarning.MOD_ID, "textures/img.png");
    private static int windowWidth;
    private static int windowHeight;
    private static int posX;
    private static int posY;
    private static final int zoomLevel = 2;
    private static final int[] textureSize = TextureInfo.getSize();
    private static final int textureWidth = textureSize[0] * zoomLevel;
    private static final int textureHeight = textureSize[1] * zoomLevel;
    private static final MinecraftClient client = MinecraftClient.getInstance();

    @Override
    public int getDrawPriority() {
        return 200;
    }

    public static void render(DrawContext context) {
        // Проверка на состояние конфига
        if (isWarningEnabled()) return;

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
                // TODO: В будущем возможно заменить на событие resize окна
                var window = client.getWindow();
                if (windowWidth != window.getScaledWidth() || windowHeight != window.getScaledHeight()) {
                    windowWidth = window.getScaledWidth();
                    windowHeight = window.getScaledHeight();
                    posX = (windowWidth - textureWidth) / 2; // По центру по горизонтали
                    posY = textureHeight / 2; // Немного ниже верхнего края
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