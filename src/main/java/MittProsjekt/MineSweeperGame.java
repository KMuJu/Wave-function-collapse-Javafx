package MittProsjekt;

import java.util.HashMap;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.util.Pair;

import javafx.scene.Node;
public class MineSweeperGame {
    int rader, kolonner;
    int TILESIZE;
    int aapneRuter = 0;
    Tile[][] grid;
    HashMap<String, Integer[]> raderForVanskelighet = new HashMap<>();
    Boolean startet = false;
    Boolean spill = true;
    Long startTid;
    Long sluttTid;
    String vanskelighetsgrad;

    MineSweeperController controller;

    TileGenerator tileGenerator = new TileGenerator();
    List<Pair<Integer, Integer>> bomber;
    ObservableList<Node> tiles;
    int antallBomber;
    

    public MineSweeperGame(MineSweeperController controller, int x, int y, int antallBomber){
        this.controller = controller;
        grid = new Tile[y][x];
        tileGenerator.setGrid(grid);
        tiles = tileGenerator.makeTiles(x, y, e->clickEvent(e));
        this.antallBomber = antallBomber;
    }

    public ObservableList<Node> getTiles(){
        return tiles;
    }

    public void plaserBomber(){
        for (Pair<Integer, Integer> pos : bomber){
            grid[pos.getValue()][pos.getKey()].setBombe(true);
        }
    }

    public void clickEvent(MouseEvent e){
        if (!(e.getTarget() instanceof Tile)){return;}
        try{
            Tile target = (Tile)e.getTarget();
            int x = target.getX();
            int y = target.getY();
            if (e.getButton().equals(MouseButton.PRIMARY)){
                click(x, y);
            }
            else if (e.getButton().equals(MouseButton.SECONDARY)){
                mark(x, y);
            }
        }
        catch(Exception ex){
            System.out.println("Feil: " + ex);
            System.out.println("Target: " + e.getTarget());

        }
    }

    public void showBombs(){
        for (Pair<Integer, Integer> pos : bomber){
            grid[pos.getValue()][pos.getKey()].open();
        }
    }

    public void click(int x, int y){
        
        if (!startet){
            bomber = tileGenerator.getBombePos(x, y, antallBomber);
            startTid = System.nanoTime();
            startet = true;
            plaserBomber();
            tileGenerator.fiksBomber();
            controller.setStartTid(startTid);
            // timer.start();
        }
        if (!spill){return;}
        sjekkSeier();
        if (grid[y][x].isMarked() || grid[y][x].isOpen()){
            return;
        }

        if(grid[y][x].open()){
            aapneRuter++;
        }
        else{
            tap();
            return;
        }
        
        if (grid[y][x].getAntall()==0){
            for (int dy = -1; dy<2; dy++){
                for (int dx = -1; dx<2; dx++){
                    if (dx==0&&dy==0  || outside(x + dx, y + dy)){continue;}
                    click(dx + x, dy + y);
                }
            }
        }
        sjekkSeier();
    }

    public void mark(int x, int y){
        grid[y][x].mark();
    }

    public boolean outside(int x, int y){
        return x<0||y<0||x>=grid[0].length||y>=grid.length;
    }

    public void sjekkSeier(){
        if (bomber.size() + aapneRuter == grid.length*grid[0].length){
            System.out.println("du vant");
            seier();
        }
    }

    public void seier(){
        spill = false;
        controller.seier();
        controller.stopTimer();
    }

    public void tap(){
        spill = false;
        showBombs();
        controller.stopTimer();
    }
}
