import java.util.*;

class Solution {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        
        List<ArrayDeque<Integer>> stackList = new ArrayList<>();

        // 스택의 개수를 알고 있으므로 아래 코드로 바꾸는 게 더 좋다.
        // ArrayDeque<Integer>[] stackLists = new ArrayDeque[board.length];
        
        //열마다 스택으로 분할
        for(int c = 0; c < board[0].length; c++) {
            ArrayDeque<Integer> stack = new ArrayDeque<>();
            for (int r = board.length - 1; r >= 0; r--) {
                if (board[r][c] != 0)
                    stack.push(board[r][c]);
            }
            stackList.add(stack);
        }
        
        ArrayDeque<Integer> basketStack = new ArrayDeque<>();
        
        for(int move: moves) {
            ArrayDeque<Integer> stack = stackList.get(move - 1);
            
            //인형이 없는 곳에 크레인을 작동시키는 경우
            if (stack.isEmpty()) {
                continue;
            }
            
            //바구니가 비어있지 않은 경우
            if (!basketStack.isEmpty() && basketStack.peek() == stack.peek()) {
                basketStack.pop();
                stack.pop();
                answer += 2;
                continue;
            }
            
            //바구니가 비어있는 경우
            basketStack.push(stack.pop());
        }
        
        return answer;
    }
}