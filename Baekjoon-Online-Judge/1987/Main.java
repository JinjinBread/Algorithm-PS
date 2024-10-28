import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int R;
    static int C;
    static char[][] board;
    static boolean[] isVisited;
    static int[] rw = { -1, 1, 0, 0 };
    static int[] cw = { 0, 0, -1, 1 };
    static int MAX_VISITED = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        board = new char[R][C];
        isVisited = new boolean[26];

        for (int i = 0; i < R; i++) {
            char[] alphabet = br.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                board[i][j] = alphabet[j];
            }
        }

        isVisited[board[0][0] - 'A'] = true; // 좌측 상단 칸 (1행 1열) 에는 말이 놓여 있다.
        dfs(0, 0, 1);
        System.out.println(MAX_VISITED);
    }


    static void dfs(int r, int c, int count) {

        for (int i = 0; i < 4; i++) {
            int nextR = r + rw[i];
            int nextC = c + cw[i];

            if (!isValid(nextR, nextC)) {
                continue;
            }

            char nextAlphabet = board[nextR][nextC];

            if (isVisited[nextAlphabet - 'A']) { // 이동할 칸의 알파벳을 이미 만났다면
                continue;
            }

            isVisited[nextAlphabet - 'A'] = true;
            dfs(nextR, nextC, count + 1);
            isVisited[nextAlphabet - 'A'] = false;
        }

        MAX_VISITED = Math.max(MAX_VISITED, count);
    }

    static boolean isValid(int r, int c) {
        return r >= 0 && c >= 0 && r < R && c < C;
    }

}
