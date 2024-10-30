import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class Country {
        int r;
        int c;
        public Country(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int N;
    static int L;
    static int R;
    static int[][] land;
    static boolean[][] visited;
    static boolean[][][] isOpened;
    // 좌상우하
    static int[] rw = { 0, -1, 0, 1 };
    static int[] cw = { -1, 0, 1, 0 };
    static int day = 0;

    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // N x N 크기의 땅
        L = Integer.parseInt(st.nextToken()); // 인구 차이가 L 이상
        R = Integer.parseInt(st.nextToken()); // R 이하

        land = new int[N][N];
        visited = new boolean[N][N];
        // isOpened = new boolean[N][N][4]; // 좌 open - 0, 상 open - 1, 우 open - 2, 하 open - 3
        /*
           1
        0     2
           3
        */

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                land[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (true) {

            boolean isAnyOpened = false;

            for (int i = 0; i < N; i++) {
                Arrays.fill(visited[i], false);
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) {
                        // 연합으로 묶여진 나라가 2개 이상인 경우 (== 국경선이 있음)
                        if (bfs(i, j) > 1) {
                            isAnyOpened = true;
                        }
                    }
                }
            }

            // // 열린 국경선 모두 닫아줌
            // for (int i = 0; i < N; i++) {
            //     for (int j = 0; j < N; j++) {
            //         Arrays.fill(isOpened[i][j], false);
            //     }
            // }

            // 열린 국경선이 없으면 반복문 끝냄 -> 열린 국경선이 없음(== 인구 이동 발생 안함)
            if (!isAnyOpened) {
                break;
            }

            day++;
        }

        System.out.println(day);
    }

    // static boolean isAllClosed() {
    //     for (int i = 0; i < N; i++) {
    //         for (int j = 0; j < N; j++) {
    //             for (int k = 0; k < 4; k++) {
    //                 if (isOpened[i][j][k]) {
    //                     return false; 
    //                 }
    //             }
    //         }
    //     }
    //     return true;
    // }

    // static boolean openBorder() {

    //     boolean isAnyOpened = false;

    //     for (int i = 0; i < N; i++) {
    //         for (int j = 0; j < N - 1; j++) {
    //             // 행 고정 열 이동 (가로로 인접한 나라)
    //             int c1 = land[i][j];
    //             int c2 = land[i][j+1];

    //             // 행 이동 열 고정 (세로로 인접한 나라)
    //             int c3 = land[j][i];
    //             int c4 = land[j+1][i];

    //             if (canOpen(c1, c2)) {
    //                 isOpened[i][j][2] = true;
    //                 isOpened[i][j+1][0] = true;
    //                 isAnyOpened = true;
    //             }

    //             if (canOpen(c3, c4)) {
    //                 isOpened[j][i][3] = true;
    //                 isOpened[j+1][i][1] = true;
    //                 isAnyOpened = true;

    //             }
    //         }
    //     }

    //     return isAnyOpened;
    // }
    
    static int bfs(int row, int col) {
        
        Queue<Country> q = new ArrayDeque<>();
        List<Country> union = new ArrayList<>(); // 현재 연합에 소속한 나라
        
        q.add(new Country(row, col));
        union.add(new Country(row, col));
        visited[row][col] = true;
        int total = land[row][col];
        int countryNum = 1;
        
        while (!q.isEmpty()) {
            Country now = q.poll();
            
            for (int i = 0; i < 4; i++) {
                int r = now.r + rw[i];
                int c = now.c + cw[i];
                
                // 국경선 개방 조건에 맞는 경우 open
                if (isValid(r, c) && !visited[r][c] && canOpen(land[now.r][now.c], land[r][c])) {
                    total += land[r][c];
                    countryNum++;
                    q.add(new Country(r, c));
                    union.add(new Country(r, c));
                    visited[r][c] = true;
                }
            }
        }
        
        int people = total / countryNum;
        
        // 인구 수 갱신
        for (Country unit : union) {
            land[unit.r][unit.c] = people;
        }
        
        return countryNum;
    }
    
    static boolean isValid(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < N;
    }
    
    static boolean canOpen(int c1, int c2) {
        int diff = Math.abs(c1 - c2);
        return diff >= L && diff <= R;
    }
}
