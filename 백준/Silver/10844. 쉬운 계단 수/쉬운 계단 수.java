import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    
    // N == 2,
    // 10, 12, 21, 23, 32, 34, 43, 45, 54, 56, 65, 67, 76, 78, 87, 89, 98
    // 맨 뒷자리수를 기준으로 개수 세기

    static int N;
    static int[][] dp;
    static int stairNum;

    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        dp = new int[N+1][10];

        Arrays.fill(dp[1], 1);
        dp[1][0] = 0; // 0으로 시작하는 수는 계단 수가 아님.

        for(int i = 2; i <= N; i++) {
            for (int j = 0; j < 10; j++) {
                if (j == 0) {
                    dp[i][j] = dp[i-1][j+1];
                    continue;
                }

                if (j == 9) {
                    dp[i][j] = dp[i-1][j-1];
                    continue;
                }

                dp[i][j] = (dp[i-1][j-1] + dp[i-1][j+1]) % 1_000_000_000;
            }
        }

        long sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += dp[N][i];
        }

        long result = sum % 1_000_000_000;

        System.out.println(result);
    }
}