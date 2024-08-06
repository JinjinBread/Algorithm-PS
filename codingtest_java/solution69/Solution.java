
class Solution {

    public static void main(String[] args) {
        System.out.println(solution(new int[][]{ {1,3,3,2}, {2,1,4,1}, {1,5,2,3}}));
        System.out.println(solution(new int[][]{ {1,7,13,2,6}, {2,-4,2,5,4}, {5,3,5,-3,1}}));
    }
    
    private static int solution(int[][] arr) {

        // 각 열에 조약돌은 적어도 하나는 놓아야 한다.
        // 행의 수는 3으로 고정
        // 각 조약돌의 상·하·좌·우에는 조약돌을 놓을 수 없다.

        // 각 열마다 조약돌을 놓는 경우의 수는
        // 1. 0번째 행에만 놓음 2. 1번째 행에만 3. 2번째 행에만 4. 0, 2번째 행에만
        // 총 4개의 경우의 수가 있다.

        int col = arr[0].length;

        int[][] dp = new int[4][col]; // 각 열마다 4가지의 경우로 조약돌을 놓을 때 도출되는 가중치(이전 가중치까지 합산한)를 저장할 dp
        
        // 첫 번째 경우
        dp[0][0] = arr[0][0];
        // 두 번째 경우
        dp[1][0] = arr[1][0];
        // 세 번째 경우
        dp[2][0] = arr[2][0];
        // 네 번째 경우
        dp[3][0] = arr[0][0] + arr[2][0];

        // 두 번째 열부터 시작
        for (int i = 1; i < col; i++) {
            // 현재 i 열을 첫 번째 경우로 조약돌을 넣는 경우엔
            // 이전 열에는 두 번째, 세 번째 경우여야만 한다.
            // 따라서 두 번째, 세 번째 경우 중 최대 가중치를 가져와서 현재 가중치를 더한다.
            dp[0][i] = arr[0][i] + Math.max(dp[1][i-1], dp[2][i-1]);
            dp[1][i] = arr[1][i] + Math.max(dp[0][i-1], Math.max(dp[2][i-1], dp[3][i-1]));
            dp[2][i] = arr[2][i] + Math.max(dp[0][i-1], dp[1][i-1]);
            dp[3][i] = arr[0][i] + arr[2][i] + dp[1][i-1];
        }

        // 마지막 열에서 가장 큰 값을 가져와서 리턴
        return Math.max(dp[0][col-1], Math.max(dp[1][col-1], Math.max(dp[2][col-1], dp[3][col-1])));
    }

}
