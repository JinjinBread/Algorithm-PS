import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        
        String answer = "";
        
        // 완주자, (동명이인 수 -> 없으면 1명)
        Map<String, Integer> map = new HashMap<>();
        
        for (String person: completion) {
            // 동명이인이 있으면
            // if (map.containsKey(person)) {
            //     int num = map.get(person);
            //     map.put(person, num++);
            // }
            // map.put(person, 1);
            
            // 위의 코드를 한 줄로 쓸 수 있음
            // getOrDefault는 key에 해당하는 값이 있으면 그 값을 반환하고
            // 없으면 기본값(여기선 0)을 반환한다.
            map.put(person, map.getOrDefault(person, 0) + 1);
        }
        
        for (String person: participant) {
            
            // 완주자 명단에 없는 참가자이거나
            // 참가자 중 완주하지 못한 동명이인
            // if (!map.containsKey(person) || map.get(person) == 0) {
            //     answer = person;
            //     break;
            // }
            
            // 마찬가지 위 분기문을 아래처럼 쓸 수 있음
            // person이 없으면 0을 반환 -> true가 되므로 분기문에 들어감
            if (map.getOrDefault(person, 0) == 0) {
                answer = person;
                break;
            }
            
            map.put(person, map.get(person) - 1);
        }
        
        return answer;
    }
}