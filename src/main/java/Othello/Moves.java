package Othello;

import java.util.ArrayList;
import java.util.List;

public class Moves {
    
    Tile[][] grid;


    public boolean valid(int x, int y, int turn){
        int x1 = x;
        int y1 = y;
        if (grid[y][x].getBrikke()!=0){
            return false;
        }
        for (int dy = -1; dy<2; dy++){
            for (int dx = -1; dx<2; dx++){
                x = x1;
                y = y1;
                if (outside(x+dx, y+dy) || dx==0 && dy==0 || grid[y+dy][x+dx].getBrikke()==0){continue;}
                if (grid[y+dy][x+dx].getBrikke() == turn){continue;}
                // Tile rute = grid[y+dy][x+dx];
                
                while (grid[y+dy][x+dx].getBrikke() == turn%2+1){
                    x += dx;
                    y += dy;
                    if (outside(x+dx, y+dy)){break;}
                    // rute = grid[y+dy][x+dx];
                }
                if (outside(x+dx, y+dy)){continue;}
                if (grid[y+dy][x+dx].getBrikke() == turn){
                    return true;
                }
            }
        }


        return false;
    }

    public List<Tile> getChangeTiles(int x, int y, int turn){
        int x1 = x;
        int y1 = y;
        // System.out.println(x + ", " + y);
        if (grid[y][x].getBrikke()!=0){
            return null;
        }
        List<Tile> retur = new ArrayList<>();
        for (int dy = -1; dy<2; dy++){
            for (int dx = -1; dx<2; dx++){
                x = x1;
                y = y1;
                if (outside(x+dx, y+dy) || dx==0 && dy==0){
                    continue;
                }
                Tile rute = grid[y+dy][x+dx];
                if (turn == rute.getBrikke()){
                    continue;
                }
                List<Tile> rekke = new ArrayList<>();
                if (turn != rute.getBrikke() && rute.getBrikke() != 0){
                    // System.out.println("hei");
                    while (turn != rute.getBrikke() && rute.getBrikke() != 0){
                        x += dx;
                        y += dy;
                        rekke.add(rute);
                        if (outside(x+dx, y+dy)){break;}
                        rute = grid[y+dy][x+dx];
                    }
                    if (outside(x+dx, y+dy)){continue;}
                    if (turn == rute.getBrikke()){
                        for (Tile t : rekke){
                            retur.add(t);
                        }
                    }
                }
            }
        }
        // System.out.println(retur.toString());
        return retur;
    }

    public boolean outside(int x, int y){
        return x<0||y<0||x>=grid[0].length||y>=grid.length;
    }

    public Moves(Tile[][] grid){
        this.grid = grid;
    }

    public List<int[]> generateMoves(int turn){
        List<int[]> moves = new ArrayList<>();
        for (int x = 0; x<8; x++){
            for (int y = 0; y<8; y++){
                if (grid[y][x].getBrikke()!=0){continue;}
                if (valid(x, y, turn)){
                    moves.add(grid[y][x].getPos());
                }
            }
        }
        return moves;
    }
}
