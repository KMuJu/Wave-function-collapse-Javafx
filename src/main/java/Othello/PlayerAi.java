package Othello;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class PlayerAi implements PlayerInterface{
    int score;
    int[][] corners = new int[][]{{0,0}, {0,7}, {7,7}, {7,0}};
    Tile[][] grid;
    Moves moves;
    int turn;
    OthelloGame game;

    PlayerAi(Tile[][] grid, OthelloGame game, int turn){
        this.grid = grid;
        moves = new Moves(game.getGrid());
        this.game = game;
        this.turn = turn;
    }

    public double h(int[] pos1, int[] pos2){
        int deltaX = Math.abs(pos1[0] - pos2[0]);
        int deltaY = Math.abs(pos1[1] - pos2[1]);
        return 1.4*Math.min(deltaX, deltaY) + Math.abs(deltaX - deltaY);
    }

    @Override
    public void click(int x, int y) {
        game.change(x, y, turn);
    }

    @Override
    public void spill() {
        Random r = new Random();
        List<int[]> currmoves = moves.generateMoves(turn);
        System.out.println(turn);
        System.out.println("ai moves: " + currmoves.stream().map(a -> Arrays.toString(a)).collect(Collectors.toList()).toString());
        int[] pos = currmoves.get(r.nextInt(currmoves.size()));
        System.out.println("Den valgte: " + Arrays.toString(pos));
        click(pos[0], pos[1]);
    }
    
}
