import java.util.*;

class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {

        // 판매원, 판매원의 부모 ("-"는 부모가 없는 것을 의미)
        Map<String, String> parentMap = new HashMap<>();
        // 판매원, 판매원의 수익
        Map<String, Integer> profit = new HashMap<>();
        
        for (int i = 0; i < enroll.length; i++) {
            parentMap.put(enroll[i], referral[i]);
            profit.put(enroll[i], 0);
        }
        
        for (int i = 0; i < seller.length; i++) {
            String person = seller[i];
            
            int price = amount[i] * 100;
            int tenPer = price / 10;
            
            while (price > 0 && !person.equals("-")) {
                profit.put(person, profit.get(person) + price - tenPer);
                price = tenPer; //총이익
                tenPer /= 10; // 떼어가는 10%
                person = parentMap.get(person);
            }
        }
        
        int[] result = new int[enroll.length];

        for (int i = 0; i < enroll.length; i++) {
            result[i] = profit.get(enroll[i]);
        }

        return result;
    }
    
}