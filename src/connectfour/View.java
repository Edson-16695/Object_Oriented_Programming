package connectfour;

import connectfour.model.Cell;

public interface View {

    void draw();
    void playerWins(int player);

    void update(Cell cell, int line, int col);

    void clearBoard();
}
