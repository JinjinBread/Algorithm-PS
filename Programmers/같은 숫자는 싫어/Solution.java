import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        stack.push(arr[0]);
        
        for (int i = 1; i < arr.length; i++) {
            
            if (!stack.isEmpty() && stack.peek() == arr[i]) {
                continue;
            }
            else {
                stack.push(arr[i]);
            }
            
        }
        
        int[] answer = new int[stack.size()];
        int i = stack.size() - 1;
        while (!stack.isEmpty()) {
            // 선입후출 이므로 순서를 반대로 저장해준다.
            answer[i--] = stack.pop();
        }
        
        return answer;
    }
}