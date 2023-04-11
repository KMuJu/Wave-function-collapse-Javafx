package Biome;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.scene.paint.Color;

public class Tile {
    Biome biome;
    int[] pos;
    List<Biome> muligeBiomes;
    int size = 10;
    
    public Tile(int[] pos, List<Biome> muligeBiomes) {
        this.pos = pos;
        this.muligeBiomes = muligeBiomes;
    }

    @Override
    public String toString() {
        return "[" + Integer.toString(pos[0]) + ", " + Integer.toString(pos[1]) + ", " + muligeBiomes.size() + ", " + isCollapsed() + "]";
    }

    public void removeState(Biome biome){
        // muligeBiomes.remove(biome);
        List<Biome> mulige = new ArrayList<>(Arrays.asList(biome.getNaboer()));
        List<Biome> midlertidig = new ArrayList<>(muligeBiomes);
        for (Biome b : muligeBiomes){
            if (!mulige.contains(b)){
                midlertidig.remove(b);
            }
        }
        muligeBiomes = new ArrayList<>(midlertidig);
    }

    public void setBiome(Biome biome){
        this.biome = biome;
    }

    public boolean isCollapsed(){
        return biome!=null;
    }
    public Biome getBiome() {
        return biome;
    }
    public int[] getPos() {
        return pos;
    }

    public List<Biome> getMuligeBiomes() {
        return muligeBiomes;
    }

    public int getSize() {
        return size;
    }

    public Color getFarge(){
        if (biome == null){
            return Color.LIGHTGREY;
        }
        return biome.getFarge();
    }

    
}
