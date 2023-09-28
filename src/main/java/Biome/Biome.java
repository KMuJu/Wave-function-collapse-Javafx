package Biome;

import javafx.scene.paint.Color;

public class Biome {
    Color farge;
    Biome[] naboer;

    
    public Biome(Color farge) {
        this.farge = farge;
    }
    public Color getFarge() {
        return farge;
    }
    public Biome[] getNaboer() {
        return naboer;
    }
    public void setNaboer(Biome[] naboer) {
        this.naboer = naboer;
    }

    
}
