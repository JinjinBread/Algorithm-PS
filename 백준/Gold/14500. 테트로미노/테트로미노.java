import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int M;
    static int max = Integer.MIN_VALUE;
    static int[][] paper;
    static boolean[][] visited;
    static int[] rw = { -1, 1, 0, 0 };
    static int[] cw = { 0, 0, -1, 1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 세로 길이
        M = Integer.parseInt(st.nextToken()); // 가로 길이

        paper = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                Arrays.fill(visited[i], false);
                visited[i][j] = true;
                getMaxExceptionShape(i, j);
                dfs(i, j, paper[i][j], 1);
            }
        }

        System.out.println(max);

    }

    static void dfs(int r, int c, int total, int count) {

        if (count == 4) {
            max = Math.max(max, total);
            return;
        }

        for (int i = 0; i < 4; i++) {
            
            int nextR = r + rw[i];
            int nextC = c + cw[i];

            if (isValid(nextR, nextC) && !visited[nextR][nextC]) {
                visited[nextR][nextC] = true;
                dfs(nextR, nextC, total + paper[nextR][nextC], count + 1);
                visited[nextR][nextC] = false;
            }

        }

    }

    static void getMaxExceptionShape(int r, int c) {

        // ㅏ, ㅓ, ㅜ, ㅗ 모양 찾기

        // ㅏ
        if ((r - 1) >= 0 && (r + 1) < N && (c + 1) < M) {
            int total = paper[r][c] + paper[r-1][c] + paper[r+1][c] + paper[r][c+1];
            max = Math.max(total, max);
        }

        // ㅓ
        if ((r - 1) >= 0 && (r + 1) < N && (c - 1) >= 0) {
            int total = paper[r][c] + paper[r-1][c] + paper[r+1][c] + paper[r][c-1];
            max = Math.max(total, max);
        }

        // ㅜ
        if ((r + 1) < N && (c - 1) >= 0 && (c + 1) < M) {
            int total = paper[r][c] + paper[r+1][c] + paper[r][c-1] + paper[r][c+1];
            max = Math.max(total, max);
        }

        // ㅗ
        if ((r - 1) >= 0 && (c - 1) >= 0 && (c + 1) < M) {
            int total = paper[r][c] + paper[r-1][c] + paper[r][c-1] + paper[r][c+1];
            max = Math.max(total, max);
        }
    }

    static boolean isValid(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < M;
    }
    
}
