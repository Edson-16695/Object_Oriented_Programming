package connectfour.gui;

import connectfour.View;
import connectfour.model.Cell;
import connectfour.model.ConnectFourModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

    @Override
    public void update(Cell cell, int line, int col) {
        CellButton button = buttons[line][col];
        if(cell == Cell.PLAYER1){
            button.setTic();
        }else {
            button.setTac();
        }
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
