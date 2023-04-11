package Biome;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class CanvasController {
    @FXML
    Canvas canvas;

    GraphicsContext ctx;

    BiomeController biomeController;

    @FXML
    void initialize(){
        ctx = canvas.getGraphicsContext2D();
        biomeController = new BiomeController(this);
        fill();
        canvas.setOnMouseClicked(e->biomeController.start());
        canvas.setOnKeyPressed(e -> {
            System.out.println(e.getCode() + ", " + KeyCode.SPACE);
            if (e.getCode() == KeyCode.SPACE) {
                biomeController = new BiomeController(this);
                fill();
            }
        });
    }

    public void fill(){
        ctx.setFill(Color.rgb(100, 100, 100));
        ctx.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        
    }

    public void drawRect(int[] pos, int s, Color c){
        ctx.setFill(c);
        ctx.fillRect(pos[0]*s, pos[1]*s, s, s);
    }
}
