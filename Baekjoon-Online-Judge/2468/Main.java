import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    static class Location {
        int r;
        int c;
        public Location(int r, int c) { this.r = r; this.c = c; }
    }
    
    static int N;
    static int[][] map;
    static boolean[][] visited;
    static final int[] rw = { -1, 1, 0, 0 };
    static final int[] cw = { 0, 0, -1, 1 };

    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        Set<Integer> heightSet = new HashSet<>();
        map = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int height = Integer.parseInt(st.nextToken());
                map[i][j] = height;
                heightSet.add(height);
            }
        }

        // 아무 지역도 물에 잠기지 않을 수 있으므로 최대 안전 영역 수를 1로 초기화
        int max_zone = 1;

        for (int height : heightSet) {

            // setting
            for (int i = 0; i < N; i++) {
                Arrays.fill(visited[i], false);
            }

            int zone = 0;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    // 물에 잠긴 곳이면 건너 뜀
                    if (map[i][j] <= height || visited[i][j]) {
                        continue;
                    }
                    bfs(i, j, height);
                    zone++;
                }
            }

            max_zone = Math.max(max_zone, zone);
        }


        bw.write(String.valueOf(max_zone));
        bw.flush();
    }

    static void bfs(int r, int c, int baseHeight) {

        Queue<Location> q = new ArrayDeque<>();
        q.add(new Location(r, c));

        while (!q.isEmpty()) {

            Location now = q.poll();
            visited[now.r][now.c] = true;
            
            for (int i = 0; i < 4; i++) {
                int nextR = now.r + rw[i];
                int nextC = now.c + cw[i];
    
                if (isValid(nextR, nextC, baseHeight) && !visited[nextR][nextC]) {
                    q.add(new Location(nextR, nextC));
                    visited[nextR][nextC] = true;
                }
            }
        }
    }

    static boolean isValid(int r, int c, int baseHeight) {
        return r < N && c < N && r >= 0 && c >= 0 && map[r][c] > baseHeight;
    }
}