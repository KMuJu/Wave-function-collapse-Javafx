package Othello;

import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class Tile extends StackPane {
    int TILESIZE;
    int brikke = 0;             // 0 -> tom rute, 1 -> hvit rute, 2 -> sort rute
    int x, y;

    @Override
    public String toString() {
        return "[" + Integer.toString(x) + ", " + Integer.toString(y) + "]";
    }

    Tile(int x, int y, int TILESIZE){
        this.x = x;
        this.y = y;
        this.TILESIZE = TILESIZE;

        setPrefSize(TILESIZE, TILESIZE);
        setStyle("-fx-background-color:#8bbd51");
        setBorder(new Border(new BorderStroke(Color.BLACK, 
            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

    }

    public int[] getPos(){
        return new int[]{x, y};
    }
    public int getBrikke(){
        return brikke;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public Boolean setBrikke(int tur){
        getChildren().clear();
        brikke = tur;

        Color farge = tur==1 ? Color.BLACK : Color.WHITE;
        Circle brikken = new Circle(x*TILESIZE, y*TILESIZE, TILESIZE * 0.35);
        brikken.setFill(farge);
        brikken.setStroke(farge.invert());
        brikken.setStrokeWidth(2);

        Text t = new Text(Integer.toString(brikke));
        t.setStroke(farge.invert());


        getChildren().clear();
        getChildren().addAll(brikken, t);

        return null;
    }
}
