import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static class Block {
        int r;
        int c;
        public Block(int r, int c) { this.r = r; this.c = c; }
    }

    private static int[][] board;

    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        board = new int[9][9];

        for (int i = 0; i < 9; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        sudoku();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                bw.write(board[i][j] + " ");
            }
            bw.newLine();
        }

        bw.flush();
    }

    // 같은 행에 num이 있는지
    private static boolean existEqualNumAtRow(int r, int num) {
        return Arrays.stream(board[r]).anyMatch(n -> n == num);
    }

    // 같은 열에 num이 있는지
    private static boolean existEqualNumAtCol(int c, int num) {
        for (int i = 0; i < 9; i++) {
            if (board[i][c] == num)
                return true;
        }
        return false;
    }

    // 정사각형 내에 num이 있는지
    private static boolean existEqualNumAtRect(int r, int c, int num) {

        int rangeR = (r / 3) * 3;
        int rangeC = (c / 3) * 3;

        for (int i = rangeR; i < rangeR + 3; i++) {
            for (int j = rangeC; j < rangeC + 3 ; j++) {
                if (board[i][j] == num)
                    return true;
            }
        }

        return false;
    }

    private static Block getEmptyBlock() {

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == 0)
                    return new Block(i, j);
            }
        }
        return null;
    }

    private static boolean sudoku() {

        Block block = getEmptyBlock();
        
        // 모든 빈 칸이 채워짐
        if (block == null) {
            return true;
        }

        for (int i = 1; i <= 9; i++) {

            int r = block.r;
            int c = block.c;

            // 같은 행, 열, 3*3 범위에 해당 숫자(i)가 존재하는 경우
            if (existEqualNumAtRow(r, i) || existEqualNumAtCol(c, i) || existEqualNumAtRect(r, c, i)) {
                continue;
            }

            board[r][c] = i;
            
            // 모든 빈 칸을 돈 후, 다 채워지면 true를 반환한다.
            if (sudoku()) {
                return true;
            }

            // sudoku가 false를 반환하면,
            // ==> 이후 빈칸에 들어갈 숫자가 없다 ==> 이전 작업에서 정답이 아닌 수를 넣었다는 의미
            // 넣은 수(i)를 다시 초기화한다.
            board[r][c] = 0;
        }

        return false;
    }
}
