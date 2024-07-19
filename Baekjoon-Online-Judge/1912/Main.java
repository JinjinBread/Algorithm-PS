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

        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            dp[i] = Integer.parseInt(st.nextToken());
        }

        // dp[i - 1]에는 이전 최대 부분합이 들어있음
        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(dp[i], dp[i - 1] + dp[i]);
            // int sum = dp[i - 1] + dp[i];
            // if (sum > dp[i])
            //     dp[i] = sum;
        }

        int answer = Arrays.stream(dp).max().getAsInt();
        bw.write(String.valueOf(answer));
        bw.flush();
    }

}
