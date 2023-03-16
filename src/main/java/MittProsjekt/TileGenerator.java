package MittProsjekt;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.util.Pair;
import javafx.event.EventHandler;

public class TileGenerator {

    Tile[][] grid;
    
    public void setGrid(Tile[][] grid) {
        this.grid = grid;
    }

    public ObservableList<Node> makeTiles(int x, int y, EventHandler<MouseEvent> e){
        int TILESIZE = 600/x;
        List<Node> l = new ArrayList<>();
        for (int i = 0; i<x; i++){
            for (int j = 0; j<y; j++){
                Tile rute = new Tile(i, j, false, TILESIZE);
                rute.relocate(i*TILESIZE, j*TILESIZE);
                rute.setOnMouseClicked(e);
                grid[j][i] = rute;
                l.add(rute);
            }
        }
        
        return FXCollections.observableList(l);
    }

    public List<Pair<Integer, Integer>> getBombePos(int antallBomber){
        Random r = new Random();
        Set<Pair<Integer, Integer>> set = new HashSet<>();
        while (set.size()<antallBomber){
            set.add(new Pair<Integer,Integer>(r.nextInt(grid[0].length), r.nextInt(grid.length)));
        }
        ArrayList<Pair<Integer, Integer>> retur = new ArrayList<>(set);
        return retur;
    }

    public List<Pair<Integer, Integer>> getBombePos(int x, int y, int antallBomber){
        Random r = new Random();
        Set<Pair<Integer, Integer>> set = new HashSet<>();
        while (set.size()<antallBomber){
            int x1 = r.nextInt(grid[0].length);
            int y1 = r.nextInt(grid.length);
            if (Math.abs(x-x1)<=1 && Math.abs(y-y1)<=1){
                continue;
            }
            set.add(new Pair<Integer,Integer>(x1, y1));
        }
        ArrayList<Pair<Integer, Integer>> retur = new ArrayList<>(set);
        return retur;
    }

    public void fiksBomber(){
        for (int y = 0; y<grid.length; y++){
            for (int x = 0; x<grid[0].length; x++){
                int antall = 0;

                for (int dx = -1; dx<2; dx++){
                    for (int dy = -1; dy<2; dy++){
                        if (outside(x+dx, y+dy) || (dx==0&&dy==0)){continue;}
                        if (grid[y+dy][x+dx].isBombe()){
                            antall += 1;
                        }
                    }
                }
                
                grid[y][x].setAntallBomber(!grid[y][x].isBombe() ? antall: 9);

            }
        }
    }

    public boolean outside(int x, int y){
        return x<0||y<0||x>=grid[0].length||y>=grid.length;
    }
}
