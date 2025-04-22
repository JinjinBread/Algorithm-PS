import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        
        String answer = "";
        Map<String, Integer> count = new HashMap<>(); 
        
        for(String part: participant) {
            count.put(part, count.getOrDefault(part, 0) + 1);
        }
        
        for(String complete: completion) {
            count.put(complete, count.get(complete) - 1);
        }
        
        for(Map.Entry<String, Integer> entry: count.entrySet()) {
            if (count.get(entry.getKey()) != 0) {
                answer = entry.getKey();
            }
        }
        
        return answer;
    }
}