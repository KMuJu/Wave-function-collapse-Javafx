package Othello;

import java.util.ArrayList;
import java.util.List;
import javafx.animation.AnimationTimer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class OthelloGame {
    Tile[][] grid = new Tile[8][8];
    int TILESIZE = 600/8;

    int turn = 1;
    Moves moves;
    int hviteBrikker = 2;
    int sorteBrikker = 2;
    // List<PlayerInterface> spillere = new ArrayList<>();
    PlayerAi playerAi = new PlayerAi(grid, this, 2);

    boolean spiller = true;

    AnimationTimer aiTimer;
    long starttid;
    long elapsed;

    public OthelloGame(){
        moves = new Moves(grid);
        aiTimer = new AnimationTimer() {

            @Override
            public void handle(long now) {
                elapsed = System.nanoTime() - starttid;
                if (elapsed>Math.pow(10, 9)){
                    playerAi.spill();
                    spiller = true;
                    stop();
                }
            }
            
        };
        // spillere.add(new PlayerAi(grid, this, 1));
    }

    public ObservableList<Node> makeBoard(){
        List<Node> ruter = new ArrayList<>();
        for (int i = 0; i<8; i++){
            for (int j = 0; j<8; j++){
                Tile rute = new Tile(i, j, TILESIZE);
                rute.relocate(i*TILESIZE, j*TILESIZE);
                rute.setOnMouseClicked(e->clickEvent(e));
                grid[j][i] = rute;
                ruter.add(rute);
            }
        }

        grid[3][3].setBrikke(1);
        grid[3][4].setBrikke(2);
        grid[4][4].setBrikke(1);
        grid[4][3].setBrikke(2);
        showMoves();

        return FXCollections.observableList(ruter);
    }



    public void clickEvent(MouseEvent e){
        try {
            Tile target = (Tile) e.getTarget();
            if (e.getButton().equals(MouseButton.PRIMARY)){
                click(target.getX(), target.getY());
            }
            else {
                check(target.getX(), target.getY());
                // System.out.println(target.toString());
            }
        }
        catch (Exception exception){
            exception.printStackTrace();

        }
    }

    public void check(int x, int y){
        System.out.println(moves.valid(x, y, turn));
    }
    public Tile[][] getGrid(){
        return grid;
    }

    public void change(int x, int y, int turn){
        changeTiles(x, y, turn);
        grid[y][x].setBrikke(turn);
        
        if (turn == 1){sorteBrikker++;}
        if (turn == 2){hviteBrikker++;}
        showMoves();
    }
    public void spillAI(){
        playerAi.spill();
    }

    void click(int x, int y){
        if (!spiller){return;}
        if (!moves.valid(x, y, turn)){
            System.out.println(x + ", " + y);
            System.out.println("ikke lovlig");
            return;
        }
        change(x, y, turn);
        // turn = turn%2+1;
        starttid = System.nanoTime();
        aiTimer.start();
        spiller = false;
        // playerAi.spill();
        
    }

    public void changeTiles(int x, int y, int turn){
        System.out.println(moves.getChangeTiles(x, y, turn).toString());
        for (Tile t : moves.getChangeTiles(x, y, turn)){
            // t.setStyle("-fx-background-color: blue");
            t.setBrikke(turn);
            if (turn == 1){
                sorteBrikker++;
                hviteBrikker--;
            }
            if (turn == 2){
                
                hviteBrikker++;
                sorteBrikker--;
            }
        }
    }

    public void showMoves(){
        for (Tile[] rad : grid){
            for (Tile rute : rad){
                rute.setStyle("-fx-background-color: #8bbd51");
            }
        }
        if (moves.generateMoves(turn).size()==0){
            System.out.println("hvite: " + hviteBrikker);
            System.out.println("sorte: " + sorteBrikker);
        }
        for (int[] pos : moves.generateMoves(turn)){
            grid[pos[1]][pos[0]].setStyle("-fx-background-color: red");
        }
    }
    
}
