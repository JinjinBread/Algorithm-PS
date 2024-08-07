class Solution {
    public int solution(int[] money) {
        
        int N = money.length;
        
        int[] dp1 = new int[N]; // 첫 집을 터는 경우
        int[] dp2 = new int[N]; // 첫 집을 털지 않는 경우
        
        dp1[0] = money[0];
        dp1[1] = money[0]; // 첫 집을 털면 두 번째 집은 털 수 없으므로
        
        // 첫 번째 집을 턴 경우, 마지막 집은 털 수 없다.
        for (int i = 2; i < N - 1; i++) {
            // Math.max를 해줘야 함
            // 하지 않으면 N-2, 즉 4번째 수가 91로 됨
            // tc: [90, 0, 0, 95, 1, 1] -> dp1: [90, 90, 90, 185, 185, 0]
            dp1[i] = Math.max(dp1[i-1], money[i] + dp1[i-2]);
        }
        
        dp2[1] = money[1];
        // 첫 번째 집을 털지 않는 경우
        for (int i = 2; i < N; i++) {
            dp2[i] = Math.max(dp2[i-1], money[i] + dp2[i-2]);
        }
        
        return Math.max(dp1[N-2], dp2[N-1]);
    }
}