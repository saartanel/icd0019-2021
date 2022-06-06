package gol;

import java.util.Arrays;

public class Game {

    private boolean[][] board = new boolean[50][50];

    public void markAlive(int x, int y) {
        board[x][y] = true;
    }

    public boolean isAlive(int x, int y) {
        return board[x][y];
    }

    public void toggle(int x, int y) {
        board[x][y] = !board[x][y];
    }

    public Integer getNeighbourCount(int x, int y) {
        int neighbourCount = 0;

        neighbourCount += checkTopRow(x, y);
        neighbourCount += checkCurRow(x, y);
        neighbourCount += checkBottomRow(x, y);

        return neighbourCount;
    }

    public int checkTopRow(int x, int y) {
        int neighbourCount = 0;
        if (x > 0 && y > 0 && board[x - 1][y - 1]) {
            neighbourCount++;
        }
        if (x > 0 && board[x - 1][y]) {
            neighbourCount++;
        }
        if (x > 0 && y < board.length - 1 && board[x - 1][y + 1]) {
            neighbourCount++;
        }
        return neighbourCount;
    }

    public int checkCurRow(int x, int y) {
        int neighbourCount = 0;

        if (y > 0 && board[x][y - 1]){
            neighbourCount ++;
        } if (y < board.length - 1 && board[x][y + 1]) {
            neighbourCount++;
        }
        return neighbourCount;
    }

    public int checkBottomRow(int x, int y) {
        int neighbourCount = 0;

        if (y > 0 && x < board.length - 1 && board[x + 1][y - 1]){
            neighbourCount ++;
        } if (x < board.length - 1 && board[x + 1][y]) {
            neighbourCount ++;
        } if (x < board.length - 1 && y < board.length - 1 && board[x + 1][y + 1]) {
            neighbourCount ++;
        }
        return neighbourCount;
    }

    public void nextFrame() {
        boolean[][] boardCopy = new boolean[50][50];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                boardCopy[i][j] = nextState(board[i][j], getNeighbourCount(i, j));
            }
        }
        board = boardCopy;
    }

    public void clear() {
        for (boolean[] booleans : board) {
            Arrays.fill(booleans, false);
        }
    }

    public boolean nextState(boolean isLiving, int neighborCount) {
        if (isLiving && neighborCount <= 3 && neighborCount >= 2){
            return true;
        }
        return !isLiving && neighborCount == 3;
    }
}
