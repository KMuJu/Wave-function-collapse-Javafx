package MittProsjekt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class UIGenerator {

    // her skal ting som skal brukes senere
    ComboBox<String> dropDown = new ComboBox<>();
    Text tid = new Text();
    
    UIGenerator(){
        dropDown.getItems().setAll(
            "Lett",
            "Middels",
            "Vanskelig"
        );
    }
    
    public VBox startSkjerm(EventHandler<MouseEvent> e){
        VBox boks = new VBox();
        boks.setPrefSize(600, 600);
        // boks.setStyle("-fx-background-color:black;");
        boks.setAlignment(Pos.CENTER);
        Text tittel = new Text("MineSweeper");
        tittel.setFont(new Font(22));

        HBox velgNivå = new HBox();
        velgNivå.setAlignment(Pos.CENTER);

        
        dropDown.setValue("Lett");

        Button startSpillKnapp = new Button();
        startSpillKnapp.setText("Start spillet");
        startSpillKnapp.setOnMouseClicked(e);
        

        velgNivå.getChildren().addAll(dropDown, startSpillKnapp);
       
        boks.getChildren().addAll(tittel, velgNivå);
        for (Node n : boks.getChildren()){
            VBox.setMargin(n, new Insets(5));
        }
        for (Node n : velgNivå.getChildren()){
            HBox.setMargin(n, new Insets(5));
        }
        
        return boks;
    }

    public ObservableList<Node> spillTopp(EventHandler<MouseEvent> tilbakeE, EventHandler<MouseEvent> startPaaNyttE){
        Button tilbake = new Button("Tilbake");
        
        Button startPaaNytt = new Button("Start på nytt");

        tilbake.setOnMouseClicked(tilbakeE);
        startPaaNytt.setOnMouseClicked(startPaaNyttE);

        HBox.setMargin(tilbake, new Insets(5));
        HBox.setMargin(tid, new Insets(5));
        HBox.setMargin(startPaaNytt, new Insets(5));

        List<Node> l = new ArrayList<>(Arrays.asList(tilbake, tid, startPaaNytt));
        return FXCollections.observableList(l);
    }

    public StackPane seierOverlay(EventHandler<MouseEvent> e, String tid){
        StackPane overLap = new StackPane();
        overLap.setPrefSize(400, 400);
        overLap.relocate(100, 100);
        
        VBox fill = new VBox();
        fill.setPrefSize(400, 400);
        fill.setStyle("-fx-background-color:#f5f2cb");
        fill.setAlignment(Pos.TOP_CENTER);

        Text tittel = new Text("Du Vant");
        tittel.setFont(Font.font("Verdana", FontWeight.BOLD, 32));

        Text hvorLangTid = new Text("Du brukte: " + tid + "s");
        hvorLangTid.setFont(Font.font("Verdana", FontWeight.BOLD, 22));

        Button provPaaNyttKnapp = new Button("Start på nytt");
        provPaaNyttKnapp.setOnMouseClicked(e);

        VBox.setMargin(tittel, new Insets(20, 0, 40, 0));
        VBox.setMargin(hvorLangTid, new Insets(20, 0, 20, 0));
        VBox.setMargin(provPaaNyttKnapp, new Insets(100, 0, 20, 0));

        fill.getChildren().addAll(tittel, hvorLangTid);

        // for (Double i : score){
        //     Text t = new Text(Double.toString(i) + "s");
        //     t.setFont(Font.font("Verdana", FontWeight.BOLD, 32));
        //     VBox.setMargin(t, new Insets(5));
        //     fill.getChildren().add(t);
        // }

        fill.getChildren().addAll(provPaaNyttKnapp);

        overLap.getChildren().addAll(fill);

        return overLap;
    }
}
