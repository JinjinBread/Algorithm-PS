import java.util.*;

class Solution {
    
    private Map<String, Integer> clothCnt = new HashMap<>();
    
    public int solution(String[][] clothes) {
        
        int answer = 1;
        
        for(String[] clothArr: clothes) {
            
            String cloth = clothArr[0];
            String type = clothArr[1];
            
            int count = 
                clothCnt.getOrDefault(type, 0);
            clothCnt.put(type, count + 1);
        }
        
        for(Map.Entry<String, Integer> entry: clothCnt.entrySet()) {
            int count = entry.getValue();
            answer *= (count + 1); // 1은 '입지 않는다' 경우 수 (count 는 종류 별 의상 개수)
        }
        
        return answer - 1;
    }
    
}