
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    
    private static class Stuff {
        int weight;
        int value;

        public Stuff(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 물품의 수
        int K = Integer.parseInt(st.nextToken()); // 버틸 수 있는 무게

        Stuff[] stuffs = new Stuff[N+1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            stuffs[i] = new Stuff(w, v);
        }

        int[][] dp = new int[N+1][K+1];

        for (int i = 1; i <= N; i++) {
            Stuff stuff = stuffs[i];
            for (int j = 1; j <= K; j++) {
                
                if (stuff.weight > j) {
                    dp[i][j] = dp[i-1][j];
                    continue;
                }

                // j(현재 버틸 수 있는 무게) 일 때, 최대 가치 --> dp[i-1][j] 와
                // '현재 짐의 가치 + 남은 버틸 수 있는 무게의 최대 가치' 를 비교하여 더 큰 값을 저장한다.
                dp[i][j] = Math.max(dp[i-1][j], stuff.value + dp[i-1][j - stuff.weight]);
            }
        }

        bw.write(String.valueOf(dp[N][K]));
        bw.flush();
    }

}
