package MittProsjekt;

import java.util.HashMap;

import javafx.event.Event;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Tile extends StackPane{
    private int x, y, antallBomberRundt, TILESIZE;
    private Boolean bombe;
    private Boolean open = false;
    private Boolean marked = false;
    private Text tekst;
    private HashMap<Integer, Color> farger = new HashMap<>();
    private String openColor = "#f0e8b6";
    private String notOpenColor = "#98d94e";

    public Tile(int x, int y, Boolean bombe, int TILESIZE) {
        this.x = x;
        this.y = y;
        this.bombe = bombe;
        this.TILESIZE = TILESIZE;
        setPrefSize(TILESIZE, TILESIZE);
        setStyle("-fx-background-color:" + notOpenColor);
        setBorder(new Border(new BorderStroke(Color.BLACK, 
            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        tekst = new Text();
        tekst.setVisible(false);
        tekst.setFont(Font.font("Verdana", FontWeight.BOLD, 22));
        
        farger.put(0, Color.BLACK);
        farger.put(1, Color.BLUE);
        farger.put(2, Color.DARKGREEN);
        farger.put(3, Color.RED);
        farger.put(4, Color.PURPLE);
        farger.put(5, Color.MAROON);
        farger.put(6, Color.TURQUOISE);
        farger.put(7, Color.BLACK);
        farger.put(8, Color.GRAY);

        getChildren().addAll(tekst);
    }

    public void click(){
        Event.fireEvent(this, new MouseEvent(MouseEvent.MOUSE_CLICKED, x*TILESIZE, y*TILESIZE, x*TILESIZE, y*TILESIZE, MouseButton.PRIMARY, 1, 
        true, true, true, true, true, true, false, true, true, true, null));
    }

    public boolean open(){
        tekst.setVisible(true);
        open = true;
        setStyle(!bombe ? "-fx-background-color:"+openColor :  "-fx-background-color:red;");
        return !bombe;
    }

    public void mark(){
        if (open){return;}
        if (!marked){
            marked = true;
            setStyle("-fx-background-color:yellow;");
        }
        else{
            marked = false;
            setStyle("-fx-background-color:"+ notOpenColor);
        }
    }

    public void setBombe(Boolean bombe) {
        this.bombe = bombe;
    }

    public void setAntallBomber(int a){
        antallBomberRundt = a;
        tekst.setText(!bombe ? Integer.toString(a) : "X");
        tekst.setText(a!=0 ? tekst.getText() : "");
        tekst.setFill(farger.get(a));
    }

    public void setTekst(Text tekst) {
        this.tekst = tekst;
    }

    public int getAntall(){
        return antallBomberRundt;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Boolean isBombe() {
        return bombe;
    }

    public Boolean isOpen() {
        return open;
    }

    public Boolean isMarked() {
        return marked;
    }    
}
