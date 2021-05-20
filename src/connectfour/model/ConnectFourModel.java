package connectfour.model;

import connectfour.View;

public class ConnectFourModel{

    private final View VIEW;
    public final int LINE = 6;
    public final int COL = 7;
    Cell[][] boardData;
    int turnCounter = 0;

    public ConnectFourModel(View view){
        this.VIEW = view;
        this.boardData = new Cell[this.LINE][this.COL];
        this.fillBoard();
    }

    private void fillBoard() {
        for (int line = 0; line < this.LINE; line++) {
            for (int col = 0; col < this.COL; col++) {
                this.boardData[line][col] = Cell.EMPTY;
            }
        }
    }

    private boolean isFree(int line, int col) {
        if (boardData[line][col] == Cell.EMPTY){
            return true;
        }
        return false;
    }

    public int cellSelected(int col){
        int line = this.startFromBottom(col);
        this.updateSelected(line, col);

        return line;
    }

    private void updateSelected(int line, int col){
        if (isFree(line, col)){
            this.updateBoardState(line, col);
            Cell cell = getCell(line, col);
            VIEW.update(cell, line, col);
            turnCounter++;
        }
    }

    private Cell getCell(int line, int col){
        return this.boardData[line][col];
    }

    private void updateBoardState(int line, int col){
        if (this.turnCounter % 2 == 0){
            this.boardData[line][col] = Cell.PLAYER1;
        }else {
            this.boardData[line][col] = Cell.PLAYER2;
        }
    }
    private int startFromBottom (int col){
        for (int line = (LINE - 1); line >= 0; line--) {
            if(isFree(line, col)){
                return line;
            }
        }
        return 0;
    }


}
