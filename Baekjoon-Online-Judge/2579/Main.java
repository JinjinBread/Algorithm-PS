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
        int[] points = new int[n+1];
        int[] dp = new int[n+1]; // 계단이 i개 있을 때의 최대 점수를 저장한다.

        for (int i = 1; i <= n; i++) {
            points[i] = Integer.parseInt(br.readLine());
        }

        dp[1] = points[1];

        if (n > 1) {
            dp[2] = points[1] + points[2];
        }

        for (int i = 3; i <= n; i++) {
            // i. oxo
            // ii. xoo
            dp[i] = Math.max(dp[i-2], dp[i-3] + points[i-1]) + points[i];
        }

        bw.write(String.valueOf(dp[n]));
        bw.flush();
    }
}
