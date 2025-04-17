import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    /*
     * 
     * 3 x 3 부분 행렬을 뒤집는 연산
     * 
     * 연산을 몇 번해야 A 행렬을 B 행렬로 변환할 수 있는지, 최소 연산 수를 출력
     * 바꿀 수 없다면 -1을 출력
     * 
     */
    static int N; // 행(row)
    static int M; // 열(column)
    static int[][] matrixA;
    static int[][] matrixB;

    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        matrixA = new int[N][M];
        matrixB = new int[N][M];
        
        // 행렬 A 입력
        for (int i = 0; i < N; i++) {
            String row = br.readLine();
            for (int j = 0; j < M; j++) {
                matrixA[i][j] = row.charAt(j) - '0';
            }
        }

        // 행렬 B 입력
        for (int i = 0; i < N; i++) {
            String row = br.readLine();
            for (int j = 0; j < M; j++) {
                matrixB[i][j] = row.charAt(j) - '0';
            }
        }

        if (N < 3 || M < 3) {
            int i = 0;
            for (; i < N; i++) {
                if (!Arrays.equals(matrixA[i], matrixB[i])) {
                    break;
                }
            }
            if (i == N) {
                System.out.println(0);
            }
            else {
                System.out.println(-1);
            }
            return;
        }

        int result = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (matrixA[i][j] != matrixB[i][j]) {
                    if(operate(i, j)) {
                        result++;
                        continue;
                    }
                    else {
                        System.out.println(-1);
                        return;
                    }
                }
            }
        }
        System.out.println(result);

    }

    static boolean operate(int startR, int startC) {
        if (startR + 3 > N || startC + 3 > M) {
            return false;
        }

        for (int i = startR; i < startR + 3; i++) {
            for (int j = startC; j < startC + 3; j++) {
                matrixA[i][j] = matrixA[i][j] == 1 ? 0 : 1;
            }
        }

        return true;
    }
}