import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    static class Location {
        int r;
        int c;
        public Location(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static class DirectionInfo {
        int second;
        char direction;
        public DirectionInfo(int second, char direction) {
            this.second = second;
            this.direction = direction;
        }
    }
    
    static int N;
    static int[][] board;
    static boolean[][] visited;
    // 좌 상 우 하
    static int[] rw = { 0, -1, 0, 1 };
    static int[] cw = { -1, 0, 1, 0 };
    static int direction;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        board = new int[N+1][N+1];
        visited = new boolean[N+1][N+1];

        int K = Integer.parseInt(br.readLine());

        // 사과가 있는 곳을 2로, 뱀의 몸이 있는 곳을 1로
        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            board[r][c] = 2;
        }

        int L = Integer.parseInt(br.readLine()); // 뱀의 방향 변환 횟수

        DirectionInfo[] directionInfo = new DirectionInfo[L];

        for (int i = 0; i < L; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());
            char C = st.nextToken().charAt(0); // L == 76, D == 68

            directionInfo[i] = new DirectionInfo(X, C);
        }

        int directionInfoNum = 0; // second 오름차순으로 주어져있다.
        direction = 2; // 뱀은 처음에 오른쪽으로 향한다.
        int second = 0; // 소요 시간

        // 시작 위치
        int r = 1;
        int c = 1;
        Deque<Location> dq = new ArrayDeque<>();
        dq.add(new Location(r, c));

        while (true) {

            if (directionInfoNum < L) {
                DirectionInfo info = directionInfo[directionInfoNum];
            
                if (info.second == second) {
                    directionInfoNum++;

                    if (info.direction == 'L') { // L(왼쪽으로 90도 회전)
                        direction = (direction + 3) % 4;
                    }
                    else { // D(오른쪽으로 90도 회전)
                        direction = (direction + 1) % 4;
                    }
                }
            }

            r = r + rw[direction];
            c = c + cw[direction];

            second++;

            if (isWall(r, c) || board[r][c] == 1) {
                break;
            }

            // 사과가 없다면
            if (board[r][c] != 2) {
                Location tail = dq.pollFirst();
                board[tail.r][tail.c] = 0;
            }

            dq.add(new Location(r, c));
            board[r][c] = 1;
        }

        bw.write(String.valueOf(second));
        bw.flush();
    }

    static boolean isWall(int r, int c) {
        return r <= 0 || c <= 0 || r > N || c > N;
    }

    // static void initVisited() {
    //     for (int i = 1; i <= N; i++) {
    //         for (int j = 1; j <= N; j++) {
    //             visited[i][j] = false;
    //         }
    //     }
    // }

    // static void dfs(int r, int c, int depth, int sneakLength) {

    //     if (depth == sneakLength) {
    //         board[r][c] = 0; // 꼬리를 당긴다.
    //         return;
    //     }

    //     visited[r][c] = true;

    //     // 처음에는 이전 위치로 이동.
    //     if (depth == 0) {
    //         int reverseDirection = (direction + 2) % 4; // 반대 방향 좌 <-> 우, 상 <-> 하
    //         int movedR = r + rw[reverseDirection];
    //         int movedC = c + cw[reverseDirection];

    //         dfs(movedR, movedC, depth + 1, sneakLength);
    //         return;
    //     }

    //     for (int i = 0; i < 4; i++) {
            
    //         int movedR = r + rw[i];
    //         int movedC = c + cw[i];

    //         if (isWall(movedR, movedC) || visited[movedR][movedC])
    //             continue;

    //         if (board[movedR][movedC] == 1) {
    //             dfs(movedR, movedC, depth + 1, sneakLength);
    //             return;
    //         }
    //     }
    // }
}
