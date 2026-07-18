package nrb.mtw;

import net.minecraft.client.texture.NativeImage;
import net.minecraft.util.Identifier;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class TextureManager {
    private static final File userPath = new File("./mtw/textures/img.png");
    private static Identifier identifier;
    private static int[] size;

    // FIXME: Возможно стоит поместить логику loadImage в конструктор для большей безопасности
    public static void loadImage() {
        dirManage();

        // Проверка наличия файла в директории юзера
        if (userPath.isFile()) identifier = Identifier.of("./mtw/textures/img.png");
        else identifier = Identifier.of(MissingTotemWarning.MOD_ID, "textures/img.png");

        findSize(identifier.getPath());
    }

    private static void dirManage() {
        if (userPath.getParentFile().exists()) return;
        try {
            userPath.getParentFile().mkdirs();
        } catch (Exception e) {
            MissingTotemWarning.LOGGER.error("[ERROR] Failed to create directories for user texture path.");
        }
    }

//    private static NativeImage userLoad() {
//
//        try (InputStream stream = Files.newInputStream(userPath.toPath())) {
//            return NativeImage.read(stream);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    private static NativeImage defaultLoad() {
//        try (InputStream stream = MissingTotemWarning.class.getResourceAsStream("/assets/mtw/textures/img.png")) {
//            if (stream == null) {
//                throw new RuntimeException("Default image not found in resources.");
//            }
//            return NativeImage.read(stream);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

    private static void findSize(String path) {
        NativeImage image;
        try (InputStream stream =  Files.newInputStream(new File(path).toPath())) {
            image = NativeImage.read(stream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (image) {
            int width = image.getWidth();
            int height = image.getHeight();
        }
    }

    public static Identifier getIdentifier() {
        return identifier;
    }

    public static int[] getSize() {
        return size;
    }
}