package MittProsjekt;


import java.util.HashMap;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class MineSweeperController {
    @FXML
    Pane bane;
    @FXML
    VBox bakgrunn;
    @FXML
    HBox topp;

    String vanskelighetsgrad;
    HashMap<String, Integer[]> raderForVanskelighet = new HashMap<>();

    // private final ScoreFileSupport fileSupport = new ScoreFileSupport();
    // TileGenerator tileGenerator = new TileGenerator();
    UIGenerator uiGenerator = new UIGenerator();
    UiController uiController;
    MineSweeperGame game;
    ComboBox<String> dropDown;
    Text tid;
    AnimationTimer timer;
    long elapsed;
    long startTid;

    @SuppressWarnings("unchecked")
    @FXML
    void initialize(){
        topp.setAlignment(Pos.CENTER);
        uiController = new UiController(bane);
        uiController.oppdaterView(uiGenerator.startSkjerm(e->startSpill()));
        // System.out.println( ( (ComboBox<String>) ( (HBox) ( (VBox) bane.getChildren().get(0) ).getChildren().get(1) ).getChildren().get(0) ).getValue());
        
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                long elapsedMillis = System.nanoTime() - startTid;
                elapsed = elapsedMillis;
                tid.setText(Long.toString(elapsedMillis / (int)Math.pow(10, 9)));
            }
        };

        raderForVanskelighet.put("Lett", new Integer[]{10,10, 3});
        raderForVanskelighet.put("Middels", new Integer[]{15,15, 40});
        raderForVanskelighet.put("Vanskelig", new Integer[]{20,20, 75});
        dropDown = (ComboBox<String>) ( (HBox) ( (VBox) bane.getChildren().get(0) ).getChildren().get(1) ).getChildren().get(0);
    }
    
    public void startSpill(){
        //mye casting og get children for å få riktig klasse og så tiden
        vanskelighetsgrad = dropDown.getValue();
        topp.getChildren().clear();
        elapsed = 0;

        int kolonner = raderForVanskelighet.get(vanskelighetsgrad)[0];
        int rader = raderForVanskelighet.get(vanskelighetsgrad)[1];
        int antallBomber = raderForVanskelighet.get(vanskelighetsgrad)[2];
        game = new MineSweeperGame(this, rader, kolonner, antallBomber);
        uiController.oppdaterView(game.getTiles());
        topp.getChildren().addAll(uiGenerator.spillTopp(e->tilbake(), e->startSpill()));

        tid = ((Text) topp.getChildren().get(1));
    }

    public void tilbake(){
        topp.getChildren().clear();
        uiController.oppdaterView(uiGenerator.startSkjerm(e->startSpill()));
    }

    public UiController getUiController(){
        return uiController;
    }

    public UIGenerator getGenerator(){
        return uiGenerator;
    }

    public double getTime(){
        return (elapsed/(int)Math.pow(10,7))/100.0;
    }

    public void seier(){
        bane.getChildren().add(uiGenerator.seierOverlay(e->startSpill(), Double.toString(getTime())));
    }

    public void setStartTid(long t){
        startTid = t;
        timer.start();
    }

    public void stopTimer(){
        timer.stop();
    }


}
