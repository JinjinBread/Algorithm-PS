import java.util.*;

class Solution {
    
    //닫힌 괄호는 가장 가까운 열린 괄호와 상쇄됨


    boolean solution(String s) {
        ArrayDeque<Character> bracket = new ArrayDeque<>();
        
        for(int i = 0; i < s.length(); i++) {
            char b = s.charAt(i);
            if (b == '(') {
                bracket.push(b);
                continue;
            }
            
            //b가 ')'인 경우
            if (bracket.isEmpty()) {
                return false;
            }

            bracket.pop();
        }
        
        return bracket.isEmpty();
    }
}