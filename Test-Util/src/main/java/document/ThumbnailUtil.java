package document;

import lombok.SneakyThrows;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @Author waiter
 * @Date 2020/11/29 0029 19:37
 * @Version 1.0
 * @Description https://github.com/coobird/thumbnailator/wiki/Examples
 */
public class ThumbnailUtil {

    @SneakyThrows
    public static void scale(File input, File output, int width, int height) {
        Thumbnails.of(input).size(width, height).toFile(output);
    }

    @SneakyThrows
    public static void scale(String input, String output, int width, int height) {
        Thumbnails.of(input).size(width, height).toFile(output);
    }

    @SneakyThrows
    public static void scale(InputStream input, OutputStream output, int width, int height) {
        Thumbnails.of(input).size(width, height).toOutputStream(output);
    }

    @SneakyThrows
    public static void rotateAddWatermark(String input, String output, String watermark, int width, int height, int rotate) {
        Thumbnails.of(input)
                .size(width, height)
                .rotate(rotate)
                .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(watermark)), 0.5f)
                .outputQuality(0.8)
                .toFile(output);
    }

    @SneakyThrows
    public static void scaleWithFixedSize(String input, int width, int height) {
        BufferedImage originalImage = ImageIO.read(new File(input));

        BufferedImage thumbnail = Thumbnails.of(originalImage)
                .size(width, height)
                .asBufferedImage();
    }


    @SneakyThrows
    public static BufferedImage scaleWithGivenFactor(String input, double factor) {
        BufferedImage originalImage = ImageIO.read(new File(input));
        return Thumbnails.of(originalImage)
                .scale(factor)
                .asBufferedImage();
    }

    @SneakyThrows
    public static void main(String[] args) {
        Thumbnails.of("C:/Users/Administrator/Desktop/u=2868660659,1310641645&fm=26&gp=0.jpg")
                .scale(0.5)
                .toFile("C:/Users/Administrator/Desktop/u=2868660659,1310641645&fm=26&gp=0.png");

    }

}
