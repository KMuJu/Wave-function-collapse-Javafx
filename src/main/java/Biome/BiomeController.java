package Biome;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import javafx.scene.paint.Color;

public class BiomeController {
    CanvasController canvasController;

    Random rand = new Random();

    int TILESIZE = 10;
    Biome[] biomes;
    Tile[][] tiles = new Tile[600/TILESIZE][900/TILESIZE];
    int antallBiomes = 4;

    public BiomeController(CanvasController canvasController) {
        this.canvasController = canvasController;
        generateBiomes();
        generateTiles();
        // tiles[10][10].setBiome(biomes[0]);
        // tiles[10][11].setBiome(biomes[1]);
        // tiles[10][12].setBiome(biomes[2]);
        // tiles[10][13].setBiome(biomes[3]);
        // run();
        
    }

    public void start(){
        for (int i = 0; i<600/TILESIZE*900/TILESIZE; i++){
            // if (i%100==0){
            //     System.out.println(i);
            // }
            run();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        System.out.println("ferdig kalk");
        // draw();
    }

    public void run(){
        // canvasController.fill();
        Tile tile = selectTile();
        if (tile==null){
            return;
        }
        List<Biome> mulige = tile.getMuligeBiomes();
        Biome biome = mulige.get(rand.nextInt(mulige.size()));
        tile.setBiome(biome);
        // drawTile(tile);

        int[] pos = tile.getPos();
        int[][] naboer = new int[][]{{1,0}, {-1,0}, {0,1}, {0,-1}};
        
        for (int[] d : naboer){
            int x = (pos[0]+d[0]+tiles[0].length)%tiles[0].length;
            int y = (pos[1]+d[1]+tiles.length)%tiles.length;
            
            tiles[y][x].removeState(biome);
            
            // System.out.println(t);
        }
        drawTile(tile);
        
    }
    
    private Tile selectTile(){
        
        // Long time = System.nanoTime();
        
        List<Tile> ruter = new ArrayList<>();
        for (Tile[] r : tiles){
            ruter.addAll(Arrays.asList(r));
        }
        // System.out.println((System.nanoTime() - time)/Math.pow(10, 9));
        ruter.sort((a,b) -> Integer.compare(a.getMuligeBiomes().size(), b.getMuligeBiomes().size()));
        // System.out.println((System.nanoTime() - time)/Math.pow(10, 9));
        // System.out.println("aa " + ruter.subList(0, 10));
        List<Tile> rList = ruter.stream().filter(r->!r.isCollapsed()).collect(Collectors.toList());
        // System.out.println((System.nanoTime() - time)/Math.pow(10, 9));
        // System.out.println("bb " + ruter.subList(0, 10));
        List<Tile> mulige = rList.stream().filter(r -> r.getMuligeBiomes().size()==rList.get(0).getMuligeBiomes().size()).collect(Collectors.toList());
        // System.out.println((System.nanoTime() - time)/Math.pow(10, 9));
        
        // for (Tile t : mulige){
        //     canvasController.drawRect(t.getPos(), TILESIZE, Color.RED);
        // }
        // System.out.println(mulige);
        if (mulige.size()==0){
            draw();
            return null;
        }
        Tile t = mulige.get(rand.nextInt(mulige.size()));
        return t;
    }

    private void generateTiles(){
        for (int i = 0; i<tiles.length; i++){
            for (int j = 0; j<tiles[i].length; j++){
                tiles[i][j] = new Tile(new int[]{j, i}, new ArrayList<>(Arrays.asList(biomes)));
            }
        }
    }

    private void generateBiomes(){
        biomes = new Biome[antallBiomes];
        biomes[0] = new Biome(Color.LIGHTGREEN);
        biomes[1] = new Biome(Color.BLUE);
        biomes[2] = new Biome(Color.DARKGREEN);
        biomes[3] = new Biome(Color.GRAY);

        biomes[0].setNaboer(new Biome[]{biomes[0], biomes[1], biomes[2], biomes[3]});
        biomes[1].setNaboer(new Biome[]{biomes[0], biomes[1]});
        biomes[2].setNaboer(new Biome[]{biomes[0], biomes[2]});
        biomes[3].setNaboer(new Biome[]{biomes[0], biomes[3]});
    }

    public void draw(){
        for (int i = 0; i<tiles.length; i++){
            for (int j = 0; j<tiles[i].length; j++){
                canvasController.drawRect(new int[]{j, i}, TILESIZE, tiles[i][j].getFarge());
            }
        }
    }
    

    private void drawTile(Tile t){
        canvasController.drawRect(t.getPos(), TILESIZE, t.getFarge());
    }
}
