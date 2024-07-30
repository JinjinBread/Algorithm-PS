import java.util.*;

class Solution {
    
    public String solution(int[] numbers) {

        List<String> list = new ArrayList<>();
        
        for(int number: numbers) {
            list.add(String.valueOf(number));
        }
        
        list.sort((o1, o2) -> {
            int a = Integer.parseInt(o1 + o2);
            int b = Integer.parseInt(o2 + o1);
            return Integer.compare(b, a); // b가 a보다 더 크면 o1과 o2의 순서를 바꾼다. (내림차순)
        });
        
        StringBuilder sb = new StringBuilder();
        for(String str: list) {
            sb.append(str);
        }
        
        // numbers의 원소에는 0이 들어갈 수 있다. 즉, 0000이 나올 수도 있다는 의미
        // 따라서 맨 앞 글자가 0이면 0으로 바꾼다.
        return sb.charAt(0) == '0' ? "0" : sb.toString();
    }
    
}