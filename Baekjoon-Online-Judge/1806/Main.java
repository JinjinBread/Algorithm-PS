import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    static int[] arr;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        arr = new int[N+1];
        dp = new int[N+1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int minLen = Integer.MAX_VALUE;
        int p1 = 1;
        int p2 = 2;
        for (int i = 1; i <= N; i++) {
            dp[i] = dp[i - 1] + arr[i];

            if (dp[i] >= S) {
                p2 = i;
                while (dp[i] >= S) {
                    dp[i] -= arr[p1];
                    p1++;
                }
                p1 -= 1; // 바로 전으로 원복
                dp[i] += arr[p1]; // 바로 전으로 원복
                minLen = Math.min(minLen, (p2 - p1 + 1));
            }
        }

        minLen = minLen == Integer.MAX_VALUE ? 0 : minLen;

        bw.write(String.valueOf(minLen));
        bw.flush();
    }
}