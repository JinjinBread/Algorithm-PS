import java.util.*;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        
        int answer = 0;
        //원하는 제품명, 해당 제품의 희망 구매 개수
        Map<String, Integer> buyList = new HashMap<>();
        
        for (int i = 0; i < want.length; i++) {
            buyList.put(want[i], number[i]);
        }
        
        for (int i = 0; i < discount.length - 9; i++) {
            Map<String, Integer> discountList = new HashMap<>();
            
            for (int j = i; j < 10 + i; j++) {
                String product = discount[j];
                
                if (buyList.containsKey(product))
                    discountList.put(product, discountList.getOrDefault(product, 0) + 1);
            }
            
            //사려는 품목과 할인 품목이 일치하는 경우
            // 총 10개의 항목이므로 어떤 품목이 더 많을 일 없이, 완벽히 일치해야 함
            if (buyList.equals(discountList))
                answer++;
            
        }
        
        
        return answer;
    }
}