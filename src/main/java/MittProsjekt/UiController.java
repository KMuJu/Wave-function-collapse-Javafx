package MittProsjekt;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class UiController {
    Pane bane;
    
    public UiController(Pane bane) {
        this.bane = bane;
    }
    
    public void oppdaterView(Node n){
        bane.getChildren().clear();
        bane.getChildren().addAll(n);
    }
    public void oppdaterView(ObservableList<Node> l){
        bane.getChildren().clear();
        bane.getChildren().addAll(l);
    }
}
