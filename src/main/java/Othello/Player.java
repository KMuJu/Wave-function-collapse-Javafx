package Othello;

public class Player implements PlayerInterface{

    int score;
    int[][] corners = new int[][]{{0,0}, {0,7}, {7,7}, {7,0}};
    Tile[][] grid;
    Moves moves;
    int turn;
    OthelloGame game;
    boolean spill = true;

    Player(Tile[][] grid, OthelloGame game, int turn){
        this.grid = grid;
        moves = new Moves(game.getGrid());
        this.game = game;
        this.turn = turn;
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

    @Override
    public void click(int x, int y) {
        game.change(x, y, turn);
    }

    @Override
    public void spill() {
        
    }
    
}
