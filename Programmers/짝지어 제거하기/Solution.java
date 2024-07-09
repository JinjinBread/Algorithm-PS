import java.util.*;

class Solution {
    public int solution(String s) {
        // 1. 알파벳이 2개 붙어 있는 짝을 찾는다.
        // 2. 그 두 알파벳을 제거한다.
        // 3. 제거하여 생긴 공백을 뒷 문자열을 앞당겨 제거한다.
        // 4. 문자열이 모두 제거된다면 짝지어 제거하기 성공(1)
        // 4-1. 그렇지 않다면, 짝지어 제거하기 실패(0)
        
        //문자열 s를 문자 하나 씩 스택에 넣고 가장 최근의 문자와 push 하려는 문자가 같으면 pop
        ArrayDeque<Character> stack = new ArrayDeque<>();
        
        for (int i = 0; i < s.length(); i++) {
            
            char alpha = s.charAt(i);
            
            //스택이 비어있으면 그냥 삽입
            if (stack.isEmpty()) {
                stack.push(alpha);
                continue;
            }
            
            //스택의 최상단 데이터와 삽입하려는 데이터가 같으면 (== 같은 데이터가 붙어있다.)
            //스택을 pop한다.
            if (stack.peek().equals(alpha)) {
                stack.pop();
                continue;
            }
            
            //스택이 비어있진 않으나, 삽입하려는 데이터가 스택의 최상단 데이터와 같지 않은 경우
            //(== 다른 데이터가 붙어있다.)
            stack.push(alpha);
        }
        
        if (stack.isEmpty())
            return 1;

        return 0;
    }
}