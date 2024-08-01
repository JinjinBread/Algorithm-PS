import java.util.Arrays;

class Solution {

    public static void main(String[] args) {
        System.out.println(solution("ABCBDAB", "BDCAB"));
        System.out.println(solution("AAA", "AAA"));
    }

    // 최장 공통 부분 수열(LCS)의 길이를 계산하라
    private static int solution(String str1, String str2) {

        int x = str1.length();
        int y = str2.length();

        int[][] dp = new int[x + 1][y + 1];

        for (int i = 1; i <= x; i++) {
            for (int j = 1; j <= y; j++) {
                // 같은 문자면 이전 LCS에 1을 더한다
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                }
                // 다른 문자면 각 문자열의 현재 위치 이전까지의 최장 길이를 가져온다.
                else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        
        // 반복이 종료되면 dp[x][y]에 최장 공통 부분 수열이 저장된다.
        return dp[x][y];
    }

}
