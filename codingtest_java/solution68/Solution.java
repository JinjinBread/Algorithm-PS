import java.util.Arrays;

class Solution {

    public static void main(String[] args) {
        System.out.println(solution(new int[]{1, 4, 2, 3, 1, 5, 7, 3}));
        System.out.println(solution(new int[]{3, 2, 1}));
    }

    // 최장 증가 부분 수열(LIS) 구하기
    private static int solution(int[] nums) {

        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                // 현재 수가 이전 수 보다 크면
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

        }

        return Arrays.stream(dp).max().getAsInt();
    }


}
