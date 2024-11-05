import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] coins = new int[n+1];

        for (int i = 1; i <= n; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(coins);

        int[][] dp = new int[n+1][k+1];

        for (int i = 1; i <= n; i++) {
            int w = coins[i];
            for (int j = 1; j <= k; j++) {

                if (j < w) {
                    dp[i][j] = dp[i-1][j];
                    continue;
                }

                dp[i][j] = dp[i-1][j] + dp[i][j-w];

                // 현재 가치의 합이 동전의 가치 값과 같다면, 경우의 수 하나를 더 늘려준다.
                if (j == w) {
                    dp[i][j] += 1;
                }
            }
        }

        System.out.println(dp[n][k]);
    }
}
