package connectfour.model;

import connectfour.View;

public class ConnectFourModel{

    private int count_piece = 0;

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

    // To be able to play new game
    public void resetGame(){
        turnCounter = 0;
        this.fillBoard();
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

            this.checkBoardState(line,col);
        }
    }

    /**
     *Check the board for the winning or draw positions
     *
     * @param line
     * @param col
     */
    private void checkBoardState(int line, int col) {

        if (inWinnableTurn(this.turnCounter)) {
            if (isWinPosition(line, col)) {
                this.VIEW.playerWins((this.turnCounter - 1) % 2);
            }else if (isDrawPosition()) {
                this.VIEW.draw();
            }
        }
    }


    /**
     * Check for winning condition
     * @param line
     * @param col
     * @return true if a winning condition was found
     */
    public boolean isWinPosition(int line, int col) {
        // Verificar se a jogada resultou numa sequência vencedora
        // Os métodos são invocados sequencialmente e quando um deles devolve 'true' os seguintes já não são invocados
        return (horizontalWinning(line) ||
                verticalWinning(col) ||
                mainDiagonal() ||
                secondDiagonal() ||
                thirdDiagonal() ||
                antiDiagonal() ||
                secondAntiDiagonal() ||
                thirdAntiDiagonal());
    }

    /**
     * Checks if it is possible to win in this turn
     * @param turnCounter the turn to check
     * @return true if can win false otherwise
     */
    public boolean inWinnableTurn(int turnCounter) {
        if(turnCounter >= 7){
            return true;
        }else {
            return false;
        }
        //return turnCounter >=7;
    }

    /**
     * Check the horizontal winning
     * @param line
     * @return true if it find  4 followed pieces in lines otherwise false
     */
    // jogador a ganhar no horizontal
    private boolean horizontalWinning(int line) {

        //Checking Horizontal Win
        count_piece = 1;
        for (int col = 0; col < COL - 1; col++) {

            if (boardData[line][col] != Cell.EMPTY && boardData[line][col + 1] == boardData[line][col]) {
                count_piece++;
                if (count_piece == 4) {

                    return true;
                }
            }
            else
            {
                count_piece = 1;
            }
        }

        return false;  // no 4-in-a-line found
    }

    /**
     * Check the Vertical winning
     * @param col
     * @ true if it find  4 followed pieces in columns otherwise false
     */
    private boolean verticalWinning(int col) {

        //Checking Vertical Win
        count_piece = 1;
        for (int line = 0; line < LINE - 1; line++) {

            if (boardData[line][col] != Cell.EMPTY && boardData[line + 1][col] == boardData[line][col]) {
                count_piece++;
                if (count_piece == 4) {

                    return true;
                }
            }
            else
            {
                count_piece = 1;
            }
        }

        return false;  // no 4-in-a-line found
    }

    /**
     * Check the main diagonals winning
     * @return true if it find 4 followed pieces in diagonal
     */
    private boolean mainDiagonal() {
        // Top-Left -> Bottom-Right
        int col = (COL - 1);
        int count_piece = 1;

        for (int line = (LINE - 1); line > 0; line--) {

            if (boardData[line][col] != Cell.EMPTY &&  boardData[line][col] == boardData[line - 1][col - 1]) {
                count_piece++;
                if (count_piece >= 4) {

                    return true;
                }
            }
            else
            {
                count_piece = 1;
            }
            col--;
        }

        col = this.COL - 2;

        for (int line = (LINE - 1); line > 0; line--) {

            if (boardData[line][col] != Cell.EMPTY && boardData[line - 1][col - 1] == boardData[line][col]) {
                count_piece++;
                if (count_piece >= 4) {
                    // System.out.println("you win");
                    return true;
                }
            }
            else
            {
                count_piece = 1;
            }
            col--;
        }
        return false;  // no 4-in-a-line found
    }

    /**
     * Check the second diagonals winning
     * @return true if it find 4 followed pieces in diagonal
     */
    private boolean secondDiagonal () {

        int col = (COL - 1);
        int line = (LINE - 1);
        int count_piece = 1;

        for (int line2 = 4; line2 >= 1; line2--) {

            if (boardData[line2][col] != Cell.EMPTY && boardData[line2 - 1][col - 1] == boardData[line2][col]) {
                count_piece++;
                if (count_piece >= 4) {
                    // System.out.println("you win");
                    return true;
                }
            }
            else
            {
                count_piece = 1;
            }
            col--;
        }

        for (int col2 = 4; col2 > 0; col2--) {
            if (boardData[line][col2] != Cell.EMPTY && boardData[line - 1][col2 - 1] == boardData[line][col2]) {
                count_piece++;
                if (count_piece >= 4) {
                    // System.out.println("you win");
                    return true;
                }
            }
            else
            {
                count_piece = 1;
            }
            line--;
        }
        return false;
    }

    /**
     * Check the thrid diagonals winning
     * @return true if it find 4 followed pieces in diagonal
     */
    private boolean thirdDiagonal() {

        int col = (COL - 1);
        int line = (LINE - 1);
        int count_piece = 1;

        for (int line3 = 3; line3 > 0; line3--) {

            if (boardData[line3][col] != Cell.EMPTY && boardData[line3 - 1][col - 1] == boardData[line3][col]) {
                count_piece++;
                if (count_piece >= 4) {
                    // System.out.println("you win");
                    return true;
                }
            }
            else
            {
                count_piece = 1;
            }
            col--;
        }

        for (int col3 = 3; col3 > 0; col3--) {
            if (boardData[line][col3] != Cell.EMPTY && boardData[line - 1][col3 - 1] == boardData[line][col3]) {
                count_piece++;
                if (count_piece >= 4) {
                    // System.out.println("you win");
                    return true;
                }
            }
            else
            {
                count_piece = 1;
            }
            line--;
        }
        return false;
    }

    /**
     * Check the anti diagonals winning
     * @return true if it find 4 followed pieces in anti diagonal
     */
    private boolean antiDiagonal() {
        // Top-Right -> Bottom-Left
        int col = 0;
        int line = (LINE - 1);
        int count_piece = 1;

        for (int line2 = (LINE - 1); line2 > 0; line2--) {

            if (boardData[line2][col] != Cell.EMPTY && boardData[line2 - 1][col + 1] == boardData[line2][col]) {
                count_piece++;
                if (count_piece >= 4) {
                    // System.out.println("you win");
                    return true;
                }
            }
            else
            {
                count_piece = 1;
            }
            col++;
        }

        for (int col2 = 1; col2 < this.COL - 1; col2++) {

            if (boardData[line][col2] != Cell.EMPTY && boardData[line - 1][col2 + 1] == boardData[line][col2]) {
                count_piece++;
                if (count_piece >= 4) {
                    // System.out.println("you win");
                    return true;
                }
            }
            else
            {
                count_piece = 1;
            }
            line--;
        }
        return false;  // no 4-in-a-line found
    }

    /**
     * Check the secong anti diagonals winning
     * @return true if it find 4 followed pieces in anti diagonal
     */
    private boolean secondAntiDiagonal () {

        int col = 0;
        int line = (LINE - 1);
        int count_piece = 1;

        for (int line2 = (LINE - 2); line2 >= 1 ; line2--) {

            if (boardData[line2][col] != Cell.EMPTY && boardData[line2 - 1][col + 1] == boardData[line2][col]) {
                count_piece++;
                if (count_piece >= 4) {
                    // System.out.println("you win");
                    return true;
                }
            }
            else
            {
                count_piece = 1;
            }
            col++;
        }

        for (int col2 = 2; col2 <= 5 ; col2++) {
            if (boardData[line][col2] != Cell.EMPTY && boardData[line - 1][col2 + 1] == boardData[line][col2]) {
                count_piece++;
                if (count_piece >= 4) {
                    // System.out.println("you win");
                    return true;
                }
            }
            else
            {
                count_piece = 1;
            }
            line--;
        }
        return false;
    }

    /**
     * Check the third diagonals winning
     * @return true if it find 4 followed pieces in anti diagonal
     */
    private boolean thirdAntiDiagonal() {

        int col = 0;
        int line = (LINE - 1);
        int count_piece = 1;

        for (int line3 = 3; line3 > 0; line3--) {

            if (boardData[line3][col] != Cell.EMPTY && boardData[line3 - 1][col + 1] == boardData[line3][col]) {
                count_piece++;
                if (count_piece >= 4) {
                    // System.out.println("you win");
                    return true;
                }
            }
            else
            {
                count_piece = 1;
            }
            col++;
        }

        for (int col3 = 3; col3 < 6 ; col3++) {
            if (boardData[line][col3] != Cell.EMPTY && boardData[line - 1][col3 + 1] == boardData[line][col3]) {
                count_piece++;
                if (count_piece >= 4) {
                    // System.out.println("you win");
                    return true;
                }
            }
            else
            {
                count_piece = 1;
            }
            line--;
        }
        return false;
    }


    /**
     * Check if the game ended in a draw
     * @return true if the draw condition was found
     */
    public boolean isDrawPosition() {
        return turnCounter == this.LINE * this.COL;
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
