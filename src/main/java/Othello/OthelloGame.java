package Othello;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class OthelloGame {
    Tile[][] grid = new Tile[8][8];
    int TILESIZE = 600/8;
    OthelloController controller;

    int turn = 1;
    Moves moves;
    int hviteBrikker = 2;
    int sorteBrikker = 2;
    // List<PlayerInterface> spillere = new ArrayList<>();
    PlayerAi playerAi1 = new PlayerAi(grid, this, 1, 0.8, 0.50);
    PlayerAi playerAi2 = new PlayerAi(grid, this, 2, 0.8, 0.6);
    Player player = new Player(grid, this, 1);

    boolean spill = true;

    AnimationTimer aiTimer1;
    AnimationTimer aiTimer2;
    long starttid1;
    long elapsed1;
    long starttid2;
    long elapsed2;

    double tidsIntervall = 1* Math.pow(10, 9);
    ArrayList<AnimationTimer> timers = new ArrayList<>(2);
    ArrayList<PlayerInterface> players = new ArrayList<>(2);
    ArrayList<Long> elapsedList = new ArrayList<>(2);
    ArrayList<Long> startTimeList = new ArrayList<>(2);
    Double vanskelighetsgrad = 0.8;
    double prefHjorne = 0.6;
    
    public OthelloGame(OthelloController c, int antallAi){
        double tidsIntervall = 0.1* Math.pow(10, 9);
        players = new ArrayList<>(2);
        timers = new ArrayList<>(antallAi);
        elapsedList = new ArrayList<>(antallAi);
        startTimeList = new ArrayList<>(antallAi);
        controller = c;
        moves = new Moves(grid);
        aiTimer1 = new AnimationTimer() {

            @Override
            public void handle(long now) {
                if (!spill){return;}
                // if (!spiller1){return;}
                elapsed1 = System.nanoTime() - starttid1;
                if (elapsed1>1*tidsIntervall){
                    playerAi1.spill();
                    // spiller1 = false;
                    stop();
                    aiTimer2.start();
                    starttid2 = System.nanoTime();
                }
            }
            
        };
        aiTimer2 = new AnimationTimer() {

            @Override
            public void handle(long now) {
                if (!spill){return;}
                // if (spiller1){return;}
                elapsed2 = System.nanoTime() - starttid2;
                if (elapsed2>1*tidsIntervall){
                    playerAi2.spill();
                    // spiller1 = true;
                    stop();
                    aiTimer1.start();
                    starttid1 = System.nanoTime();
                }
            }
            
        };
        // aiTimer1.start();
        // starttid1 = System.nanoTime();

        for (int i = 0; i<2-antallAi; i++){
            players.add(new Player(grid, this, i+1));
            players.get(i).setSpill(i%2==0 ? true : false);
        }

        for (int i = 2-antallAi; i<2; i++){
            players.add(new PlayerAi(grid, this, i+1, vanskelighetsgrad, prefHjorne));
            players.get(i).setSpill(i%2==0 ? true : false);
            
        }

        elapsedList.add((long) 0);
        elapsedList.add((long) 0);
        startTimeList.add(System.nanoTime());
        startTimeList.add(System.nanoTime());


        for (int i = 2-antallAi; i<2; i++){
            int index = i;
            // System.out.println(index);
            timers.add(new AnimationTimer() {
                @Override
                public void handle(long now) {
                    if (!spill){return;}
                    elapsedList.set(index, System.nanoTime() - startTimeList.get(index));
                    if (elapsedList.get(index)>1*tidsIntervall){

                        players.get(index).spill();
                        players.get(index).setSpill(false);
                        players.get((index+1)%2).setSpill(true);

                        stop();
                        // System.out.println(index + ", " + players.toString());
                        // System.out.println(players.get((index+1)%2).getClass());
                        if (players.get((index+1)%2) instanceof Player){
                            showMoves(index);
                            return;
                        }
                        timers.get((index+1)%antallAi).start();
                        startTimeList.set((index+1)%antallAi, System.nanoTime());
                    }
                }
            });
        }
        if (antallAi == 2){
            timers.get(0).start();
        }
        // spillere.add(new PlayerAi(grid, this, 1));
    }

    public void restart(Pane bane){
        bane.getChildren().clear();
        bane.getChildren().addAll(makeBoard());
        aiTimer1.stop();
        aiTimer1.start();
        starttid1 = System.nanoTime();
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
        // showMoves(turn);

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
        seier();
    }


    void click(int x, int y){
        int turn = 1;
        Player player = (Player) players.get(0);
        for (PlayerInterface p : players){
            if (p.getSpill()){
                turn = p.getTurn();
                player = (Player) p;
            }
        }
        System.out.println(turn);
        // if (!spiller1){return;}
        if (!moves.valid(x, y, turn)){
            System.out.println(x + ", " + y);
            System.out.println("ikke lovlig");
            return;
        }

        if (players.get(turn%2) instanceof PlayerAi){
            startTimeList.set((turn+1)%2, System.nanoTime());
            startTimeList.set((turn)%2, System.nanoTime());
            timers.get((turn+1)%2).start();
        }
        else {
            players.get(turn%2).setSpill(true);
            player.setSpill(false);
        }
        player.click(x, y);
        
        // showMoves(turn);
        // spiller1 = false;
        // playerAi.spill();
        
    }

    public void changeTiles(int x, int y, int turn){
        for (Tile t : moves.getChangeTiles(x, y, turn)){
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

    public void showMoves(int turn){
        for (Tile[] rad : grid){
            for (Tile rute : rad){
                rute.setStyle("-fx-background-color: #8bbd51");
            }
        }
        
        for (int[] pos : moves.generateMoves(turn)){
            grid[pos[1]][pos[0]].setStyle("-fx-background-color: red");
        }
    }

    public void seier(){
        if (moves.generateMoves(1).size()==0 && moves.generateMoves(2).size()==0){
            // System.out.println("hvite: " + hviteBrikker);
            // System.out.println("sorte: " + sorteBrikker);
            // System.out.println("____________________________");
            spill = false;
            int vinner = hviteBrikker>sorteBrikker ? 0 : 1;
            controller.newScore(vinner);
        }
    }
    
}
