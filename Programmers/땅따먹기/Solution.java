import java.util.*;

class Solution {
    int solution(int[][] land) {
        
        int N = land.length;
        int[][] dp = new int[4][N];
        
        dp[0][0] = land[0][0];
        dp[1][0] = land[0][1];
        dp[2][0] = land[0][2];
        dp[3][0] = land[0][3];
        
        for (int i = 1; i < N; i++) {
            dp[0][i] = land[i][0] + Math.max(dp[1][i-1], Math.max(dp[2][i-1], dp[3][i-1]));
            dp[1][i] = land[i][1] + Math.max(dp[0][i-1], Math.max(dp[2][i-1], dp[3][i-1]));
            dp[2][i] = land[i][2] + Math.max(dp[0][i-1], Math.max(dp[1][i-1], dp[3][i-1]));
            dp[3][i] = land[i][3] + Math.max(dp[0][i-1], Math.max(dp[1][i-1], dp[2][i-1]));
        }
        
        return Math.max(dp[0][N-1], Math.max(dp[1][N-1], Math.max(dp[2][N-1], dp[3][N-1])));
    }
}