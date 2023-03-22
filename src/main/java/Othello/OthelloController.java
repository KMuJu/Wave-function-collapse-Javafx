package Othello;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

public class OthelloController {
    Tile[][] grid = new Tile[8][8];
    int TILESIZE = 600/8;
    int n;
    int tur;

    @FXML
    Pane bane;

    OthelloGame game = new OthelloGame();

    @FXML
    void initialize(){
        bane.getChildren().addAll(game.makeBoard());
    }

    
    
}
