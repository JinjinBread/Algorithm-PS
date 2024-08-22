import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        char[] s1 = br.readLine().toCharArray();
        char[] s2 = br.readLine().toCharArray();

        int r = s1.length + 1;
        int c = s2.length + 1;

        int[][] dp = new int[r][c];

        for (int i = 1; i < r; i++) {
            for (int j = 1; j < c; j++) {
                // 공통 부분인 경우
                if (s1[i-1] == s2[j-1]) {
                    // 왼쪽 대각선에 있는 수에 + 1
                    dp[i][j] = dp[i-1][j-1] + 1;
                }
                else {
                    //왼쪽, 위 값 중 큰 값을 가져옴
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        bw.write(String.valueOf(dp[r-1][c-1]));
        bw.flush();
    }

}
