import java.util.Arrays;
import java.util.List;

public class Solution {

    public static void main(String[] args) {

        int[][] result = solution(new int[][] 
        {
            {5, 3, 0, 0, 7, 0, 0, 0, 0},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 9, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, 7, 9}
        });

        for (int[] res: result) {
            System.out.println(Arrays.toString(res));
        }
    }

    private static int[][] Board;

    private static class Block {
        int i, j;
        public Block(int i, int j) { this.i = i; this.j = j; }
    }

    private static Block findEmptyBlock() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (Board[i][j] == 0)
                    return new Block(i, j);
            }
        }
        return null; // 빈 곳이 없음
    }

    private static boolean sudoku() {

        Block emptyBlock = findEmptyBlock();

        // 빈 칸이 다 채워짐
        if (emptyBlock == null)
            return true;

        int row = emptyBlock.i;
        int col = emptyBlock.j;

        for (int i = 1; i <= 9; i++) {
            
            if (isValid(row, col, i)) {
                Board[row][col] = i;

                // 또 다른 빈칸을 찾아 재귀 (결국 나중에 빈칸이 다 채워지면 되돌아옴)
                if (sudoku()) {
                    return true;
                }

                // sudoku()가 false를 리턴했을 때, 즉 들어갈 값이 없으면
                // 현재 넣은 값을 다시 0으로 되돌려 놓음 (넣은 값이 알맞은 해가 아니기 때문)
                Board[row][col] = 0;
            }
        }

        return false;
    }

    private static boolean isValid(int r, int c, int num) {
        return isAvailableNumAtRow(r, num) && isAvailableNumAtCol(c, num) && isAvailableNumAtBox(r, c, num);
    }

    private static boolean isAvailableNumAtRow(int r, int num) {
        for (int i = 0; i < 9; i++) {
            if (Board[r][i] == num)
                return false;
        }
        return true;
    }

    private static boolean isAvailableNumAtCol(int c, int num) {
        for (int i = 0; i < 9; i++) {
            if (Board[i][c] == num)
                return false;
        }
        return true;
    }

    private static boolean isAvailableNumAtBox(int r, int c, int num) {

        int rw = r / 3 * 3;
        int cw = c / 3 * 3;

        for (int i = rw; i < rw + 3; i++) {
            for (int j = cw; j < cw + 3; j++) {
                if (Board[i][j] == num)
                    return false;
            }
        }

        return true;
    }

    private static int[][] solution(int[][] board) {
        Board = board;
        sudoku();
        return Board;
    }
}
