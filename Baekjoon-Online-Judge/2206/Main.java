import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class Location {
        int r;
        int c;
        boolean useBroken;
        int movedNum;
        public Location(int r, int c, boolean useBroken, int movedNum) {
            this.r = r;
            this.c = c;
            this.useBroken = useBroken;
            this.movedNum = movedNum;
        }
    }

    static int N;
    static int M;
    static int[][] map;
    static boolean[][] visited;
    static boolean[][] brokenVisited;
    static int[] rw = { -1, 1, 0, 0 };
    static int[] cw = { 0, 0, -1, 1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            char[] info = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                map[i][j] = info[j] - '0';
            }
        }

        visited = new boolean[N][M];
        brokenVisited = new boolean[N][M];

        int result = backtrack(0, 0);

        System.out.println(result);
    }

    static int backtrack(int r, int c) {

        Queue<Location> q = new ArrayDeque<>();
        q.add(new Location(r, c, false, 1));
        visited[r][c] = true;

        while (!q.isEmpty()) {

            Location now = q.poll();

            if (now.r == (N - 1) && now.c == (M - 1)) {
                return now.movedNum;
            }
            
            for (int i = 0; i < 4; i++) {
                
                int nextR = now.r + rw[i];
                int nextC = now.c + cw[i];

                if (!isValid(nextR, nextC)) {
                    continue;
                }

                if (now.useBroken) {

                    if (map[nextR][nextC] == 1 || brokenVisited[nextR][nextC]) {
                        continue;
                    }

                    brokenVisited[nextR][nextC] = true;
                    q.add(new Location(nextR, nextC, true, now.movedNum + 1));
                }
                else {
                    if (map[nextR][nextC] == 1) {
                        brokenVisited[nextR][nextC] = true;
                        q.add(new Location(nextR, nextC, true, now.movedNum + 1));
                    }
                    else {
                        visited[nextR][nextC] = true;
                        q.add(new Location(nextR, nextC, now.useBroken, now.movedNum + 1));
                    }
                }
            }
        }
        
        return -1;
    }

    static boolean isValid(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < M && !visited[r][c];
    }
}
