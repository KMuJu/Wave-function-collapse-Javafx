package TileTest;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class Tile {
    final List<String> kanter;
    final Image image;
    final int weight;
    final int rotations;

    Tile(Image img, ArrayList<String> kanter, int weight, int rotations){
        image = img;
        this.kanter = new ArrayList<>(kanter);
        this.weight = weight;
        this.rotations = rotations;
    }

    

    public Image getImage() {
        return image;
    }

    public String getKant(int kant){
        return kanter.get(kant);
    }

    public Tile rotate(int n){

        ImageView imageView = new ImageView(image.getUrl());
        imageView.setRotate(90*n);
        
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);

        Image image = imageView.snapshot(params, null);
        int len = kanter.size();
        List<String> newKanter = new ArrayList<>(len);

        for (int i = 0; i<len; i++){
            String kant = kanter.get((i-n+len)%len);
            newKanter.add(kant);
        }

        return new Tile(image, new ArrayList<>(newKanter), weight, rotations);
    }

    public int getRotations() {
        return rotations;
    }

    public int getWeight() {
        return weight;
    }

    public List<String> getKanter(){
        return kanter;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return kanter.toString();
    }
}
