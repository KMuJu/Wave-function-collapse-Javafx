package MittProsjekt;

import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class MineSweeperController {
    @FXML
    Pane bane;
    @FXML
    VBox bakgrunn;
    @FXML
    HBox topp;

    private final ScoreFileSupport fileSupport = new ScoreFileSupport();

    @FXML
    void initialize(){
        fileSupport.getScoreMap();
    }
}
