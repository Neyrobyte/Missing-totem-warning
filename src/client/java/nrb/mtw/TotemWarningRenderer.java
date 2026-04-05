package nrb.mtw;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import nrb.mtw.config.ModConfig;

public class TotemWarningRenderer {
    private static final Identifier TEXTURE = Identifier.of("missingtotemwarning", "textures/img.png");
    private static int windowWidth;
    private static int windowHeight;
    private static int posX;
    private static int posY;
    private static final int[] textureSize = TextureInfo.getSize();
    private static final int textureWidth = textureSize[0];
    private static final int textureHeight = textureSize[1];
    private static final int zoomLevel = 2;


    public static void render(DrawContext context, ModConfig CONFIG) {
        MinecraftClient client = MinecraftClient.getInstance();

        // Проверка на состояние конфига
        if (!CONFIG.enableWarning) {
            return;
        }

        if (client.player != null) {
            ItemStack mainHand = client.player.getMainHandStack();
            ItemStack offHand = client.player.getOffHandStack();
            boolean hasTotem = mainHand.isOf(Items.TOTEM_OF_UNDYING) || offHand.isOf(Items.TOTEM_OF_UNDYING);

            // Не показывать предупреждение в креативе и спектаторе
            if (client.player.isCreative() || client.player.isSpectator()) {
                return;
            }

            // Не показывать, если игрок уже мертв
            if (client.player.isDead()) {
                return;
            }

            if (!hasTotem) {
                // Обновлять поля только если они изменились
                if (windowWidth != client.getWindow().getScaledWidth() || windowHeight != client.getWindow().getScaledHeight()) {
                    windowWidth = client.getWindow().getScaledWidth();
                    windowHeight = client.getWindow().getScaledHeight();
                    posX = (windowWidth - textureWidth * zoomLevel) / 2; // По центру по горизонтали
                    posY = textureHeight / 2; // Немного ниже верхнего края

                    // client.player.sendMessage(Text.of("if use"), false); // Отладка
                }

                context.drawTexture(
                        TEXTURE,
                        posX,
                        posY,
                        0,
                        0,
                        textureWidth * zoomLevel,
                        textureHeight * zoomLevel,
                        textureWidth * zoomLevel,
                        textureHeight * zoomLevel
                );
            }
        }
    }
}