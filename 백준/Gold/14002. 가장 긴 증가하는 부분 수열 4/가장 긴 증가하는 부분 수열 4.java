import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] arr;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[N];
        dp[0] = 1;

        int maxLen = dp[0];

        for (int i = 1; i < N; i++) {
            int len = getMax(i);
            dp[i] = len + 1;
            if (maxLen < dp[i]) {
                maxLen = dp[i];
            }
        }

        bw.write(String.valueOf(maxLen));
        bw.newLine();

        StringBuilder sb = new StringBuilder();
        for (int i = N-1; i >= 0; i--) {
            
            if (dp[i] == maxLen) {
                sb.insert(0, arr[i] + " ");
                maxLen--;
            }
        }

        bw.write(sb.toString());
        bw.flush();
    }

    static int getMax(int idx) {

        int maxLen = 0;

        for (int i = 0; i < idx; i++) {
            if (arr[i] < arr[idx]) {
                maxLen = Math.max(maxLen, dp[i]);
            }
        }

        return maxLen;
    }
}
