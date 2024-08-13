import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] amount = new int[n+1];

        for (int i = 1; i <= n; i++) {
            amount[i] = Integer.parseInt(br.readLine());
        }

        int[] dp = new int[n+1];
        dp[1] = amount[1];

        if (n > 1) {
            dp[2] = dp[1] + amount[2];
        }

        // 와인을 마시는 경우의 수는 3가지
        // 1. o o x
        // 2. o x o
        // 3. x o o
        for (int i = 3; i <= n; i++) {
            dp[i] = Math.max(dp[i-1], Math.max(dp[i-2] + amount[i], dp[i-3] + amount[i-1] + amount[i]));
        }

        bw.write(String.valueOf(dp[n]));
        bw.flush();
    }
}
