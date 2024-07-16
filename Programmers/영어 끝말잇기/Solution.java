import java.util.*;

class Solution {
    public int[] solution(int n, String[] words) {
        int[] answer = {0, 0};
        // 끝말잇기 중 나온 단어를 저장할 set
        Set<String> history = new HashSet<>();
        char startCh = words[0].charAt(0); // 첫 단어의 맨 앞 글자로 초기화
        
        for (int i = 0; i < words.length; i++) {
            
            String word = words[i];

            // 앞 사람의 마지막 문자로 시작하지 않은 단어인 경우
            // 이미 등장했던 단어인 경우
            if ((word.charAt(0) != startCh) || history.contains(word)) {
                answer[0] = (i % n) + 1; // 번호
                answer[1] = round + 1; // 차례
                return answer;
            }
            
            history.add(word);
            startCh = word.charAt(word.length() - 1);
        }
        
        
        return answer;
    }
}