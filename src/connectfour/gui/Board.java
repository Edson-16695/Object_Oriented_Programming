package connectfour.gui;

import javafx.scene.layout.GridPane;

public class Board extends GridPane {

    private int line = 6;
    private int col = 7;
    private CellButton[][] buttons;

    public Board(){
        this.createBoard();

    }

    private void createBoard(){

        this.buttons = new CellButton[this.line][this.col];
        for (int i = 0; i < line ; i++) {
            for (int j = 0; j < col; j++) {
                CellButton button = new CellButton();
                this.add(button, j, i);
                this.buttons[i][j] = button;

            }

        }
    }
}
