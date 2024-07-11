import java.util.*;

class Solution {
    public String solution(String[] cards1, String[] cards2, String[] goal) {
        
        // goal 또한 큐에 넣어 비교해가며 푸는 방법도 있다.
        ArrayDeque<String> q1 = new ArrayDeque<>();
        ArrayDeque<String> q2 = new ArrayDeque<>();
        
        for (int i = 0; i < cards1.length; i++) {
            q1.add(cards1[i]);
        }
        
        for (int i = 0; i < cards2.length; i++) {
            q2.add(cards2[i]);
        }
        
        for (String find : goal) {
            
            // q1 먼저 찾아본다.
            if (!q1.isEmpty() && q1.getFirst().equals(find)) {
                q1.poll();
            }
            // q1 없으면 q2에서 찾아본다.
            else if (!q2.isEmpty() && q2.getFirst().equals(find)) {
                q2.poll();
            }
            // 둘다 없으면 result = No
            else {
                return "No";
            }
        }
        
        
        
        return "Yes";
    }
}