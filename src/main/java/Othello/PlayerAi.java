package Othello;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
// import java.util.stream.Collectors;

import javafx.util.Pair;

public class PlayerAi implements PlayerInterface{
    int score;
    int[][] corners = new int[][]{{0,0}, {0,7}, {7,7}, {7,0}};
    Tile[][] grid;
    Moves moves;
    int turn;
    OthelloGame game;
    double vanskelighetsgrad;
    double vilTilHjørner;
    Random r = new Random();
    boolean spill = true;

    PlayerAi(Tile[][] grid, OthelloGame game, int turn, double vanskelighetsgrad,  double vilTilHjørner){
        this.grid = grid;
        moves = new Moves(game.getGrid());
        this.game = game;
        this.turn = turn;
        this.vanskelighetsgrad = vanskelighetsgrad;
        this.vilTilHjørner = vilTilHjørner;
    }

    public int getTurn(){
        return turn;
    }

    public boolean getSpill(){
        return spill;
    }

    public void setSpill(boolean b){
        spill = b;
    }

    public double h(int[] pos1, int[] pos2){
        int deltaX = Math.abs(pos1[0] - pos2[0]);
        int deltaY = Math.abs(pos1[1] - pos2[1]);
        double d = 1.4*Math.min(deltaX, deltaY) + Math.abs(deltaX - deltaY);
        if (d==0){
            return -8;
        }
        if (d<2){
            return 20;
        }
        return d;
    }

    @Override
    public void click(int x, int y) {
        game.change(x, y, turn);
    }

    public Double distanceFromCorner(int[] pos){
        List<Double> d = Arrays.asList(corners)
                            .stream()
                            .map(p -> h(p, pos))
                            .map(p -> 8-p)
                            .sorted()
                            .toList();
        
        return Collections.min(d);
    }

    public Double evaluate(int[] pos){
        Double random = r.nextDouble()* (1-vanskelighetsgrad);
        // System.out.println(distanceFromCorner(pos));
        Double antall = (double) moves.getChangeTiles(pos[0], pos[1], turn).size();
        Double value = (1-vilTilHjørner)*antall + vilTilHjørner*distanceFromCorner(pos);
        return value*vanskelighetsgrad + antall*random;
    }

    @Override
    public void spill() {
        
        List<int[]> currmoves = moves.generateMoves(turn);

        if (currmoves.size()==0){
            System.out.println("ugyldige moves");
            game.showMoves(turn%2+1);
            return;
        }

        Pair<int[], Double> moveOgEv = new Pair<>(currmoves.get(0), 0.0);

        for (int[] pos : currmoves){
            if (evaluate(pos)>moveOgEv.getValue()){
                moveOgEv = new Pair<int[],Double>(pos, evaluate(pos));
            }
        }

        int[] pos = moveOgEv.getKey();
        
        // System.out.println("ai moves: " + currmoves.stream().map(a -> Arrays.toString(a)).collect(Collectors.toList()).toString());
        // System.out.println("Den valgte: " + Arrays.toString(pos));
        click(pos[0], pos[1]);
    }
    
}
