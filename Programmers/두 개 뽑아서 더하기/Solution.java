import java.util.*;

class Solution {
    public int[] solution(int[] numbers) {
        int[] answer = {};

        //입출력 예를 보면 5 + 2 = 7, 0 + 7 = 7 -> 중복값이 나오지만
        //result에는 하나밖에 없음, 즉 중복 불가
        //Set 자료구조 사용
        Set<Integer> set = new HashSet<>();
        
        for (int i = 0; i < numbers.length - 1; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                set.add(numbers[i] + numbers[j]);
            }
        }
        
        answer = set.stream().sorted().mapToInt(Integer::intValue).toArray();
        
        return answer;
    }
}