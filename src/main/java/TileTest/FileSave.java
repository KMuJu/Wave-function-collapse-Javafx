package TileTest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

import javax.imageio.ImageIO;

import java.awt.image.RenderedImage;

import javafx.scene.canvas.Canvas;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.WritableImage;

public class FileSave {
    Canvas canvas;

    FileSave(Canvas canvas){
        this.canvas = canvas;
    }

    private Path getSaveFolderPath() {
        // System.out.println(Path.of("src\\main\\resources\\").toString());
        return Path.of("src\\main\\resources\\TileTest\\Saves");
    }

    private Path getNewFilePath() {
        String name = Integer.toString(getSaveFolderPath().toFile().listFiles().length);
        return getSaveFolderPath().resolve(name + ".png");
    }

    private boolean ensureFilePath() {
        try {
            Files.createDirectories(getSaveFolderPath());
            return true;
        } catch (IOException ioe) {
            return false;
        }
    }

    public void test(){
        System.out.println(Arrays.toString(getSaveFolderPath().toFile().listFiles()));
    }

    public void save() throws IOException{
        ensureFilePath();
        WritableImage writableImage = new WritableImage((int) canvas.getWidth(), (int) canvas.getHeight());
        canvas.snapshot(null, writableImage);
        RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
        ImageIO.write(renderedImage, "png", getNewFilePath().toFile());
    }


}
