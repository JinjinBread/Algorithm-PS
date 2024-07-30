import java.util.*;

class Solution {
    public String[] solution(String[] strings, int n) {

        // n 번째 알파벳을 기준으로 정렬 (같으면 사전순으로 정렬)
        Arrays.sort(strings, (o1, o2) -> o1.charAt(n) == o2.charAt(n) ? o1.compareTo(o2) : Character.compare(o1.charAt(n), o2.charAt(n)));
        
        return strings;
    }
}