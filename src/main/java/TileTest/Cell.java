package TileTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.scene.image.Image;

public class Cell implements Item{
    private int x, y;
    private Tile t;
    private List<Tile> states;
    private int heapIndex = 0;

    Cell(int x, int y, List<Tile> states){
        this.x = x;
        this.y = y;
        this.states = new ArrayList<>(states);
    }

    public void newNabo(Cell c, int kant){
        List<Tile> statesCopy = new ArrayList<>(states);
        // System.out.println(kant + ": " + c.getKant(kant));
        for (Tile t : statesCopy){
            // System.out.println(t);
            String k = t.getKant((kant+2)%4);
            String kantString = reverse(k);
            if (!c.getKant(kant).equals(kantString)){
                // System.out.println((kant+2)%4 + ": " + t.getKant((kant+2)%4));
                states.remove(t);
            }
        }
        // System.out.println(states);
    }

    public boolean newNaboRec(Cell c, int kant){
        List<Tile> statesCopy = new ArrayList<>(states);
        // System.out.println(kant + ": " + c.getKant(kant));
        Set<Tile> set = new HashSet<>();
        for (Tile tile : c.getStates()){
            for (Tile t : states){
                String k = t.getKant((kant+2)%4);
                String kantString = reverse(k);
                if (tile.getKant(kant).equals(kantString)){
                    set.add(t);
                }
            }
        }
        if (set.size()==states.size()) return false;
        for (Tile t : statesCopy){
            if (!set.contains(t)){
                states.remove(t);
            }
        }
        return true;
        // System.out.println(states);
    }

    private String reverse(String s){
        String retur = "";
        for (char c : s.toCharArray()){
            retur = c + retur;
        }
        return retur;
    }

    public void setT(Tile t) {
        this.t = t;
        this.states = new ArrayList<>(Arrays.asList(t));
    }

    public List<Tile> getStates() {
        return states;
    }

    public boolean isCollapsed(){
        return t!=null;
    }

    public Image getImage(){
        if (t==null){
            return new Image(getClass().getResource("blank.png").toString());
        }
        return t.getImage();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getKant(int kant){
        return t.getKant(kant);
    }

    public boolean valid(Cell c, int kant){
        return getKant(kant).equals(c.getKant(kant));
    }

    public List<String> getKanter(){
        return t.getKanter();
    }

    public int getNumStates(){
        return states.size();
    }

    @Override
    public String toString() {
        return Integer.toString(states.size());
        // return "[" + x + ", " + y  + ", " +states.size() + "]";
    }

    @Override
    public int getNum() {
        return getNumStates();
    }

    @Override
    public int getIndex() {
        return heapIndex;
    }

    @Override
    public void setIndex(int i){
        heapIndex = i; 
    }

    @Override
    public int compareTo(Item item){
        return item.getNum() - getNum();
    }

}
