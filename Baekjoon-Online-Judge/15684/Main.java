import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int H;
    static int[][] map;
    static boolean foundSolution;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new int[H+1][N+1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            map[a][b] = 1; // 1이면 왼 -> 오
            map[a][b+1] = 2; // 2면 왼 <- 오 (b+1) 기준에서 왼쪽으로 가는 가로선
        }

        int answer = -1;
        for (int maxUsed = 0; maxUsed <= 3; maxUsed++) {
            if (backtrack(1, 0, maxUsed)) {
                answer = maxUsed;
                break;
            }
        }

        System.out.println(answer);
    }

    static boolean backtrack(int row, int usedNum, int maxUsed) {

        if (usedNum == maxUsed) {
            return isNToN();
        }

        for (int i = row; i <= H; i++) {
            for (int j = 1; j < N; j++) {
                
                if (map[i][j] == 0 && map[i][j+1] == 0) {
                    map[i][j] = 1;
                    map[i][j+1] = 2;
                    
                    if (backtrack(i, usedNum + 1, maxUsed)) {
                        return true;
                    }

                    map[i][j] = 0;
                    map[i][j+1] = 0;
                }
            }
        }
        
        return false;
    }

    static boolean isNToN() {


        for (int i = 1; i <= N; i++) {

            int row = 1;
            int col = i;

            while (row <= H) {

                if (map[row][col] == 1) {
                    col++;
                }
                else if (map[row][col] == 2) {
                    col--;
                }

                row++;
            }

            if (i != col) {
                return false;
            }
        }

        return true;
    }
}
