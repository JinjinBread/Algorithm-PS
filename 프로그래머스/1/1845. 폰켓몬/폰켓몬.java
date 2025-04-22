import java.util.*;

class Solution {
    public int solution(int[] nums) {
        
        int pick = nums.length / 2;
        Set<Integer> pickSet = new HashSet<>();
        
        for(int num: nums) {
            if (pickSet.size() == pick) {
                break;
            }
            
            if (pickSet.contains(num)) {
                continue;
            }
            
            pickSet.add(num);
        }
        
        return pickSet.size();
    }
}