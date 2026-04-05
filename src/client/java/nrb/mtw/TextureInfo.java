package nrb.mtw;

import net.minecraft.client.texture.NativeImage;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class TextureInfo {
    public static @Nullable NativeImage loadImage() {
        if (false) { // Если в директории юзера есть соответствующий файл, то грузим его оттуда, иначе грузим из ресурсов (еще нет реализации
            File file = new File("/assets/missingtotemwarning/textures/img.png");

            if (!file.exists() || !file.isFile()) {
                System.out.println("[ERROR] Texture not found.");
                return null;
            }

            try (InputStream stream = Files.newInputStream(file.toPath())) {
                return NativeImage.read(stream);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            try (InputStream stream = TextureInfo.class
                    .getResourceAsStream("/assets/missingtotemwarning/textures/img.png")) {

                if (stream == null) {
                    System.out.println("[ERROR] Texture not found in resources.");
                    return null;
                }

                return NativeImage.read(stream);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static int[] getSize() {
        NativeImage image = loadImage();
        if (image == null) {
            throw new IllegalStateException("Image not found");
        }

        try (image) {
            return new int[]{image.getWidth(), image.getHeight()};
        }
    }
}
