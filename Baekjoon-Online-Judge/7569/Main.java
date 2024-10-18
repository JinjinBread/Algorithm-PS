
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {

    static class Location {
        int h;
        int r;
        int c;
        int day;
        public Location(int h, int r, int c, int day) {
            this.h = h;
            this.r = r;
            this.c = c;
            this.day = day;
        }
    }

    static int[][][] box;
    static int M; // 열
    static int N; // 행
    static int H;
    static Queue<Location> tomato;
    static boolean[][][] visited;

    static final int[] hw = { 0, 0, 0, 0, 1, -1 };
    static final int[] rw = { -1, 1, 0, 0, 0, 0 };
    static final int[] cw = { 0, 0, -1, 1, 0, 0 };

    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        box = new int[H][N][M];
        visited = new boolean[H][N][M];
        tomato = new ArrayDeque<>();

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < M; k++) {
                    box[i][j][k] = Integer.parseInt(st.nextToken());
                    // 익은 토마토면
                    if (box[i][j][k] == 1) {
                        tomato.add(new Location(i, j, k, 0));
                        visited[i][j][k] = true;
                    }
                }
            }    
        }

        int day = bfs();
        bw.write(String.valueOf(day));
        bw.flush();
    }

    static int bfs() {

        int day = 0;

        while (!tomato.isEmpty()) {

            Location now = tomato.poll();

            for (int i = 0; i < 6; i++) {
                int h = now.h + hw[i];
                int r = now.r + rw[i];
                int c = now.c + cw[i];

                if (isValidLocation(h, r, c)) {
                    tomato.add(new Location(h, r, c, now.day + 1));
                    box[h][r][c] = 1;
                    visited[h][r][c] = true;
                }
            }

            day = now.day;
        }

        // 익지 않은 토마토가 있다면
        if (!isAllRipen()) {
            return -1;
        }

        return day;
    }

    static boolean isAllRipen() {
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    if (box[i][j][k] == 0) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    static boolean isValidLocation(int h, int r, int c) {
        return h >= 0 && r >= 0 && c >= 0 &&
            h < H && r < N && c < M &&
            !visited[h][r][c] &&
            box[h][r][c] != -1; // 토마토가 들어 있지 않은 경우
    }

}