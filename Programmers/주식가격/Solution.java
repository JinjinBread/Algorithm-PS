import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        int n = prices.length;
        int[] answer = new int[n];
        
        //O(N^2) 알고리즘
        // for (int i = 0; i < n - 1; i++) {
        //     int curPrice = prices[i];
        //     for (int j = i + 1; j < n; j++) {
        //         if (prices[j] < curPrice)
        //             break;
        //         answer[i]++;
        //     }
        // }
        
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        
        for (int i = 0; i < n; i++) {
            
            //stack에는 인덱스를 저장한다.
            //가장 최근의 데이터의 가격이 떨어진 경우
            //stack.peek()는 이전 초
            while (!stack.isEmpty() && prices[stack.peek()] > prices[i]) {
                int sec = stack.pop();
                answer[sec] = i - sec;
            }
            stack.push(i);
        }
        
        //스택에 남아 있는 인덱스들은 가격이 떨어지지 않은 경우
        while (!stack.isEmpty()) {
            
            int sec = stack.pop();
            
            answer[sec] = (n - 1) - sec;            
        }
        
        
        return answer;
    }
}