import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        
        Arrays.sort(people);
        
        int i = 0; // 가장 가벼운 사람
        int j = people.length - 1; // 가장 무거운 사람
        int boat = 0; // 필요한 보트 수
        

        while(i <= j) {

            // 무게 제한을 넘지 않는 경우
            if (people[i] + people[j] <= limit) {
                i++;
            }

            j--;
            boat++; // 무게 제한이 넘는 경우, 가장 무거운 사람은 반드시 혼자 보트를 타야한다는 의미이다.
        }
        
        return boat;
    }
}