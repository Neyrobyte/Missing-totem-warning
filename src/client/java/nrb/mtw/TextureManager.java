package nrb.mtw;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.util.Identifier;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * TextureManager class for getting correct image size
 * Auto user dir management. Auto-detection of image location in user directory
 *
 * @see MissingTotemWarningClient
 * @see WarningRender
 */

public class TextureManager {
    private static final File userPath = new File("mtw/textures/img.png");
    private static Identifier id = Identifier.of(MissingTotemWarning.MOD_ID, "textures/img.png");
    private static int[] size;

    public static void loadImage() {
        dirManage();
        if (userPath.isFile()) {
            try {
                NativeImage image = NativeImage.read(new FileInputStream(userPath));
                if (image.getWidth() <= 0 || image.getHeight() <= 0) {
                    throw new IllegalStateException("Invalid image size");
                }
                id = MinecraftClient.getInstance()
                        .getTextureManager()
                        .registerDynamicTexture("mtw_user_texture",
                                new NativeImageBackedTexture(image));
                size = readSizeFromFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else size = readSizeFromResource();
    }

    private static void dirManage() {
        if (userPath.getParentFile().exists()) return;

        try {
            userPath.getParentFile().mkdirs();
        } catch (Exception e) {
            MissingTotemWarning.LOGGER.error("[ERROR] Failed to create directories for user texture path.");
        }
    }

    private static int[] readSizeFromResource() {
        try {
            var resource = MinecraftClient.getInstance()
                    .getResourceManager()
                    .getResource(TextureManager.id);

            try (InputStream stream = resource.get().getInputStream();
                 NativeImage img = NativeImage.read(stream)) {

                return new int[]{img.getWidth(), img.getHeight()};
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static int[] readSizeFromFile() {
        try (InputStream stream = new FileInputStream(TextureManager.userPath)) {
            NativeImage img = NativeImage.read(stream);
            return new int[]{img.getWidth(), img.getHeight()};
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Identifier getId() {
        return id;
    }

    public static int[] getSize() {
        return size;
    }
}
