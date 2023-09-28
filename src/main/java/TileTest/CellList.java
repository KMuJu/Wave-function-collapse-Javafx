package TileTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CellList {
    Cell[] cells;

    public CellList(List<Cell> liste){
        cells = new Cell[liste.size()];

        for (int i = 0; i < liste.size(); i++) {
            cells[i] = liste.get(i);
            cells[i].setIndex(i);
        }
    }

    void print(){
        System.out.println(Arrays.toString(cells));
    }


    public List<Cell> getLowestList(){
        List<Cell> retur = new ArrayList<>();
        int num = cells[0].getNum();
        for (Cell c : cells){
            if (c.isCollapsed()) continue;
            if (c.getNum()!= num) break;
            retur.add(c);

        }
        return retur;
    }

    public void update(Cell cell){
        // System.out.println("Cell: " + cell.getX() + ", " + cell.getY());
        if (cell.isCollapsed()){
            // System.out.println("Du er kollapsed");
            moveDown(cell);
            return;
        }
        moveUp(cell);
    }

    private void moveDown(Cell cell){
        while (cell.getIndex()+1<cells.length){
            swap(cell, cells[cell.getIndex()+1]);
        }
        // System.out.println(cell.getIndex());
    }

    private void moveUp(Cell cell){
        int i = 0;
        while (true) {
            i++;
            if (i+2>cells.length) return;
            if (cell.getIndex()-1>=0){
                Cell c1 = cells[cell.getIndex()-1];
                if (cell.compareTo(c1)>0){
                    swap(cell, c1);
                    continue;
                }
            }
            return;
        }
    }

    private void swap(Cell a, Cell b){
        cells[a.getIndex()] = b;
        cells[b.getIndex()] = a;
        int aIndex = a.getIndex();
        a.setIndex(b.getIndex());
        b.setIndex(aIndex);
    }
}
