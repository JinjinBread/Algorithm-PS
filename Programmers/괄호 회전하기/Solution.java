import java.util.*;

class Solution {
    //1. pop으로 제일 앞에 있는 괄호를 꺼내서 뒤에 넣는다.
    //2. 올바른 괄호 문자열인지 검증한다.
    
    public int solution(String s) {
        
        HashMap<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put('}', '{');
        map.put(']', '[');
        
        int n = s.length(); // 입력 문자열 s의 원본 길이
        s += s; // 입력 문자열 s에 s를 이어붙임
        //시작 인덱스를 한 칸씩 뒤로 미루면 왼쪽으로 1칸 회전시키는 것과 같음
        int count = 0;
        ArrayDeque<Character> stack = new ArrayDeque<>();
        
        for (int i = 0; i < n; i++) {
            stack.clear();
            for (int j = i; j < i + n; j++) { //j는 최대 2n - 2, s를 out of bounds 할 일 없음.
            
                char bracket = s.charAt(j);
                
                //bracket이 ')' or '}' or ']' 가 아니면,
                //즉 열린 괄호라면
                if (!map.containsKey(bracket)) {
                    stack.push(bracket);
                    continue;
                }
                
                if (stack.isEmpty())
                    break;
                
                char matchBracket = map.get(bracket);
                char lBracket = stack.pop();
                
                if (matchBracket != lBracket) {
                    break;
                }
            
                // 모든 괄호를 훑었고 stack이 비어있는 경우
                if (j == (i + n - 1) && stack.isEmpty())
                    count++;
            }
            
        }
        
        return count;
    }
    
}