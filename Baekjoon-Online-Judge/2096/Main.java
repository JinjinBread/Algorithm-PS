import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        int[][] board = new int[N][3];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] min = new int[N][3];
        int[][] max = new int[N][3];

        min[0] = Arrays.copyOf(board[0], 3);
        max[0] = Arrays.copyOf(board[0], 3);

        // 최댓값 구하기
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < 3; j++) {
                switch (j) {
                    case 0:
                        max[i][j] = Math.max(max[i-1][j], max[i-1][j+1]);
                        break;
                    case 1:
                        max[i][j] = Math.max(max[i-1][j], Math.max(max[i-1][j-1], max[i-1][j+1]));
                        break;
                    case 2:
                        max[i][j] = Math.max(max[i-1][j-1], max[i-1][j]);
                        break;
                }
                max[i][j] += board[i][j];
            }
        }

        // 최솟값 구하기
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < 3; j++) {
                switch (j) {
                    case 0:
                        min[i][j] = Math.min(min[i-1][j], min[i-1][j+1]);
                        break;
                    case 1:
                        min[i][j] = Math.min(min[i-1][j], Math.min(min[i-1][j-1], min[i-1][j+1]));
                        break;
                    case 2:
                        min[i][j] = Math.min(min[i-1][j-1], min[i-1][j]);
                        break;
                }
                min[i][j] += board[i][j];
            }
        }

        int maxAns = Arrays.stream(max[N-1]).max().getAsInt();
        int minAns = Arrays.stream(min[N-1]).min().getAsInt();

        bw.write(maxAns + " " + minAns);
        bw.flush();
    }
}