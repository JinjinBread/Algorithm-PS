class Solution {
    
    public int solution(int n) {
        
        int[] dp = new int[n+1];
        
        dp[0] = 0;
        dp[1] = 1;
        
        // 간혹 정답을 특정 수로 나눈 나머지로 구하라는 문제가 출제된다.
        // 이는 자료형의 오버플로우를 방지하기 위해 문제 출제에 사용되는 방식이다.
        // 예를 들어 int형의 표현 범위는 -21억부터 +21억인데, 47번째의 피보나치 수인 +29억부터는 int형의 표현 범위를 초과한다.
        // 따라서 만약 반복문에서 % 1234567를 하지 않고 return 문에서 % 1234567를 한다면 오버플로가 발생할 것이다.
        for(int i = 2; i <= n; i++) {
            dp[i] = (dp[i-2] + dp[i-1]) % 1234567;
        }

        return dp[n];
    }
    
}