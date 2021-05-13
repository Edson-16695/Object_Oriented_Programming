package connectfour.model;

public class ConnectFourModel {

    final int LINE = 6;
    final int COL = 7;
    Cell[][] boardData;

    private boolean isFree(int line, int col) {
        if (boardData[line][col] == Cell.EMPTY){
            return true;
        }
        return false;
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
