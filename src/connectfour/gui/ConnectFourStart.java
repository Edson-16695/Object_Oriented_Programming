package connectfour.gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



public class ConnectFourStart extends Application {

    private VBox vBox;
    private Button resetButton;
    private Board board;

    public static void main(String[] args) { Application.launch(args);}

    @Override
    public void start(Stage primaryStage) {

        ResetButtonHandler resetButtonHandler = new ResetButtonHandler();
        board = new Board(); // create object of Board class
        resetButton = new Button("RESET");
        resetButton.setOnAction(resetButtonHandler);
        vBox = new VBox(board, resetButton);


        Scene scene = new Scene(vBox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    class ResetButtonHandler implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event) {
           board.clearBoard();
        }
    }
}
