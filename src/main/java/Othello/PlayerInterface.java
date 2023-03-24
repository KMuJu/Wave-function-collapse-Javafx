package Othello;

public interface PlayerInterface {
    void click(int x, int y);
    void spill();

    int getTurn();
    boolean getSpill();
    void setSpill(boolean b);
}
