package Othello;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class UiGenerator {
    Pane bane;
    
    ComboBox<String> dropDown = new ComboBox<>();

    public UiGenerator(Pane bane){
        this.bane = bane;
        dropDown.getItems().setAll(
            "bot v bot",
            "1v1",
            "1v bot"
        );
    }

    public ComboBox<String> getComboBox(){
        return dropDown;
    }

    public VBox createStartScene(EventHandler<MouseEvent> e){
        VBox rute = new VBox();
        rute.setPrefSize(600, 600);
        rute.setAlignment(Pos.CENTER);
        Text tittel = new Text("OTHELLO");

        HBox rad = new HBox();

        Button start = new Button("Start");
        start.setOnMouseClicked(e);

        dropDown.setValue("bot v bot");

        HBox.setMargin(start, new Insets(10));
        HBox.setMargin(dropDown, new Insets(10));

        rad.setAlignment(Pos.CENTER);
        rad.getChildren().addAll(dropDown, start);

        
        VBox.setMargin(tittel, new Insets(10));
        VBox.setMargin(rad, new Insets(10));
        rute.getChildren().addAll(tittel, rad);

        return rute;
    }
}
