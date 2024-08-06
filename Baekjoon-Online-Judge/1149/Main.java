import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[][] cost = new int[N][3];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            cost[i][0] = Integer.parseInt(st.nextToken());
            cost[i][1] = Integer.parseInt(st.nextToken());
            cost[i][2] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[3][N];
        dp[0][0] = cost[0][0];
        dp[1][0] = cost[0][1];
        dp[2][0] = cost[0][2];

        for (int i = 1; i < N; i++) {
            // 만약 현재 집을 빨강으로 칠하려면 그 전집은 초록 또는 파랑이어야 함. 그 중 싼 비용을 택함
            dp[0][i] = cost[i][0] + Math.min(dp[1][i-1], dp[2][i-1]);
            dp[1][i] = cost[i][1] + Math.min(dp[0][i-1], dp[2][i-1]);
            dp[2][i] = cost[i][2] + Math.min(dp[0][i-1], dp[1][i-1]);
        }

        int minCost = Math.min(dp[0][N-1], Math.min(dp[1][N-1], dp[2][N-1]));

        bw.write(String.valueOf(minCost));
        bw.flush();
    }
}
