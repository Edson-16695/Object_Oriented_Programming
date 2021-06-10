package connectfour.gui;

import connectfour.View;
import connectfour.model.Cell;
import connectfour.model.ConnectFourModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;

public class Board extends GridPane implements View {

    private CellButton[][] buttons;
    private final ConnectFourModel connectFourModel;
    //private CellButton cellButton;

    // constructor class
    public Board(){
        this.connectFourModel = new ConnectFourModel(this);
        this.createBoard();
    }

    // create board
    public void createBoard(){

       ConnectFourButtonHandler buttonHandler = new ConnectFourButtonHandler();
       // this.buttons = new CellButton[line][col];

        this.buttons = new CellButton[this.connectFourModel.LINE][this.connectFourModel.COL];
        for (int i = 0; i < this.connectFourModel.LINE ; i++) {
            for (int j = 0; j < this.connectFourModel.COL; j++) {
                CellButton btn= new CellButton(); // create object of CellButton class
                btn.setOnAction(buttonHandler);
                this.add(btn, j, i);
                this.buttons[i][j] = btn;
            }
        }
    }

    /**
     * Shows a message stating that the game ended in a draw.
     */
    @Override
    public void draw() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION,
                "Draw!");
        alert.show();
    }

    /**
     * Shows a message stating that a player has won the game.
     * @param player The winner
     */
    @Override
    public void playerWins(int player) {
        String s = player % 2 == 0 ? "PLAYER1" : "PLAYER2"; // Se 'player' for par, 's' toma o valor de "PLAYER1", caso contrário "PLAYER2"
        Alert alert = new Alert(Alert.AlertType.INFORMATION,
                "Player '" + s + "' won!");
        alert.show();
    }

    @Override
    public void update(Cell cell, int line, int col) {
        CellButton button = buttons[line][col];
        if(cell == Cell.PLAYER1){
            button.setTic();
        }else {
            button.setTac();
        }
    }

    @Override
    public void clearBoard() {
        getChildren().clear();
        this.createBoard();
        connectFourModel.resetGame();
    }

    class ConnectFourButtonHandler implements EventHandler<ActionEvent>{
        //int counter = 0;
        @Override
        public void handle(ActionEvent event) {
            CellButton button = (CellButton) event.getSource();
            int line = Board.this.getRowIndex(button);
            int col = Board.this.getColumnIndex(button);

            System.out.println(line + " " + col);
            connectFourModel.cellSelected(col);
        }
    }
}
