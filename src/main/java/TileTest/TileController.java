package TileTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import CircuitFolder.Cicruit;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class TileController {
    int TILESIZE;
    Cell[][] grid;

    List<Tile> tiles = new ArrayList<>();
    List<Cell> ruter;

    CellList cellList;
    CanvasController canvasController;
    Random rand = new Random();
    Cicruit circuit = new Cicruit();
    private int w;
    private int h;
    private int n;
    
    // Long time = System.nanoTime();

    TileController(CanvasController canvasController, int w, int h){
        this.canvasController = canvasController;
        grid = new Cell[h][w];
        this.h = h;
        this.w = w;

        circuit();
        // System.out.println(tiles);
        
        
        for (int i = 0; i<h; i++){
            for (int j = 0; j<w; j++){
                grid[i][j] = new Cell(j, i, tiles);
            }
        }
        
        ruter = new ArrayList<>();
        for (Cell[] r : grid){
            ruter.addAll(Arrays.asList(r));
        }
        
        cellList = new CellList(ruter);
        
    }

    public void circuit(){
        // System.out.println(circuit.getClass().getResource("0.png"));
        tiles.add( new Tile(new Image(circuit.getClass().getResource("0.png").toString()),  new ArrayList<>(Arrays.asList("BBB", "BBB", "BBB", "BBB")),      10, 0));
        tiles.add( new Tile(new Image(circuit.getClass().getResource("1.png").toString()),  new ArrayList<>(Arrays.asList("GGG", "GGG", "GGG", "GGG")),      5, 0));
        tiles.add( new Tile(new Image(circuit.getClass().getResource("2.png").toString()),  new ArrayList<>(Arrays.asList("GGG", "GLG", "GGG", "GGG")),      1, 4));
        tiles.add( new Tile(new Image(circuit.getClass().getResource("3.png").toString()),  new ArrayList<>(Arrays.asList("GGG", "GIG", "GGG", "GIG")),      1, 4));
        tiles.add( new Tile(new Image(circuit.getClass().getResource("4.png").toString()),  new ArrayList<>(Arrays.asList("BGG", "GLG", "GGB", "BBB")),      6, 4));
        tiles.add( new Tile(new Image(circuit.getClass().getResource("5.png").toString()),  new ArrayList<>(Arrays.asList("BGG", "GGG", "GGG", "GGB")),      1, 4));
        tiles.add( new Tile(new Image(circuit.getClass().getResource("6.png").toString()),  new ArrayList<>(Arrays.asList("GGG", "GLG", "GGG", "GLG")),      1, 4));
        tiles.add( new Tile(new Image(circuit.getClass().getResource("7.png").toString()),  new ArrayList<>(Arrays.asList("GIG", "GLG", "GIG", "GLG")),      1, 4));
        tiles.add( new Tile(new Image(circuit.getClass().getResource("8.png").toString()),  new ArrayList<>(Arrays.asList("GIG", "GGG", "GLG", "GGG")),      1, 4));
        tiles.add( new Tile(new Image(circuit.getClass().getResource("9.png").toString()),  new ArrayList<>(Arrays.asList("GLG", "GLG", "GGG", "GLG")),      1, 4));
        tiles.add( new Tile(new Image(circuit.getClass().getResource("10.png").toString()), new ArrayList<>(Arrays.asList("GLG", "GLG", "GLG", "GLG")),      3, 4));
        tiles.add( new Tile(new Image(circuit.getClass().getResource("11.png").toString()), new ArrayList<>(Arrays.asList("GLG", "GLG", "GGG", "GGG")),      3, 4));
        tiles.add( new Tile(new Image(circuit.getClass().getResource("12.png").toString()), new ArrayList<>(Arrays.asList("GGG", "GLG", "GGG", "GLG")),      1, 4));
    
        int len = tiles.size();
        for (int index = 0; index<len; index++){
            Tile t = tiles.get(index);
            for (int i = 1; i<t.getRotations();i++){
                tiles.add(t.rotate(i));
            }
        }
    }

    public void basicTiles(){
        Tile rett = new Tile(new Image(getClass().getResource("rett.png").toString()), new ArrayList<>(Arrays.asList("EEE", "EAE", "EEE", "EAE")),      100000,     4);
        Tile corner = new Tile(new Image(getClass().getResource("corner.png").toString()), new ArrayList<>(Arrays.asList("EAE", "EEE", "EEE", "EAE")),  100000,     4);
        Tile kryss = new Tile(new Image(getClass().getResource("kryss.png").toString()), new ArrayList<>(Arrays.asList("EAE", "EAE", "EAE", "EAE")),    100 ,         4);
        Tile tee = new Tile(new Image(getClass().getResource("tee.png").toString()), new ArrayList<>(Arrays.asList("EEE", "EAE", "EAE", "EAE")),        0 ,         4);
        Tile ende = new Tile(new Image(getClass().getResource("ende.png").toString()), new ArrayList<>(Arrays.asList("EEE", "EEE", "EEE", "EAE")),      0 ,         4);
        
        tiles.add(rett);
        tiles.add(rett.rotate(1));
        tiles.add(corner);
        tiles.add(corner.rotate(1));
        tiles.add(corner.rotate(2));
        tiles.add(corner.rotate(3));
        tiles.add(kryss);
        tiles.add(tee);
        tiles.add(tee.rotate(1));
        tiles.add(tee.rotate(2));
        tiles.add(tee.rotate(3));
        tiles.add(ende);
        tiles.add(ende.rotate(1));
        tiles.add(ende.rotate(2));
        tiles.add(ende.rotate(3));
    }
    
    public boolean restart(){
        for (int i = 0; i<h; i++){
            for (int j = 0; j<w; j++){
                grid[i][j] = new Cell(j, i, tiles);
                // grid[i][j].setT(tiles.get(rand.nextInt(tiles.size())));
            }
        }
        
        ruter = new ArrayList<>();
        for (Cell[] r : grid){
            ruter.addAll(Arrays.asList(r));
        }
        cellList = new CellList(ruter);
        return start();
    }
    
    public boolean start(){
        n = 0;
        while (n < grid.length*grid[0].length){
            if (!run()) return false;
        }
        // for (int i = 0; i<grid.length*grid[0].length; i++){
        //     // if (i%100==0) System.out.println(i);
        //     if (!run()) return false;
        // }
        // draw();
        return true;
        // draw();
    }

    public boolean run(){
        // System.out.println(Arrays.toString(cellList.cells));
        Cell rute;
        try {
            rute = getRandomCell(cellList.getLowestList());
        } catch (Exception e) {
            // TODO: handle exception
            rute = null;
            return true;
        }
        // System.out.println((System.nanoTime()-time)/Math.pow(10, 9));
        endreCelle(rute);
        if (!rute.isCollapsed()) return false;
        
        cellList.update(rute);
        
        int[][] naboer = new int[][]{{0,-1}, {1,0}, {0,1}, {-1,0}};
        
        for (int i = 0; i<naboer.length; i++){
            int[] d = naboer[i];
            int x = (rute.getX()+d[0]+grid[0].length)%grid[0].length;
            int y = (rute.getY()+d[1]+grid.length)%grid.length;
            // int x = (rute.getX()+d[0]);
            // int y = (rute.getY()+d[1]);
            if (outside(x, y)) continue;
            grid[y][x].newNabo(rute, i);
            cellList.update(grid[y][x]);
            endreNaboer(grid[y][x]);
        }
        // cellList.print();
        return true;
    }

    private void endreCelle(Cell cell){
        try {
            cell.setT(getRandomTile(cell.getStates()));
            n++;
        } catch (Exception e) {
            // throw new IllegalArgumentException("AA");
            // System.out.println(rute.getX() + ", " + rute.getY() + ", " + rute.getStates());
            // System.out.println(cell.getNumStates());
            canvasController.drawRect(new int[]{cell.getX(), cell.getY()}, TILESIZE, Color.RED);
        }
    }

    private void endreNaboer(Cell cell){
        if (cell.isCollapsed()) return;
        if (cell.getNumStates()==1){
            endreCelle(cell);
            if (!cell.isCollapsed()) return;
        }
        
        cellList.update(cell);
        
        int[][] naboer = new int[][]{{0,-1}, {1,0}, {0,1}, {-1,0}};
        
        for (int i = 0; i<naboer.length; i++){
            int[] d = naboer[i];
            int x = (cell.getX()+d[0]+grid[0].length)%grid[0].length;
            int y = (cell.getY()+d[1]+grid.length)%grid.length;
            // int x = (cell.getX()+d[0]);
            // int y = (cell.getY()+d[1]);

            if (outside(x, y)) continue;

            Boolean b = grid[y][x].newNaboRec(cell, i);
            // if (grid[y][x].getStates().size()==1){
            //     endreCelle(grid[y][x]);
            //     // System.out.println("AA");
            // }
            cellList.update(grid[y][x]);
            
            if (b) endreNaboer(grid[y][x]);
        }
        
    }

    private boolean outside(int x, int y){
        return x<0||x>=grid[0].length || y<0||y>=grid.length;
    }

    private Cell getRandomCell(List<Cell> l){
        return l.get(rand.nextInt(l.size()));
    }
    private Tile getRandomTile(List<Tile> l){
        int total = 0;
        for (Tile t : l){
            total += t.getWeight();
        }
        double r = Math.random() * total;
        int count = 0;
        for (Tile t : l){
            count += t.getWeight();
            if (count>=r){
                return t;
            }
        }
        return l.get(rand.nextInt(l.size()));
    }

    

    public void draw(){
        for (int i = 0; i<h; i++){
            for (int j = 0; j<w; j++){
                canvasController.drawIMG(grid[i][j]);
            }
        }
    }

    public void print(){
        List<Cell> ruter = new ArrayList<>();
        for (Cell[] r : grid){
            ruter.addAll(Arrays.asList(r));
        }
        System.out.println(ruter.toString());
    }

}
