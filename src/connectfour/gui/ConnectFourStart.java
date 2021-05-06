package connectfour.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ConnectFourStart extends Application {
    public static void main(String[] args) { Application.launch(args);}

    @Override
    public void start(Stage primaryStage) {
        Board board = new Board(); // create object of Board class
        Scene scene = new Scene(board);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
