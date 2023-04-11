package TileTest;


import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class CanvasController {
    
    @FXML
    Canvas canvas;

    GraphicsContext ctx;

    TileController tileController;
    int TILESIZE = 20;
    

    @FXML
    void initialize(){
        ctx = canvas.getGraphicsContext2D();
        fill();
        canvas.setOnMouseClicked(e->restart(e));
        tileController = new TileController(this, TILESIZE);
    }
    
    private void restart(MouseEvent e){
        // fill();
        // tileController = new TileController(this, TILESIZE);
        Long time = System.nanoTime();
        if (e.getButton().equals(MouseButton.SECONDARY)){
            boolean b = false;
            int i = 0;
            while (!b){
                System.out.println(i);
                if (i==1000){break;}
                fill();
                b = tileController.restart();
                i++;
            }
            tileController.draw();
            System.out.println(((System.nanoTime()-time)/(int)Math.pow(10, 7))/Math.pow(10, 2));
        }
        else {
            tileController.run();
            // tileController.draw();
        }
    }

    public void fill() {
        ctx.setFill(Color.DARKGREY);
        ctx.fillRect(0, 0, 1000, 600);
    }

    public void drawIMG(Cell c){
        ctx.drawImage(c.getImage(), c.getX()*TILESIZE, c.getY()*TILESIZE, TILESIZE, TILESIZE);
    }

    public void drawRect(int[] pos, int s, Color c){
        ctx.setFill(c);
        ctx.fillRect(pos[0]*s+3, pos[1]*s+3, s-6, s-6);
    }
}
