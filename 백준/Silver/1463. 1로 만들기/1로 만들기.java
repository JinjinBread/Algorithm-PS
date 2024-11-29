import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        dp = new int[n+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        
        dp[n] = 0;
        operate(n);

        System.out.println(dp[1]);
    }

    static void operate(int num) {

        for (int i = num; i >= 1; i--) {
            
            if ((i % 3) == 0) {
                int n = i / 3;
                dp[n] = Math.min(dp[n], dp[i] + 1);
            }
    
            if ((i % 2) == 0) {
                int n = i / 2;
                dp[n] = Math.min(dp[n], dp[i] + 1);
            }

            dp[i-1] = Math.min(dp[i-1], dp[i] + 1);
        }

    }
    
}
