package connectfour.gui;

import connectfour.model.Cell;
import connectfour.model.ConnectFourModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.GridPane;

public class Board extends GridPane {

    private int line = 6;
    private int col = 7;
    private CellButton[][] buttons;

    // constructor class
    public Board(){
        this.createBoard();
    }

    // create board
    public void createBoard(){

       ConnectFourButtonHandler buttonHandler = new ConnectFourButtonHandler();
       // this.buttons = new CellButton[line][col];

        this.buttons = new CellButton[this.line][this.col];
        for (int i = 0; i < line ; i++) {
            for (int j = 0; j < col; j++) {
                CellButton btn = new CellButton(); // create object of CellButton class
                btn.setOnAction(buttonHandler);
                this.add(btn, j, i);
                this.buttons[i][j] = btn;
            }
        }
    }

    class ConnectFourButtonHandler implements EventHandler<ActionEvent>{
        int counter = 0;
        @Override
        public void handle(ActionEvent event) {
            CellButton button = (CellButton) event.getSource();
            if(counter % 2 == 0){ // if it's even
                button.setTic();
            }else { // if it is odd
                button.setTac();
            }
            counter++;


            //int line = Board.this.getRowIndex(button);
           // int col = Board.this.getColumnIndex(button);
        }
    }
}
