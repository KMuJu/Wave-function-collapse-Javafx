package Othello;

public class Player implements PlayerInterface{

    int score;
    int[][] corners = new int[][]{{0,0}, {0,7}, {7,7}, {7,0}};
    Tile[][] grid;
    Moves moves;
    int turn;

    Player(Tile[][] grid, int turn){
        this.grid = grid;
        moves = new Moves(grid);
    }

    @Override
    public void click(int x, int y) {
        
    }

    @Override
    public void spill() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'spill'");
    }
    
}
