import java.util.*;

class Solution {
    public int solution(int[][] board) {
        
        int r = board.length;
        int c = board[0].length;
        
        for (int i = 1; i < r; i++) {
            for (int j = 1; j < c; j++) {
                
                if (board[i][j] == 0) {
                    continue;
                }
                
                // 위, 왼쪽, 대각선 중 하나라도 0인 경우
                if (board[i-1][j] == 0 || board[i][j-1] == 0 || board[i-1][j-1] == 0) {
                    continue;
                }
                
                // 위의 분기문을 사용하지 않아도 됨 (하나라도 0이 있으면 0 + 1이 되므로 원래 값과 같음)
                // 모두 1이면 그 중 가장 작은 값에다 1을 더함
                board[i][j] = Math.min(board[i-1][j], Math.min(board[i][j-1], board[i-1][j-1])) + 1;
            }
        }
        
        int answer = 0;
            
        for(int i = 0; i < r; i++) {
            int max = Arrays.stream(board[i]).max().getAsInt();
            answer = Math.max(answer, max);
        }
        
        // answer은 한 변의 길이 (넓이를 return 해야 함)
        return answer * answer;
    }
}