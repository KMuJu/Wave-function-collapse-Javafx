package Biome;


import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BiomeApp extends Application{
    
    
    
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setResizable(false);
        primaryStage.setTitle("Othello");
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("App.fxml")));
        primaryStage.setScene(scene);
        
        primaryStage.show();
    }
}