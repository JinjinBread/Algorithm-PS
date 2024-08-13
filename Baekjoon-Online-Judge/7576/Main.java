import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static class Node {
        int r;
        int c;
        int day;
        public Node(int r, int c, int day) { this.r = r; this.c = c; this.day = day; }
    }

    // 상, 하, 좌, 우
    private static int[] rw = { -1, 1, 0, 0 };
    private static int[] cw = { 0, 0, -1, 1 };

    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken()); // 상자 열 수
        int N = Integer.parseInt(st.nextToken()); // 상자 행 수

        int[][] box = new int[N][M];

        Queue<Node> pq = new ArrayDeque<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int tomato = Integer.parseInt(st.nextToken());
                box[i][j] = tomato;
                if (tomato == 1) {
                    pq.add(new Node(i, j, 0));
                }
            }
        }

        int days = 0;

        while (!pq.isEmpty()) {

            Node now = pq.poll();
            days = now.day;

            for (int i = 0; i < 4; i++) {
                
                int r = now.r + rw[i];
                int c = now.c + cw[i];

                // 범위를 벗어나는 경우
                if (r < 0 || c < 0 || r >= N || c >= M) {
                    continue;
                }

                // 토마토가 없는 경우 or 이미 익은 토마토인 경우
                if (box[r][c] == -1 || box[r][c] == 1) {
                    continue;
                }

                box[r][c] = 1;
                pq.add(new Node(r, c, now.day + 1));
            }
        }

        for (int i = 0; i < N; i++) {
            // 익지 않은 토마토가 있다면
            if (Arrays.stream(box[i]).anyMatch(tmt -> tmt == 0)) {
                bw.write(String.valueOf(-1));
                bw.flush();
                return;
            }
        }
        
        bw.write(String.valueOf(days));
        bw.flush();
    }
}
