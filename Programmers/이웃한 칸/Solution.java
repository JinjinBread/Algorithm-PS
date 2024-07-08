import java.util.*;

class Solution {
    public int solution(String[][] board, int h, int w) {
        int answer = 0;
        
        //board[h][w]의 위, 아래, 좌, 우 칸 중 같은 색으로 칠해진 칸의 개수는?
        //결과는 최소 0, 최대 4
        
        int n = board.length;
        int count = 0;
        int[] dh = new int[]{0, 1, -1, 0};
        int[] dw = new int[]{1, 0, 0, -1};
        
        for(int i = 0; i < 4; i++) {
            int h_check = h + dh[i];
            int w_check = w + dw[i];
            
            if (!isValidBound(h_check, w_check, n))
                continue;
            
            if (board[h][w] == board[h_check][w_check])
                count++;
        }
        
        return count;
    }
    
    private boolean isValidBound(int h, int w, int bound) {
        return h >= 0 && h < bound && w >= 0 && w < bound;
    }
}