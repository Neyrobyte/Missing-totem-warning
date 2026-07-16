package nrb.mtw;

import net.minecraft.client.texture.NativeImage;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class TextureInfo {
    public static @Nullable NativeImage loadImage() {
//        TODO: Допилить реализацию загрузки текстуры из директории юзера, если она там есть
        // Если в директории юзера есть соответствующий файл,
        // то загружать его оттуда (еще не реализовано),
        // иначе грузим из ресурсов
        if (true) { // Из ресурсов
            try (InputStream stream = TextureInfo.class
                    // TODO: В будущем заменить на чтение размеров через InputStream из IHDR chunk'а изображения
                    // Для оптимизации загрузки, чтобы не грузить весь файл.
                    .getResourceAsStream("/assets/missingtotemwarning/textures/img.png")) {

                if (stream == null) {
                    MissingTotemWarning.LOGGER.error("[ERROR] Texture not found in resources.");
                    return null;
                }
                return NativeImage.read(stream);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else { // Из директории юзера
            File file = new File("/assets/missingtotemwarning/textures/img.png");

            if (!file.exists() || !file.isFile()) {
                MissingTotemWarning.LOGGER.error("[ERROR] Texture not found in user directory.");
                return null;
            }

            try (InputStream stream = Files.newInputStream(file.toPath())) {
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
