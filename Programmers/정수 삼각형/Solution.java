import java.util.*;

class Solution {
    public int solution(int[][] triangle) {
        
        int n = triangle.length;
        int[][] dp = new int[n + 1][n + 1];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < triangle[i].length; j++) {
                // 왼쪽 위에서 내려 온 숫자, 오른쪽 위에서 내려 온 숫자 중 큰 값을 사용
                dp[i+1][j+1] = triangle[i][j] + Math.max(dp[i][j], dp[i][j+1]);
            }
        }
        
        int answer = 0;
        
        for (int i = 1; i <= n; i++) {
            answer = Arrays.stream(dp[i]).max().getAsInt();
        }
        
        return answer;
    }
    
}