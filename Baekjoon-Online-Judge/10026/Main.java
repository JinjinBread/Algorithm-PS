import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main {
    
    private static int N;
    // 상하좌우
    private static int[] rw = { -1, 1, 0, 0 };
    private static int[] cw = { 0, 0, -1, 1 };
    private static boolean[][] visited;
    private static char[][] map;

    private static class Node {
        int r;
        int c;
        public Node(int r, int c) { this.r = r; this.c = c; }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            char[] row = br.readLine().toCharArray();
            map[i] = Arrays.copyOf(row, N);
        }

        int zone = calculateZone();
        
        convertRgBlind();
        for (int i = 0; i < N; i++) {
            Arrays.fill(visited[i], false);
        }
        int rgBlindZone = calculateZone();

        bw.write(zone + " " + rgBlindZone);
        bw.flush();
    }

    private static int calculateZone() {

        Queue<Node> q = new ArrayDeque<>();
        int zoneCnt = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    char zoneColor = map[i][j];

                    q.add(new Node(i, j));
                    visited[i][j] = true;

                    while (!q.isEmpty()) {

                        Node now = q.poll();

                        for (int k = 0; k < 4; k++) {
                            
                            int r = now.r + rw[k];
                            int c = now.c + cw[k];

                            if (isValidLocation(r, c) && !visited[r][c] && map[r][c] == zoneColor) {
                                q.add(new Node(r, c));
                                visited[r][c] = true;
                            }
                        }
                    }
                    zoneCnt++;
                }
            }
        }

        return zoneCnt;
    }

    private static boolean isValidLocation(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < N;
    }

    private static void convertRgBlind() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 'R') {
                    map[i][j] = 'G';
                }
            }
        }
    }

}
