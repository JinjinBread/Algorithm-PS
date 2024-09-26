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
        int r;
        int c;
        int time;
        public Location(int r, int c, int time) { this.r = r; this.c = c; this.time = time; }
    }

    static int R;
    static int C;
    static char[][] map;
    static final int[] rw = { -1, 1, 0, 0 };
    static final int[] cw = { 0, 0, 1, -1 };
    static Queue<Location> water; // 물 위치
    static Queue<Location> hedgehog; // 고슴도치 위치
    static Location dest; // 비버의 굴 위치
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        water = new ArrayDeque<>();
        hedgehog = new ArrayDeque<>();
        visited = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            String mapInfo = st.nextToken();
            for (int j = 0; j < C; j++) {
                map[i][j] = mapInfo.charAt(j);
                if (map[i][j] == '*') {
                    water.add(new Location(i, j, 0));
                }
                if (map[i][j] == 'S') {
                    hedgehog.add(new Location(i, j, 0));
                }
                if (map[i][j] == 'D') {
                    dest = new Location(i, j, 0);
                }
            }
        }

        bw.write(bfs());
        bw.flush();
    }

    static String bfs() {

        int state = -1;

        while (!hedgehog.isEmpty()) {
            flowWater();
            // 비버의 굴에 도착했다면
            state = moveHedgehog();
            if (state != -1)
                return String.valueOf(state);
        }
        

        return "KAKTUS";
    }

    static int moveHedgehog() {
        
        int canGoNum = hedgehog.size();

        for (int i = 0; i < canGoNum; i++) {

            Location hLoc = hedgehog.poll();

            if ((hLoc.r == dest.r) && (hLoc.c == dest.c)) {
                return hLoc.time;
            }

            visited[hLoc.r][hLoc.c] = true;

            for (int j = 0; j < 4; j++) {
                int r = hLoc.r + rw[j];
                int c = hLoc.c + cw[j];

                if (isValid(r, c) && !visited[r][c]) {

                    visited[r][c] = true;
                    hedgehog.add(new Location(r, c, hLoc.time + 1));
                }
            }
        }

        return -1;
    }

    static void flowWater() {
        
        int waterNum = water.size();

        for (int i = 0; i < waterNum; i++) {
            Location waterLoc = water.poll();

            for (int j = 0; j < 4; j++) {
                
                int flowWaterR = waterLoc.r + rw[j];
                int flowWaterC = waterLoc.c + cw[j];

                // 추가로 물은 비버의 소굴에도 흐르지 않는다.
                if (isValid(flowWaterR, flowWaterC) && map[flowWaterR][flowWaterC] != 'D') {
                    map[flowWaterR][flowWaterC] = '*';
                    water.add(new Location(flowWaterR, flowWaterC, 0));
                }
            }
        }
    }

    static boolean isValid(int r, int c) {
        return r < R && c < C && r >= 0 && c >= 0 && map[r][c] != 'X' && map[r][c] != '*';
    }
}
