import java.util.*;

class Solution {
    public int solution(int[] nums) {
        
        // 가장 많은 폰켓몬 종류의 개수
        Set<Integer> set = new HashSet<>();
        
        for(int num: nums) {
            set.add(num);
        }
        
        int get = nums.length / 2;
        int type = set.size();
        
        return Math.min(get, type);
    }
}