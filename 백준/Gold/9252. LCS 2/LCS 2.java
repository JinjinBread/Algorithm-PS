import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static char[] str1;
    static char[] str2;
    static int str1Len;
    static int str2Len;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        str1 = br.readLine().toCharArray();
        str2 = br.readLine().toCharArray();

        str1Len = str1.length;
        str2Len = str2.length;

        dp = new int[str1Len + 1][str2Len + 1];
        
        // 문자가 같으면, i-1, j-1의 값에 + 1한 수와 vs i-1, j 값 중 큰 값을 가져옴
        // 다르면, i, j-1 값 vs i-1, j 값 중 큰 값 가져옴

        for (int i = 1; i <= str1Len; i++) {
            for (int j = 1; j <= str2Len; j++) {
                if (str1[i-1] == str2[j-1]) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                }
                else {
                    dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
                }
            }            
        }

        System.out.println(dp[str1Len][str2Len]);
        System.out.println(getLCS());
    }

    static String getLCS() {
        StringBuilder sb = new StringBuilder();
        
        for (int i = str1Len, j = str2Len; i > 0 && j > 0; i--, j--) {

            if (str1[i-1] == str2[j-1]) {
                sb.append(str1[i-1]);
                continue;
            }

            if (dp[i-1][j] > dp[i][j-1]) {
                j++;
            }
            else {
                i++;
            }
        }

        return sb.reverse().toString();
    }
}