package Othello;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;


public class OthelloController {
    Tile[][] grid = new Tile[8][8];
    int TILESIZE = 600/8;
    int tur, n;

    @FXML
    Pane bane;

    @FXML
    HBox topp;

    OthelloGame game;
    int[] score = new int[2];

    UiGenerator uiGenerator;
    String antall;

    @FXML
    void initialize(){
        uiGenerator = new UiGenerator(bane);
        startScreen();
        topp.setAlignment(Pos.CENTER);
    }

    public void startScreen(){
        topp.getChildren().clear();
        bane.getChildren().clear();
        bane.getChildren().addAll(uiGenerator.createStartScene(e->startGame(uiGenerator.getComboBox().getValue())));
    }

    public void startGame(String antall){
        System.out.println(antall);
        int antallAi = 0;
        if (antall.equals("bot v bot")){
            antallAi = 2;
        }
        else if (antall.equals("1v1")){
            antallAi = 0;
        }
        else {antallAi = 1;}
        this.antall = antall;
        game = new OthelloGame(this, antallAi);
        topp.getChildren().clear();

        Button restart = new Button("Restart");
        Button tilbake = new Button("Othello");
        tilbake.setOnMouseClicked(e->startScreen());
        restart.setOnMouseClicked(e->startGame(antall));
        
        topp.getChildren().addAll(tilbake, restart);
        bane.getChildren().clear();
        bane.getChildren().addAll(game.makeBoard());
        n++;
    }

    public void newScore(int vinner){
        score[vinner]++;
        if (n%10==0){System.out.println(n);}
        if (n<1){
            startGame(antall);
        }
        else{
            System.out.println("Seiere:");
            System.out.println("Hvite: " + score[0]);
            System.out.println("Sorte: " + score[1]);
            System.out.println("____________________________");
        }
    }
    
}
